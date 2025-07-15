package aurelion.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder(setterPrefix = "with")
public class WildflyData {
    private Long id;
    private String wildflyName;
    private String projectName;
    private String environmentName;
    private String databasePath;
    private LocalDateTime lastModified;
    private Long projectId;
    private Long databaseId;
}
