package ru.batov.evasoupboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.batov.evasoupboot.dao.DirectionDao;
import ru.batov.evasoupboot.services.BrowserParsing;

@SpringBootApplication
public class EvaSoupBootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(EvaSoupBootApplication.class, args);
        String s = run.getBean(BrowserParsing.class).getHistoryDirection("http://dbs/eva/Documents/History/fbf7974a-1289-4a5a-824e-35983331a1a0");
        System.out.println(s);
    }

}
