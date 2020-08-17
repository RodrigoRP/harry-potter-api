package com.rodrigorp.harrypotterapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CharacterUpdateDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private JsonNullable<String> name = JsonNullable.undefined();

    @NotNull
    private JsonNullable<String> role = JsonNullable.undefined();

    @NotNull
    private JsonNullable<String> school = JsonNullable.undefined();

    @NotNull
    private JsonNullable<String> patronus = JsonNullable.undefined();
}
