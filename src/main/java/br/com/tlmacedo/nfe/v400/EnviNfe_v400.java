package br.com.tlmacedo.nfe.v400;

import br.com.tlmacedo.nfe.model.vo.EnviNfeVO;
import br.com.tlmacedo.nfe.model.vo.NfeVO;
import br.com.tlmacedo.nfe.service.NFev400;
import br.com.tlmacedo.nfe.service.ServiceUtilXml;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TEnviNFe;

import javax.xml.bind.JAXBException;

public class EnviNfe_v400 {

    private TEnviNFe tEnviNFe;
    private static NfeVO nfeVO;
    private String xml;

    public String getXmlNfe(EnviNfeVO enviNfeVO) throws JAXBException {
        settEnviNFe(new TEnviNFe());
        setNfeVO(enviNfeVO.getNfe());
        gettEnviNFe().setVersao(enviNfeVO.getVersao());
        gettEnviNFe().setIdLote(enviNfeVO.getIdLote());
        gettEnviNFe().setIndSinc(enviNfeVO.getIndSinc());
        gettEnviNFe().getNFe().add(new Nfe_v400().getTnFe());
        setXml(ServiceUtilXml.objectToXml(gettEnviNFe()));
        if (NFev400.PRINT_PROMPT)
            System.out.printf("\n%sxml: \n%s\n",
                    (NFev400.AMB_PRODUCAO) ? "prod_" : "hom_",
                    getXml());
        return getXml();
    }

    /**
     * Begin Getters and Setters
     */

    public TEnviNFe gettEnviNFe() {
        return tEnviNFe;
    }

    public void settEnviNFe(TEnviNFe tEnviNFe) {
        this.tEnviNFe = tEnviNFe;
    }

    public static NfeVO getNfeVO() {
        return nfeVO;
    }

    public static void setNfeVO(NfeVO nfeVO) {
        EnviNfe_v400.nfeVO = nfeVO;
    }


    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    /**
     * END Getters and Setters
     */
}
