package org.djr.cdi.properties.decrypt;

import org.apache.commons.codec.binary.Base64;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@ApplicationScoped
@Named("DefaultDecryptor")
public class DefaultDecryptor implements Decryptor {
    @Override
    public String decrypt(String toDecrypt) {
        Base64.decodeBase64(toDecrypt);
        return new String(Base64.decodeBase64(toDecrypt));
    }
}
