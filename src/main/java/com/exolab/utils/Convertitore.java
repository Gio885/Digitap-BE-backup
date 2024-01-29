package com.exolab.utils;

import com.exolab.dto.AreaDto;
import com.exolab.model.Area;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Convertitore {

/*
OPTIONAL viene utilizzato per gestire la presenza di un valore, riducendo il rischio del null pointer exception
.ofNullable(T value) se il valore di area e nullo viene creata un istanza id optional vuota
.orElse(T other) restituisce il valore other nel se il valore contenuto non è presente quindi se area e nullo.
 */
    public AreaDto convertAreaModelToAreaDtoDetail(Area areaDaConvertire){
        AreaDto areaDto = new AreaDto();
        if(Objects.nonNull(areaDaConvertire)){
           // areaDto.setObjectId(areaDaConvertire.getId().toHexString());
            areaDto.setCodice(areaDaConvertire.getCodice());
            areaDto.setEnteGestore(areaDaConvertire.getEnteGestore());
            areaDto.setIt(areaDaConvertire.getIt());
            areaDto.setProvincia(areaDaConvertire.getProvincia());
            areaDto.setRegione(areaDaConvertire.getRegione());
            areaDto.setTags(areaDaConvertire.getTags());
            areaDto.setType(areaDaConvertire.getType());
        }
        return areaDto;
    }

    public AreaDto convertAreaModelToAreaDtoNoDetail(Area area){
        AreaDto areaDto = new AreaDto();
       // areaDto.setObjectId(area.getId().toHexString());
        areaDto.setIt(area.getIt());
        areaDto.setTags(area.getTags());
        return areaDto;
    }

    /*
    public List<AreaDto> convertListaAreaModelToAreaDto(List<Area> listaAreeDaConvertire){
        List<AreaDto> listaAreaDto = new ArrayList<AreaDto>();
        for(Area e : listaAreeDaConvertire){
            AreaDto area = convertAreaModelToAreaDto(Optional.ofNullable(e));
            listaAreaDto.add(area);
        }
        return listaAreaDto;
    };
    */


}
