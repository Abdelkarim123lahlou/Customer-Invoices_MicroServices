package org.openlab.openlabcustmerservice;

import org.openlab.openlabcustmerservice.dto.CustomerRequestDTO;
import org.openlab.openlabcustmerservice.services.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OpenlabcustmerserviceApplication {

    public static void main(String[] args) {

        SpringApplication.run(OpenlabcustmerserviceApplication.class, args);
    }
  @Bean
    CommandLineRunner start(CustomerService customerService){
        return  args -> {
            customerService.save(new CustomerRequestDTO("C01","EAI","EAI@gmail.com"));
            customerService.save(new CustomerRequestDTO("C02","Berexia","Ber@Berexia.com"));
        };
    }
}
