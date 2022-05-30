package ro.siit.LottoApp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.siit.LottoApp.entity.Player;
import ro.siit.LottoApp.repository.PlayerRepository;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    private PlayerRepository playerRepository;

    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/options")
    public String options(Model model, Principal principal){
        Player player = playerRepository.findByName(principal.getName());
        Long id = player.getId();
        model.addAttribute("id", id);
        return "options";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }



}
