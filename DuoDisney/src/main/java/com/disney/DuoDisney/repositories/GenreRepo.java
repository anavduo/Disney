package com.disney.DuoDisney.repositories;


import com.disney.DuoDisney.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//@author aduo

@Repository
public interface GenreRepo extends JpaRepository<Genre, String> {

    @Query("SELECT g FROM Genre g WHERE g.name= :name ")
    public Genre findByName(@Param("name") String name);

}
