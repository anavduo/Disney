package com.disney.DuoDisney.services.implement;

import com.disney.DuoDisney.dto.GenreDTO;
import com.disney.DuoDisney.entity.GenreEntity;
import com.disney.DuoDisney.exception.ParamNotFound;
import com.disney.DuoDisney.mapper.GenreMapper;
import com.disney.DuoDisney.repository.GenreRepo;
import com.disney.DuoDisney.service.GenreService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@author aduo
@Service
public class GenreServiceImp implements GenreService {

    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private GenreRepo genreRepo;

    @Override
    public GenreDTO save(GenreDTO genreDto) {
        GenreEntity genreEntity = genreMapper.genreDTO2Entity(genreDto); //To convert GenreDTO to GenreEntity.
        GenreEntity genreSaved = genreRepo.save(genreEntity);//To save the entity with id, name and image,like an entity.

        // For the controller we have to convert to DTO.
        GenreDTO result = genreMapper.genreEntity2DTO(genreSaved, false); //Finally we reconvert the saved entity to DTO.
        return result;
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        List<GenreEntity> entities = genreRepo.findAll();
        List<GenreDTO> result = genreMapper.genreEntityList2DTOList(entities, false);
        return result;
    }

    @Override
    public GenreDTO modify(String id, GenreDTO genreDTO) {
        GenreEntity savedGenre = this.getById(id);
        savedGenre.setName(genreDTO.getName());
        GenreEntity editedGenre = genreRepo.save(savedGenre);
        GenreDTO result = genreMapper.genreEntity2DTO(editedGenre, false);
        return result;
    }

    @Override
    public void deleteGenreById(String id) {
    }

    public GenreEntity getById(String id) {
        Optional<GenreEntity> genreEntity = genreRepo.findById(id);
        if (!genreEntity.isPresent()) {
            throw new ParamNotFound("Genre does not exist: " + id);
        }
        return genreEntity.get();
    }
}
