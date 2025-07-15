package aurelion.service;

import aurelion.dto.WildflyData;
import aurelion.enums.EnvironmentEnum;
import aurelion.model.WildflyModel;
import aurelion.repository.DatabaseRepository;
import aurelion.repository.ProjectRepository;
import aurelion.repository.WildflyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class WildflyService {
    private final DatabaseRepository databaseRepository;
    private final ProjectRepository projectRepository;
    private final WildflyRepository wildflyRepository;

    public void updateWildfly(final Long id, final String name, final Long projectId, final String environment, final Long databaseId, RedirectAttributes redirectAttributes) {
        try {
            WildflyModel wildflyModel = wildflyRepository.findById(id).orElse(null);
            if (wildflyModel != null) {
                wildflyModel.setName(name);
                wildflyModel.setProject(projectRepository.findById(projectId).orElse(null));
                wildflyModel.setEnvironment(EnvironmentEnum.valueOf(environment));
                wildflyModel.setDatabase(databaseRepository.findById(databaseId).orElse(null));
                wildflyRepository.save(wildflyModel);
                redirectAttributes.addFlashAttribute("message", "Project updated successfully!");
                redirectAttributes.addFlashAttribute("messageType", "success");
            } else {
                redirectAttributes.addFlashAttribute("message", "Project not found!");
                redirectAttributes.addFlashAttribute("messageType", "danger");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error updating project: " + e.getMessage());
            redirectAttributes.addFlashAttribute("messageType", "danger");
        }
    }

    public List<WildflyData> getWildflyDataList() {
        List<WildflyModel> wildflyModelList = wildflyRepository.findAll();
        List<WildflyData> wildflyDataList = new ArrayList<>();

        for (WildflyModel wildflyModel : wildflyModelList) {
            wildflyDataList.add(WildflyData.builder()
                    .withId(wildflyModel.getId())
                    .withWildflyName(wildflyModel.getName())
                    .withProjectName(wildflyModel.getProject().getName())
                    .withProjectId(wildflyModel.getProject().getId())
                    .withEnvironmentName(wildflyModel.getEnvironment().name())
                    .withDatabasePath(wildflyModel.getDatabase().getPath())
                    .withDatabaseId(wildflyModel.getDatabase().getId())
                    .withLastModified(wildflyModel.getLastModified())
                    .build()
            );
        }
        return wildflyDataList;
    }
}
