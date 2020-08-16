package com.rodrigorp.harrypotterapi.service;

import com.rodrigorp.harrypotterapi.model.CharacterHP;
import com.rodrigorp.harrypotterapi.model.PotterApi;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CharacterService extends GenericService<CharacterHP, Long> {

    List<CharacterHP> findAllByHouse(String id);

    Mono<PotterApi[]> searchHouseById(String apiKey, String houseId);
}
