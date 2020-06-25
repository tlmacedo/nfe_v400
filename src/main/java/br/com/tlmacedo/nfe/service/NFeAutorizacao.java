package br.com.tlmacedo.nfe.service;

import br.inf.portalfiscal.wsdl.nfe.prod.nfeAutorizacao4.NfeAutorizacao4Stub;
import br.inf.portalfiscal.wsdl.nfe.prod.nfeAutorizacao4.NfeAutorizacao4Stub.NfeDadosMsg;
import br.inf.portalfiscal.wsdl.nfe.prod.nfeAutorizacao4.NfeAutorizacao4Stub.NfeResultMsg;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.rmi.RemoteException;

public class NFeAutorizacao {

    private OMElement ome = null;

    private String xml = null;
    private NfeDadosMsg dadosMsg_Prod;
    private NfeAutorizacao4Stub stub_Prod;
    private NfeResultMsg resultMsg_Prod;

    private br.inf.portalfiscal.wsdl.nfe.hom.nfeAutorizacao4.NfeAutorizacao4Stub.NfeDadosMsg dadosMsg_Hom;
    private br.inf.portalfiscal.wsdl.nfe.hom.nfeAutorizacao4.NfeAutorizacao4Stub stub_Hom;
    private br.inf.portalfiscal.wsdl.nfe.hom.nfeAutorizacao4.NfeAutorizacao4Stub.NfeResultMsg resultMsg_Hom;


    public NFeAutorizacao(String xmlNFeAssinada) throws XMLStreamException, RemoteException, JAXBException {
        setOme(AXIOMUtil.stringToOM(xmlNFeAssinada));

        if (NFev400.AMB_PRODUCAO) {
            setDadosMsg_Prod(new NfeDadosMsg());
            setStub_Prod(new NfeAutorizacao4Stub());
            getDadosMsg_Prod().setExtraElement(getOme());
            setResultMsg_Prod(getStub_Prod().nfeAutorizacaoLote(getDadosMsg_Prod()));
        } else {
            setDadosMsg_Hom(new br.inf.portalfiscal.wsdl.nfe.hom.nfeAutorizacao4.NfeAutorizacao4Stub.NfeDadosMsg());
            setStub_Hom(new br.inf.portalfiscal.wsdl.nfe.hom.nfeAutorizacao4.NfeAutorizacao4Stub());
            getDadosMsg_Hom().setExtraElement(getOme());
            setResultMsg_Hom(getStub_Hom().nfeAutorizacaoLote(getDadosMsg_Hom()));
        }
    }

    public String getXmlAutorizacaoNFe() throws JAXBException {
        if (NFev400.AMB_PRODUCAO)
            setXml(getResultMsg_Prod().getExtraElement().toString());
        else
            setXml(getResultMsg_Hom().getExtraElement().toString());

        if (NFev400.PRINT_PROMPT)
            System.out.printf("\n%sxmlNFeAutorizacao: \n%s\n",
                    (NFev400.AMB_PRODUCAO) ? "prod_" : "hom_",
                    getXml());

        NFeConsRecibo.getXmlConsReciNFe(getXml());
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

    public NfeDadosMsg getDadosMsg_Prod() {
        return dadosMsg_Prod;
    }

    public void setDadosMsg_Prod(NfeDadosMsg dadosMsg_Prod) {
        this.dadosMsg_Prod = dadosMsg_Prod;
    }

    public NfeAutorizacao4Stub getStub_Prod() {
        return stub_Prod;
    }

    public void setStub_Prod(NfeAutorizacao4Stub stub_Prod) {
        this.stub_Prod = stub_Prod;
    }

    public NfeResultMsg getResultMsg_Prod() {
        return resultMsg_Prod;
    }

    public void setResultMsg_Prod(NfeResultMsg resultMsg_Prod) {
        this.resultMsg_Prod = resultMsg_Prod;
    }

    public br.inf.portalfiscal.wsdl.nfe.hom.nfeAutorizacao4.NfeAutorizacao4Stub.NfeDadosMsg getDadosMsg_Hom() {
        return dadosMsg_Hom;
    }

    public void setDadosMsg_Hom(br.inf.portalfiscal.wsdl.nfe.hom.nfeAutorizacao4.NfeAutorizacao4Stub.NfeDadosMsg dadosMsg_Hom) {
        this.dadosMsg_Hom = dadosMsg_Hom;
    }

    public br.inf.portalfiscal.wsdl.nfe.hom.nfeAutorizacao4.NfeAutorizacao4Stub getStub_Hom() {
        return stub_Hom;
    }

    public void setStub_Hom(br.inf.portalfiscal.wsdl.nfe.hom.nfeAutorizacao4.NfeAutorizacao4Stub stub_Hom) {
        this.stub_Hom = stub_Hom;
    }

    public br.inf.portalfiscal.wsdl.nfe.hom.nfeAutorizacao4.NfeAutorizacao4Stub.NfeResultMsg getResultMsg_Hom() {
        return resultMsg_Hom;
    }

    public void setResultMsg_Hom(br.inf.portalfiscal.wsdl.nfe.hom.nfeAutorizacao4.NfeAutorizacao4Stub.NfeResultMsg resultMsg_Hom) {
        this.resultMsg_Hom = resultMsg_Hom;
    }

    /**
     * END Getters and Setters
     */
}
