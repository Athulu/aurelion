package aurelion.service;

import aurelion.dto.WildflyData;
import aurelion.model.WildflyModel;
import aurelion.repository.DatabaseRepository;
import aurelion.repository.ProjectRepository;
import aurelion.repository.WildflyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class WildflyService {
    private final DatabaseRepository databaseRepository;
    private final ProjectRepository projectRepository;
    private final WildflyRepository wildflyRepository;

    public List<WildflyData> getWildflyDataList() {
        List<WildflyModel> wildflyModelList = wildflyRepository.findAll();
        List<WildflyData> wildflyDataList = new ArrayList<>();

        //    private String wildflyName;
        //    private String projectName;
        //    private String environmentName;
        //    private String databasePath;
        //    private LocalDateTime lastModified;
        for (WildflyModel wildflyModel : wildflyModelList) {
            WildflyData wildflyData = new WildflyData();
            wildflyData.setId(wildflyModel.getId());
            wildflyData.setWildflyName(wildflyModel.getName());
            wildflyData.setProjectName(wildflyModel.getProject().getName());
            wildflyData.setEnvironmentName(wildflyModel.getEnvironment().name());
            wildflyData.setDatabasePath(wildflyModel.getDatabase().getPath());
            wildflyData.setLastModified(wildflyModel.getLastModified());
            wildflyDataList.add(wildflyData);
        }

        return wildflyDataList;
    }
}
