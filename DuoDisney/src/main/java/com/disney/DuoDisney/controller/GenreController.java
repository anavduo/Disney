package com.disney.DuoDisney.controller;

// @author aduo
import com.disney.DuoDisney.dto.GenreDTO;
import com.disney.DuoDisney.service.GenreService;
import java.util.List;
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

@RequestMapping("genres")
@Controller
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getALL() {

        List<GenreDTO> genres = genreService.getAllGenres();
        return ResponseEntity.ok().body(genres);
    }

    @PostMapping
    public ResponseEntity<GenreDTO> save(@RequestBody GenreDTO genreDTO) {
        GenreDTO savedGenre = genreService.save(genreDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGenre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreDTO> modify(@PathVariable Long id, @RequestBody GenreDTO genreDTO) {
        GenreDTO editedGenre = genreService.modify(id, genreDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(editedGenre);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        genreService.deleteGenreById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
