package com.disney.DuoDisney.controller;

// @author aduo
import com.disney.DuoDisney.dto.CharacterDTO;
import com.disney.DuoDisney.service.CharService;
import com.disney.DuoDisney.service.MovieService;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("characters")
@Controller
public class CharacterController {

    @Autowired
    private CharService charService;
    @Autowired
    private MovieService movieService;

    @Autowired
    public CharacterController(CharService characterService) {
        this.charService = characterService;
    }

    @GetMapping
    public ResponseEntity<List<CharacterDTO>> getAllCharacters() {

        List<CharacterDTO> characters = charService.getAllCharacters();
        return ResponseEntity.ok().body(characters);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getDetailsById(@PathVariable Long id) {
        CharacterDTO charDTO = this.charService.getDetailById(id);
        return ResponseEntity.ok(charDTO);
    }

    @GetMapping("/filters")
    public ResponseEntity<List<CharacterDTO>> getDetailsByFilter(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Set<Long> movies,
            @RequestParam(required = false, defaultValue = "ASC") String order) {

        List<CharacterDTO> dtoList = this.charService.getByFilters(name, age, movies, order);
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping
    public ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO characterDTO) {
        CharacterDTO savedCharacter = charService.save(characterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCharacter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> update(@PathVariable Long id, @RequestBody CharacterDTO charDTO) {
        CharacterDTO result = this.charService.modify(id, charDTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CharacterDTO> delete(@PathVariable Long id) {
        this.charService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}/movie/{idMovie}")
    public ResponseEntity<Void> addMovie(@PathVariable Long id, @PathVariable Long idMovie) {
        this.charService.addMovie(id, idMovie);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}/movie/{idMovie}")
    public ResponseEntity<Void> removeMovie(@PathVariable Long id, @PathVariable Long idMovie) {
        this.charService.removeMovie(id, idMovie);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{movieId}/character/{charId}")
    public ResponseEntity<Void> addCharacter(@PathVariable Long movieId, @PathVariable Long charId) {
        movieService.addCharacter(movieId, charId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{movieId}/genre/{genreId}")
    public ResponseEntity<Void> addGenre(@PathVariable Long movieId, @PathVariable Long genreId) {
        movieService.addGenre(movieId, genreId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
