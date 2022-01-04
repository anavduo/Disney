
package com.disney.DuoDisney.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

// @author aduo

@Getter
@Setter
public class MovieDTO {
 private Long id;
    private String image;
    private String title;
    private String creationDate;
    private Integer rating;

    private List<CharacterDTO> characters;
    private List<GenreDTO> genres;
}
