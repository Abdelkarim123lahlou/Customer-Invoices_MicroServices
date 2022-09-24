package com.invoiceservice.training;

import com.invoiceservice.training.dto.InvoiceRequestDTO;
import com.invoiceservice.training.services.InvoiceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
@EnableFeignClients
public class TrainingApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainingApplication.class, args);
    }
    @Bean
  CommandLineRunner commandLineRunner(InvoiceService invoiceService){
        return  args -> {
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(1452),"C01"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(95800),"C01"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(9000),"C02"));
        };
  }
}
