package com.invoiceservice.training.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Customer {
    private String id;
    private String nom;
    private String email;
}
