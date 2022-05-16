package ro.siit.LottoApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ro.siit.LottoApp.entity.Player;
import ro.siit.LottoApp.repository.PlayerRepository;
import ro.siit.LottoApp.service.PlayerService;

@Controller
public class RegistrationController {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerService playerService;

    @GetMapping("/register")
    public String form(){
        return "createUser";
    }

    @PostMapping("/register")
    public String addPlayer(Model model, @RequestParam("name") String name, @RequestParam("password") String password){
        playerService.savePlayer(name, password);
        return "redirect:/login";
    }
}
