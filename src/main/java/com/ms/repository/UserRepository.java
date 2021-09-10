package com.ms.repository;

import com.ms.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    public Long countById(Long id);

}
