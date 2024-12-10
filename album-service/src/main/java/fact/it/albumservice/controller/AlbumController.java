package fact.it.albumservice.controller;

import fact.it.albumservice.dto.AlbumRequest;
import fact.it.albumservice.dto.AlbumResponse;
import fact.it.albumservice.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AlbumResponse> getAllAlbums() {
        return albumService.getAllAlbums();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveAlbum(@RequestBody AlbumRequest albumRequest) {
        albumService.saveAlbum(albumRequest);
    }
}
