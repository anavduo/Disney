package com.disney.DuoDisney.services.implement;

import com.disney.DuoDisney.dto.GenreDTO;
import com.disney.DuoDisney.entity.GenreEntity;
import com.disney.DuoDisney.entity.MovieEntity;
import com.disney.DuoDisney.exception.ParamNotFound;
import com.disney.DuoDisney.mapper.GenreMapper;
import com.disney.DuoDisney.repository.GenreRepo;
import com.disney.DuoDisney.service.GenreService;
import com.disney.DuoDisney.service.MovieService;
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
  
//    @Autowired
//    private MovieService movieService;

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
        List<GenreDTO> result = genreMapper.genreEntityList2DTOList(entities, true);
        return result;
    }

    @Override
    public GenreDTO modify(Long id, GenreDTO genreDTO) {
        GenreEntity savedGenre = this.getGenreById(id);
        savedGenre.setName(genreDTO.getName());
        GenreEntity editedGenre = genreRepo.save(savedGenre);
        GenreDTO result = genreMapper.genreEntity2DTO(editedGenre, false);
        return result;
    }

    @Override
    public void deleteGenreById(Long id) {
        genreRepo.deleteById(id);
    }

    public GenreEntity getGenreById(Long id) {
        Optional<GenreEntity> genreEntity = genreRepo.findById(id);
        if (!genreEntity.isPresent()) {
            throw new ParamNotFound("Genre does not exist: " + id);
        }
        return genreEntity.get();
    }

    @Override
    public void addMovie(Long genreId, Long movieId) {
        GenreEntity genreEntity=this.getGenreById(genreId);
//        genreEntity.getMovies().size();
//
//        MovieEntity movieEntity = movieService.getById(movieId);
//        genreEntity.getMovies().add(movieEntity);
        genreRepo.save(genreEntity);
    }
}
