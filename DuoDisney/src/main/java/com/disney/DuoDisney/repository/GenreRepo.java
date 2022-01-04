package com.disney.DuoDisney.repository;


import com.disney.DuoDisney.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//@author aduo

@Repository
public interface GenreRepo extends JpaRepository<GenreEntity, String> {

//    @Query("SELECT g FROM Genre g WHERE g.name= :name ")
//    public GenreEntity findByName(@Param("name") String name);

}
