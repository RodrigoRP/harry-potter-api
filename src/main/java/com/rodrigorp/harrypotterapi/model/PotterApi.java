package com.rodrigorp.harrypotterapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PotterApi implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonProperty("_id")
    private String id;

    @JsonProperty("name")
    private String name;

}
