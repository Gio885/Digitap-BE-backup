package com.exolab.service.impl;

import ch.qos.logback.classic.pattern.Util;
import com.exolab.dto.AreaDto;
import com.exolab.model.Area;
import com.exolab.repository.AreaRepository;
import com.exolab.service.AreaService;
import com.exolab.utils.Convertitore;
import com.exolab.utils.Costanti;
import com.exolab.utils.Utils;
import com.mongodb.BasicDBObject;
import com.mongodb.client.model.Filters;
import org.bson.BSONObject;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.MongoExpression;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class AreaServiceImpl implements AreaService {

    // MongoTemplate e una classe fornita da spring data MongoDB che semplifica l'interazione con un db mongo
    // viene utilizzato per operazioni di Crud insert update remove. Supporta query avanzate con l'utilizzo di query e criteria,
    //operazioni di aggragazione. Per l' utilizzo di mongoTemplate occorre avere una classe di configurazione che estende AbstractMongoClientConfiguration
    // che e una classe che fornisce una configurazione di base per il client mongo.Per avere un istanza di mongoTemplate è necessario
    //configurare il mongoclient, la classe AbstractMongoClientConfiguration gestisce la creazione del client in modo automatico.
    //Non e stato necessario definire nella classe config un metodo annotato con @bean che mi restitusse un istanza MongoTemplate(MONGO CLIENT, GET DB NAME)
    private final MongoTemplate mongoTemplate;
    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    public AreaServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /*
    stream() libreria utilizzata per eseguire operazioni sugli elementi, uno stream rappresenta una sequenza di elementi
    il map trasforma viene utilizzato per trasformare un oggetto applicandolo ad un funzione,  itero sulla lista e prendo ogni singolo elemento passandolo al convertitore,
    raccolgo il risultato tramite collect, colect trasforma gli elementi dello stream in una collezione. Collectors.toList crea una lista in cui vengono raccolti gli elementi
    -> lambda expression vengono utilizzate per creare oggeti di interfacce funzionali ? APPROFONDIRE
    :: Method reference per rifersi a un metodo esistente solitamente metodo di classe o un costruttore es. (String :: toUpperCase())
       (IL METODO toList usa methood reference per creare una lista facendo (ArrayList::new, List::add,(left, right) -> { left.addAll(right); return left; },CH_ID);
    OPERAZIONI INTERMEDIE SU STREAM
    filter/map/flatMap/sorted/distinct
    OPERAZIONI TERMINALI SU STREAM
    forEach/collect/reduce/count/anyMatch allMatch noneMatch / findFirst findAny
    Gli stream eseguono operazioni in modo pigro(Lazy) il che significa che le operazioni non vengono eseguito fino a quando non
    viene invocata un OPERAZIONE TERMINALE
    */

    //@Override
    public List<AreaDto> findAllArea2(String tipo) {
        List<Area> listaAree = areaRepository.findAllByType(tipo);
        List<AreaDto> listaAreeDto =
                listaAree.stream().map(area -> new Convertitore().convertAreaModelToAreaDtoNoDetail(area)).collect(Collectors.toList());
        return listaAreeDto;
        //UTILIZZO MAP E NON FOR EACH PERCHE DEVO TRASFORMARE OGNI ELEMENTO DELLO STREAM APPLICANDO UNA FUNZIONE E RESTITUISCE UNO
        //STREAM DI ELEMENTI TRASFORMATI CHE POI RACCOLGO IN UNA COLLEZIONE
        //FOR EACH E UN OPERAZIONE TERMINALE CHE ESEGUE UN OPERAZIONE SU OGNI ELEMENTO DELLA LISTA MA NON RESTITUISCE NULLA
    }

    @Override
    public List<AreaDto> findAllArea(String language,String tipo) {
        /*
        Projection Operation consente di plasmare i documenti risultanti selezionando o escludendo specifici campi
        Potrei tirarmi fuori il campo dentro dettagli assegnarlo ad una string e passarlo ai risultati delle area andandolo
        ad inserire dentro l'oggetto it o altrimenti mi tiro fuori tutto it e basta
        senno posso passare la query sopra il metodo a mongo template
         */
        ProjectionOperation projectionOperation = Aggregation.project( "tags","codice").and(ConditionalOperators.ifNull(language+".nome").thenValueOf("it.nome")).as(language+".nome");
        ReplaceRootOperation replaceRootOperation = Aggregation.replaceRoot().withValueOf(ObjectOperators.valueOf(language).mergeWith(new Utils().createDocument("codice","tags","_id")));

        /*
        MATCH OPERATION VIENE UTILIZZATO PER DEFINIRE LA FASE DI $match basata sul criterio Criteria.where ("type").is(tipo)
        MatchOperation matchOperation = Aggregation.match(Criteria.where("type").is(tipo));
        Aggregation aggregation = Aggregation.newAggregation(
        matchOperation,
        projectionOperation);
         */
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("type").is(tipo)),
                projectionOperation,replaceRootOperation);
        return  mongoTemplate.aggregate(aggregation, mongoTemplate.getCollectionName(Area.class), AreaDto.class).getMappedResults();
        //aggregate metodo che restituisce un AggregationResult, prende 3 parametri in inpunt ( AGGREGAZIONE , COLLEZIONE , RISULTATO)
    }


    @Override
    public AreaDto findByCodice(String language,String codice) {
        ProjectionOperation projectionOperation = Aggregation.project("codice","type").and(ConditionalOperators.ifNull(language+".nome").thenValueOf("it.nome")).as(language+".nome").and(ConditionalOperators.ifNull(language+".descrizione").thenValueOf("it.descrizione")).as(language+".descrizione");
        ReplaceRootOperation replaceRootOperation = Aggregation.replaceRoot().withValueOf(ObjectOperators.valueOf(language).mergeWith(new Utils().createDocument("codice","nome","descrizione","type","_id")));
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(Criteria.where("codice").is(codice)),projectionOperation,replaceRootOperation);
        return mongoTemplate.aggregate(aggregation,mongoTemplate.getCollectionName(Area.class),AreaDto.class).getUniqueMappedResult();
        //o utilizzo getUniqueMappedResult che mi restituisce il risultato mappato oppure se l'ometto tiro fuori AggregationResult<AreaDto>
    }

    @Override
    public AreaDto findById(String language,String id) {
        ObjectId objectId = new ObjectId(id);
        MatchOperation matchOperation = Aggregation.match(Criteria.where("_id").is(objectId));
        ProjectionOperation projectionOperation = Aggregation.project("codice","location","provincia","regione","enteGestore","type").and(ConditionalOperators.ifNull(language).thenValueOf("it")).as(language);
        ReplaceRootOperation replaceRootOperation = Aggregation.replaceRoot().withValueOf(ObjectOperators.valueOf(language).mergeWith(new Utils().createDocument("codice","location","provincia","regione","enteGestore","type","coordinates","_id")));
        /*ProjectionOperation projectionOperation1 = Aggregation.project().andExclude("en");   HO TOLTO LA PROIEZIONE PERCHE NEL REPLACE ROOT ANZICHE FARE IL MERGE OBJECT TRA
        ROOTS E EN, FACCIO IL MERGE TRA I CAMPI CHE PRENDO DAL ROOTS E EN.*/
        Aggregation aggregation = Aggregation.newAggregation(matchOperation,projectionOperation,replaceRootOperation);
        return mongoTemplate.aggregate(aggregation,mongoTemplate.getCollectionName(Area.class),AreaDto.class).getUniqueMappedResult();
        //merge object perche se il campo en non e presente fa il merge sulla lingua it react i 18n
    }

    /*
    @Override
    public AreaDto findById(String id) {
        ObjectId objectId = new ObjectId(id);
        LookupOperation lookupOperation = Aggregation.lookup("luoghi_amp","codice","codice","lista");
        MatchOperation matchOperation = Aggregation.match(Criteria.where("_id").is(id));
        Aggregation aggregation = Aggregation.newAggregation(matchOperation,lookupOperation);
        return mongoTemplate.aggregate(aggregation,Area.class,AreaDto.class).getUniqueMappedResult();
    }
     ProjectionOperation projectionOperation = Aggregation.project()
                .and(
                        new AggregationExpression() {
                            @Override
                            public Document toDocument(AggregationOperationContext aggregationOperationContext) {
                                return new Document("en",
                                        new Document("$ifNull", Arrays.asList("$en", "$it")));
                            }
                        }
                    ).as("en");
*/
    @Override
    public AreaDto findRandomArea(String language,Optional<String>tipo) {
        /*
    $project
        Project operation e un operazione che viene utilizzata per la creazione di una proiezione in cui andiamo a specificare
        quali campi devo essere inclusi o esclusi dalla proiezione
        Criteria viene utilizzato per definire dei criteri per filtrare i documenti, in questo caso sto creando un condizione basata
        su un espressione regolare dove ^ indica l'inizio di una stringa EUAP e la parte della regex che stiamo cercando.
        E CASE SENSITIVE SE NON UTILIZZIAMO L'OPZIONE I ---- regex("^euap","i") NON E CASE SENSITIVE
        Verifica se il tipo opzionale è presente, se presente viene aggiunto un criterio per filtrare i documenti
    $match
        Match Operation viene utilizzato per creare un operazione di match secondi i criteri definiti dall'oggetto criteria
    $sort
        Utilizzato per ordinare i risultati in questo caso al codice campo in ordine ascendente, cosa che non mi serve
    $sample
        Utilizzato per ottenere un documento casuale tra i documenti risultanti
        Aggregation utilizzato per unire tutte le operazioni (STAGE) definiti sopra.
        Questa e una "pipeline" di aggregazione, ovvero a una sequenza di processi o operazioni che vengono eseguite in successione su un insieme di dati.
        l'aggregazione è il processo di elaborazione dei dati che consente di trasformare e manipolare i dati nei documenti di una collezione.
        l'esecuzione dell'aggregazione viene eseguita da mongoTemplate, in cui specifichiamo aggregazione, su quale collezione (class in input)
        e output di uscita (class di output)
    $replaceRoot
        { $replaceRoot: { newRoot: { $mergeObjects: [ { _id: "$_id", first: "", last: "" }, "$name" ] } } }
        replaceRoot ObjectOperation.valueOf("nome campo") mergewith createDocument con i campi che voglio selezionare     \\$$ROOT prende tutto il documento\\
        in questo caso prendo il campo it o en che contiene proprieta che vengono portate tutto a primo livello e poi faccio il merge con i campi che voglio prendere dal documento
         */
        ProjectionOperation projectionOperation = Aggregation.project("codice","type").and(ConditionalOperators.ifNull(language+".nome").thenValueOf("it.nome")).as(language+".nome").and(ConditionalOperators.ifNull(language+".descrizione").thenValueOf("it.descrizione")).as(language+".descrizione");
        Criteria criteria = Criteria.where("codice").regex("^EUAP"); //regex("^ooo","i") REGEX CASE IN-SENSITIVE
        if(tipo.isPresent()){
            criteria = criteria.and("type"). is(tipo.get()); // .get per prendere l'oggetto wrappato dentro optional
        }
        MatchOperation matchOperation = Aggregation.match(criteria);
        SortOperation sortOperation = Aggregation.sort(Sort.Direction.ASC,"codice");
        SampleOperation sample = Aggregation.sample(1);
        ReplaceRootOperation replaceRootOperation = Aggregation.replaceRoot().withValueOf(ObjectOperators.valueOf(language).mergeWith(new Utils().createDocument("codice","nome","descrizione","type","_id")));
        Aggregation aggregation = Aggregation.newAggregation(matchOperation,sortOperation,sample,projectionOperation,replaceRootOperation);
        return mongoTemplate.aggregate(aggregation,mongoTemplate.getCollectionName(Area.class),AreaDto.class).getUniqueMappedResult();
    }
    @Override
    public List<AreaDto> findAllAreePerRegione(String language,String regione) {
        ProjectionOperation projectionOperation = Aggregation.project("codice","type").and(ConditionalOperators.ifNull(language+".nome").thenValueOf("it.nome")).as(language+".nome").and(ConditionalOperators.ifNull(language+".descrizione").thenValueOf("it.descrizione")).as(language+".descrizione");
        Criteria criteria = Criteria.where("regione").regex(regione,"i");
        MatchOperation matchOperation = Aggregation.match(criteria);
        ReplaceRootOperation replaceRootOperation = Aggregation.replaceRoot().withValueOf(ObjectOperators.valueOf(language).mergeWith(new Utils().createDocument("codice","nome","descrizione","type","_id")));
        Aggregation aggregation = Aggregation.newAggregation(matchOperation,projectionOperation,replaceRootOperation);
        List<AreaDto> lista =  mongoTemplate.aggregate(aggregation,mongoTemplate.getCollectionName(Area.class),AreaDto.class).getMappedResults();
        return lista;
    }

    @Override
    public List<AreaDto> findByRicerca(String language, String ricerca) {
        ProjectionOperation projectionOperation = Aggregation.project("codice","type").and(ConditionalOperators.ifNull(language+".nome").thenValueOf("it.nome")).as(language+".nome").and(ConditionalOperators.ifNull(language+".descrizione").thenValueOf("it.descrizione")).as(language+".descrizione");
        Criteria criteria1 = Criteria.where(language + ".nome").regex(ricerca, "i");
        Criteria criteria2 = Criteria.where(language + ".descrizione").regex(ricerca, "i");
        Criteria criteria3 = Criteria.where("regione").regex(ricerca, "i");
        MatchOperation matchOperation = Aggregation.match(new Criteria().orOperator(criteria1, criteria2,criteria3));
        ReplaceRootOperation replaceRootOperation = Aggregation.replaceRoot().withValueOf(ObjectOperators.valueOf(language).mergeWith(new Utils().createDocument("codice","nome","descrizione","type","_id")));
        Aggregation aggregation = Aggregation.newAggregation(matchOperation,projectionOperation,replaceRootOperation);
        List<AreaDto> lista =  mongoTemplate.aggregate(aggregation,mongoTemplate.getCollectionName(Area.class),AreaDto.class).getMappedResults();
        return lista;
    }
}