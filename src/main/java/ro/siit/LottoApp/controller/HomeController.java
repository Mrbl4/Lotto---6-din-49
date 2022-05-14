package ro.siit.LottoApp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

//    @PostMapping("/options")
//    public String options1(Model model){
////        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
////        Player player = (Player) auth.getPrincipal();
////        Long id = player.getId();
////        model.addAttribute("id", id);
//        return "redirect:/players/{id}/add-ticket";
//    }


}
