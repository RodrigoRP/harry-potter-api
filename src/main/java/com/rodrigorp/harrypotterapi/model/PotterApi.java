package com.rodrigorp.harrypotterapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PotterApi {

    @JsonProperty("_id")
    private String id;

    @JsonProperty("name")
    private String name;

}
