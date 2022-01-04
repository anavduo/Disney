package com.disney.DuoDisney.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

// @author aduo
@Setter
@Getter
public class CharacterDTO {

    private Long id;
    private String name;
    private String image;
    private Integer age;
    private Double weight;
    private String story;

    private List<MovieDTO> movies;

}
