package fact.it.playlistservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(value = "playlists")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Playlist {
    @Id
    private String id;
    private String name;
    private String description;
    private String owner;
    private Date lastModifiedDate;
    private List<String> songsList;
}
