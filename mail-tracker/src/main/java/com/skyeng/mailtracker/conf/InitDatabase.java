package com.skyeng.mailtracker.conf;


import com.skyeng.mailtracker.model.PostOffice;
import com.skyeng.mailtracker.model.postalitem.Item;
import com.skyeng.mailtracker.model.postalitem.ItemType;
import com.skyeng.mailtracker.service.PostalItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class InitDatabase {

    private static final Logger log = LoggerFactory.getLogger(InitDatabase.class);

    @Bean
    CommandLineRunner init(PostalItemService service) {

        return args -> {
            log.info("init data  " + service.register(Item.builder()
                    .recipientIndex(5432167890L)
                    .receiverName("Vladimir")
                    .recipientAddress("Volzhskiy Druzhby Ul bld 121 appt 160")
                    .postOffice(PostOffice.builder().id(1L).build())
                    .type(ItemType.builder().id(1L)
                            .build()).build()));
        };
    }
}