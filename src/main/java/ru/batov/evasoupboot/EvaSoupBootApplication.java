package ru.batov.evasoupboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.batov.evasoupboot.dao.DirectionDao;
import ru.batov.evasoupboot.services.Main;

@SpringBootApplication
public class EvaSoupBootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(EvaSoupBootApplication.class, args);
        DirectionDao bean = run.getBean(DirectionDao.class);
        bean.getCountDirectionByUrl("");
    }

}
