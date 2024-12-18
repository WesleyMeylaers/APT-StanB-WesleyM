package fact.it.albumservice;

import fact.it.albumservice.dto.AlbumRequest;
import fact.it.albumservice.dto.AlbumResponse;
import fact.it.albumservice.model.Album;
import fact.it.albumservice.repository.AlbumRepository;
import fact.it.albumservice.service.AlbumService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AlbumServiceUnitTests {

    @InjectMocks
    private AlbumService albumService;

    @Mock
    private AlbumRepository albumRepository;

    @BeforeEach
    void setUp() {
        // Setup voorafgaande acties als nodig
    }

    @Test
    public void testGetAllAlbums() {
        // Arrange
        Album album1 = new Album("1", "Album 1", "Artist 1", "Label 1", Arrays.asList("Track 1", "Track 2"));
        Album album2 = new Album("2", "Album 2", "Artist 2", "Label 2", Arrays.asList("Track 3", "Track 4"));

        when(albumRepository.findAll()).thenReturn(Arrays.asList(album1, album2));

        // Act
        List<AlbumResponse> result = albumService.getAllAlbums();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Album 1", result.get(0).getTitle());
        assertEquals("Artist 2", result.get(1).getArtist());
        verify(albumRepository, times(1)).findAll();
    }

    @Test
    public void testSaveAlbum() {
        // Arrange
        AlbumRequest albumRequest = new AlbumRequest();
        albumRequest.setTitle("New Album");
        albumRequest.setArtist("New Artist");
        albumRequest.setLabel("New Label");
        albumRequest.setTrackList(Arrays.asList("Track 1", "Track 2"));

        Album album = Album.builder()
                .title("New Album")
                .artist("New Artist")
                .label("New Label")
                .trackList(Arrays.asList("Track 1", "Track 2"))
                .build();

        when(albumRepository.save(any(Album.class))).thenReturn(album);

        // Act
        albumService.saveAlbum(albumRequest);

        // Assert
        verify(albumRepository, times(1)).save(any(Album.class));
    }
}
