package com.disney.DuoDisney.repository;

import com.disney.DuoDisney.entity.CharacterEntity;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// @author aduo
@Repository
public interface CharRepo extends JpaRepository<CharacterEntity, String> {

//    @Query("SELECT c FROM Character c WHERE c.name= :name ")
//    public CharacterEntity findByName(@Param("name") String name);

    List<CharacterEntity> findAll(Specification<CharacterEntity> entitySpecification);

}
