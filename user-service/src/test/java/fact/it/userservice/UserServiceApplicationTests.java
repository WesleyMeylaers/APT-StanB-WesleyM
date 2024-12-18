package fact.it.userservice;

import fact.it.userservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import fact.it.userservice.dto.UserResponse;
import fact.it.userservice.model.User;
import fact.it.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceApplicationTests {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        // Arrange
        User user1 = new User(1L, "user1", "user1@example.com");
        User user2 = new User(2L, "user2", "user2@example.com");
        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        // Act
        List<UserResponse> userResponses = userService.getAllUsers();

        // Assert
        assertEquals(2, userResponses.size());
        assertEquals("user1", userResponses.get(0).getUsername());
        assertEquals("user2", userResponses.get(1).getUsername());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetUserById_UserExists() {
        // Arrange
        User user = new User(1L, "user1", "user1@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Act
        UserResponse userResponse = userService.getUserById(1L);

        // Assert
        assertNotNull(userResponse);
        assertEquals(1L, userResponse.getId());
        assertEquals("user1", userResponse.getUsername());
        assertEquals("user1@example.com", userResponse.getEmail());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testGetUserById_UserNotFound() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.getUserById(1L));
        assertEquals("User not found", exception.getMessage());
        verify(userRepository, times(1)).findById(1L);
    }
}