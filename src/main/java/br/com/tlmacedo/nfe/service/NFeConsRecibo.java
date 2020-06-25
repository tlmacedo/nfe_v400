package br.com.tlmacedo.nfe.service;

import br.inf.portalfiscal.xsd.nfe.consReciNFe.TConsReciNFe;
import br.inf.portalfiscal.xsd.nfe.retEnviNFe.TRetEnviNFe;

import javax.xml.bind.JAXBException;

public class NFeConsRecibo {

    private static TConsReciNFe tConsReciNFe;
    private static TRetEnviNFe tRetEnviNFe;
    private static String xml;


    public static void getXmlConsReciNFe(String xmlRetAutorizacao) throws JAXBException {
        settRetEnviNFe(ServiceUtilXml.xmlToObject(xmlRetAutorizacao, TRetEnviNFe.class));
        setXml(ServiceUtilXml.objectToXml(newConsReciNFe()));
        NFev400.XML_CONS_RECIBO = getXml();

        if (NFev400.PRINT_PROMPT)
            System.out.printf("\n%sxmlConsReciNFe:\n%s\n",
                    (NFev400.AMB_PRODUCAO) ? "prod_" : "hom_",
                    getXml());
        //return getXml();
    }

    private static TConsReciNFe newConsReciNFe() {
        settConsReciNFe(new TConsReciNFe());
        gettConsReciNFe().setVersao(gettRetEnviNFe().getVersao());
        gettConsReciNFe().setTpAmb(gettRetEnviNFe().getTpAmb());
        gettConsReciNFe().setNRec(gettRetEnviNFe().getInfRec().getNRec());
        return gettConsReciNFe();
    }

    /**
     * Begin Getters and Setters
     */

    public static TConsReciNFe gettConsReciNFe() {
        return tConsReciNFe;
    }

    public static void settConsReciNFe(TConsReciNFe tConsReciNFe) {
        NFeConsRecibo.tConsReciNFe = tConsReciNFe;
    }

    public static TRetEnviNFe gettRetEnviNFe() {
        return tRetEnviNFe;
    }

    public static void settRetEnviNFe(TRetEnviNFe tRetEnviNFe) {
        NFeConsRecibo.tRetEnviNFe = tRetEnviNFe;
    }

    public static String getXml() {
        return xml;
    }

    public static void setXml(String xml) {
        NFeConsRecibo.xml = xml;
    }

    /**
     * END Getters and Setters
     */
}
