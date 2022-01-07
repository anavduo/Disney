package com.disney.DuoDisney.service;

import com.disney.DuoDisney.dto.CharBasicDTO;
import com.disney.DuoDisney.dto.CharacterDTO;
import com.disney.DuoDisney.entity.CharacterEntity;
import java.util.List;
import java.util.Set;

//@author aduo
public interface CharService {

    List<CharacterDTO> getAllCharacters();

    List<CharBasicDTO> getBasicCharList();

    CharacterDTO modify(Long id, CharacterDTO charDTO);

    CharacterDTO save(CharacterDTO charDTO);

    CharacterEntity getCharById(Long id);

    void delete(Long id);

    List<CharacterDTO> getByFilters(String name, Integer age, Set<Long> movies, String order);

    CharacterDTO getDetailById(Long id);

    void addMovie(Long id, Long idMovie);

    void removeMovie(Long id, Long idMovie);

}
