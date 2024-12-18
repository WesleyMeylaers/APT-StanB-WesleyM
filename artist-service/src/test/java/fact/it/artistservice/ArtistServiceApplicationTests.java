package fact.it.artistservice;

import fact.it.artistservice.service.ArtistService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import fact.it.artistservice.dto.ArtistRequest;
import fact.it.artistservice.dto.ArtistResponse;
import fact.it.artistservice.model.Artist;
import fact.it.artistservice.repository.ArtistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ArtistServiceApplicationTests {

    @Mock
    private ArtistRepository artistRepository;

    @InjectMocks
    private ArtistService artistService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllArtists() {
        // Arrange
        Artist artist1 = Artist.builder()
                .id("1")
                .name("Artist One")
                .imageUrl("http://example.com/artist1.jpg")
                .biography("Biography of artist one.")
                .genre("Rock")
                .build();

        Artist artist2 = Artist.builder()
                .id("2")
                .name("Artist Two")
                .imageUrl("http://example.com/artist2.jpg")
                .biography("Biography of artist two.")
                .genre("Pop")
                .build();

        when(artistRepository.findAll()).thenReturn(List.of(artist1, artist2));

        // Act
        List<ArtistResponse> artistResponses = artistService.getAllArtists();

        // Assert
        assertEquals(2, artistResponses.size());
        assertEquals("Artist One", artistResponses.get(0).getName());
        assertEquals("Rock", artistResponses.get(0).getGenre());
        assertEquals("Artist Two", artistResponses.get(1).getName());
        assertEquals("Pop", artistResponses.get(1).getGenre());
        verify(artistRepository, times(1)).findAll();
    }

    @Test
    void testSaveArtist() {
        // Arrange
        ArtistRequest artistRequest = ArtistRequest.builder()
                .name("New Artist")
                .imageUrl("http://example.com/newartist.jpg")
                .biography("Biography of new artist.")
                .genre("Jazz")
                .build();

        // Act
        artistService.saveArtist(artistRequest);

        // Assert
        verify(artistRepository, times(1)).save(any(Artist.class));
    }
}