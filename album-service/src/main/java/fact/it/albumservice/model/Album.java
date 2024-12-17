package fact.it.albumservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(value = "albums")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Album {
    @Id
    private String id;
    private String title;
    private String artist;

    private String label;
    private List<String> trackList;
}

