package com.disney.DuoDisney.repositories;



import com.disney.DuoDisney.entities.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// @author aduo

@Repository
public interface CharRepo extends JpaRepository<Character, String> {

    @Query("SELECT c FROM Character c WHERE c.name= :name ")
    public Character findByName(@Param("name") String name);
}
