package aurelion.controller;

import aurelion.model.Project;
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
        List<Project> projects = projectRepository.findAll();
        model.addAttribute("projects", projects);
        return "index";
    }

    @GetMapping("/projects")
    public String projects(Model model) {
        List<Project> projects = projectRepository.findAll();
        model.addAttribute("projects", projects);
        return "charts";
    }

    @GetMapping("/databases")
    public String databases(Model model) {
        List<Project> projects = projectRepository.findAll();
        model.addAttribute("projects", projects);
        return "tables";
    }
}
