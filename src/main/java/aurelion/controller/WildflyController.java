package aurelion.controller;

import aurelion.model.WildflyModel;
import aurelion.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/api/wildfly")
public class WildflyController {

    @Autowired
    private ProjectRepository projectRepository;

    @PostMapping("/update")
    public String updateProject(@RequestParam Long id,
                                @RequestParam String name,
                                @RequestParam String client,
                                @RequestParam String environment,
                                @RequestParam String database,
                                RedirectAttributes redirectAttributes) {
        try {
            WildflyModel wildflyModel = projectRepository.findById(id).orElse(null);
            if (wildflyModel != null) {
                wildflyModel.setName(name);
                wildflyModel.setClient(client);
                wildflyModel.setEnvironment(environment);
                wildflyModel.setDatabase(database);
                projectRepository.save(wildflyModel);
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

    @PostMapping("/delete")
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
