package com.rodrigorp.harrypotterapi.mapper;

import com.rodrigorp.harrypotterapi.dto.CharacterNewDTO;
import com.rodrigorp.harrypotterapi.model.CharacterHP;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapper {

    private final ModelMapper modelMapper;

    public CharacterMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CharacterHP toModel(CharacterNewDTO characterNewDTO) {
        return modelMapper.map(characterNewDTO, CharacterHP.class);
    }
}
