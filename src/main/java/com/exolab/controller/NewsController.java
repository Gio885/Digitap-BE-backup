package com.exolab.controller;

import com.exolab.model.Area;
import com.exolab.model.News;
import com.exolab.service.NewsService;
import com.exolab.utils.Costanti;
import com.exolab.utils.EndPointRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = Costanti.LOCAL_HOST)
@RestController
@RequestMapping(EndPointRest.NEWS_REST)
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping(EndPointRest.NEWS_FIND_ALL)
    public ResponseEntity<List<News>> findAllNews(){
        try{
            return new ResponseEntity<List<News>>(newsService.findAllByTipoNotifica(), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
