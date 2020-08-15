package com.rodrigorp.harrypotterapi.service.impl;

import com.rodrigorp.harrypotterapi.model.CharacterHP;
import com.rodrigorp.harrypotterapi.repository.CharacterRepository;
import com.rodrigorp.harrypotterapi.service.CharacterService;
import com.rodrigorp.harrypotterapi.service.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository repository;

    public CharacterServiceImpl(CharacterRepository repository) {
        this.repository = repository;
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
}
