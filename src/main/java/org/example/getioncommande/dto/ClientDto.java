package org.example.getioncommande.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

@Data
@Getter
@Setter
public class ClientDto implements Serializable {
    Long idClient;
    String nom;
    String prenom;
    String adresse;

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    String tel;
}