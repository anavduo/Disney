package com.disney.DuoDisney.service;

import com.disney.DuoDisney.dto.GenreDTO;
import java.util.List;

// @author aduo

public interface GenreService {

    GenreDTO save(GenreDTO dto);

    List<GenreDTO> getAllGenres();

    GenreDTO modify(Long id, GenreDTO genreDTO);

    void deleteGenreById(Long id);
}
