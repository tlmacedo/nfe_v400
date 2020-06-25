package br.com.tlmacedo.nfe.service;

import br.com.tlmacedo.nfe.v400.Nfe_v400;
import br.inf.portalfiscal.wsdl.nfe.prod.nfeRetAutorizacao4.NfeRetAutorizacao4Stub;
import br.inf.portalfiscal.xsd.nfe.retConsReciNFe.TRetConsReciNFe;
import br.inf.portalfiscal.xsd.nfe.retConsReciNFe.TProtNFe.InfProt;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.rmi.RemoteException;
import java.util.Base64;

public class NFeRetAutorizacao {

    private OMElement ome = null;
    private String xml;
    private TRetConsReciNFe tRetConsReciNFe = null;
    private InfProt infProt = null;


    private NfeRetAutorizacao4Stub.NfeDadosMsg dadosMsg_Prod;
    private NfeRetAutorizacao4Stub stub_Prod;
    private NfeRetAutorizacao4Stub.NfeResultMsg resultMsg_Prod;

    private br.inf.portalfiscal.wsdl.nfe.hom.nfeRetAutorizacao4.NfeRetAutorizacao4Stub.NfeDadosMsg dadosMsg_Hom;
    private br.inf.portalfiscal.wsdl.nfe.hom.nfeRetAutorizacao4.NfeRetAutorizacao4Stub stub_Hom;
    private br.inf.portalfiscal.wsdl.nfe.hom.nfeRetAutorizacao4.NfeRetAutorizacao4Stub.NfeResultMsg resultMsg_Hom;


    public NFeRetAutorizacao(String xmlConsRecibo) throws XMLStreamException, RemoteException, InterruptedException, JAXBException {
        setOme(AXIOMUtil.stringToOM(xmlConsRecibo));

        if (NFev400.AMB_PRODUCAO) {
            setDadosMsg_Prod(new NfeRetAutorizacao4Stub.NfeDadosMsg());
            setStub_Prod(new NfeRetAutorizacao4Stub());
            getDadosMsg_Prod().setExtraElement(getOme());
        } else {
            setDadosMsg_Hom(new br.inf.portalfiscal.wsdl.nfe.hom.nfeRetAutorizacao4.NfeRetAutorizacao4Stub.NfeDadosMsg());
            setStub_Hom(new br.inf.portalfiscal.wsdl.nfe.hom.nfeRetAutorizacao4.NfeRetAutorizacao4Stub());
            getDadosMsg_Hom().setExtraElement(getOme());
        }
    }

    public boolean ConsultaRetAutorizacao() throws RemoteException, JAXBException {
        boolean retorno = false;
        if (NFev400.AMB_PRODUCAO) {
            setResultMsg_Prod(getStub_Prod().nfeRetAutorizacaoLote(getDadosMsg_Prod()));
            setXml(getResultMsg_Hom().getExtraElement().toString());
        } else {
            setResultMsg_Hom(getStub_Hom().nfeRetAutorizacaoLote(getDadosMsg_Hom()));
            setXml(getResultMsg_Hom().getExtraElement().toString());
        }
        settRetConsReciNFe(ServiceUtilXml.xmlToObject(getXml(), TRetConsReciNFe.class));
        if (!tRetConsReciNFe.getCStat().equals("105"))
            retorno = true;
        if (NFev400.PRINT_PROMPT)
            System.out.printf("\n%sxml%sNFeRetAutorizacao: \n%s\n",
                    (NFev400.AMB_PRODUCAO) ? "prod_" : "hom_",
                    (!retorno) ? "_parcial_" : "",
                    getXml());

        return retorno;
    }

