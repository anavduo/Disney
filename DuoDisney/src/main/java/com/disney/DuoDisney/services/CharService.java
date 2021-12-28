package com.disney.DuoDisney.services;

import com.disney.DuoDisney.entities.Character;
import com.disney.DuoDisney.entities.Movie;
import com.disney.DuoDisney.myexceptions.ServiceError;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.disney.DuoDisney.repositories.CharRepo;

//@author aduo

@Service
public class CharService {

    @Autowired
    private CharRepo cr;

    @Transactional
    public void createCharacter(String name) throws ServiceError {
        validateCharacter(name);
        Character c = new Character();
      //  ArrayList<Movie> movies = new ArrayList();

        if (name != null) {
            c.setName(name);
            c.setImage(name);
          //  c.setMovie(movie);
            cr.save(c);

        } else {
            throw new ServiceError("Oops there was an error");
        }
    }

    public void validateCharacter(String name) throws ServiceError {
        if (name == null || name.isEmpty()) {
            throw new ServiceError("The name seems not to be tipped");
        }
        if (cr.findByName(name) != null) {
            throw new ServiceError("That name already exists");
        }
    }

    @Transactional
    public void upDateCharacter(Character c, String name) throws ServiceError {
        c = cr.findByName(c.getName());
        if (c != null) {
            c.setName(name);
            cr.save(c);
        } else {
            throw new ServiceError("We cound not find that featue");
        }
    }

    @Transactional
    public void deleteCharacter(Character c) throws ServiceError {
        c = cr.findByName(c.getName());
        if (c != null) {
            c.setAlta(false);
            cr.save(c);
        } else {
            throw new ServiceError("We cound not find that charater");
        }
    }

    @Transactional //(readOnly = true)
    public Character getOne(String id) {
        return cr.getOne(id);
    }

    public List<Character> ListGenre() {
        return cr.findAll();
    }
}
