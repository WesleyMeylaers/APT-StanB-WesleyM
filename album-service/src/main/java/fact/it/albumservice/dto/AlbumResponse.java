package fact.it.albumservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlbumResponse {
    private String id;
    private String title;
    private String artist;
    private Date releaseDate;
    private String label;
    private List<String> trackList;
}
