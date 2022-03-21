package xades4j;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import xades4j.algorithms.ExclusiveCanonicalXMLWithoutComments;
import xades4j.algorithms.XPath2FilterTransform;
import xades4j.production.DataObjectReference;
import xades4j.production.Enveloped;
import xades4j.production.SignatureAlgorithms;
import xades4j.production.SignerTestBase;
import xades4j.production.XadesBesSigningProfile;
import xades4j.production.XadesSigner;
import xades4j.production.XadesSigningProfile;
import xades4j.production.XadesTSigningProfile;
import xades4j.properties.DataObjectDesc;
import xades4j.providers.KeyingDataProvider;
import xades4j.providers.impl.DirectPasswordProvider;
import xades4j.providers.impl.FileSystemKeyStoreKeyingDataProvider;
import xades4j.providers.impl.HttpTsaConfiguration;

public class Main extends SignerTestBase{

	public static void main(String[] args) {
		
		try {
			
			System.out.println("INICIA");
			Document doc = getDocument("document.xml");
	        Element elemToSign = doc.getDocumentElement();

	        SignatureAlgorithms algorithms = new SignatureAlgorithms()
	                .withCanonicalizationAlgorithmForTimeStampProperties(new ExclusiveCanonicalXMLWithoutComments("ds", "xades"))
	                .withCanonicalizationAlgorithmForSignature(new ExclusiveCanonicalXMLWithoutComments());

	        XadesSigner signer = new XadesTSigningProfile(keyingProviderMy)
	                .withSignatureAlgorithms(algorithms)
	                .with(new HttpTsaConfiguration("http://timestamp.digicert.com"))
	                .newSigner();
	        new Enveloped(signer).sign(elemToSign);
	        
	        outputDocument(doc, "document.signed.t.bes2.xml");
			
	        System.out.println("Termina");
		} catch (Exception e) {
			System.out.println("ERROR "+e);
			e.printStackTrace();
		}
	}

}
