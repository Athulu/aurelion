package aurelion.service;

import aurelion.dto.WildflyData;
import aurelion.repository.DatabaseRepository;
import aurelion.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@AllArgsConstructor
public class NavigationService {
    private final DatabaseRepository databaseRepository;
    private final ProjectRepository projectRepository;
    private final WildflyService wildflyService;

    public void setAttributes(Model model){
        List<WildflyData> wildflyDataList = wildflyService.getWildflyDataList();
        model.addAttribute("wildflyDataList", wildflyDataList);
        model.addAttribute("databaseList", databaseRepository.findAll());
        model.addAttribute("projectList", projectRepository.findAll());
    }

}
