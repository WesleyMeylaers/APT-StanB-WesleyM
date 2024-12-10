package fact.it.playlistservice.controller;

import fact.it.playlistservice.dto.PlaylistRequest;
import fact.it.playlistservice.dto.PlaylistResponse;
import fact.it.playlistservice.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/playlists")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPlaylist(@RequestBody PlaylistRequest playlistRequest) {
        playlistService.createPlaylist(playlistRequest);
    }

    // Haal alle playlists op
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PlaylistResponse> getAllPlaylists() {
        return playlistService.getAllPlaylists();
    }
}
