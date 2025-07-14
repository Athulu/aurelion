package aurelion.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WildflyData {
    private Long id;
    private String wildflyName;
    private String projectName;
    private String environmentName;
    private String databasePath;
    private LocalDateTime lastModified;
}
