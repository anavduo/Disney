package com.disney.DuoDisney.repository;

import com.disney.DuoDisney.entity.CharacterEntity;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @author aduo
@Repository
public interface CharRepo extends JpaRepository<CharacterEntity, Long> {

    List<CharacterEntity> findAll(Specification<CharacterEntity> entitySpecification);

}
