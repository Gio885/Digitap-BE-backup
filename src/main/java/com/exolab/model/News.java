package com.exolab.model;

import com.exolab.utils.Costanti;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = Costanti.DOCUMENT_NEWS)
public class News {

    @Id
    private ObjectId id;
    private String titolo;
    private String tipoNotifica;
    private String codice;
    private String testoNotifica;
    private String descrizioneBreve;
    private Date data;

    public ObjectId getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getTipoNotifica() {
        return tipoNotifica;
    }

    public String getCodice() {
        return codice;
    }

    public String getTestoNotifica() {
        return testoNotifica;
    }

    public String getDescrizioneBreve() {
        return descrizioneBreve;
    }

    public Date getData() {
        return data;
    }
}
