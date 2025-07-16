package aurelion.controller;

import aurelion.service.WildflyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@AllArgsConstructor
@RequestMapping("/api/wildfly")
public class WildflyController {
    private final WildflyService wildflyService;

    @PostMapping("/update")
    public String updateProject(
            @RequestParam Long id,
            @RequestParam String name,
            @RequestParam Long projectId,
            @RequestParam String environment,
            @RequestParam Long databaseId,
            RedirectAttributes redirectAttributes) {
        wildflyService.updateWildfly(id, name, projectId, environment, databaseId, redirectAttributes);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteProject(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        wildflyService.deleteWildfly(id, redirectAttributes);
        return "redirect:/";
    }
}
