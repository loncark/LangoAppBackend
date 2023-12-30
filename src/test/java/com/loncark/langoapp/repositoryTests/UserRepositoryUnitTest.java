package com.loncark.langoapp.repositoryTests;

import com.loncark.langoapp.MockDataTest;
import com.loncark.langoapp.domain.User;
import com.loncark.langoapp.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryUnitTest extends MockDataTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindAll() {
        List<User> actualUsers = userRepository.findAll();
        assertEquals(8, actualUsers.size());
    }

    @Test
    public void testFindById() {
        Optional<User> actualUser = userRepository.findById(1L);
        assertEquals(Optional.of(mockUsers.get(0)), actualUser);
    }

    @Test
    public void testDeleteById() {
        userRepository.deleteById(2L);
        assertFalse(userRepository.findById(2L).isPresent());
    }

    @Test
    public void testSave() {
        User newUser = new User(null, "Alice", "$2a$10$abc", "ROLE_USER", "USA", "Passionate coder", "ENGLISH");
        User savedUser = userRepository.save(newUser);
        assertEquals(newUser.getId(), 9L);
        assertEquals(newUser.getName(), savedUser.getName());
    }

    @Test
    public void testFindByName() {
        Optional<User> actualUser = userRepository.findByName("Marko");
        assertEquals(Optional.of(mockUsers.get(1)), actualUser);
    }

    @Test
    public void testFindByLanguagesContaining() {
        List<User> actualUsers = userRepository.findByLanguagesContaining("ENGLISH");
        assertEquals(7, actualUsers.size());
    }
}

