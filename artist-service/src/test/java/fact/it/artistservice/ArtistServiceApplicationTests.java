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

    @Test
    void contextLoads() {
    }
}