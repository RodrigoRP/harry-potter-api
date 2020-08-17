package com.rodrigorp.harrypotterapi.controller;

import com.rodrigorp.harrypotterapi.dto.CharacterNewDTO;
import com.rodrigorp.harrypotterapi.dto.CharacterUpdateDto;
import com.rodrigorp.harrypotterapi.model.CharacterHP;
import com.rodrigorp.harrypotterapi.model.PotterApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

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

    @ApiOperation("Find all Characters by HouseID")
    ResponseEntity<List<CharacterHP>> findAllByHouse(String id);

    @ApiOperation("Update Character by Id")
    ResponseEntity<CharacterHP> update(Long id, CharacterUpdateDto characterUpdateDto);

    @ApiOperation("Find Characters Name by HouseID from https://www.potterapi.com/")
    ResponseEntity<Mono<PotterApi[]>> findHouseById(String houseId);

}
