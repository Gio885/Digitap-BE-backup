package com.exolab.dto;

import com.exolab.model.Area;
import com.exolab.model.DettagliArea;
import org.bson.types.ObjectId;

import java.util.List;

public class AreaDto extends Area {
    private String id;
    private String nome;
    private String categoria;
    private String descrizione;
    private String storiaGeografia;
    private String floraFauna;
    private String indirizzo;
    private String telefono;
    private String email;
    private String pec;
    private String sito;
    private String url;
    private Integer tipologia;
    private List<Double> coordinates;
    public AreaDto() {

    }
    public String getid() {
        return id;
    }

    public void setid(ObjectId id) {
        this.id = id.toHexString();
    }

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

    public Integer getTipologia() {
        return tipologia;
    }

    public void setTipologia(Integer tipologia) {
        this.tipologia = tipologia;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }
}

