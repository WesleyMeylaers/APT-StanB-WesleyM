package fact.it.artistservice.controller;

import fact.it.artistservice.dto.ArtistRequest;
import fact.it.artistservice.dto.ArtistResponse;
import fact.it.artistservice.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ArtistResponse> getAllArtists() {
        return artistService.getAllArtists();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveArtist(@RequestBody ArtistRequest artistRequest) {
        artistService.saveArtist(artistRequest);
    }
}
