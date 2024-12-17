package fact.it.albumservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumRequest {
    private String title;
    private String artist;

    private String label;
    private List<String> trackList;
}
