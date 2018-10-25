package com.luciano.oauth.demooauth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * Created by koerich on 27/02/17.
 */
@Component
public class KeyConfiguration {

	@Value("${server.ssl.key-store}")
	private String KEYSTORE;
	
	@Value("${server.ssl.key-alias}")
	private String KEY_ALIAS;
	
	@Value("${server.ssl.key-store-password}")
	private String KEYSTORE_PASSWORD;
	
	@Value("${server.ssl.key-password}")
	private String KEY_PASSWORD;

	public PrivateKey getPrivate() {

		PrivateKey privateKey = null;
		try (FileInputStream is = new FileInputStream(ResourceUtils.getFile(KEYSTORE))) {
			
			KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
			keystore.load(is, KEYSTORE_PASSWORD.toCharArray());
			Key key = keystore.getKey(KEY_ALIAS,KEY_PASSWORD.toCharArray());
			privateKey = (PrivateKey) key;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return privateKey;

	}

	public PublicKey getPublic() {
		PublicKey publicKey = null;
		String publicKeyString = null;
		try (FileInputStream is = new FileInputStream(ResourceUtils.getFile(KEYSTORE))) {
			
			KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
			keystore.load(is, KEYSTORE_PASSWORD.toCharArray());
			Certificate cert = keystore.getCertificate(KEY_ALIAS);
			publicKey = cert.getPublicKey();

			
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			publicKeyString = savePublicKey(publicKey);
			System.out.println(publicKeyString);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}

		return publicKey;
	}

	public String savePublicKey(PublicKey publ) throws GeneralSecurityException {
		KeyFactory fact = KeyFactory.getInstance("RSA");
		X509EncodedKeySpec spec = fact.getKeySpec(publ,
				X509EncodedKeySpec.class);
		return Base64.getEncoder().encodeToString(spec.getEncoded());
	}
}
