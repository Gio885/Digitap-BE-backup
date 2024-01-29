package com.exolab.repository;

import com.exolab.model.Area;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AreaRepository extends MongoRepository<Area, ObjectId> {

    /*Query Derivata, ovvero le query derivate di Spring Data MongoDB si basano sulla convenzione di denominazione dei metodi,
    quando viene dichiarato un metodo nell'interfaccia repository, spring data analizza il nome del metodo per interpretare
    quale query MongoDB deve essere eseguita.
    COSTRUZIONE NOME DEL METODO:
    Prefisso : findBy,findAll,deleteBy ecc..
    Proprieta : nome della proprieta dell'entita su cui eseguire la query
    Operatori : aggiunta di AND OR GREATERTHAN LESSTHAN per combinare condizioni
    Valori : i valori come parametro in input per filtrare i risultati
    Query personalizzata, utilizzando l'annotazione @Query
    @Query(value = "{ 'age' : { $gt : ?0 } }", fields = "{ 'firstName' : 1, 'lastName' : 1 }")
    dove il value e la condizione, ?0 segnaposto primo parametro in input
    fileds sono i campi che vengono restituiti come risultato, 1 true quindi per restituirli 0 false per non restituirli
    Condizioni:
    {campo : {$gt : valore} } seleziona documenti dove il campo e maggiore
    $gte maggiore o uguale
    $lt dove il campo e minore
    $lte minore uguale
    {campo : {$ne : "valore"}} seleziona i documenti dove il campo non e uguale al valore
    {campo: {$eq: "valore"}} seleziona i documenti dove il campo e uguale al valore
    {$and: [condizione1, condizione2]} seleziona i documenti che soddisfano entrambe le cond
    {$or: [condizione1, condizione2]} seleziona i documenti che soddisfano una delle due cond
    { campo: { $elemMatch: { chiave1: valore1, chiave2: valore2 } } } dove il campo e un array e le sue proprieta corrispondono ai valori
     */
    //NON SERVE SPECIFICARE IL VALUE NELL'ANNOTAZIONE QUERY IN QUANTO VIENE GIA SPECIFICATO ATTRAVERSO IL NOME DEL METODO, CHE VIENE
    //ANALIZZATO DIRETTAMENTE DA SPRING, QUINDI SA CHE DEVE FILTRARE I RISULTATI TRAMITE IL TIPO, OVVERO LA STRING CHE GLI VIENE PASSATA
    @Query(fields = "{'it.nome': 1,'tags': 1}")
    List<Area> findAllByType(String tipo);


}
