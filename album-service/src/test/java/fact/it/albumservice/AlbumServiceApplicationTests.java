package fact.it.albumservice;

import fact.it.albumservice.service.AlbumService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import fact.it.albumservice.dto.AlbumRequest;
import fact.it.albumservice.dto.AlbumResponse;
import fact.it.albumservice.model.Album;
import fact.it.albumservice.repository.AlbumRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AlbumServiceApplicationTests {
    @Mock
    private AlbumRepository albumRepository;

    @InjectMocks
    private AlbumService albumService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAlbums() {
        // Arrange
        Album album1 = Album.builder()
                .id("1")
                .title("Album 1")
                .artist("Artist 1")
                .label("Label 1")
                .trackList(List.of("Track 1", "Track 2"))
                .build();

        Album album2 = Album.builder()
                .id("2")
                .title("Album 2")
                .artist("Artist 2")
                .label("Label 2")
                .trackList(List.of("Track A", "Track B"))
                .build();

        when(albumRepository.findAll()).thenReturn(List.of(album1, album2));

        // Act
        List<AlbumResponse> albumResponses = albumService.getAllAlbums();

        // Assert
        assertEquals(2, albumResponses.size());
        assertEquals("Album 1", albumResponses.get(0).getTitle());
        assertEquals("Artist 1", albumResponses.get(0).getArtist());
        assertEquals("Album 2", albumResponses.get(1).getTitle());
        assertEquals("Artist 2", albumResponses.get(1).getArtist());
        verify(albumRepository, times(1)).findAll();
    }

    @Test
    void testSaveAlbum() {
        // Arrange
        AlbumRequest albumRequest = AlbumRequest.builder()
                .title("New Album")
                .artist("New Artist")
                .label("New Label")
                .trackList(List.of("Track X", "Track Y"))
                .build();

        Album album = Album.builder()
                .title("New Album")
                .artist("New Artist")
                .label("New Label")
                .trackList(List.of("Track X", "Track Y"))
                .build();

        // Act
        albumService.saveAlbum(albumRequest);

        // Assert
        verify(albumRepository, times(1)).save(any(Album.class));
    }
}