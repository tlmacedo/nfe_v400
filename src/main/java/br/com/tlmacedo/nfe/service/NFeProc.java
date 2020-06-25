package br.com.tlmacedo.nfe.service;

import br.inf.portalfiscal.xsd.nfe.procNFe.TNFe;
import br.inf.portalfiscal.xsd.nfe.procNFe.TNfeProc;
import br.inf.portalfiscal.xsd.nfe.procNFe.TProtNFe;
import br.inf.portalfiscal.xsd.nfe.retConsReciNFe.TRetConsReciNFe;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.bind.JAXBException;
import java.util.Base64;

public class NFeProc {

    private String xml;
    private String strChaveId;
    private String strNFeAssinado;
    private String strNFeRetAutorizacao;
    private TNfeProc tNfeProc;
    private TRetConsReciNFe reciNFe;


    public NFeProc(String xmlNFeAssinado, String xmlNFeRetAutorizacao) throws JAXBException {
        settNfeProc(new TNfeProc());
        setStrNFeAssinado(xmlNFeAssinado);
        setStrNFeRetAutorizacao(xmlNFeRetAutorizacao);
        setReciNFe(ServiceUtilXml.xmlToObject(xmlNFeRetAutorizacao, TRetConsReciNFe.class));
    }

    public TNFe getTNFe() throws JAXBException {
        Document document = ServiceDocumentFactory.documentFactory(getStrNFeAssinado());
        setStrChaveId(((Element) document.getElementsByTagName("infNFe").item(0)).getAttribute("Id"));
        return ServiceUtilXml.xmlToObject(ServiceOutputXML.outputXML(document.getDocumentElement().getElementsByTagName("NFe").item(0)), TNFe.class);
    }

    public TProtNFe getTProtNFe() throws JAXBException {
        Document document = ServiceDocumentFactory.documentFactory(getStrNFeRetAutorizacao());
        if (getStrChaveId().contains(((Element) document.getElementsByTagName("chNFe").item(0)).getTextContent()))
            return ServiceUtilXml.xmlToObject(ServiceOutputXML.outputXML(document.getDocumentElement().getElementsByTagName("protNFe").item(0)), TProtNFe.class);
        return null;
    }

    public TNfeProc getResultNFeProc() throws JAXBException {
        gettNfeProc().setVersao(getReciNFe().getVersao());
        gettNfeProc().setNFe(getTNFe());
        gettNfeProc().setProtNFe(getTProtNFe());
        NFev400.DIG_VAL = Base64.getEncoder().encodeToString(gettNfeProc().getProtNFe().getInfProt().getDigVal());
        return gettNfeProc();
    }

    public String getStrResultNFeProc() throws JAXBException {
        setXml(ServiceUtilXml.objectToXml(getResultNFeProc()));
        if (NFev400.PRINT_PROMPT)
            System.out.printf("\n%sxmlNFeProc: \n%s\n",
                    (NFev400.AMB_PRODUCAO) ? "prod_" : "hom_",
                    getXml());
        return getXml();
    }

    /**
     * Begin Getters and Setters
     */

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getStrChaveId() {
        return strChaveId;
    }

    public void setStrChaveId(String strChaveId) {
        this.strChaveId = strChaveId;
    }

    public String getStrNFeAssinado() {
        return strNFeAssinado;
    }

    public void setStrNFeAssinado(String strNFeAssinado) {
        this.strNFeAssinado = strNFeAssinado;
    }

    public String getStrNFeRetAutorizacao() {
        return strNFeRetAutorizacao;
    }

    public void setStrNFeRetAutorizacao(String strNFeRetAutorizacao) {
        this.strNFeRetAutorizacao = strNFeRetAutorizacao;
    }

    public TNfeProc gettNfeProc() {
        return tNfeProc;
    }

    public void settNfeProc(TNfeProc tNfeProc) {
        this.tNfeProc = tNfeProc;
    }

    public TRetConsReciNFe getReciNFe() {
        return reciNFe;
    }

    public void setReciNFe(TRetConsReciNFe reciNFe) {
        this.reciNFe = reciNFe;
    }


/**
 * END Getters and Setters
 */
}
