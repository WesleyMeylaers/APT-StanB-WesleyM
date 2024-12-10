package fact.it.artistservice.service;

import fact.it.artistservice.dto.ArtistRequest;
import fact.it.artistservice.dto.ArtistResponse;
import fact.it.artistservice.model.Artist;
import fact.it.artistservice.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;

    public List<ArtistResponse> getAllArtists() {
        return artistRepository.findAll().stream()
                .map(artist -> ArtistResponse.builder()
                        .id(artist.getId())
                        .name(artist.getName())
                        .imageUrl(artist.getImageUrl())
                        .biography(artist.getBiography())
                        .genre(artist.getGenre())
                        .build())
                .toList();
    }

    public void saveArtist(ArtistRequest artistRequest) {
        Artist artist = new Artist();
        artist.setName(artistRequest.getName());
        artist.setImageUrl(artistRequest.getImageUrl());
        artist.setBiography(artistRequest.getBiography());
        artist.setGenre(artistRequest.getGenre());

        artistRepository.save(artist);
    }
}
