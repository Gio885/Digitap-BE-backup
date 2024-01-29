package com.exolab.service.impl;

import com.exolab.dto.AreaDto;
import com.exolab.dto.ItinerarioDto;
import com.exolab.model.Area;
import com.exolab.model.Itinerario;
import com.exolab.service.ItinerarioService;
import com.exolab.utils.Costanti;
import com.exolab.utils.Utils;
import com.mongodb.client.DistinctIterable;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItinerarioServiceImpl implements ItinerarioService {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ItinerarioServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<ItinerarioDto> findAllItinerariParco(String language,String codice) {
        MatchOperation matchOperation = Aggregation.match(Criteria.where("codice").is(codice));
        ProjectionOperation projectionOperation = Aggregation.project("_id","codice","pathId","dislivello","idDifficolta","lunghezza","status","tMedioPerc","tipologia").and(ConditionalOperators.ifNull(language).thenValueOf("it")).as(language);
        ReplaceRootOperation replaceRootOperation = Aggregation.replaceRoot().withValueOf(ObjectOperators.valueOf(language).mergeWith(new Utils().createDocument("_id","codice","pathId","dislivello","idDifficolta","lunghezza","status","tMedioPerc","tipologia")));
        Aggregation aggregation = Aggregation.newAggregation(matchOperation,projectionOperation,replaceRootOperation);
        List<ItinerarioDto> listaItinerariParco = mongoTemplate.aggregate(aggregation, mongoTemplate.getCollectionName(Itinerario.class), ItinerarioDto.class).getMappedResults();
        return listaItinerariParco;
    }

    //QUESTO METODO NON VIENE USATO SU FE, UTILIZZO FIND ALL ITINERARI PARCO PER TIRARE FUORI GLI ITINERARI DI UN PARTO NEL COMPONENTE ITINERARIO
    @Override
    public AreaDto findParcoFromItinerario(String id) {
        LookupOperation lookupOperation = Aggregation.lookup(mongoTemplate.getCollectionName(Area.class),"codice","codice","parco");
        MatchOperation matchOperation = Aggregation.match(Criteria.where("_id").is(new ObjectId(id)));
        Aggregation aggregation = Aggregation.newAggregation(lookupOperation,matchOperation);
        AreaDto areaDto = mongoTemplate.aggregate(aggregation,mongoTemplate.getCollectionName(Itinerario.class),AreaDto.class).getUniqueMappedResult();
        return areaDto;
    }


}

