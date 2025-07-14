package aurelion.controller;

import aurelion.dto.WildflyData;
import aurelion.model.WildflyModel;
import aurelion.repository.WildflyRepository;
import aurelion.service.WildflyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class NavigationController {

    @Autowired
    private WildflyRepository wildflyRepository;

    @Autowired
    private WildflyService wildflyService;

    @GetMapping("/")
    public String index(Model model) {
//        List<WildflyModel> wildflyModelList = wildflyRepository.findAll();
        List<WildflyData> wildflyDataList = wildflyService.getWildflyDataList();
        model.addAttribute("wildflyDataList", wildflyDataList);
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
