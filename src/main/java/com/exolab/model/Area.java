package com.exolab.model;

import com.exolab.utils.Costanti;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = Costanti.DOCUMENT_AREA)
public class Area {
    @Id
    private ObjectId id;
    private String codice;
    private DettagliArea it;
    private DettagliArea location;
    private String provincia;
    private String provvedimento;
    private String regione;
    private String superficieMare;
    private List<String> tags;
    private String type;
    private DettagliArea enteGestore;

    private DettagliArea en;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public DettagliArea getIt() {
        return it;
    }

    public void setIt(DettagliArea it) {
        this.it = it;
    }

    public DettagliArea getLocation() {
        return location;
    }

    public void setLocation(DettagliArea location) {
        this.location = location;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getProvvedimento() {
        return provvedimento;
    }

    public void setProvvedimento(String provvedimento) {
        this.provvedimento = provvedimento;
    }

    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public String getSuperficieMare() {
        return superficieMare;
    }

    public void setSuperficieMare(String superficieMare) {
        this.superficieMare = superficieMare;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DettagliArea getEnteGestore() {
        return enteGestore;
    }

    public void setEnteGestore(DettagliArea enteGestore) {
        this.enteGestore = enteGestore;
    }

    public DettagliArea getEn() {
        return en;
    }

    public void setEn(DettagliArea en) {
        this.en = en;
    }
}

