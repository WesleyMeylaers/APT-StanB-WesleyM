package fact.it.userservice;

import fact.it.userservice.dto.UserRequest;
import fact.it.userservice.dto.UserResponse;
import fact.it.userservice.model.User;
import fact.it.userservice.repository.UserRepository;
import fact.it.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTests {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        // Setup voorafgaande acties als nodig
    }

    @Test
    public void testGetAllUsers() {
        // Arrange
        User user1 = new User(1L, "user1", "user1@example.com");
        User user2 = new User(2L, "user2", "user2@example.com");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        // Act
        List<UserResponse> result = userService.getAllUsers();

        // Assert
        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).getUsername());
        assertEquals("user2", result.get(1).getUsername());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testGetUserById_Success() {
        // Arrange
        User user = new User(1L, "user1", "user1@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Act
        UserResponse result = userService.getUserById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("user1", result.getUsername());
        assertEquals("user1@example.com", result.getEmail());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetUserById_NotFound() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> userService.getUserById(1L));
        assertEquals("User not found", exception.getMessage());
        verify(userRepository, times(1)).findById(1L);
    }
    @Test
    public void testCreateUser() {
        // Arrange
        User user = new User(1L, "newuser", "newuser@example.com");
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        UserResponse result = userService.createUser(new UserRequest("newuser", "newuser@example.com"));

        // Assert
        assertNotNull(result);
        assertEquals("newuser", result.getUsername());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testUpdateUser_Success() {
        // Arrange
        User existingUser = new User(1L, "olduser", "olduser@example.com");
        User updatedUser = new User(1L, "updateduser", "updateduser@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        // Act
        UserResponse result = userService.updateUser(1L, new UserRequest("updateduser", "updateduser@example.com"));

        // Assert
        assertNotNull(result);
        assertEquals("updateduser", result.getUsername());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testDeleteUser_Success() {
        // Arrange
        when(userRepository.existsById(1L)).thenReturn(true);

        // Act
        userService.deleteUser(1L);

        // Assert
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteUser_NotFound() {
        // Arrange
        when(userRepository.existsById(1L)).thenReturn(false);

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> userService.deleteUser(1L));
        assertEquals("User not found", exception.getMessage());
        verify(userRepository, never()).deleteById(anyLong());
    }

}
