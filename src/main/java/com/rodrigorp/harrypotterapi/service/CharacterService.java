package com.rodrigorp.harrypotterapi.service;

import com.rodrigorp.harrypotterapi.model.CharacterHP;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CharacterService extends GenericService<CharacterHP, Long> {
    List<CharacterHP> findAllByHouse(String id);
}