    public String getXmlRetAutorizacaoNFe() throws RemoteException, InterruptedException, JAXBException {
        boolean consErr;
        while (consErr = !ConsultaRetAutorizacao())
            Thread.sleep(1000);

        setInfProt(gettRetConsReciNFe().getProtNFe().get(0).getInfProt());
        System.out.printf("getInfprot: %s\n", getInfProt().getCStat());

        /**
         * <tpAmb>2</tpAmb>
         *             <verAplic>AM4.00</verAplic>
         *             <chNFe>13200608009246000136550010000006151202020066</chNFe>
         *             <dhRecbto>2020-06-23T15:10:17-04:00</dhRecbto>
         *             <nProt>113200008696476</nProt>
         *             <digVal>+H54RVKbEgNu5GNNENpGr6aa2lU=</digVal>
         *             <cStat>100</cStat>
         *             <xMotivo>Autorizado o uso da NF-e</xMotivo>
         */
        System.out.printf(
                String.format("\n-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n" +
                                "<resultado>\n" +
                                "\t<tpAmb>%s</tpAmb>\n" +
                                "\t<verAplic>%s</verAplic>\n" +
                                "\t<chNFe>%s</chNFe>\n" +
                                "\t<dhRecbto>%s</dhRecbto>\n" +
                                "\t<nProt>%s</nProt>\n" +
                                "\t<digVal>%s</digVal>\n" +
                                "\t<cStat>%s</cStat>\n" +
                                "\t<xMotivo>%s</xMotivo>\n" +
                                "</resultado>" +
                                "\n-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n",
                        getInfProt().getTpAmb(),
                        getInfProt().getVerAplic(),
                        getInfProt().getChNFe(),
                        getInfProt().getDhRecbto(),
                        getInfProt().getNProt(),
                        Base64.getEncoder().encodeToString(getInfProt().getDigVal()),
                        getInfProt().getCStat(),
                        getInfProt().getXMotivo()
                )
        );

        if (getInfProt().getCStat().equals("100")) {
            System.out.printf("\nPassou\n");
        } else {
            System.out.printf("\nErrou\n");
            throw new ExceptionDuplicidadeNFe(
                    String.format("cStat")
            );
        }

        return getXml();
    }

    /**
     * Begin Getters and Setters
     */

    public OMElement getOme() {
        return ome;
    }

    public void setOme(OMElement ome) {
        this.ome = ome;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public TRetConsReciNFe gettRetConsReciNFe() {
        return tRetConsReciNFe;
    }

    public void settRetConsReciNFe(TRetConsReciNFe tRetConsReciNFe) {
        this.tRetConsReciNFe = tRetConsReciNFe;
    }

    public InfProt getInfProt() {
        return infProt;
    }

    public void setInfProt(InfProt infProt) {
        this.infProt = infProt;
    }

    public NfeRetAutorizacao4Stub.NfeDadosMsg getDadosMsg_Prod() {
        return dadosMsg_Prod;
    }

    public void setDadosMsg_Prod(NfeRetAutorizacao4Stub.NfeDadosMsg dadosMsg_Prod) {
        this.dadosMsg_Prod = dadosMsg_Prod;
    }

    public NfeRetAutorizacao4Stub getStub_Prod() {
        return stub_Prod;
    }

    public void setStub_Prod(NfeRetAutorizacao4Stub stub_Prod) {
        this.stub_Prod = stub_Prod;
    }

    public NfeRetAutorizacao4Stub.NfeResultMsg getResultMsg_Prod() {
        return resultMsg_Prod;
    }

    public void setResultMsg_Prod(NfeRetAutorizacao4Stub.NfeResultMsg resultMsg_Prod) {
        this.resultMsg_Prod = resultMsg_Prod;
    }

    public br.inf.portalfiscal.wsdl.nfe.hom.nfeRetAutorizacao4.NfeRetAutorizacao4Stub.NfeDadosMsg getDadosMsg_Hom() {
        return dadosMsg_Hom;
    }

    public void setDadosMsg_Hom(br.inf.portalfiscal.wsdl.nfe.hom.nfeRetAutorizacao4.NfeRetAutorizacao4Stub.NfeDadosMsg dadosMsg_Hom) {
        this.dadosMsg_Hom = dadosMsg_Hom;
    }

    public br.inf.portalfiscal.wsdl.nfe.hom.nfeRetAutorizacao4.NfeRetAutorizacao4Stub getStub_Hom() {
        return stub_Hom;
    }

    public void setStub_Hom(br.inf.portalfiscal.wsdl.nfe.hom.nfeRetAutorizacao4.NfeRetAutorizacao4Stub stub_Hom) {
        this.stub_Hom = stub_Hom;
    }

    public br.inf.portalfiscal.wsdl.nfe.hom.nfeRetAutorizacao4.NfeRetAutorizacao4Stub.NfeResultMsg getResultMsg_Hom() {
        return resultMsg_Hom;
    }

    public void setResultMsg_Hom(br.inf.portalfiscal.wsdl.nfe.hom.nfeRetAutorizacao4.NfeRetAutorizacao4Stub.NfeResultMsg resultMsg_Hom) {
        this.resultMsg_Hom = resultMsg_Hom;
    }

    /**
     * END Getters and Setters
     */
}
