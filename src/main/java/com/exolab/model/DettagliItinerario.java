package com.exolab.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DettagliItinerario {

    private String indicazione;
    private String descStatus;
    private String puntiInteresse;
    private String partenza;
    private String descrizione;
    private String normativa;
    private String divieto;
    private String descDislivello;
    private String nome;
    private String difficolta;

    public String getIndicazione() {
        return indicazione;
    }

    public void setIndicazione(String indicazione) {
        this.indicazione = indicazione;
    }

    public String getDescStatus() {
        return descStatus;
    }

    public void setDescStatus(String descStatus) {
        this.descStatus = descStatus;
    }

    public String getPuntiInteresse() {
        return puntiInteresse;
    }

    public void setPuntiInteresse(String puntiInteresse) {
        this.puntiInteresse = puntiInteresse;
    }

    public String getPartenza() {
        return partenza;
    }

    public void setPartenza(String partenza) {
        this.partenza = partenza;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getNormativa() {
        return normativa;
    }

    public void setNormativa(String normativa) {
        this.normativa = normativa;
    }

    public String getDivieto() {
        return divieto;
    }

    public void setDivieto(String divieto) {
        this.divieto = divieto;
    }

    public String getDescDislivello() {
        return descDislivello;
    }

    public void setDescDislivello(String descDislivello) {
        this.descDislivello = descDislivello;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDifficolta() {
        return difficolta;
    }

    public void setDifficolta(String difficolta) {
        this.difficolta = difficolta;
    }
}
