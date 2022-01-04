
package com.disney.DuoDisney.auth.repository;

import com.disney.DuoDisney.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @author aduo
 
@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long>{
   UserEntity findByUsername(String username);
}
