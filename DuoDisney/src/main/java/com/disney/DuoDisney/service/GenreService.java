package com.disney.DuoDisney.service;

import com.disney.DuoDisney.dto.GenreDTO;
import java.util.List;
import org.springframework.stereotype.Service;

// @author aduo
@Service // preguntar, va con @Service??
public interface GenreService {

    GenreDTO save(GenreDTO dto);

    List<GenreDTO> getAllGenres();

    GenreDTO modify(String id, GenreDTO genreDTO);

    void deleteGenreById(String id);
}
