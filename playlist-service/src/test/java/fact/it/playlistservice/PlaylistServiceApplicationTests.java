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
    @Test
    void contextLoads() {
    }
}
