package org.example.getioncommande.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;


@Value
@Data
@Builder
public class CommandeDto implements Serializable {
    int idCommande;
    Date dateCommande;
    String status;
    double total;
    Long clientIdClient;

}