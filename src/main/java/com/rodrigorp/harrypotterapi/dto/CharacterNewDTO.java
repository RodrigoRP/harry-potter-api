package com.rodrigorp.harrypotterapi.dto;

import com.rodrigorp.harrypotterapi.service.validation.CharacterInsert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@CharacterInsert
public class CharacterNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Required field")
    @Length(min = 3, max = 50, message = "The length must be between 5 and 120 characters")
    private String name;

    private String role;

    private String school;

    private String house;

    private String patronus;

}
