package com.disney.DuoDisney.mapper;

//@author aduo
import com.disney.DuoDisney.dto.CharBasicDTO;
import com.disney.DuoDisney.dto.CharacterDTO;
import com.disney.DuoDisney.dto.MovieDTO;
import com.disney.DuoDisney.entity.CharacterEntity;
import com.disney.DuoDisney.entity.MovieEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CharMapper {

//    @Autowired
//    private MovieMapper movieMapper;

    public CharacterEntity charDTO2Entity(CharacterDTO dto) {
        CharacterEntity characterEntity = new CharacterEntity();

        characterEntity.setName(dto.getName());
        characterEntity.setImage(dto.getImage());
        characterEntity.setAge(dto.getAge());
        characterEntity.setWeight(dto.getWeight());
        characterEntity.setStory(dto.getStory());

        return characterEntity;
    }

    public CharacterDTO charEntity2DTO(CharacterEntity characterEntity, boolean loadMovie) {
        CharacterDTO dto = new CharacterDTO();

        dto.setId(characterEntity.getId());
        dto.setName(characterEntity.getName());
        dto.setImage(characterEntity.getImage());
        dto.setAge(characterEntity.getAge());
        dto.setWeight(characterEntity.getWeight());
        dto.setStory(characterEntity.getStory());

//        if (loadMovie) {
//            List<MovieDTO> dtoList = new ArrayList<>();
//            for (MovieEntity entity : characterEntity.getMovies()) {
//                dtoList.add(movieMapper.movieEntity2DTO(entity, false));
//            }
//            dto.setMovies(dtoList);
//        }
        return dto;
    }

    public List<CharacterDTO> charEntityList2DTOList(List<CharacterEntity> entities, boolean loadMovie) {
        List<CharacterDTO> dtoList = new ArrayList<>();

        for (CharacterEntity entity : entities) {
            dtoList.add(charEntity2DTO(entity, loadMovie));
        }
        return dtoList;
    }

    public List<CharacterEntity> charDTOList2EntityList(List<CharacterDTO> dtoList, boolean load) {
        List<CharacterEntity> entities = new ArrayList<>();

        for (CharacterDTO dto : dtoList) {
            entities.add(this.charDTO2Entity(dto));
        }
        return entities;
    }

    public CharBasicDTO entity2BasicDTO(CharacterEntity charEntity) {
        CharBasicDTO dtoBasic = new CharBasicDTO();

        dtoBasic.setId(charEntity.getId());
        dtoBasic.setName(charEntity.getName());
        dtoBasic.setImage(charEntity.getImage());

        return dtoBasic;
    }

    public List<CharBasicDTO> basicEntityList2DTOBasicList(List<CharacterEntity> entities) {
        List<CharBasicDTO> basicList = new ArrayList<>();
        
        for (CharacterEntity entity : entities) {
             basicList.add(this.entity2BasicDTO(entity));
        }
        return basicList;
    }
}
