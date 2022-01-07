package com.disney.DuoDisney.services.implement;

import com.disney.DuoDisney.dto.MovieBasicDTO;
import com.disney.DuoDisney.dto.MovieDTO;
import com.disney.DuoDisney.dto.MovieFilterDTO;
import com.disney.DuoDisney.entity.CharacterEntity;
import com.disney.DuoDisney.entity.MovieEntity;
import com.disney.DuoDisney.exception.ParamNotFound;
import com.disney.DuoDisney.mapper.MovieMapper;
import com.disney.DuoDisney.repository.MovieRepo;
import com.disney.DuoDisney.repository.specification.MovieSpecification;
import com.disney.DuoDisney.service.CharService;
import com.disney.DuoDisney.service.MovieService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// @author aduo
@Service
public class MovieServiceImp implements MovieService {

    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private MovieRepo movieRepo;
    @Autowired
    private CharService charService;
      @Autowired
    private MovieSpecification movieSpecification;

    @Override
    public MovieDTO save(MovieDTO movie) {
        MovieEntity movieEntity = movieMapper.movieDTO2Entity(movie, true);
        MovieEntity movieSaved = movieRepo.save(movieEntity);
        MovieDTO result = movieMapper.movieEntity2DTO(movieSaved, false);
        return result;
    }

    @Override
    public List<MovieDTO> getAllMovies() {
        List<MovieEntity> entities = movieRepo.findAll();
        List<MovieDTO> result = movieMapper.movieEntityList2DTOList(entities, true);

        return result;
    }

    @Override
    public void deleteMovieById(Long id) {
        movieRepo.deleteById(id);
    }

    @Override
    public List<MovieBasicDTO> getBasicList() {
        List<MovieEntity> movieList = movieRepo.findAll();
        List<MovieBasicDTO> resultDTO = movieMapper.entityList2BasicDTO(movieList);
        return resultDTO;
    }

    @Override
    public MovieDTO modify(Long id, MovieDTO movieDTO) {
        MovieEntity savedMovie = this.getById(id);

        savedMovie.setImage(movieDTO.getImage());
        savedMovie.setTitle(movieDTO.getTitle());

        String date = movieDTO.getCreationDate().toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate transformedDate = LocalDate.parse(date, formatter);
        savedMovie.setCreationDate(transformedDate);

        savedMovie.setRating(movieDTO.getRating());
        //savedMovie.setGenreId(movieDTO.getGenreId());
        //savedMovie.setCharacters(charMapper.charDTOList2EntityList(movieDTO.getCharacters()));

        MovieEntity movieEntity = movieRepo.save(savedMovie);
        MovieDTO result = movieMapper.movieEntity2DTO(movieEntity, false);

        return result;
    }

    @Override
    public void addCharacter(Long movieId, Long characterId) {
        MovieEntity movieEntity = this.getById(movieId);
        movieEntity.getCharacters().size();

        CharacterEntity character = charService.getCharById(characterId);
        movieEntity.getCharacters().add(character);
        movieRepo.save(movieEntity);
    }

    @Override
    public MovieEntity getById(Long id) {
        Optional<MovieEntity> movieEntity = movieRepo.findById(id);
        if (!movieEntity.isPresent()) {
            throw new ParamNotFound("Movie does not exist.");
        }
        return movieEntity.get();
    }

    @Override
    public MovieDTO getByDetails(Long id) {
        MovieEntity movieEntity = this.getById(id);
        MovieDTO result = movieMapper.movieEntity2DTO(movieEntity, true);
        return result;
    }

    @Override
    public void addGenre(Long movieId, Long genreId) {
    }

    @Override
    public List<MovieDTO> getByFilters(String title, Set<Long> genre, String order) {
        MovieFilterDTO movieFilters = new MovieFilterDTO(title, genre, order);
        List<MovieEntity> entityList = movieRepo.findAll(movieSpecification.getFiltered(movieFilters));
        List<MovieDTO> result = movieMapper.movieEntityList2DTOList(entityList, true);
        return result;
    }
}
