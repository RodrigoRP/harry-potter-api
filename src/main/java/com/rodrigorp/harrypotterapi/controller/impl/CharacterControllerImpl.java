package com.rodrigorp.harrypotterapi.controller.impl;

import com.rodrigorp.harrypotterapi.controller.CharacterController;
import com.rodrigorp.harrypotterapi.dto.CharacterNewDTO;
import com.rodrigorp.harrypotterapi.dto.CharacterUpdateDto;
import com.rodrigorp.harrypotterapi.mapper.CharacterMapper;
import com.rodrigorp.harrypotterapi.model.CharacterHP;
import com.rodrigorp.harrypotterapi.model.PotterApi;
import com.rodrigorp.harrypotterapi.service.CharacterService;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CharacterControllerImpl implements CharacterController {

    private final CharacterService characterService;
    private final Environment environment;
    private final CharacterMapper mapper;

    public CharacterControllerImpl(CharacterService characterService, CharacterMapper mapper, Environment environment) {
        this.characterService = characterService;
        this.environment = environment;
        this.mapper = mapper;
    }

    @Override
    @PostMapping("/characters/")
    public ResponseEntity<Void> save(@Valid @RequestBody CharacterNewDTO characterNewDTO) {
        CharacterHP characterHP = mapper.toModel(characterNewDTO);
        characterHP = characterService.save(characterHP);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(characterHP.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @Override
    @GetMapping("/characters/{characterId}")
    public ResponseEntity<CharacterHP> findById(@PathVariable("characterId") Long characterId) {
        CharacterHP characterHP = characterService.findById(characterId);
        return ResponseEntity.ok().body(characterHP);
    }

    @Override
    @DeleteMapping("/characters/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        characterService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/characters/{id}")
    public ResponseEntity<CharacterHP> update(@PathVariable("id") Long id, @RequestBody CharacterUpdateDto characterUpdateDto) {
        CharacterHP updatedCharacter = characterService.update(characterUpdateDto, id);
        return ResponseEntity.ok().body(updatedCharacter);
    }

    @Override
    @GetMapping("/characters/")
    public ResponseEntity<List<CharacterHP>> findAll() {
        List<CharacterHP> charactersHP = characterService.findAll();
        return ResponseEntity.ok().body(charactersHP);
    }

    @Override
    @GetMapping("/characters")
    public ResponseEntity<List<CharacterHP>> findAllByHouse(@RequestParam("houseId") String houseId) {
        List<CharacterHP> charactersHP = characterService.findAllByHouse(houseId);
        return ResponseEntity.ok().body(charactersHP);
    }

    @GetMapping("/potterapi/houses/{houseId}")
    public ResponseEntity<Mono<PotterApi[]>> findHouseById(@PathVariable String houseId) {
        String apiKey = environment.getProperty("app.api.key");
        Mono<PotterApi[]> character = characterService.searchHouseById(apiKey, houseId);
        return ResponseEntity.ok().body(character);
    }

  /*  @GetMapping("/characters")
    public ResponseEntity<List<CharacterHP>> findAllByHouseId(@RequestParam("house") String houseId) {
        List<CharacterHP> charactersHP = characterService.findAllByHouse(houseId);
        return ResponseEntity.ok().body(charactersHP);
    }*/
}
