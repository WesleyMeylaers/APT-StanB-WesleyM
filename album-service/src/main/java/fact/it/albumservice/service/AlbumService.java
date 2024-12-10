package fact.it.albumservice.service;

import fact.it.albumservice.dto.AlbumRequest;
import fact.it.albumservice.dto.AlbumResponse;
import fact.it.albumservice.model.Album;
import fact.it.albumservice.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;

    public List<AlbumResponse> getAllAlbums() {
        return albumRepository.findAll().stream()
                .map(album -> AlbumResponse.builder()
                        .id(album.getId())
                        .title(album.getTitle())
                        .artist(album.getArtist())
                        .releaseDate(album.getReleaseDate())
                        .label(album.getLabel())
                        .trackList(album.getTrackList())
                        .build())
                .toList();
    }

    public void saveAlbum(AlbumRequest albumRequest) {
        Album album = Album.builder()
                .title(albumRequest.getTitle())
                .artist(albumRequest.getArtist())
                .releaseDate(albumRequest.getReleaseDate())
                .label(albumRequest.getLabel())
                .trackList(albumRequest.getTrackList())
                .build();
        albumRepository.save(album);
    }
}
