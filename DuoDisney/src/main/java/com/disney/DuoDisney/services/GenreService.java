
package com.disney.DuoDisney.services;


import com.disney.DuoDisney.entities.Genre;
import com.disney.DuoDisney.entities.Movie;
import com.disney.DuoDisney.myexceptions.ServiceError;
import com.disney.DuoDisney.repositories.GenreRepo;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// @author aduo

@Service
public class GenreService {
    @Autowired
    private GenreRepo gr;
 @Transactional
    public void createGenre(String name) throws ServiceError {
        validateGenre(name);
        Genre genre= new Genre();
        

        if (name != null) {
            genre.setName(name);
            genre.setImage(name);
          //  genre.setMovie(movie);
            gr.save(genre);

        } else {
            throw new ServiceError("Oops there was an error");
        }
    }

    public void validateGenre(String name) throws ServiceError {
        if (name == null || name.isEmpty()) {
            throw new ServiceError("The name seems not to be tipped");
        }
        if (gr.findByName(name) != null) {
            throw new ServiceError("That name already exists");
        }
    }

    @Transactional
    public void upDateGenre(Genre genre, String name) throws ServiceError {
        genre = gr.findByName(genre.getName());
        if (genre != null) {
            genre.setName(name);
            gr.save(genre);
        } else {
            throw new ServiceError("We cound not find that genre");
        }
    }

    @Transactional
    public void deleteGenre(Genre genre) throws ServiceError {
        genre = gr.findByName(genre.getName());
        if (genre != null) {
            gr.delete(genre);
        } else {
            throw new ServiceError("We cound not find that gender");
        }
    }

    @Transactional //(readOnly = true)
    public Genre getOne(String id) {
        return gr.getOne(id);
    }

    public List<Genre> ListGenre() {
        return gr.findAll();
    }
}
