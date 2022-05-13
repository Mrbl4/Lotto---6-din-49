package ro.siit.LottoApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.siit.LottoApp.service.PlayerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class GameController {

    @Autowired
    private PlayerService playerService;

    @RequestMapping("/start")
    public String startGame(Model model){
        Set<Integer> winningNumbers = playerService.generateWinningNumbers();
        model.addAttribute("numbers", winningNumbers);
        return "winning";
    }
}
