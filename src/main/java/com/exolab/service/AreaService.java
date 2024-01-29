package com.exolab.service;

import com.exolab.dto.AreaDto;
import com.exolab.model.Area;

import java.util.List;
import java.util.Optional;

public interface AreaService {
    List<AreaDto> findAllArea(String language,String tipo);
    AreaDto findByCodice(String language,String codice);
    AreaDto findById(String language,String id);
    AreaDto findRandomArea(String language,Optional<String> tipo);
    List<AreaDto> findAllAreePerRegione (String language,String regione);

    List<AreaDto> findByRicerca (String language,String ricerca);

}
