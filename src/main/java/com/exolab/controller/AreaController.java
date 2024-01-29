package com.exolab.controller;

import com.exolab.dto.AreaDto;
import com.exolab.service.AreaService;
import com.exolab.utils.Costanti;
import com.exolab.utils.EndPointRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = Costanti.LOCAL_HOST)
@RestController
@RequestMapping(EndPointRest.AREA_REST)
public class AreaController {

   @Autowired
   private AreaService areaService; //prova

    @GetMapping(EndPointRest.FIND_ALL_AREA)
    public ResponseEntity<List<AreaDto>> findAllAreaNoDetail(@RequestParam String language,@RequestParam String tipo){
        try{
            List<AreaDto> lista= areaService.findAllArea(language,tipo);
            return new ResponseEntity<List<AreaDto>>(lista, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<List<AreaDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(EndPointRest.FIND_ONE_AREA_BY_ID)
    public ResponseEntity<AreaDto> findAreaById(@RequestParam String language,@RequestParam String id){
        try{
            return new ResponseEntity<AreaDto>(areaService.findById(language,id),HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<AreaDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(EndPointRest.FIND_ONE_AREA_RANDOM)
    public ResponseEntity<AreaDto> findRandomArea(@RequestParam String language,Optional<String> tipo){
        try{
            return new ResponseEntity<AreaDto>(areaService.findRandomArea(language,tipo),HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<AreaDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(EndPointRest.FIND_ONE_AREA_BY_CODICE)
    public ResponseEntity<AreaDto> findAreaByCodice(@RequestParam String language,@RequestParam String codice){
        try{
            return new ResponseEntity<AreaDto>(areaService.findByCodice(language,codice),HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<AreaDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(EndPointRest.FIND_ALL_REGIONI)
    public ResponseEntity<String[]> findAllRegioni(){
        try{
            return new ResponseEntity<String[]>(Costanti.REGIONI,HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(EndPointRest.FIND_ALL_AREE_PER_REGIONI)
    public ResponseEntity<List<AreaDto>> findAllAreePerRegione(@RequestParam String language,@RequestParam String regione){
        System.out.println(regione);
        try{
            return new ResponseEntity<List<AreaDto>>(areaService.findAllAreePerRegione(language,regione),HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(EndPointRest.FIND_BY_RICERCA)
    public ResponseEntity<List<AreaDto>> ricercaAree (@RequestParam String language,@RequestParam String ricerca){
        try{
            List<AreaDto> risultatoAree = areaService.findByRicerca(language,ricerca);
            if(risultatoAree.isEmpty()){
                return new ResponseEntity<List<AreaDto>>(HttpStatus.NO_CONTENT);
            }else{
                return new ResponseEntity<List<AreaDto>>(areaService.findByRicerca(language,ricerca),HttpStatus.OK);
            }
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
