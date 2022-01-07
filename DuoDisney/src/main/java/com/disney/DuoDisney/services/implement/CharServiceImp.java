package com.disney.DuoDisney.services.implement;

// @author aduo
import com.disney.DuoDisney.dto.CharBasicDTO;
import com.disney.DuoDisney.dto.CharFilterDTO;
import com.disney.DuoDisney.dto.CharacterDTO;
import com.disney.DuoDisney.entity.CharacterEntity;
import com.disney.DuoDisney.entity.MovieEntity;
import com.disney.DuoDisney.exception.ParamNotFound;
import com.disney.DuoDisney.mapper.CharMapper;
import com.disney.DuoDisney.mapper.MovieMapper;
import com.disney.DuoDisney.repository.CharRepo;
import com.disney.DuoDisney.repository.specification.CharSpecification;
import com.disney.DuoDisney.service.CharService;
import com.disney.DuoDisney.service.MovieService;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
@Service
public class CharServiceImp implements CharService {

    @Autowired
    private CharMapper charMapper;
    @Autowired
    private CharRepo charRepo;
    @Autowired
    private MovieMapper movieMapper;
    @Lazy
    @Autowired
    private MovieService movieService;
    @Autowired
    private CharSpecification charSpecification;

    @Override
    public List<CharacterDTO> getAllCharacters() {
        List<CharacterEntity> charEntities = charRepo.findAll();
        List<CharacterDTO> result = charMapper.charEntityList2DTOList(charEntities, false);
        return result;
    }

    @Override
    public List<CharBasicDTO> getBasicCharList() {
        List<CharacterEntity> charList = charRepo.findAll();
        List<CharBasicDTO> resultDTO = charMapper.basicEntityList2DTOBasicList(charList);
        return resultDTO;
    }

    @Override
    public CharacterDTO modify(Long id, CharacterDTO charDTO) {
        CharacterEntity savedChar = this.getCharById(id);

        savedChar.setName(charDTO.getName());
        savedChar.setImage(charDTO.getImage());
        savedChar.setAge(charDTO.getAge());
        savedChar.setWeight(charDTO.getWeight());
        savedChar.setStory(charDTO.getStory());

        savedChar.setMovies(movieMapper.movieDTOList2EntityList(charDTO.getMovies(), false));

        CharacterEntity editedChar = charRepo.save(savedChar);

        CharacterDTO savedDTO = charMapper.charEntity2DTO(editedChar, false);

        return savedDTO;
    }

    @Override
    public CharacterDTO save(CharacterDTO charDTO) {
        CharacterEntity charEntity = new CharacterEntity();

        charEntity.setName(charDTO.getName());
        charEntity.setImage(charDTO.getImage());
        charEntity.setAge(charDTO.getAge());
        charEntity.setWeight(charDTO.getWeight());
        charEntity.setStory(charDTO.getStory());

        CharacterEntity savedChar = charRepo.save(charEntity);
        CharacterDTO savedDTO = charMapper.charEntity2DTO(savedChar, false);

        return savedDTO;
    }

    @Override
    public CharacterEntity getCharById(Long id) {

        Optional<CharacterEntity> charEntity = charRepo.findById(id);
        if (!charEntity.isPresent()) {
            throw new ParamNotFound("This Disney character does not exist."+ id);
        }
        return charEntity.get();
    }

    @Override
    public void delete(Long id) {
        charRepo.deleteById(id);
    }

    @Override
    public List<CharacterDTO> getByFilters(String name, Integer age, Set<Long> movies, String order) {
        CharFilterDTO charFilter = new CharFilterDTO(name, age, movies, order);
        List<CharacterEntity> entities = charRepo.findAll(charSpecification.getByFilters(charFilter));
        List<CharacterDTO> result = charMapper.charEntityList2DTOList(entities, true);

        return result;
    }

    @Override
    public CharacterDTO getDetailById(Long id) {
        CharacterEntity charEntity = this.getCharById(id);
        CharacterDTO result = charMapper.charEntity2DTO(charEntity, true);
        return result;
    }

    @Override
    public void addMovie(Long id, Long idMovie) {
        CharacterEntity charEntity = charRepo.getById(id);
        charEntity.getMovies().size();
        MovieEntity movie = this.movieService.getById(idMovie);
        charEntity.getMovies().add(movie);
        this.charRepo.save(charEntity);
    }

    @Override
    public void removeMovie(Long id, Long idMovie) {
        CharacterEntity charEntity = charRepo.getById(id);
        charEntity.getMovies().size();
        MovieEntity movie = this.movieService.getById(idMovie);
        charEntity.getMovies().remove(movie);
        this.charRepo.save(charEntity);
    }
}
