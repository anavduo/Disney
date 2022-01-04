package com.disney.DuoDisney.controller;

// @author aduo
import com.disney.DuoDisney.dto.MovieDTO;
import com.disney.DuoDisney.service.MovieService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieService movieService;
    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAllDetails() {

        List<MovieDTO> movies = movieService.getAllMovies();
        return ResponseEntity.ok().body(movies);
    }

    @PostMapping
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO movieDTO) {
         MovieDTO savedMovie = movieService.save(movieDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }
       @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long idMovie){
        movieService.delete(idMovie);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
