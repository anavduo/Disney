package com.disney.DuoDisney.service;

import com.disney.DuoDisney.dto.GenreDTO;
import com.disney.DuoDisney.entity.GenreEntity;
import java.util.List;

// @author aduo
public interface GenreService {

    GenreDTO save(GenreDTO dto);

    List<GenreDTO> getAllGenres();

    GenreDTO modify(Long id, GenreDTO genreDTO);

    void deleteGenreById(Long id);

    void addMovie(Long generId, Long movierId);

    public GenreEntity getGenreById(Long genreId);
}
