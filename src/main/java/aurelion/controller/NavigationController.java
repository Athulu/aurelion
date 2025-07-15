package aurelion.controller;

import aurelion.dto.WildflyData;
import aurelion.model.WildflyModel;
import aurelion.repository.DatabaseRepository;
import aurelion.repository.ProjectRepository;
import aurelion.repository.WildflyRepository;
import aurelion.service.WildflyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class NavigationController {
    private final WildflyRepository wildflyRepository;
    private final DatabaseRepository databaseRepository;
    private final ProjectRepository projectRepository;
    private final WildflyService wildflyService;

    @GetMapping("/")
    public String index(Model model) {
        List<WildflyData> wildflyDataList = wildflyService.getWildflyDataList();
        model.addAttribute("wildflyDataList", wildflyDataList);
        model.addAttribute("databaseList", databaseRepository.findAll());
        model.addAttribute("projectList", projectRepository.findAll());
        return "index";
    }

    @GetMapping("/charts")
    public String charts(Model model) {
        List<WildflyModel> wildflyModelList = wildflyRepository.findAll();
        model.addAttribute("wildflyModelList", wildflyModelList);
        return "charts";
    }

    @GetMapping("/databases")
    public String databases(Model model) {
        List<WildflyModel> wildflyModelList = wildflyRepository.findAll();
        model.addAttribute("wildflyModelList", wildflyModelList);
        return "tables";
    }
}
