package com.exolab.service.impl;

import com.exolab.model.Area;
import com.exolab.model.News;
import com.exolab.repository.AreaRepository;
import com.exolab.repository.NewsRepository;
import com.exolab.service.AreaService;
import com.exolab.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;
    @Override
    public List<News> findAllByTipoNotifica() {
        return newsRepository.findAllByTipoNotifica();}
}
