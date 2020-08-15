package com.rodrigorp.harrypotterapi.controller;

import com.rodrigorp.harrypotterapi.dto.CharacterNewDTO;
import com.rodrigorp.harrypotterapi.model.CharacterHP;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api(tags = "Character")
public interface CharacterController {

    @ApiOperation("Add new Character")
    ResponseEntity<Void> save(CharacterNewDTO characterNewDTO);

    @ApiOperation("Find Character by Id")
    ResponseEntity<CharacterHP> findById(Long id);

    @ApiOperation("Delete based on primary key")
    ResponseEntity<Void> delete(Long id);

    @ApiOperation("Find all Characters")
    ResponseEntity<List<CharacterHP>> findAll();

    @ApiOperation("Find all Characters by id House")
    ResponseEntity<List<CharacterHP>> findAllByHouse(String id);


}
