package fact.it.artistservice;

import fact.it.artistservice.dto.ArtistRequest;
import fact.it.artistservice.dto.ArtistResponse;
import fact.it.artistservice.model.Artist;
import fact.it.artistservice.repository.ArtistRepository;
import fact.it.artistservice.service.ArtistService;
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
public class ArtistServiceUnitTests {

    @InjectMocks
    private ArtistService artistService;

    @Mock
    private ArtistRepository artistRepository;

    @BeforeEach
    void setUp() {
        // Setup voor elke test
    }

    @Test
    public void testGetAllArtists() {
        // Arrange
        Artist artist1 = new Artist();
        artist1.setId("1");
        artist1.setName("Artist 1");
        artist1.setImageUrl("http://image1.com");
        artist1.setBiography("Biography 1");
        artist1.setGenre("Genre 1");

        Artist artist2 = new Artist();
        artist2.setId("2");
        artist2.setName("Artist 2");
        artist2.setImageUrl("http://image2.com");
        artist2.setBiography("Biography 2");
        artist2.setGenre("Genre 2");

        when(artistRepository.findAll()).thenReturn(Arrays.asList(artist1, artist2));

        // Act
        List<ArtistResponse> result = artistService.getAllArtists();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Artist 1", result.get(0).getName());
        assertEquals("http://image2.com", result.get(1).getImageUrl());
        verify(artistRepository, times(1)).findAll();
    }

    @Test
    public void testSaveArtist() {
        // Arrange
        ArtistRequest artistRequest = new ArtistRequest();
        artistRequest.setName("New Artist");
        artistRequest.setImageUrl("http://newartist.com");
        artistRequest.setBiography("New Biography");
        artistRequest.setGenre("New Genre");

        Artist artist = new Artist();
        artist.setName("New Artist");
        artist.setImageUrl("http://newartist.com");
        artist.setBiography("New Biography");
        artist.setGenre("New Genre");

        when(artistRepository.save(any(Artist.class))).thenReturn(artist);

        // Act
        artistService.saveArtist(artistRequest);

        // Assert
        verify(artistRepository, times(1)).save(any(Artist.class));
    }
}
