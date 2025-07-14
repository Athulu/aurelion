package aurelion.controller;

import aurelion.enums.EnvironmentEnum;
import aurelion.model.DatabaseModel;
import aurelion.model.ProjectModel;
import aurelion.model.WildflyModel;
import aurelion.repository.WildflyRepository;
import aurelion.service.WildflyService;
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
    private WildflyRepository wildflyRepository;

    @Autowired
    private WildflyService wildflyService;

    @PostMapping("/update")
    public String updateProject(@RequestParam Long id,
                                @RequestParam String name,
                                @RequestParam String projectId,
                                @RequestParam String environment,
                                @RequestParam String databaseId,
                                RedirectAttributes redirectAttributes) {
        wildflyService.updateWildfly(id, name, Long.valueOf(projectId), environment, Long.valueOf(databaseId), redirectAttributes);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteProject(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        try {
            if (wildflyRepository.existsById(id)) {
                wildflyRepository.deleteById(id);
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
