package aurelion.controller;

import aurelion.enums.EnvironmentEnum;
import aurelion.model.DatabaseModel;
import aurelion.model.ProjectModel;
import aurelion.model.WildflyModel;
import aurelion.repository.WildflyRepository;
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

    @PostMapping("/update")
    public String updateProject(@RequestParam Long id,
                                @RequestParam String name,
                                @RequestParam ProjectModel project,
                                @RequestParam String environment,
                                @RequestParam DatabaseModel database,
                                RedirectAttributes redirectAttributes) {
        try {
            WildflyModel wildflyModel = wildflyRepository.findById(id).orElse(null);
            if (wildflyModel != null) {
                wildflyModel.setName(name);
                wildflyModel.setProject(project);
                wildflyModel.setEnvironment(EnvironmentEnum.valueOf(environment));
                wildflyModel.setDatabase(database);
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
