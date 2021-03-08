package com.tts.UsersAPI.repository;

import com.tts.UsersAPI.model.UserV1;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserV1, Long> {
    List<UserV1> findByState(String state);


}
