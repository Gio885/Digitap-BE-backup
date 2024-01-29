package com.exolab.model;


import com.exolab.utils.Costanti;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = Costanti.DOCUMENT_ITINERARIO)
public class Itinerario {
    @Id
    private ObjectId id;
    private String codice;
    private String pathId;
    private String dislivello;
    private Integer idDifficolta;
    private DettagliItinerario it;
    private Integer lunghezza;
    private Integer status;
    private Integer tMedioPerc;
    private String tipologia;
    private DettagliItinerario en;

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

    public String getPathId() {
        return pathId;
    }

    public void setPathId(String pathId) {
        this.pathId = pathId;
    }

    public String getDislivello() {
        return dislivello;
    }

    public void setDislivello(String dislivello) {
        this.dislivello = dislivello;
    }

    public Integer getIdDifficolta() {
        return idDifficolta;
    }

    public void setIdDifficolta(Integer idDifficolta) {
        this.idDifficolta = idDifficolta;
    }

    public DettagliItinerario getIt() {
        return it;
    }

    public void setIt(DettagliItinerario it) {
        this.it = it;
    }

    public Integer getLunghezza() {
        return lunghezza;
    }

    public void setLunghezza(Integer lunghezza) {
        this.lunghezza = lunghezza;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer gettMedioPerc() {
        return tMedioPerc;
    }

    public void settMedioPerc(Integer tMedioPerc) {
        this.tMedioPerc = tMedioPerc;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public DettagliItinerario getEn() {
        return en;
    }

    public void setEn(DettagliItinerario en) {
        this.en = en;
    }
}
