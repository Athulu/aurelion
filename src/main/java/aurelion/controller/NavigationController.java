package aurelion.controller;

import aurelion.model.Project;
import aurelion.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping("/projects/update")
    public String updateProject(@RequestParam Long id,
                               @RequestParam String name,
                               @RequestParam String client,
                               @RequestParam String environment,
                               @RequestParam String database,
                               RedirectAttributes redirectAttributes) {
        try {
            Project project = projectRepository.findById(id).orElse(null);
            if (project != null) {
                project.setName(name);
                project.setClient(client);
                project.setEnvironment(environment);
                project.setDatabase(database);
                projectRepository.save(project);
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
        return "redirect:/";
    }

    @PostMapping("/projects/delete")
    public String deleteProject(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        try {
            if (projectRepository.existsById(id)) {
                projectRepository.deleteById(id);
                redirectAttributes.addFlashAttribute("message", "Project deleted successfully!");
                redirectAttributes.addFlashAttribute("messageType", "success");
            } else {
                redirectAttributes.addFlashAttribute("message", "Project not found!");
                redirectAttributes.addFlashAttribute("messageType", "danger");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error deleting project: " + e.getMessage());
            redirectAttributes.addFlashAttribute("messageType", "danger");
        }
        return "redirect:/";
    }
}
