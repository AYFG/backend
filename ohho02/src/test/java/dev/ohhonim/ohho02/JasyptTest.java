package dev.ohhonim.ohho02;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class JasyptTest {

    @Test
    public void jasyptTest() {
        String value = "12341234!";
        String result = jasyptEncoding(value);

        log.debug("result : {}", result);
    }

    public String jasyptEncoding(String value) {
        String key = "verysecret";

        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWITHMD5ANDDES");
        pbeEnc.setPassword(key);
        return pbeEnc.encrypt(value);
    }
}
