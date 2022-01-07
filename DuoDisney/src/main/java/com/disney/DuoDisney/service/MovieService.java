package com.disney.DuoDisney.service;

import com.disney.DuoDisney.dto.MovieBasicDTO;
import com.disney.DuoDisney.dto.MovieDTO;
import com.disney.DuoDisney.entity.MovieEntity;
import java.util.List;
import java.util.Set;

// @author aduo
public interface MovieService {

    MovieDTO save(MovieDTO movie); //Save movie

    List<MovieDTO> getAllMovies(); //List all movies

    List<MovieBasicDTO> getBasicList(); //Basic list movies

    void deleteMovieById(Long id);

    MovieDTO modify(Long id, MovieDTO movieDTO);

    void addCharacter(Long movieId, Long characterId);

    MovieEntity getById(Long id);

    MovieDTO getByDetails(Long id);

    void addGenre(Long movieId, Long genreId);

    List<MovieDTO> getByFilters(String name, Set<Long> genre, String order);
}
