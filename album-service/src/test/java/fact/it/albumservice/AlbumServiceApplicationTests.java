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
    
}