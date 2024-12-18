package fact.it.playlistservice;

import fact.it.playlistservice.service.PlaylistService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import fact.it.playlistservice.dto.PlaylistRequest;
import fact.it.playlistservice.dto.PlaylistResponse;
import fact.it.playlistservice.model.Playlist;
import fact.it.playlistservice.repository.PlaylistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PlaylistServiceApplicationTests {
    @Mock
    private PlaylistRepository playlistRepository;

    @InjectMocks
    private PlaylistService playlistService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePlaylist() {
        // Arrange
        PlaylistRequest playlistRequest = PlaylistRequest.builder()
                .name("Chill Vibes")
                .description("Relaxing music for studying")
                .owner("User123")
                .songsList(List.of("Song1", "Song2"))
                .build();

        // Act
        playlistService.createPlaylist(playlistRequest);

        // Assert
        verify(playlistRepository, times(1)).save(any(Playlist.class));
    }

    @Test
    void testGetAllPlaylists() {
        // Arrange
        Playlist playlist1 = Playlist.builder()
                .id("1")
                .name("Workout Mix")
                .description("High energy songs for the gym")
                .owner("User456")
                .lastModifiedDate(new Date())
                .songsList(List.of("Track A", "Track B"))
                .build();

        Playlist playlist2 = Playlist.builder()
                .id("2")
                .name("Road Trip")
                .description("Perfect playlist for long drives")
                .owner("User789")
                .lastModifiedDate(new Date())
                .songsList(List.of("Track X", "Track Y"))
                .build();

        when(playlistRepository.findAll()).thenReturn(List.of(playlist1, playlist2));

        // Act
        List<PlaylistResponse> playlistResponses = playlistService.getAllPlaylists();

        // Assert
        assertEquals(2, playlistResponses.size());
        assertEquals("Workout Mix", playlistResponses.get(0).getName());
        assertEquals("Road Trip", playlistResponses.get(1).getName());
        verify(playlistRepository, times(1)).findAll();
    }
}
