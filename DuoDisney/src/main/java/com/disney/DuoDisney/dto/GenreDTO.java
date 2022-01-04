package com.disney.DuoDisney.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

// @author aduo
@Getter
@Setter
public class GenreDTO {

    private Long id;
    private String name;
    private List<MovieDTO> movies;
}
