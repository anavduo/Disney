package com.disney.DuoDisney.controller;

// @author aduo
import com.disney.DuoDisney.dto.MovieBasicDTO;
import com.disney.DuoDisney.dto.MovieDTO;
import com.disney.DuoDisney.service.MovieService;
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

@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieBasicDTO>> getBasicList() {

        List<MovieBasicDTO> movies = movieService.getBasicList();
        return ResponseEntity.ok().body(movies);
    }

    @GetMapping("/alldetails")
    public ResponseEntity<List<MovieDTO>> getAllDetails() {

        List<MovieDTO> movies = movieService.getAllMovies();
        return ResponseEntity.ok().body(movies);
    }

    @PostMapping
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO movieDTO) {
        MovieDTO savedMovie = movieService.save(movieDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);

    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> modify(@PathVariable Long id, @RequestBody MovieDTO movieDTO) {
        MovieDTO editedMovie = movieService.modify(id, movieDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(editedMovie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movieService.deleteMovieById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{movieId}/character/{charId}")
    public ResponseEntity<Void> addCharacter(@PathVariable Long movieId, @PathVariable Long charId) {
        movieService.addCharacter(movieId, charId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
