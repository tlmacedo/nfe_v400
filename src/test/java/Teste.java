import br.com.tlmacedo.nfe.service.NFeProc;
import br.com.tlmacedo.nfe.service.NFev400;

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

public class Teste {

    public static void main(String[] args) throws Exception {
        String LOCAL_TIME_ZONE = "America/Manaus";
        ZoneId MY_ZONE_TIME = ZoneId.of(LOCAL_TIME_ZONE);

        String xmlReciboErr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><consReciNFe versao=\"4.00\" xmlns=\"http://www.portalfiscal.inf.br/nfe\"><tpAmb>2</tpAmb><nRec>130000155413402</nRec></consReciNFe>";
        String xmlAssinadoErr = "<enviNFe xmlns=\"http://www.portalfiscal.inf.br/nfe\" versao=\"4.00\"><idLote>000000000000085</idLote><indSinc>0</indSinc><NFe><infNFe Id=\"NFe13200608009246000136550010000006161202020063\" versao=\"4.00\"><ide><cUF>13</cUF><cNF>20202006</cNF><natOp>VENDA Dentro Do Estado</natOp><mod>55</mod><serie>1</serie><nNF>616</nNF><dhEmi>2020-06-23T00:00:00-04:00</dhEmi><dhSaiEnt>2020-06-23T00:00:00-04:00</dhSaiEnt><tpNF>1</tpNF><idDest>1</idDest><cMunFG>1302603</cMunFG><tpImp>1</tpImp><tpEmis>1</tpEmis><cDV>3</cDV><tpAmb>2</tpAmb><finNFe>1</finNFe><indFinal>1</indFinal><indPres>3</indPres><procEmi>0</procEmi><verProc>2.02</verProc></ide><emit><CNPJ>08009246000136</CNPJ><xNome>T. L. MACEDO</xNome><xFant>CAFE PERFEITO</xFant><enderEmit><xLgr>R TREZE DE MAIO</xLgr><nro>159</nro><xBairro>COROADO</xBairro><cMun>1302603</cMun><xMun>MANAUS</xMun><UF>AM</UF><CEP>69080440</CEP><cPais>1058</cPais><xPais>BRASIL</xPais><fone>92981686148</fone></enderEmit><IE>042171865</IE><CRT>3</CRT></emit><dest><CNPJ>02844344000102</CNPJ><xNome>FUNDACAO AMAZONICA DE AMPARO A PESQUISA E DESENVOLVIMENTO TE</xNome><enderDest><xLgr>AV GOVERNADOR DANILO AREOSA</xLgr><nro>1170</nro><xCpl>LOTE 164</xCpl><xBairro>DISTRITO INDUSTRIAL I</xBairro><cMun>1302603</cMun><xMun>MANAUS</xMun><UF>AM</UF><CEP>69075351</CEP><cPais>1058</cPais><xPais>BRASIL</xPais><fone>9221239797</fone></enderDest><indIEDest>9</indIEDest><email>contabil@fpf.br</email></dest><det nItem=\"1\"><prod><cProd>1137</cProd><cEAN>7898903647025</cEAN><xProd>CAFE SUPREMO ARABICA TRADICIONAL 1 KG Lt[1244] val.:22/12/2019</xProd><NCM>09011110</NCM><CEST>1709602</CEST><CFOP>5102</CFOP><uCom>PCT</uCom><qCom>20.0000</qCom><vUnCom>46.0000000000</vUnCom><vProd>920.00</vProd><cEANTrib>7898903647025</cEANTrib><uTrib>PCT</uTrib><qTrib>20.0000</qTrib><vUnTrib>46.0000000000</vUnTrib><vDesc>60.00</vDesc><indTot>1</indTot></prod><imposto><ICMS><ICMS60><orig>0</orig><CST>60</CST></ICMS60></ICMS><PIS><PISNT><CST>06</CST></PISNT></PIS><COFINS><COFINSNT><CST>06</CST></COFINSNT></COFINS></imposto></det><det nItem=\"2\"><prod><cProd>1137</cProd><cEAN>7898903647025</cEAN><xProd>CAFE SUPREMO ARABICA TRADICIONAL 1 KG Lt[1249] val.:26/02/2020</xProd><NCM>09011110</NCM><CEST>1709602</CEST><CFOP>5102</CFOP><uCom>PCT</uCom><qCom>20.0000</qCom><vUnCom>46.0000000000</vUnCom><vProd>920.00</vProd><cEANTrib>7898903647025</cEANTrib><uTrib>PCT</uTrib><qTrib>20.0000</qTrib><vUnTrib>46.0000000000</vUnTrib><vDesc>60.00</vDesc><indTot>1</indTot></prod><imposto><ICMS><ICMS60><orig>0</orig><CST>60</CST></ICMS60></ICMS><PIS><PISNT><CST>06</CST></PISNT></PIS><COFINS><COFINSNT><CST>06</CST></COFINSNT></COFINS></imposto></det><det nItem=\"3\"><prod><cProd>1137</cProd><cEAN>7898903647025</cEAN><xProd>CAFE SUPREMO ARABICA TRADICIONAL 1 KG Lt[1250] val.:30/03/2020</xProd><NCM>09011110</NCM><CEST>1709602</CEST><CFOP>5102</CFOP><uCom>PCT</uCom><qCom>20.0000</qCom><vUnCom>46.0000000000</vUnCom><vProd>920.00</vProd><cEANTrib>7898903647025</cEANTrib><uTrib>PCT</uTrib><qTrib>20.0000</qTrib><vUnTrib>46.0000000000</vUnTrib><vDesc>60.00</vDesc><indTot>1</indTot></prod><imposto><ICMS><ICMS60><orig>0</orig><CST>60</CST></ICMS60></ICMS><PIS><PISNT><CST>06</CST></PISNT></PIS><COFINS><COFINSNT><CST>06</CST></COFINSNT></COFINS></imposto></det><det nItem=\"4\"><prod><cProd>3901</cProd><cEAN>7896343087555</cEAN><xProd>MEXEDOR PRAFESTA CAFE C/250 UND Lt[8755P] val.:31/12/2021</xProd><NCM>39241000</NCM><CEST>2803300</CEST><CFOP>5102</CFOP><uCom>PCT</uCom><qCom>20.0000</qCom><vUnCom>8.2500000000</vUnCom><vProd>165.00</vProd><cEANTrib>7896343087555</cEANTrib><uTrib>PCT</uTrib><qTrib>20.0000</qTrib><vUnTrib>8.2500000000</vUnTrib><indTot>1</indTot></prod><imposto><ICMS><ICMS60><orig>0</orig><CST>60</CST></ICMS60></ICMS><PIS><PISNT><CST>06</CST></PISNT></PIS><COFINS><COFINSNT><CST>06</CST></COFINSNT></COFINS></imposto></det><det nItem=\"5\"><prod><cProd>3902</cProd><cEAN>7896343087609</cEAN><xProd>MEXEDOR PRAFESTA DRINK C/200 UND Lt[8760P] val.:31/12/2021</xProd><NCM>39241000</NCM><CEST>2803300</CEST><CFOP>5102</CFOP><uCom>PCT</uCom><qCom>15.0000</qCom><vUnCom>15.0000000000</vUnCom><vProd>225.00</vProd><cEANTrib>7896343087609</cEANTrib><uTrib>PCT</uTrib><qTrib>15.0000</qTrib><vUnTrib>15.0000000000</vUnTrib><indTot>1</indTot></prod><imposto><ICMS><ICMS60><orig>0</orig><CST>60</CST></ICMS60></ICMS><PIS><PISNT><CST>06</CST></PISNT></PIS><COFINS><COFINSNT><CST>06</CST></COFINSNT></COFINS></imposto></det><det nItem=\"6\"><prod><cProd>1702</cProd><cEAN>7896433800019</cEAN><xProd>ACUCAR ITAMARATI 1 KG Lt[] val.:31/12/2020</xProd><NCM>17019900</NCM><CEST>2899900</CEST><CFOP>5102</CFOP><uCom>KG</uCom><qCom>60.0000</qCom><vUnCom>3.0100000000</vUnCom><vProd>180.60</vProd><cEANTrib>7896433800019</cEANTrib><uTrib>KG</uTrib><qTrib>60.0000</qTrib><vUnTrib>3.0100000000</vUnTrib><indTot>1</indTot></prod><imposto><ICMS><ICMS60><orig>0</orig><CST>60</CST></ICMS60></ICMS><PIS><PISNT><CST>06</CST></PISNT></PIS><COFINS><COFINSNT><CST>06</CST></COFINSNT></COFINS></imposto></det><det nItem=\"7\"><prod><cProd>1704</cProd><cEAN>7896804600453</cEAN><xProd>ACUCAR SACHE BOM SABOR 5 G C/336 UND Lt[1167080719] val.:31/07/2020</xProd><NCM>17019900</NCM><CEST>1709900</CEST><CFOP>5102</CFOP><uCom>CX</uCom><qCom>1.0000</qCom><vUnCom>40.0000000000</vUnCom><vProd>40.00</vProd><cEANTrib>7896804600453</cEANTrib><uTrib>CX</uTrib><qTrib>1.0000</qTrib><vUnTrib>40.0000000000</vUnTrib><indTot>1</indTot></prod><imposto><ICMS><ICMS60><orig>0</orig><CST>60</CST></ICMS60></ICMS><PIS><PISNT><CST>06</CST></PISNT></PIS><COFINS><COFINSNT><CST>06</CST></COFINSNT></COFINS></imposto></det><total><ICMSTot><vBC>0.00</vBC><vICMS>0.00</vICMS><vICMSDeson>0.00</vICMSDeson><vFCPUFDest>0.00</vFCPUFDest><vICMSUFDest>0.00</vICMSUFDest><vICMSUFRemet>0.00</vICMSUFRemet><vFCP>0.00</vFCP><vBCST>0.00</vBCST><vST>0.00</vST><vFCPST>0.00</vFCPST><vFCPSTRet>0.00</vFCPSTRet><vProd>3370.60</vProd><vFrete>0.00</vFrete><vSeg>0.00</vSeg><vDesc>180.00</vDesc><vII>0.00</vII><vIPI>0.00</vIPI><vIPIDevol>0.00</vIPIDevol><vPIS>0.00</vPIS><vCOFINS>0.00</vCOFINS><vOutro>0.00</vOutro><vNF>3190.60</vNF><vTotTrib>0.00</vTotTrib></ICMSTot></total><transp><modFrete>0</modFrete></transp><cobr><fat><nFat>616</nFat><vOrig>3370.60</vOrig><vDesc>180.00</vDesc><vLiq>3190.60</vLiq></fat></cobr><pag><detPag><indPag>1</indPag><tPag>99</tPag><vPag>3190.60</vPag></detPag></pag><infAdic><infCpl>TOTAL R$ 3.190,60*T*DT. VENC.:  DT. VENC.: 30/09/2019*N*DADOS BANCARIOS PARA TRANSFERENCIA*N*BANCO NUBANK AG: 0001 C/C: 90.846.088-3</infCpl></infAdic><infRespTec><CNPJ>08009246000136</CNPJ><xContato>THIAGO LEIROS MACEDO</xContato><email>tl.macedo@hotmail.com</email><fone>92981686148</fone></infRespTec></infNFe><Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\"><SignedInfo><CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/><SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"/><Reference URI=\"#NFe13200608009246000136550010000006161202020063\"><Transforms><Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/><Transform Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/></Transforms><DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"/><DigestValue>AxXkNo40YSF9Ps+AZcc8zzBZAyI=</DigestValue></Reference></SignedInfo><SignatureValue>OMYbHh4oSwayo3uVyZ9lz3A/LOb6Hm6aObZE4UM50rNtp4P8Nw+Krq5Ng61lLpWhZevFbyXZd5Xq&#13;\n" +
                "uQxU9fNMEXaXIHz6tPKYUCKM/gjBxYGsKoJbW5qbEqTKzaOZAI9iE4qavRJgBzPfEl4KFRTXjnJN&#13;\n" +
                "mh/3TT6Nl5JEDS+vWvYJognnBBBx9E+CyPcUl2pIfGGXmglGiI9Un5uT559Lk/ucpVnB6ANm0PXv&#13;\n" +
                "aPgVJ7JNe8HGNGyweDBDKjyJ1iT1WGTAYH19YpiYCyDeDmToZ+kHpUCI+vdDmczojs6CuHgbStIO&#13;\n" +
                "QGK8lL/rEc6rZOkIQvaaIFS0NH4TyJ2ut2ypLg==</SignatureValue><KeyInfo><X509Data><X509Certificate>MIIH9jCCBd6gAwIBAgIILOIUUKlZVGEwDQYJKoZIhvcNAQELBQAwdTELMAkGA1UEBhMCQlIxEzAR&#13;\n" +
                "BgNVBAoMCklDUC1CcmFzaWwxNjA0BgNVBAsMLVNlY3JldGFyaWEgZGEgUmVjZWl0YSBGZWRlcmFs&#13;\n" +
                "IGRvIEJyYXNpbCAtIFJGQjEZMBcGA1UEAwwQQUMgU0VSQVNBIFJGQiB2NTAeFw0yMDA0MTYyMTAw&#13;\n" +
                "MDBaFw0yMzA0MTYyMTAwMDBaMIIBGjELMAkGA1UEBhMCQlIxCzAJBgNVBAgMAkFNMQ8wDQYDVQQH&#13;\n" +
                "DAZNQU5BVVMxEzARBgNVBAoMCklDUC1CcmFzaWwxGDAWBgNVBAsMDzAwMDAwMTAwOTU4MDgzMzE2&#13;\n" +
                "MDQGA1UECwwtU2VjcmV0YXJpYSBkYSBSZWNlaXRhIEZlZGVyYWwgZG8gQnJhc2lsIC0gUkZCMRYw&#13;\n" +
                "FAYDVQQLDA1SRkIgZS1DTlBKIEEzMRkwFwYDVQQLDBBBQyBTRVJBU0EgUkZCIHY1MRcwFQYDVQQL&#13;\n" +
                "DA4wMTE0Njk1NzAwMDEwMzEWMBQGA1UECwwNQVIgVFJFVk9DSEVDSzEiMCAGA1UEAwwZVCBMIE1B&#13;\n" +
                "Q0VETzowODAwOTI0NjAwMDEzNjCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBALs8aZPR&#13;\n" +
                "eSRXREv9KovxzTwyttErAiPsgOYV6XG4ZpgsywK8HB15C8L2aw8X1LY5bx9xqMMCFWIxsjos5yJa&#13;\n" +
                "LlHrb5Urcr2+WHZ3gRLno2AbfGv6nRVyXCnuZaVwb3yCH70xDPyNsYnd/PcW4X7Bb6rqZ+kKj142&#13;\n" +
                "0A2PrHuynmvftPz8mHoLxhHCp0mJsQ4X8lMJTD57F3XUWgJDAwgxPSXNyUjq9d2fwigm46KU9Fdm&#13;\n" +
                "4TbysDrHiC5CybfAKEHewxQWKDyN1BBKCpwP41ooRC9rw/BemOwEIgvpUwTRX1LPBgb+bII9K0sC&#13;\n" +
                "7PH/4is6KlgHh4+bGDKMLpcOXaN4WcsCAwEAAaOCAuEwggLdMAkGA1UdEwQCMAAwHwYDVR0jBBgw&#13;\n" +
                "FoAU7PFBUVeo5jrpXrOgIvkIirU6h48wgZkGCCsGAQUFBwEBBIGMMIGJMEgGCCsGAQUFBzAChjxo&#13;\n" +
                "dHRwOi8vd3d3LmNlcnRpZmljYWRvZGlnaXRhbC5jb20uYnIvY2FkZWlhcy9zZXJhc2FyZmJ2NS5w&#13;\n" +
                "N2IwPQYIKwYBBQUHMAGGMWh0dHA6Ly9vY3NwLmNlcnRpZmljYWRvZGlnaXRhbC5jb20uYnIvc2Vy&#13;\n" +
                "YXNhcmZidjUwgbEGA1UdEQSBqTCBpoEVVEwuTUFDRURPQEhPVE1BSUwuQ09NoB8GBWBMAQMCoBYT&#13;\n" +
                "FFRISUFHTyBMRUlST1MgTUFDRURPoBkGBWBMAQMDoBATDjA4MDA5MjQ2MDAwMTM2oDgGBWBMAQME&#13;\n" +
                "oC8TLTAyMDQxOTgxNTIzMDk1NTAyMzAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMKAXBgVgTAED&#13;\n" +
                "B6AOEwwwMDAwMDAwMDAwMDAwcQYDVR0gBGowaDBmBgZgTAECAwowXDBaBggrBgEFBQcCARZOaHR0&#13;\n" +
                "cDovL3B1YmxpY2FjYW8uY2VydGlmaWNhZG9kaWdpdGFsLmNvbS5ici9yZXBvc2l0b3Jpby9kcGMv&#13;\n" +
                "ZGVjbGFyYWNhby1yZmIucGRmMB0GA1UdJQQWMBQGCCsGAQUFBwMCBggrBgEFBQcDBDCBnQYDVR0f&#13;\n" +
                "BIGVMIGSMEqgSKBGhkRodHRwOi8vd3d3LmNlcnRpZmljYWRvZGlnaXRhbC5jb20uYnIvcmVwb3Np&#13;\n" +
                "dG9yaW8vbGNyL3NlcmFzYXJmYnY1LmNybDBEoEKgQIY+aHR0cDovL2xjci5jZXJ0aWZpY2Fkb3Mu&#13;\n" +
                "Y29tLmJyL3JlcG9zaXRvcmlvL2xjci9zZXJhc2FyZmJ2NS5jcmwwHQYDVR0OBBYEFIIEYDiE2ogr&#13;\n" +
                "cC/XQd28cccn4ugOMA4GA1UdDwEB/wQEAwIF4DANBgkqhkiG9w0BAQsFAAOCAgEAghuyfpNLTb4H&#13;\n" +
                "tTSUjB5XyCNWlEwk8oDrBrm0QFVxpL7ekuCPYaltYkk2nap07RzyrIW+8ygL4P9P/9T4eX+pST6G&#13;\n" +
                "OS6EwrQN0IXRS8a05h60sbKEg0Yo0amFY7e/+4g8MfThUGiqlvif5XSOC0x/lQLZdiMDC1N9tqDP&#13;\n" +
                "zvfheW4MUtH7YnRNCbnKAP6kKHwiwzYoH0ZaoFUPoY/MEjE6xiJHahN6py79TSLSW3hXZq3zzWSs&#13;\n" +
                "tdLnm1zAVKmZLUsx6tM3dFnWz52f2V56j9par07uDqQBolebUZOFOgAJCiOCmuUjviDROnItkv5r&#13;\n" +
                "MfpZtaMVuKx2o2ay5wl6ebkXO4SmrIAmA4UuBrvY14lEBx2u9zX0IJddfddKHzN8GybHMcrJR601&#13;\n" +
                "tbMFFPusphX8Gj/ClnU94P0bvi8u4daJKQApZQr6lnwKZ3TWuNwpCPx6JhP+7BfSmbZEwDgiIYas&#13;\n" +
                "Wpe2d4HHhinzrIYEAgkcDJ72C6SJWDr7c8vBiXVxXVdmqykuMVg+8ANlsFnrd9MVxa65rY9Giwdy&#13;\n" +
                "6zcOBffaHLHcLR2nyCqkC6O8t2ZsKpR07fEXwy3loFKnujtpnVGMeGq7fKeXP5WSKAp4jeVOQfTl&#13;\n" +
                "VhoTC9XdnKE03w4VXVyGQ47tJETSKNd6FdnLq6bA25loHJrBVye2BXNyf97HTn0=</X509Certificate></X509Data></KeyInfo></Signature></NFe></enviNFe>";
        String xmlRetAutorizacaoErr = null;


        String xmlReciboOK = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><consReciNFe versao=\"4.00\" xmlns=\"http://www.portalfiscal.inf.br/nfe\"><tpAmb>2</tpAmb><nRec>130000155390587</nRec></consReciNFe>";
        String xmlAssinadoOK = "<enviNFe xmlns=\"http://www.portalfiscal.inf.br/nfe\" versao=\"4.00\"><idLote>000000000000085</idLote><indSinc>0</indSinc><NFe><infNFe Id=\"NFe13200608009246000136550010000006151202020066\" versao=\"4.00\"><ide><cUF>13</cUF><cNF>20202006</cNF><natOp>VENDA Dentro Do Estado</natOp><mod>55</mod><serie>1</serie><nNF>615</nNF><dhEmi>2020-06-22T00:00:00-04:00</dhEmi><dhSaiEnt>2020-06-22T00:00:00-04:00</dhSaiEnt><tpNF>1</tpNF><idDest>1</idDest><cMunFG>1302603</cMunFG><tpImp>1</tpImp><tpEmis>1</tpEmis><cDV>6</cDV><tpAmb>2</tpAmb><finNFe>1</finNFe><indFinal>1</indFinal><indPres>3</indPres><procEmi>0</procEmi><verProc>2.02</verProc></ide><emit><CNPJ>08009246000136</CNPJ><xNome>T. L. MACEDO</xNome><xFant>CAFE PERFEITO</xFant><enderEmit><xLgr>R TREZE DE MAIO</xLgr><nro>159</nro><xBairro>COROADO</xBairro><cMun>1302603</cMun><xMun>MANAUS</xMun><UF>AM</UF><CEP>69080440</CEP><cPais>1058</cPais><xPais>BRASIL</xPais><fone>92981686148</fone></enderEmit><IE>042171865</IE><CRT>3</CRT></emit><dest><CNPJ>02844344000102</CNPJ><xNome>FUNDACAO AMAZONICA DE AMPARO A PESQUISA E DESENVOLVIMENTO TE</xNome><enderDest><xLgr>AV GOVERNADOR DANILO AREOSA</xLgr><nro>1170</nro><xCpl>LOTE 164</xCpl><xBairro>DISTRITO INDUSTRIAL I</xBairro><cMun>1302603</cMun><xMun>MANAUS</xMun><UF>AM</UF><CEP>69075351</CEP><cPais>1058</cPais><xPais>BRASIL</xPais><fone>9221239797</fone></enderDest><indIEDest>9</indIEDest><email>contabil@fpf.br</email></dest><det nItem=\"1\"><prod><cProd>1137</cProd><cEAN>7898903647025</cEAN><xProd>CAFE SUPREMO ARABICA TRADICIONAL 1 KG Lt[1244] val.:22/12/2019</xProd><NCM>09011110</NCM><CEST>1709602</CEST><CFOP>5102</CFOP><uCom>PCT</uCom><qCom>20.0000</qCom><vUnCom>46.0000000000</vUnCom><vProd>920.00</vProd><cEANTrib>7898903647025</cEANTrib><uTrib>PCT</uTrib><qTrib>20.0000</qTrib><vUnTrib>46.0000000000</vUnTrib><vDesc>60.00</vDesc><indTot>1</indTot></prod><imposto><ICMS><ICMS60><orig>0</orig><CST>60</CST></ICMS60></ICMS><PIS><PISNT><CST>06</CST></PISNT></PIS><COFINS><COFINSNT><CST>06</CST></COFINSNT></COFINS></imposto></det><det nItem=\"2\"><prod><cProd>1137</cProd><cEAN>7898903647025</cEAN><xProd>CAFE SUPREMO ARABICA TRADICIONAL 1 KG Lt[1249] val.:26/02/2020</xProd><NCM>09011110</NCM><CEST>1709602</CEST><CFOP>5102</CFOP><uCom>PCT</uCom><qCom>20.0000</qCom><vUnCom>46.0000000000</vUnCom><vProd>920.00</vProd><cEANTrib>7898903647025</cEANTrib><uTrib>PCT</uTrib><qTrib>20.0000</qTrib><vUnTrib>46.0000000000</vUnTrib><vDesc>60.00</vDesc><indTot>1</indTot></prod><imposto><ICMS><ICMS60><orig>0</orig><CST>60</CST></ICMS60></ICMS><PIS><PISNT><CST>06</CST></PISNT></PIS><COFINS><COFINSNT><CST>06</CST></COFINSNT></COFINS></imposto></det><det nItem=\"3\"><prod><cProd>1137</cProd><cEAN>7898903647025</cEAN><xProd>CAFE SUPREMO ARABICA TRADICIONAL 1 KG Lt[1250] val.:30/03/2020</xProd><NCM>09011110</NCM><CEST>1709602</CEST><CFOP>5102</CFOP><uCom>PCT</uCom><qCom>20.0000</qCom><vUnCom>46.0000000000</vUnCom><vProd>920.00</vProd><cEANTrib>7898903647025</cEANTrib><uTrib>PCT</uTrib><qTrib>20.0000</qTrib><vUnTrib>46.0000000000</vUnTrib><vDesc>60.00</vDesc><indTot>1</indTot></prod><imposto><ICMS><ICMS60><orig>0</orig><CST>60</CST></ICMS60></ICMS><PIS><PISNT><CST>06</CST></PISNT></PIS><COFINS><COFINSNT><CST>06</CST></COFINSNT></COFINS></imposto></det><det nItem=\"4\"><prod><cProd>3901</cProd><cEAN>7896343087555</cEAN><xProd>MEXEDOR PRAFESTA CAFE C/250 UND Lt[8755P] val.:31/12/2021</xProd><NCM>39241000</NCM><CEST>2803300</CEST><CFOP>5102</CFOP><uCom>PCT</uCom><qCom>20.0000</qCom><vUnCom>8.2500000000</vUnCom><vProd>165.00</vProd><cEANTrib>7896343087555</cEANTrib><uTrib>PCT</uTrib><qTrib>20.0000</qTrib><vUnTrib>8.2500000000</vUnTrib><indTot>1</indTot></prod><imposto><ICMS><ICMS60><orig>0</orig><CST>60</CST></ICMS60></ICMS><PIS><PISNT><CST>06</CST></PISNT></PIS><COFINS><COFINSNT><CST>06</CST></COFINSNT></COFINS></imposto></det><det nItem=\"5\"><prod><cProd>3902</cProd><cEAN>7896343087609</cEAN><xProd>MEXEDOR PRAFESTA DRINK C/200 UND Lt[8760P] val.:31/12/2021</xProd><NCM>39241000</NCM><CEST>2803300</CEST><CFOP>5102</CFOP><uCom>PCT</uCom><qCom>15.0000</qCom><vUnCom>15.0000000000</vUnCom><vProd>225.00</vProd><cEANTrib>7896343087609</cEANTrib><uTrib>PCT</uTrib><qTrib>15.0000</qTrib><vUnTrib>15.0000000000</vUnTrib><indTot>1</indTot></prod><imposto><ICMS><ICMS60><orig>0</orig><CST>60</CST></ICMS60></ICMS><PIS><PISNT><CST>06</CST></PISNT></PIS><COFINS><COFINSNT><CST>06</CST></COFINSNT></COFINS></imposto></det><det nItem=\"6\"><prod><cProd>1702</cProd><cEAN>7896433800019</cEAN><xProd>ACUCAR ITAMARATI 1 KG Lt[] val.:31/12/2020</xProd><NCM>17019900</NCM><CEST>2899900</CEST><CFOP>5102</CFOP><uCom>KG</uCom><qCom>60.0000</qCom><vUnCom>3.0100000000</vUnCom><vProd>180.60</vProd><cEANTrib>7896433800019</cEANTrib><uTrib>KG</uTrib><qTrib>60.0000</qTrib><vUnTrib>3.0100000000</vUnTrib><indTot>1</indTot></prod><imposto><ICMS><ICMS60><orig>0</orig><CST>60</CST></ICMS60></ICMS><PIS><PISNT><CST>06</CST></PISNT></PIS><COFINS><COFINSNT><CST>06</CST></COFINSNT></COFINS></imposto></det><det nItem=\"7\"><prod><cProd>1704</cProd><cEAN>7896804600453</cEAN><xProd>ACUCAR SACHE BOM SABOR 5 G C/336 UND Lt[1167080719] val.:31/07/2020</xProd><NCM>17019900</NCM><CEST>1709900</CEST><CFOP>5102</CFOP><uCom>CX</uCom><qCom>1.0000</qCom><vUnCom>40.0000000000</vUnCom><vProd>40.00</vProd><cEANTrib>7896804600453</cEANTrib><uTrib>CX</uTrib><qTrib>1.0000</qTrib><vUnTrib>40.0000000000</vUnTrib><indTot>1</indTot></prod><imposto><ICMS><ICMS60><orig>0</orig><CST>60</CST></ICMS60></ICMS><PIS><PISNT><CST>06</CST></PISNT></PIS><COFINS><COFINSNT><CST>06</CST></COFINSNT></COFINS></imposto></det><total><ICMSTot><vBC>0.00</vBC><vICMS>0.00</vICMS><vICMSDeson>0.00</vICMSDeson><vFCPUFDest>0.00</vFCPUFDest><vICMSUFDest>0.00</vICMSUFDest><vICMSUFRemet>0.00</vICMSUFRemet><vFCP>0.00</vFCP><vBCST>0.00</vBCST><vST>0.00</vST><vFCPST>0.00</vFCPST><vFCPSTRet>0.00</vFCPSTRet><vProd>3370.60</vProd><vFrete>0.00</vFrete><vSeg>0.00</vSeg><vDesc>180.00</vDesc><vII>0.00</vII><vIPI>0.00</vIPI><vIPIDevol>0.00</vIPIDevol><vPIS>0.00</vPIS><vCOFINS>0.00</vCOFINS><vOutro>0.00</vOutro><vNF>3190.60</vNF><vTotTrib>0.00</vTotTrib></ICMSTot></total><transp><modFrete>0</modFrete></transp><cobr><fat><nFat>615</nFat><vOrig>3370.60</vOrig><vDesc>180.00</vDesc><vLiq>3190.60</vLiq></fat></cobr><pag><detPag><indPag>1</indPag><tPag>99</tPag><vPag>3190.60</vPag></detPag></pag><infAdic><infCpl>TOTAL R$ 3.190,60*T*DT. VENC.:  DT. VENC.: 30/09/2019*N*DADOS BANCARIOS PARA TRANSFERENCIA*N*BANCO NUBANK AG: 0001 C/C: 90.846.088-3</infCpl></infAdic><infRespTec><CNPJ>08009246000136</CNPJ><xContato>THIAGO LEIROS MACEDO</xContato><email>tl.macedo@hotmail.com</email><fone>92981686148</fone></infRespTec></infNFe><Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\"><SignedInfo><CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/><SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"/><Reference URI=\"#NFe13200608009246000136550010000006151202020066\"><Transforms><Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/><Transform Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/></Transforms><DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"/><DigestValue>+H54RVKbEgNu5GNNENpGr6aa2lU=</DigestValue></Reference></SignedInfo><SignatureValue>pH3EHL3DE2mZBkjIzKrxBJr9UWfBSRZjpALEFhOOVp7ZvbC/ZGlep5Pu+v8bQVyVRvMIg9PZwEmV&#13;\n" +
                "VXaM7+s8OsLKd4r+LZe4Wc01jTDzI/1yqHeM0utCCAGLgewMctQakXK/qCgnw4DN5brTTQZT0QKI&#13;\n" +
                "USsnRVq+0xZyoAwcRTs12Nb37DA58jWnpE7AqC8X9ib32+sL2xCstkgr5OgWlnt+MZIgnTlJ8qi4&#13;\n" +
                "cNW/K1Hx0sSXQ+gjzsIWYkHuC2L4v2HgoETevFpTNopTHQo+fGvMcJVH8/DaSqmuovmuj/4mA8h/&#13;\n" +
                "b0QDG6ks/eFpjhO1Deo4uxjauqq/adoYUgRE5g==</SignatureValue><KeyInfo><X509Data><X509Certificate>MIIH9jCCBd6gAwIBAgIILOIUUKlZVGEwDQYJKoZIhvcNAQELBQAwdTELMAkGA1UEBhMCQlIxEzAR&#13;\n" +
                "BgNVBAoMCklDUC1CcmFzaWwxNjA0BgNVBAsMLVNlY3JldGFyaWEgZGEgUmVjZWl0YSBGZWRlcmFs&#13;\n" +
                "IGRvIEJyYXNpbCAtIFJGQjEZMBcGA1UEAwwQQUMgU0VSQVNBIFJGQiB2NTAeFw0yMDA0MTYyMTAw&#13;\n" +
                "MDBaFw0yMzA0MTYyMTAwMDBaMIIBGjELMAkGA1UEBhMCQlIxCzAJBgNVBAgMAkFNMQ8wDQYDVQQH&#13;\n" +
                "DAZNQU5BVVMxEzARBgNVBAoMCklDUC1CcmFzaWwxGDAWBgNVBAsMDzAwMDAwMTAwOTU4MDgzMzE2&#13;\n" +
                "MDQGA1UECwwtU2VjcmV0YXJpYSBkYSBSZWNlaXRhIEZlZGVyYWwgZG8gQnJhc2lsIC0gUkZCMRYw&#13;\n" +
                "FAYDVQQLDA1SRkIgZS1DTlBKIEEzMRkwFwYDVQQLDBBBQyBTRVJBU0EgUkZCIHY1MRcwFQYDVQQL&#13;\n" +
                "DA4wMTE0Njk1NzAwMDEwMzEWMBQGA1UECwwNQVIgVFJFVk9DSEVDSzEiMCAGA1UEAwwZVCBMIE1B&#13;\n" +
                "Q0VETzowODAwOTI0NjAwMDEzNjCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBALs8aZPR&#13;\n" +
                "eSRXREv9KovxzTwyttErAiPsgOYV6XG4ZpgsywK8HB15C8L2aw8X1LY5bx9xqMMCFWIxsjos5yJa&#13;\n" +
                "LlHrb5Urcr2+WHZ3gRLno2AbfGv6nRVyXCnuZaVwb3yCH70xDPyNsYnd/PcW4X7Bb6rqZ+kKj142&#13;\n" +
                "0A2PrHuynmvftPz8mHoLxhHCp0mJsQ4X8lMJTD57F3XUWgJDAwgxPSXNyUjq9d2fwigm46KU9Fdm&#13;\n" +
                "4TbysDrHiC5CybfAKEHewxQWKDyN1BBKCpwP41ooRC9rw/BemOwEIgvpUwTRX1LPBgb+bII9K0sC&#13;\n" +
                "7PH/4is6KlgHh4+bGDKMLpcOXaN4WcsCAwEAAaOCAuEwggLdMAkGA1UdEwQCMAAwHwYDVR0jBBgw&#13;\n" +
                "FoAU7PFBUVeo5jrpXrOgIvkIirU6h48wgZkGCCsGAQUFBwEBBIGMMIGJMEgGCCsGAQUFBzAChjxo&#13;\n" +
                "dHRwOi8vd3d3LmNlcnRpZmljYWRvZGlnaXRhbC5jb20uYnIvY2FkZWlhcy9zZXJhc2FyZmJ2NS5w&#13;\n" +
                "N2IwPQYIKwYBBQUHMAGGMWh0dHA6Ly9vY3NwLmNlcnRpZmljYWRvZGlnaXRhbC5jb20uYnIvc2Vy&#13;\n" +
                "YXNhcmZidjUwgbEGA1UdEQSBqTCBpoEVVEwuTUFDRURPQEhPVE1BSUwuQ09NoB8GBWBMAQMCoBYT&#13;\n" +
                "FFRISUFHTyBMRUlST1MgTUFDRURPoBkGBWBMAQMDoBATDjA4MDA5MjQ2MDAwMTM2oDgGBWBMAQME&#13;\n" +
                "oC8TLTAyMDQxOTgxNTIzMDk1NTAyMzAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMKAXBgVgTAED&#13;\n" +
                "B6AOEwwwMDAwMDAwMDAwMDAwcQYDVR0gBGowaDBmBgZgTAECAwowXDBaBggrBgEFBQcCARZOaHR0&#13;\n" +
                "cDovL3B1YmxpY2FjYW8uY2VydGlmaWNhZG9kaWdpdGFsLmNvbS5ici9yZXBvc2l0b3Jpby9kcGMv&#13;\n" +
                "ZGVjbGFyYWNhby1yZmIucGRmMB0GA1UdJQQWMBQGCCsGAQUFBwMCBggrBgEFBQcDBDCBnQYDVR0f&#13;\n" +
                "BIGVMIGSMEqgSKBGhkRodHRwOi8vd3d3LmNlcnRpZmljYWRvZGlnaXRhbC5jb20uYnIvcmVwb3Np&#13;\n" +
                "dG9yaW8vbGNyL3NlcmFzYXJmYnY1LmNybDBEoEKgQIY+aHR0cDovL2xjci5jZXJ0aWZpY2Fkb3Mu&#13;\n" +
                "Y29tLmJyL3JlcG9zaXRvcmlvL2xjci9zZXJhc2FyZmJ2NS5jcmwwHQYDVR0OBBYEFIIEYDiE2ogr&#13;\n" +
                "cC/XQd28cccn4ugOMA4GA1UdDwEB/wQEAwIF4DANBgkqhkiG9w0BAQsFAAOCAgEAghuyfpNLTb4H&#13;\n" +
                "tTSUjB5XyCNWlEwk8oDrBrm0QFVxpL7ekuCPYaltYkk2nap07RzyrIW+8ygL4P9P/9T4eX+pST6G&#13;\n" +
                "OS6EwrQN0IXRS8a05h60sbKEg0Yo0amFY7e/+4g8MfThUGiqlvif5XSOC0x/lQLZdiMDC1N9tqDP&#13;\n" +
                "zvfheW4MUtH7YnRNCbnKAP6kKHwiwzYoH0ZaoFUPoY/MEjE6xiJHahN6py79TSLSW3hXZq3zzWSs&#13;\n" +
                "tdLnm1zAVKmZLUsx6tM3dFnWz52f2V56j9par07uDqQBolebUZOFOgAJCiOCmuUjviDROnItkv5r&#13;\n" +
                "MfpZtaMVuKx2o2ay5wl6ebkXO4SmrIAmA4UuBrvY14lEBx2u9zX0IJddfddKHzN8GybHMcrJR601&#13;\n" +
                "tbMFFPusphX8Gj/ClnU94P0bvi8u4daJKQApZQr6lnwKZ3TWuNwpCPx6JhP+7BfSmbZEwDgiIYas&#13;\n" +
                "Wpe2d4HHhinzrIYEAgkcDJ72C6SJWDr7c8vBiXVxXVdmqykuMVg+8ANlsFnrd9MVxa65rY9Giwdy&#13;\n" +
                "6zcOBffaHLHcLR2nyCqkC6O8t2ZsKpR07fEXwy3loFKnujtpnVGMeGq7fKeXP5WSKAp4jeVOQfTl&#13;\n" +
                "VhoTC9XdnKE03w4VXVyGQ47tJETSKNd6FdnLq6bA25loHJrBVye2BXNyf97HTn0=</X509Certificate></X509Data></KeyInfo></Signature></NFe></enviNFe>";
        String xmlRetAutorizacaoOk = null;

        NFev400 nFev400 = new NFev400(null, MY_ZONE_TIME, false, true);
        if (nFev400.errNoCertificado())
            return;


//        nFev400.setXmlAssinado(xmlAssinadoOK);
//        nFev400.newNFev400_xmlConsRecibo(xmlReciboOK);


        nFev400.setXmlAssinado(xmlAssinadoErr);
        nFev400.newNFev400_xmlConsRecibo(xmlReciboErr);


        nFev400.exec_tarefas();

    }


}
