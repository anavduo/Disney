package com.disney.DuoDisney.repository;

import com.disney.DuoDisney.entity.MovieEntity;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//@author aduo
@Repository
public interface MovieRepo extends JpaRepository<MovieEntity, Long> {

//    @Query("SELECT m FROM Movie m WHERE m.title= :title ")
//    public MovieEntity findByTitle(@Param("title") String title);

    List<MovieEntity> findAll(Specification<MovieEntity> entitySpecification);

}
