package com.exolab.service;

import com.exolab.model.News;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface NewsService {

    List<News> findAllByTipoNotifica();
}
