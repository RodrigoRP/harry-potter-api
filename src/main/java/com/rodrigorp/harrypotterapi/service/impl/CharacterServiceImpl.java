package com.rodrigorp.harrypotterapi.service.impl;

import com.rodrigorp.harrypotterapi.model.CharacterHP;
import com.rodrigorp.harrypotterapi.model.PotterApi;
import com.rodrigorp.harrypotterapi.repository.CharacterRepository;
import com.rodrigorp.harrypotterapi.service.CharacterService;
import com.rodrigorp.harrypotterapi.service.exception.ObjectNotFoundException;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository repository;
    private final WebClient webClient;

    public CharacterServiceImpl(CharacterRepository repository, WebClient webClient) {
        this.repository = repository;
        this.webClient = webClient;
    }

    @Override
    public CharacterHP save(CharacterHP entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        repository.deleteById(id);
    }

    @Override
    public CharacterHP findById(Long id) {
        Optional<CharacterHP> characterHP = repository.findById(id);
        return characterHP.orElseThrow(() -> new ObjectNotFoundException(
                "Character not found! Id: " + id + ", Tipo: " + CharacterHP.class.getName()));
    }

    @Override
    public List<CharacterHP> findAll() {
        return repository.findAll();
    }

    @Override
    public CharacterHP update(CharacterHP entity, Long id) {
        return null;
    }

    @Override
    public List<CharacterHP> findAllByHouse(String id) {
        Optional<List<CharacterHP>> houseList = repository.findAllByHouse(id);
        return houseList.orElseThrow(() -> new ObjectNotFoundException(
                "House not found!  Id: " + id + ", Tipo: " + CharacterHP.class.getName()));
    }

    @Override
    public Mono<PotterApi[]> searchHouseById(String apiKey, String houseId) {
        return webClient.get()
                .uri("houses/" + houseId + "?key=" + apiKey)
                .retrieve()
                .bodyToMono(PotterApi[].class);
    }
}
