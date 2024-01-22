package com.loncark.langoapp.serviceTests;

import com.loncark.langoapp.MockDataTest;
import com.loncark.langoapp.domain.User;
import com.loncark.langoapp.dto.UserDTO;
import com.loncark.langoapp.repository.UserRepository;
import com.loncark.langoapp.service.AppointmentService;
import com.loncark.langoapp.service.UserService;
import com.loncark.langoapp.service.UserServiceImpl;
import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

@RunWith(EasyMockRunner.class)
public class UserServiceUnitTest extends MockDataTest {

    private final UserRepository userRepository = EasyMock.createMock(UserRepository.class);
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final AppointmentService aptService = EasyMock.createMock(AppointmentService.class);
    private final UserService userService = new UserServiceImpl(userRepository, passwordEncoder, aptService);

    @Test
    public void GivenValidId_WhenFindById_ThenReturnUserDTO() {
        // Arrange
        Long userId = 1L;
        Optional<User> mockUser = Optional.of(mockUsers.get(0));
        expect(userRepository.findById(userId)).andReturn(mockUser);
        replay(userRepository);

        // Act
        Optional<UserDTO> actualResult = userService.findById(userId);

        // Assert
        Optional<UserDTO> expectedResult = Optional.of(mockUserDTOs.get(0));

        assertEquals(actualResult, expectedResult);
        verify(userRepository);
    }

    @Test
    public void GivenValidUser_WhenSave_ThenReturnSavedUserDTO() {
        // Arrange
        User user = new User(null, "Paul", "paulpassword",
                "ROLE_USER", "CROATIA", "A new user.", "CROATIAN,ENGLISH");
        User savedUser = new User(9L, "Paul", "whoKnowsWhatTheEncoderEncoded",
                "ROLE_USER", "CROATIA", "A new user.", "CROATIAN,ENGLISH");
        expect(userRepository.save(user)).andReturn(savedUser);
        replay(userRepository);

        // Act
        Optional<UserDTO> actualResult = userService.save(user);

        // Assert
        Optional<UserDTO> expectedResult = Optional.of(new UserDTO(savedUser));

        assertEquals(actualResult.get().getId(), expectedResult.get().getId());
        assertEquals(actualResult.get().getName(), expectedResult.get().getName());
        verify(userRepository);
    }

    @Test
    public void GivenValidId_WhenDeleteById_ThenRepositoryCalled() {
        // Arrange
        Long userId = 3L;
        userRepository.deleteById(userId);
        expectLastCall().once();
        replay(userRepository);

        // Act
        userService.deleteById(userId);

        // Assert
        verify(userRepository);
    }

    @Test
    public void GivenValidName_WhenFindByName_ThenReturnUserDTO() {
        // Arrange
        String userName = "Ivan";
        Optional<User> mockUser = Optional.of(mockUsers.get(2));
        expect(userRepository.findByName(userName)).andReturn(mockUser);
        replay(userRepository);

        // Act
        Optional<UserDTO> actualResult = userService.findByName(userName);

        // Assert
        Optional<UserDTO> expectedResult = Optional.of(new UserDTO(mockUser.get()));

        assertEquals(actualResult, expectedResult);
        verify(userRepository);
    }

    @Test
    public void GivenValidLanguage_WhenFindByLanguage_ThenReturnUserDTOList() {
        // Arrange
        String language = "english";

        expect(userRepository.findByLanguagesContaining(language.toUpperCase())).andReturn(mockUsers.subList(0,2));
        replay(userRepository);

        // Act
        List<UserDTO> actualResult = userService.findByLanguage(language);

        // Assert
        assertEquals(actualResult, mockUserDTOs.subList(0,2));
        verify(userRepository);
    }
}

