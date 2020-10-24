package com.home.bcrypt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BCryptTest {

    @Test
    public void testBCrypt() {
        // 第二个参数随机生成的盐
        String hashpw = BCrypt.hashpw("secret", BCrypt.gensalt());
        String hashpw2 = BCrypt.hashpw("123", BCrypt.gensalt());
        // 每次生成的加密串不一样
        System.out.println("hashpw = " + hashpw);
        System.out.println("hashpw2 = " + hashpw2);
        System.out.println(BCrypt.checkpw("123", hashpw));
        System.out.println(BCrypt.checkpw("123", hashpw2));
    }
}
