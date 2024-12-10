package fact.it.artistservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArtistResponse {
    private String id;
    private String name;
    private String imageUrl;
    private String biography;
    private String genre;
}