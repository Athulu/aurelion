package aurelion.controller;

import aurelion.model.WildflyModel;
import aurelion.repository.WildflyRepository;
import aurelion.service.NavigationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class NavigationController {
    private final WildflyRepository wildflyRepository;
    private final NavigationService navigationService;

    @GetMapping("/")
    public String index(Model model) {
        navigationService.setAttributes(model);
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
