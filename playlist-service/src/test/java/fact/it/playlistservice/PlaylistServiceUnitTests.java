package fact.it.playlistservice;

import fact.it.playlistservice.dto.PlaylistRequest;
import fact.it.playlistservice.dto.PlaylistResponse;
import fact.it.playlistservice.model.Playlist;
import fact.it.playlistservice.repository.PlaylistRepository;
import fact.it.playlistservice.service.PlaylistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlaylistServiceUnitTests {

    @InjectMocks
    private PlaylistService playlistService;

    @Mock
    private PlaylistRepository playlistRepository;

    @BeforeEach
    void setUp() {
        // Setup voor elke test
    }

    @Test
    public void testCreatePlaylist() {
        // Arrange
        PlaylistRequest playlistRequest = new PlaylistRequest();
        playlistRequest.setName("Chill Vibes");
        playlistRequest.setDescription("Relaxing music for work or study");
        playlistRequest.setOwner("User123");
        playlistRequest.setSongsList(Arrays.asList("song1", "song2", "song3"));

        Playlist playlist = new Playlist();
        playlist.setId("1");
        playlist.setName("Chill Vibes");
        playlist.setDescription("Relaxing music for work or study");
        playlist.setOwner("User123");
        playlist.setLastModifiedDate(new Date());
        playlist.setSongsList(Arrays.asList("song1", "song2", "song3"));

        when(playlistRepository.save(any(Playlist.class))).thenReturn(playlist);

        // Act
        playlistService.createPlaylist(playlistRequest);

        // Assert
        verify(playlistRepository, times(1)).save(any(Playlist.class));
    }

    @Test
    public void testGetAllPlaylists() {
        // Arrange
        Playlist playlist1 = new Playlist();
        playlist1.setId("1");
        playlist1.setName("Chill Vibes");
        playlist1.setDescription("Relaxing music for work or study");
        playlist1.setOwner("User123");
        playlist1.setLastModifiedDate(new Date());
        playlist1.setSongsList(Arrays.asList("song1", "song2", "song3"));

        Playlist playlist2 = new Playlist();
        playlist2.setId("2");
        playlist2.setName("Workout Hits");
        playlist2.setDescription("High energy music for workouts");
        playlist2.setOwner("User456");
        playlist2.setLastModifiedDate(new Date());
        playlist2.setSongsList(Arrays.asList("song4", "song5", "song6"));

        when(playlistRepository.findAll()).thenReturn(Arrays.asList(playlist1, playlist2));

        // Act
        List<PlaylistResponse> result = playlistService.getAllPlaylists();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Chill Vibes", result.get(0).getName());
        assertEquals("Workout Hits", result.get(1).getName());
        verify(playlistRepository, times(1)).findAll();
    }
}
