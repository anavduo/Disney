package com.disney.DuoDisney.mapper;

// @author aduo
import com.disney.DuoDisney.dto.MovieBasicDTO;
import com.disney.DuoDisney.dto.MovieDTO;
import com.disney.DuoDisney.entity.MovieEntity;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
//    @Autowired
//    private CharMapper charMapper;
    @Autowired
    private GenreMapper genreMapper;

    public MovieEntity movieDTO2Entity(MovieDTO dto, boolean loadCharacters) {
        MovieEntity movieEntity = new MovieEntity();

        movieEntity.setImage(dto.getImage());
        movieEntity.setTitle(dto.getTitle());
        //cast string to date
        String date = dto.getCreationDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate transformedDate = LocalDate.parse(date, formatter);
        movieEntity.setCreationDate(transformedDate);

        movieEntity.setRating(dto.getRating());
        return movieEntity;
    }

    public MovieDTO movieEntity2DTO(MovieEntity movieEntity, boolean load) {
        MovieDTO dto = new MovieDTO();
        dto.setId(movieEntity.getId());
        dto.setImage(movieEntity.getImage());
        dto.setTitle(movieEntity.getTitle());
        //1. Get the original format date
        LocalDate date = movieEntity.getCreationDate();
        //2. Convert it to string
        String formatDate = date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        dto.setCreationDate(formatDate);
        dto.setRating(movieEntity.getRating());
         if(load) {
         //   dto.setCharacters(charMapper.charEntityList2DTOList(movieEntity.getCharacters(), false));
            dto.setGenres(genreMapper.genreEntityList2DTOList(movieEntity.getGenres(),false));
        }
        return dto;
    }
     public List<MovieDTO> movieEntityList2DTOList(List<MovieEntity> entities, boolean load) {
        List<MovieDTO> dtos = new ArrayList<>();

        for (MovieEntity entity: entities) {
            dtos.add(this.movieEntity2DTO(entity,load));
            
        }
        return dtos;
    }

    public List<MovieEntity> movieDTOList2EntityList(List<MovieDTO> dtoList, boolean load) {
        List<MovieEntity> entities = new ArrayList<>();

        for (MovieDTO dto: dtoList) {
            entities.add(this.movieDTO2Entity(dto, load));
        }
        return entities;
    }
     public MovieBasicDTO entity2BasicDTO(MovieEntity movieEntity) {
        MovieBasicDTO dto = new MovieBasicDTO();

        dto.setImage(movieEntity.getImage());
        dto.setTitle(movieEntity.getTitle());

        LocalDate date = movieEntity.getCreationDate();//1. Get the original format date
        String formatDate = date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")); //2. Convert it to string
        dto.setCreationDate(formatDate);

        return dto;
    }

    public List<MovieBasicDTO> entityList2BasicDTO(List<MovieEntity> entities) {
        List<MovieBasicDTO> basicList = new ArrayList<>();
        for(MovieEntity entity : entities) {
            basicList.add(this.entity2BasicDTO(entity));
        }
        return basicList;
    }
}