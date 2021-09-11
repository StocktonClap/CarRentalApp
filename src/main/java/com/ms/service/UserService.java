package com.ms.service;

import com.ms.entities.User;
import com.ms.exceptions.UserNotFoundExpection;
import com.ms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User getUserById(Long id) throws UserNotFoundExpection {
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundExpection("Could not find any users with ID " + id);
    }

    public void delete(Long id) throws UserNotFoundExpection {
        Long count = userRepository.countById(id);
        if (count == null || count == 0) {
            throw new UserNotFoundExpection("Could not find any users with ID " + id);
        }
        userRepository.deleteById(id);
    }
}
