package fact.it.playlistservice.service;

import fact.it.playlistservice.dto.PlaylistRequest;
import fact.it.playlistservice.dto.PlaylistResponse;
import fact.it.playlistservice.model.Playlist;
import fact.it.playlistservice.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    public void createPlaylist(PlaylistRequest playlistRequest) {
        Playlist playlist = Playlist.builder()
                .name(playlistRequest.getName())
                .description(playlistRequest.getDescription())
                .owner(playlistRequest.getOwner())
                .lastModifiedDate(new Date())
                .songsList(playlistRequest.getSongsList())
                .build();
        playlistRepository.save(playlist);
    }

    public List<PlaylistResponse> getAllPlaylists() {
        return playlistRepository.findAll().stream().map(
                playlist -> PlaylistResponse.builder()
                        .id(playlist.getId())
                        .name(playlist.getName())
                        .description(playlist.getDescription())
                        .owner(playlist.getOwner())
                        .lastModifiedDate(playlist.getLastModifiedDate())
                        .songsList(playlist.getSongsList())
                        .build()
        ).toList();
    }
}
