package com.disney.DuoDisney.repositories;


import com.disney.DuoDisney.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//@author aduo

@Repository
public interface MovieRepo extends JpaRepository<Movie, String> {

    @Query("SELECT m FROM Movie m WHERE m.title= :title ")
    public Movie findByTitle(@Param("title") String title);

}
