package org.acme.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import org.acme.service.model.User;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.util.Base64;

public class JWTUtils {
    private static final String RSA_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0K+OcS99FjDC6lhEgTplZ1kD7fpz5Hn6erO1J8tdaqWdrXcfo28vg3PC7fXfeeKbHfV2dV1Z64RZYWtXqZa+08JqMZlm876eieYcQ9AorpAwn5dpQ2nxVHnbG7VXFnscPuQ3+9oWsDpXgBxSzKUfzYLm4NJVr9BG218iSVdcdZ+IGXJzGxLHyV0dUpPXhMq7DZtt0Wh+Uu/tL/ci9NjUihHvy58ML2y3Pb92HMMZszoMQDje+A+J+o6klVp9Xc6Wjk1i7YXCv+1bU0NaaoXV0rlAVuhV2B45kPYOM6L9C0JJ3Cw4px+xLSydfuLlVJ+fsCZf+QUYmAMilaYVlRUrlwIDAQAB";

    private static final String X509_CERTIFICATE = "MIIClzCCAX8CBgGROwNHDDANBgkqhkiG9w0BAQsFADAPMQ0wCwYDVQQDDARkZW1vMB4XDTI0MDgxMDA2MzczNloXDTM0MDgxMDA2MzkxNlowDzENMAsGA1UEAwwEZGVtbzCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBANCvjnEvfRYwwupYRIE6ZWdZA+36c+R5+nqztSfLXWqlna13H6NvL4Nzwu3133nimx31dnVdWeuEWWFrV6mWvtPCajGZZvO+nonmHEPQKK6QMJ+XaUNp8VR52xu1VxZ7HD7kN/vaFrA6V4AcUsylH82C5uDSVa/QRttfIklXXHWfiBlycxsSx8ldHVKT14TKuw2bbdFoflLv7S/3IvTY1IoR78ufDC9stz2/dhzDGbM6DEA43vgPifqOpJVafV3Olo5NYu2Fwr/tW1NDWmqF1dK5QFboVdgeOZD2DjOi/QtCSdwsOKcfsS0snX7i5VSfn7AmX/kFGJgDIpWmFZUVK5cCAwEAATANBgkqhkiG9w0BAQsFAAOCAQEAX0rOizkoTiUtiXWarpy36CAAbZdBTi9iyjUu28XHJoaAbzuQWLJ0PbDYYoy20OM+YHUlwnLfx+hZ1Jdf3eJn55kulJ1kVY1Bj/6/j6PTvAwR2zvsWxuntLNfIJ2S0FFkORA1uhYMn6EIckL7L1qB4cAiNbfZmUKTbWtbmxPZNtzw6FMKnTKnP1IXiomFJTTvgZ4r9cMB5eRVT0H2rFCGzClDacNqlWRv0EIyQcOo2F5cethBGCB+DCTMg1BO8Ru/MseHP2WoZ71OpLy62Y4BLuWHDor9dzUJR/I6yz/2PaFX5+XoldZzjqhIfmJTCPlqr4H5txfsxoq6K7D1M8p8og==";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    public static User parseIdToken(String idToken) throws ParseException, IOException, JOSEException, NoSuchAlgorithmException, InvalidKeySpecException, CertificateException {
        JWSObject jwsObject = JWSObject.parse(idToken);
        JWSVerifier verifier = new RSASSAVerifier(getRSAPublicKey());
        if (!jwsObject.verify(verifier)) {
            throw new RuntimeException("Invalid token");
        }
        Payload payload = jwsObject.getPayload();
        // todo verify exp time
        return objectMapper.readValue(payload.toString(), User.class);
    }


    private static RSAPublicKey getRSAPublicKeyFromCert() throws CertificateException, IOException {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        ByteArrayInputStream bis = new ByteArrayInputStream(Base64.getDecoder().decode(X509_CERTIFICATE));
        X509Certificate cert = (X509Certificate) cf.generateCertificate(bis);
        bis.close();
        return (RSAPublicKey)cert.getPublicKey();
    }

    private static RSAPublicKey getRSAPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = Base64.getDecoder().decode(RSA_PUBLIC_KEY);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) kf.generatePublic(spec);
    }
}
