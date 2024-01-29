package com.exolab.controller;

import com.exolab.dto.AreaDto;
import com.exolab.dto.ItinerarioDto;
import com.exolab.service.ItinerarioService;
import com.exolab.utils.Costanti;
import com.exolab.utils.EndPointRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = Costanti.LOCAL_HOST)
@RestController
@RequestMapping(EndPointRest.ITINERARIO_REST)
public class ItinerarioController {

    @Autowired
    private ItinerarioService itinerarioService;

    @GetMapping(EndPointRest.FIND_ALL_ITINERARIO_PARCO)
    public ResponseEntity<List<ItinerarioDto>> findAllItinerarioParco(@RequestParam String language,@RequestParam String codice){
        try{
            return new ResponseEntity<List<ItinerarioDto>>(itinerarioService.findAllItinerariParco(language,codice),HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<List<ItinerarioDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(EndPointRest.FIND_PARCO_FROM_ITINERARIO)
    public ResponseEntity<AreaDto> findParcoFromItinerario(@RequestParam String objectId){
        try{
            return new ResponseEntity<AreaDto>(itinerarioService.findParcoFromItinerario(objectId),HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<AreaDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
