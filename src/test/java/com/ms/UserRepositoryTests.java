package com.ms;

import com.ms.entities.Role;
import com.ms.entities.User;
import com.ms.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void test() {
    }

    @Test
    public void testCreateRoles() {
        Role roleAdmin = new Role("Administrator");
        Role roleUser = new Role("User");

        entityManager.persist(roleAdmin);
        entityManager.persist(roleUser);
    }

    @Test
    public void testCreateNewUserWithOneRole() {
        Role roleAdmin = entityManager.find(Role.class, 1L);
        User user = new User();
        user.setEmail("niewiadomo00001@gmail.com");
        user.setPassword("nwm1");
        user.setFirstName("Maciej");
        user.setLastName("Stępień");
        user.addRole(roleAdmin);

        userRepository.save(user);
    }

    @Test
    public void testAddNew() {
        User user = new User ();
        user.setEmail("niewiadomo0000@gmail.com");
        user.setPassword("nwm1");
        user.setFirstName("Maciej");
        user.setLastName("Stępień");

        User savedUser = userRepository.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testUserList() {
        Iterable<User> users = userRepository.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdateUser() {
        Long userId = 1L;
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        user.setPassword("bakaro");
        userRepository.save(user);

        User updatedUser = userRepository.findById(userId).get();
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("bakaro");
    }

    @Test
    public void testGetUser() {
        Long userId = 1L;
        Optional<User> optionalUser = userRepository.findById(userId);

        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }

    @Test
    public void testDeleteUser() {
        Long userId = 2L;
        userRepository.deleteById(userId);

        Optional<User> optionalUser = userRepository.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}
