package com.disney.DuoDisney.services;

import com.disney.DuoDisney.entities.Movie;
import com.disney.DuoDisney.myexceptions.ServiceError;
import com.disney.DuoDisney.repositories.MovieRepo;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// @author aduo

@Service
public class MovieService {

    @Autowired
    private MovieRepo mr;

    @Transactional
    public void createMovie(String title) throws ServiceError {
        validateMovie(title);
        Movie movie = new Movie();

        if (movie != null) {
            movie.setTitle(title);
            movie.setAlta(true);
            mr.save(movie);

        } else {
            throw new ServiceError("Oops there was an error");
        }
    }

    public void validateMovie(String title) throws ServiceError {
        if (title == null || title.isEmpty()) {
            throw new ServiceError("The title seems not to be tipped");
        }
        if (mr.findByTitle(title) != null) {
            throw new ServiceError("That tilte already exists");
        }
    }

    @Transactional
    public void upDateMovie(Movie movie, String title) throws ServiceError {
        movie = mr.findByTitle(movie.getTitle());
        if (movie != null) {
            movie.setTitle(title);
            movie.setAlta(true);
            mr.save(movie);
        } else {
            throw new ServiceError("We cound not find that movie");
        }
    }

    @Transactional
    public void deleteMovie(Movie movie) throws ServiceError {
        movie = mr.findByTitle(movie.getTitle());
        if (movie != null) {
            movie.setAlta(false);
            mr.save(movie);
        } else {
            throw new ServiceError("We cound not find that title");
        }
    }

    @Transactional //(readOnly = true)
    public Movie getOne(String id) {
        return mr.getOne(id);
    }

    public List<Movie> ListMovies() {
        return mr.findAll();
    }

}
