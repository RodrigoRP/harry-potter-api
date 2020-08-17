package com.rodrigorp.harrypotterapi.service.impl;

import com.rodrigorp.harrypotterapi.dto.CharacterUpdateDto;
import com.rodrigorp.harrypotterapi.model.CharacterHP;
import com.rodrigorp.harrypotterapi.model.PotterApi;
import com.rodrigorp.harrypotterapi.repository.CharacterRepository;
import com.rodrigorp.harrypotterapi.service.CharacterService;
import com.rodrigorp.harrypotterapi.service.exception.ObjectNotFoundException;
import com.rodrigorp.harrypotterapi.service.utils.JsonNullableUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    @CacheEvict(cacheNames = "characters", allEntries = true)
    public CharacterHP save(CharacterHP entity) {
        return repository.save(entity);
    }

    @Override
    @CacheEvict(cacheNames = "characters", key = "#id")
    public void deleteById(Long id) {
        findById(id);
        repository.deleteById(id);
    }

    @Override
    @Cacheable(cacheNames = "characters", key = "#id")
    public CharacterHP findById(Long id) {
        Optional<CharacterHP> characterHP = repository.findById(id);
        return characterHP.orElseThrow(() -> new ObjectNotFoundException(
                "Character not found! Id: " + id + ", Tipo: " + CharacterHP.class.getName()));
    }

    @Override
    @Cacheable(cacheNames = "characters", key = "#root.method.name")
    public List<CharacterHP> findAll() {
        return repository.findAll();
    }

    @Override
    @CachePut(cacheNames = "characters", key = "#id")
    public CharacterHP update(CharacterUpdateDto characterUpdateDto, Long id) {
        CharacterHP characterHP = findById(id);

        JsonNullableUtils.changeIfPresent(characterUpdateDto.getName(), characterHP::setName);
        JsonNullableUtils.changeIfPresent(characterUpdateDto.getPatronus(), characterHP::setPatronus);
        JsonNullableUtils.changeIfPresent(characterUpdateDto.getRole(), characterHP::setRole);
        JsonNullableUtils.changeIfPresent(characterUpdateDto.getSchool(), characterHP::setSchool);

        return repository.save(characterHP);
    }

    @Override
    @Cacheable(cacheNames = "characters", key = "#id")
    public List<CharacterHP> findAllByHouse(String id) {
        return repository.findAllByHouse(id);
    }

    @Override
    public Mono<PotterApi[]> searchHouseById(String apiKey, String houseId) {
        return webClient.get()
                .uri("houses/" + houseId + "?key=" + apiKey)
                .retrieve()
                .bodyToMono(PotterApi[].class);
    }
}
