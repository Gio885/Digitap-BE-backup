package com.exolab.repository;

import com.exolab.model.News;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface NewsRepository extends MongoRepository<News, ObjectId> {

    @Query(value ="{tipoNotifica: 'NEWS'}",fields = "{'titolo': 1,'descrizioneBreve': 1,'data': 1}")
    List<News> findAllByTipoNotifica();
}
