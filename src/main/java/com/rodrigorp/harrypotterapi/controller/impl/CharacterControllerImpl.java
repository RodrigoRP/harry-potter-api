package com.rodrigorp.harrypotterapi.controller.impl;

import com.rodrigorp.harrypotterapi.controller.CharacterController;
import com.rodrigorp.harrypotterapi.dto.CharacterNewDTO;
import com.rodrigorp.harrypotterapi.mapper.CharacterMapper;
import com.rodrigorp.harrypotterapi.model.CharacterHP;
import com.rodrigorp.harrypotterapi.service.CharacterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/characters")
public class CharacterControllerImpl implements CharacterController {

    private final CharacterService characterService;
    private final CharacterMapper mapper;

    public CharacterControllerImpl(CharacterService characterService, CharacterMapper mapper) {
        this.characterService = characterService;
        this.mapper = mapper;
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<Void> save(@Valid @RequestBody CharacterNewDTO characterNewDTO) {
        CharacterHP characterHP = mapper.toModel(characterNewDTO);
        characterHP = characterService.save(characterHP);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(characterHP.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CharacterHP> findById(@PathVariable("id") Long id) {
        CharacterHP characterHP = characterService.findById(id);
        return ResponseEntity.ok().body(characterHP);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        characterService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<List<CharacterHP>> findAll() {
        List<CharacterHP> charactersHP = characterService.findAll();
        return ResponseEntity.ok().body(charactersHP);
    }

    @Override
    @GetMapping("/houses/{id}")
    public ResponseEntity<List<CharacterHP>> findAllByHouse(@PathVariable("id") String id) {
        List<CharacterHP> charactersHP = characterService.findAllByHouse(id);
        return ResponseEntity.ok().body(charactersHP);
    }
}
