package com.rodrigorp.harrypotterapi.repository;

import com.rodrigorp.harrypotterapi.model.CharacterHP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterHP, Long> {

    Optional<List<CharacterHP>> findAllByHouse(String id);

    Optional<CharacterHP> findByName(String name);
}