package fact.it.artistservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value = "artists")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Artist {
    @Id
    private String id;
    private String name;
    private String imageUrl;
    private String biography;
    private String genre;
}
