package com.rodrigorp.harrypotterapi.dto;

import com.rodrigorp.harrypotterapi.service.validation.CharacterInsert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@CharacterInsert
public class CharacterNewDTO implements Serializable {

    @NotNull(message = "Name cannot be null")
    private String name;

    private String role;

    private String school;

    private String house;

    private String patronus;

}
