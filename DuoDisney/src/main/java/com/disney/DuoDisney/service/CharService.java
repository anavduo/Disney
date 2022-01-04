package com.disney.DuoDisney.service;

import com.disney.DuoDisney.dto.CharBasicDTO;
import com.disney.DuoDisney.dto.CharacterDTO;
import com.disney.DuoDisney.entity.CharacterEntity;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

//@author aduo

@Service
public interface CharService {


    public List<CharacterDTO> getAll();

    List<CharBasicDTO> getBasicCharList();

    public CharacterDTO modify(String id, CharacterDTO charDTO);

    public CharacterDTO save(CharacterDTO charDTO);

    public CharacterEntity getById(String id);

    public void delete(String id);

    public List<CharacterDTO> getByFilters(String name, Integer age, Set<Long> movies, String order);

    public CharacterDTO getDetailById(String id);

    public void addMovie(String id, Long idMovie);

    public void removeMovie(String id, Long idMovie);

}
