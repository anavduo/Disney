package com.disney.DuoDisney.mapper;

//@author aduo
import com.disney.DuoDisney.dto.GenreDTO;
import com.disney.DuoDisney.dto.MovieDTO;
import com.disney.DuoDisney.entity.GenreEntity;
import com.disney.DuoDisney.entity.MovieEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {

//    @Autowired
//    private MovieMapper movieMapper;

    public GenreEntity genreDTO2Entity(GenreDTO dto) {
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setName(dto.getName());
        
        return genreEntity;
    }

    public GenreDTO genreEntity2DTO(GenreEntity genreEntity, boolean loadMovie) {
        GenreDTO dto = new GenreDTO();
        dto.setId(genreEntity.getId());
        dto.setName(genreEntity.getName());
        
//        if (loadMovie) {
//            List<MovieDTO> dtoList = new ArrayList<>();
//            for (MovieEntity entity : genreEntity.getMovies()) {
//                dtoList.add(movieMapper.movieEntity2DTO(entity, false));
//            }
//            dto.setMovies(dtoList);
//        }
        return dto;
    }

    public List<GenreDTO> genreEntityList2DTOList(List<GenreEntity> entities, boolean loadMovie) {
        List<GenreDTO> dtoList = new ArrayList<>();

        for (GenreEntity entity : entities) {
            dtoList.add(this.genreEntity2DTO(entity, loadMovie));
        }
        return dtoList;
    }
    public List<GenreEntity> genreDTOList2EntityList(List<GenreDTO> dtoList, boolean load) {
        List<GenreEntity> entities = new ArrayList<>();

        for (GenreDTO dto : dtoList) {
            entities.add(this.genreDTO2Entity(dto));
        }
        return entities;
    }
}
