package br.com.tlmacedo.nfe.service;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.util.ArrayList;
import java.util.Collections;

public class NFeAssinar {

    private static final String NFE = "NFe";

    private Document document;
    private XMLSignatureFactory signatureFactory;
    private ArrayList<Transform> transformList;
    private String xml;


    public NFeAssinar(String xml) throws UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException, InvalidAlgorithmParameterException, MarshalException, XMLSignatureException {
        assinar(xml);
    }

    private void assinar(String xml) throws UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException, InvalidAlgorithmParameterException, MarshalException, XMLSignatureException {
        setDocument(ServiceDocumentFactory.documentFactory(xml));
        setSignatureFactory(XMLSignatureFactory.getInstance("DOM"));
        setTransformList(signatureFactory(getSignatureFactory()));

        NFev400.CERTIFICATES.setKeyInfo(getSignatureFactory());

        for (int i = 0; i < getDocument().getElementsByTagName(NFE).getLength(); i++) {
            assinarNFe(i);
        }
    }

    private ArrayList<Transform> signatureFactory(XMLSignatureFactory signatureFactory) {
        ArrayList<Transform> transformList = new ArrayList<Transform>();
        TransformParameterSpec tps = null;
        try {
            Transform envelopedTransform = signatureFactory.newTransform(
                    Transform.ENVELOPED, tps);
            Transform c14NTransform = signatureFactory.newTransform(
                    "http://www.w3.org/TR/2001/REC-xml-c14n-20010315", tps);
            transformList.add(envelopedTransform);
            transformList.add(c14NTransform);
        } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return transformList;
    }

    private void assinarNFe(int indexNFe) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, MarshalException, XMLSignatureException {
        NodeList elements = getDocument().getElementsByTagName("infNFe");
        Element el = (Element) elements.item(indexNFe);
        el.setIdAttribute("Id", true);
        String id = el.getAttribute("Id");
        Reference reference = getSignatureFactory().newReference("#" + id,
                getSignatureFactory().newDigestMethod(DigestMethod.SHA1, null), getTransformList(), null, null);

        SignedInfo signedInfo = getSignatureFactory().newSignedInfo(getSignatureFactory().newCanonicalizationMethod(
                CanonicalizationMethod.INCLUSIVE,
                (C14NMethodParameterSpec) null),
                getSignatureFactory().newSignatureMethod(SignatureMethod.RSA_SHA1, null),
                Collections.singletonList(reference));

        XMLSignature signature = getSignatureFactory().newXMLSignature(signedInfo, NFev400.CERTIFICATES.getKeyInfo());

        DOMSignContext domSignContext = new DOMSignContext(NFev400.CERTIFICATES.getPrivateKey(),
                getDocument().getElementsByTagName(NFE).item(indexNFe));

        domSignContext.setBaseURI("ok");

        signature.sign(domSignContext);

        setXml(ServiceOutputXML.outputXML(getDocument()));
    }

    public String getXmlAssinadoNFe() {
        if (NFev400.PRINT_PROMPT)
            System.out.printf("\n%sxmlAssinado: \n%s\n",
                    (NFev400.AMB_PRODUCAO) ? "prod_" : "hom_",
                    getXml());
        return getXml();
    }

    /**
     * Begin Getters and Setters
     */

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public XMLSignatureFactory getSignatureFactory() {
        return signatureFactory;
    }

    public void setSignatureFactory(XMLSignatureFactory signatureFactory) {
        this.signatureFactory = signatureFactory;
    }

    public ArrayList<Transform> getTransformList() {
        return transformList;
    }

    public void setTransformList(ArrayList<Transform> transformList) {
        this.transformList = transformList;
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
