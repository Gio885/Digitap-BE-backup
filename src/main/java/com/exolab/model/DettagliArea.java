package com.exolab.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DettagliArea {

    private String nome;
    private String categoria;
    private String descrizione;
    private String storiaGeografia;
    private String floraFauna;
    private DettagliArea infoVisita;
    private String indirizzo;
    private String telefono;
    private String email;
    private String pec;
    private String sito;
    private String url;
    private DettagliArea serviziDisabili;
    private Integer tipologia;
    private String type;
    private List<Double> coordinates;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getStoriaGeografia() {
        return storiaGeografia;
    }

    public void setStoriaGeografia(String storiaGeografia) {
        this.storiaGeografia = storiaGeografia;
    }

    public String getFloraFauna() {
        return floraFauna;
    }

    public void setFloraFauna(String floraFauna) {
        this.floraFauna = floraFauna;
    }

    public DettagliArea getInfoVisita() {
        return infoVisita;
    }

    public void setInfoVisita(DettagliArea infoVisita) {
        this.infoVisita = infoVisita;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPec() {
        return pec;
    }

    public void setPec(String pec) {
        this.pec = pec;
    }

    public String getSito() {
        return sito;
    }

    public void setSito(String sito) {
        this.sito = sito;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DettagliArea getServiziDisabili() {
        return serviziDisabili;
    }

    public void setServiziDisabili(DettagliArea serviziDisabili) {
        this.serviziDisabili = serviziDisabili;
    }

    public Integer getTipologia() {
        return tipologia;
    }

    public void setTipologia(Integer tipologia) {
        this.tipologia = tipologia;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }
}
