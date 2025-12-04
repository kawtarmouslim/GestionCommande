package org.example.getioncommande.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;
import org.example.getioncommande.entites.CommandeLigne;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;



public class CommandeDto implements Serializable {
    private Long idCommande;
    private Date dateCommande;
    private String status;
    private Double total;
    private Long clientIdClient;                    // ID du client
    private List<CommandeLigneDto> lignes = new ArrayList<>();

    public Long getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Long idCommande) {
        this.idCommande = idCommande;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Long getClientIdClient() {
        return clientIdClient;
    }

    public void setClientIdClient(Long clientIdClient) {
        this.clientIdClient = clientIdClient;
    }

    public List<CommandeLigneDto> getLignes() {
        return lignes;
    }

    public void setLignes(List<CommandeLigneDto> lignes) {
        this.lignes = lignes;
    }
}