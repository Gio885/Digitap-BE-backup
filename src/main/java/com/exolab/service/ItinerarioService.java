package com.exolab.service;

import com.exolab.dto.AreaDto;
import com.exolab.dto.ItinerarioDto;
import com.exolab.model.Itinerario;

import java.util.List;

public interface ItinerarioService {

    List<ItinerarioDto> findAllItinerariParco(String language,String codice);

    AreaDto findParcoFromItinerario(String objectId);

}
