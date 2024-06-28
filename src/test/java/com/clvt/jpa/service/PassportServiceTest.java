package com.clvt.jpa.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@SpringBootTest
public class PassportServiceTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void getRandomSequence(int numOfChar) {
        Random random = new Random();
        StringBuilder seq = new StringBuilder();
//        char c = (char) (random.nextInt(26)+'a');
        for (int i = 1; i <= numOfChar; i++)
            seq.append((char) (random.nextInt(26) + 'a'));
        logger.info("random seq : {} ",seq);


    }
}
