package br.com.tlmacedo.nfe.service;

import br.com.tlmacedo.nfe.model.vo.EnviNfeVO;
import br.com.tlmacedo.nfe.v400.EnviNfe_v400;
import com.google.gson.internal.Pair;
import javafx.concurrent.Task;

import javax.xml.bind.JAXBException;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NFev400 {

    public static boolean AMB_PRODUCAO;

    public static ZoneId ZONE_ID;
    public static DateTimeFormatter DTF_NFE_TO_LOCAL_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX",
            new Locale("pt", "br"));

    public static ServiceLoadCertificates CERTIFICATES;
    public static boolean PRINT_PROMPT;
    public static String XML_CONS_RECIBO;
    public static String DIG_VAL;

    private String xml = null;
    private String xmlAssinado = null;
    private String xmlAutorizacao = null;
    private String xmlRetAutorizacao = null;
    private String xmlProcNfe = null;

    private EnviNfeVO enviNfeVO;

    private List<Pair<String, String>> taskList = new ArrayList<>();

    public NFev400(ServiceLoadCertificates certificates, ZoneId zoneId, boolean ambProducao, boolean printPrompt) throws FileNotFoundException {
        ZONE_ID = zoneId;
        AMB_PRODUCAO = ambProducao;
        PRINT_PROMPT = printPrompt;

        if (CERTIFICATES == null) {
            if (certificates == null)
                CERTIFICATES = new ServiceLoadCertificates("certificados/tokenSafeNet5100.cfg");
            else
                CERTIFICATES = certificates;
        }

    }

    public void newNFev400(EnviNfeVO enviNfeVO) {
        setEnviNfeVO(enviNfeVO);
        getTaskList().add(new Pair<>("NFE_GERAR", "gerando NF-e"));
        getTaskList().add(new Pair<>("NFE_ASSINAR", "assinando NF-e"));
        newNFev400_xmlAssinado(null);

    }

    public void newNFev400_xmlAssinado(String xml) {
        if (xml != null)
            setXmlAssinado(xml);
        getTaskList().add(new Pair<>("NFE_TRANSMITIR", "transmitindo NF-e"));
        newNFev400_xmlConsRecibo(null);

    }

    public void newNFev400_xmlConsRecibo(String xml) {
        if (xml != null)
            XML_CONS_RECIBO = xml;
        getTaskList().add(new Pair<>("NFE_RETORNO", "consultando retorno NF-e"));
        newNFev400_xmlProtNfe(null);

    }

    public void newNFev400_xmlProtNfe(String xml) {
        if (xml != null)
            setXmlProcNfe(xml);
        getTaskList().add(new Pair<>("NFE_PROC", "gerando DANFE da NF-e"));

    }

    public boolean errNoCertificado() throws Exception {
        boolean err = true;
        if (CERTIFICATES.getX509Certificate() == null)
            CERTIFICATES.loadToken();
        err = false;
        return (err);

    }

    public Task getNewTaskNFe() throws ExceptionDuplicidadeNFe {
        int qtdTasks = getTaskList().size();
        final int[] cont = {0};
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateMessage("Loading...");
                for (Pair<String, String> tarefa : getTaskList()) {
                    updateProgress(cont[0]++, qtdTasks);
                    Thread.sleep(200);
                    updateMessage(String.format("%s", tarefa.second));
                    switch (tarefa.first) {
                        case "NFE_GERAR":
                            if (getEnviNfeVO() == null)
                                Thread.currentThread().interrupt();
                            setXml(new EnviNfe_v400().getXmlNfe(getEnviNfeVO()));
                            break;
                        case "NFE_ASSINAR":
                            if (getXml() == null)
                                Thread.currentThread().interrupt();
                            setXmlAssinado(new NFeAssinar(getXml()).getXmlAssinadoNFe());
                            break;
                        case "NFE_TRANSMITIR":
                            if (getXmlAssinado() == null)
                                Thread.currentThread().interrupt();
                            setXmlAutorizacao(new NFeAutorizacao(getXmlAssinado()).getXmlAutorizacaoNFe());
                            break;
                        case "NFE_RETORNO":
                            if (XML_CONS_RECIBO == null)
                                Thread.currentThread().interrupt();
                            setXmlRetAutorizacao(new NFeRetAutorizacao(XML_CONS_RECIBO).getXmlRetAutorizacaoNFe());
                            break;
                        case "NFE_PROC":
                            if (getXmlRetAutorizacao() == null)
                                Thread.currentThread().interrupt();
                            setXmlProcNfe(new NFeProc(getXmlAssinado(), getXmlRetAutorizacao()).getStrResultNFeProc());
                            break;
                        case "RELATORIO_IMPRIME_NFE":
                            if (getXmlProcNfe() == null)
                                Thread.currentThread().interrupt();
//                            ServiceFileXmlSave.saveTNfeProcToFile(nFev400Property().getValue().getProcNFe().gettNfeProc());
                            break;
                    }
                }
                updateMessage("tarefa concluída!!!");
                updateProgress(qtdTasks, qtdTasks);
                return null;
            }
        };

    }

    public void exec_tarefas() throws ExceptionDuplicidadeNFe, JAXBException, InvalidAlgorithmParameterException, MarshalException, NoSuchAlgorithmException, KeyStoreException, XMLSignatureException, UnrecoverableEntryException, XMLStreamException, RemoteException, InterruptedException {
        for (Pair<String, String> tarefa : getTaskList()) {
            Thread.sleep(200);
            switch (tarefa.first) {
                case "NFE_GERAR":
                    if (getEnviNfeVO() == null)
                        Thread.currentThread().interrupt();
                    setXml(new EnviNfe_v400().getXmlNfe(getEnviNfeVO()));
                    break;
                case "NFE_ASSINAR":
                    if (getXml() == null)
                        Thread.currentThread().interrupt();
                    setXmlAssinado(new NFeAssinar(getXml()).getXmlAssinadoNFe());
                    break;
                case "NFE_TRANSMITIR":
                    if (getXmlAssinado() == null)
                        Thread.currentThread().interrupt();
                    setXmlAutorizacao(new NFeAutorizacao(getXmlAssinado()).getXmlAutorizacaoNFe());
                    break;
                case "NFE_RETORNO":
                    if (XML_CONS_RECIBO == null)
                        Thread.currentThread().interrupt();
                    setXmlRetAutorizacao(new NFeRetAutorizacao(XML_CONS_RECIBO).getXmlRetAutorizacaoNFe());
                    break;
                case "NFE_PROC":
                    if (getXmlRetAutorizacao() == null)
                        Thread.currentThread().interrupt();
                    setXmlProcNfe(new NFeProc(getXmlAssinado(), getXmlRetAutorizacao()).getStrResultNFeProc());
                    break;
//                            case RELATORIO_IMPRIME_NFE:
//                                if (xmlNFeProcProperty().getValue() == null)
//                                    Thread.currentThread().interrupt();
//                                ControllerPrincipal.getCtrlPrincipal().getPrincipalStage().getScene().setCursor(Cursor.CROSSHAIR);
//                                ServiceFileXmlSave.saveTNfeProcToFile(nFev400Property().getValue().getProcNFe().gettNfeProc());
//                                ControllerPrincipal.getCtrlPrincipal().getPrincipalStage().getScene().setCursor(Cursor.DEFAULT);
//                                break;
            }
        }

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

    public String getXmlAssinado() {
        return xmlAssinado;
    }

    public void setXmlAssinado(String xmlAssinado) {
        this.xmlAssinado = xmlAssinado;
    }

    public String getXmlAutorizacao() {
        return xmlAutorizacao;
    }

    public void setXmlAutorizacao(String xmlAutorizacao) {
        this.xmlAutorizacao = xmlAutorizacao;
    }

    public String getXmlRetAutorizacao() {
        return xmlRetAutorizacao;
    }

    public void setXmlRetAutorizacao(String xmlRetAutorizacao) {
        this.xmlRetAutorizacao = xmlRetAutorizacao;
    }

    public String getXmlProcNfe() {
        return xmlProcNfe;
    }

    public void setXmlProcNfe(String xmlProcNfe) {
        this.xmlProcNfe = xmlProcNfe;
    }

    public EnviNfeVO getEnviNfeVO() {
        return enviNfeVO;
    }

    public void setEnviNfeVO(EnviNfeVO enviNfeVO) {
        this.enviNfeVO = enviNfeVO;
    }

    public List<Pair<String, String>> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Pair<String, String>> taskList) {
        this.taskList = taskList;
    }

    /**
     * END Getters and Setters
     */


}
