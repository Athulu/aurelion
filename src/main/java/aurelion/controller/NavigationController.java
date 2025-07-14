package aurelion.controller;

import aurelion.model.WildflyModel;
import aurelion.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class NavigationController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/")
    public String index(Model model) {
        List<WildflyModel> wildflyModelList = projectRepository.findAll();
        model.addAttribute("wildflyModelList", wildflyModelList);
        return "index";
    }

    @GetMapping("/charts")
    public String charts(Model model) {
        List<WildflyModel> wildflyModelList = projectRepository.findAll();
        model.addAttribute("wildflyModelList", wildflyModelList);
        return "charts";
    }

    @GetMapping("/databases")
    public String databases(Model model) {
        List<WildflyModel> wildflyModelList = projectRepository.findAll();
        model.addAttribute("wildflyModelList", wildflyModelList);
        return "tables";
    }
}
