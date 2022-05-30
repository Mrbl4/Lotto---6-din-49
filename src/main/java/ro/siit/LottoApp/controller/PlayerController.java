package ro.siit.LottoApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.siit.LottoApp.entity.Player;
import ro.siit.LottoApp.entity.Ticket;
import ro.siit.LottoApp.repository.PlayerRepository;
import ro.siit.LottoApp.repository.TicketRepository;
import ro.siit.LottoApp.service.GameService;
import ro.siit.LottoApp.service.PlayerService;

import java.security.Principal;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Controller
public class PlayerController {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    PlayerService playerService;

    @Autowired
    GameService gameService;

    @RequestMapping("/players")
    public String list(Model model){
        List<Player> players = playerRepository.findAll();
        model.addAttribute("players", players);
        return "list";
    }


    @GetMapping("/players/add")
    public String form(){
        return "addPlayer";
    }


    @PostMapping("/players/add")
    public String addPlayer(Model model, @RequestParam("name") String name, @RequestParam("password") String password){
        playerService.savePlayer(name, password);
      return "redirect:/players/";
    }


    @GetMapping("/players/{id}/add-ticket")
    public String ticketForm(Model model, @PathVariable("id") Long id){
        model.addAttribute("id", id);
        return "addTicket";
    }


    @PostMapping("/players/{id}/add-ticket")
    public String addTicket(Model model,
                            @RequestParam("noOne") int number1,
                            @RequestParam("noTwo") int number2,
                            @RequestParam("noThree") int number3,
                            @RequestParam("noFour") int number4,
                            @RequestParam("noFive") int number5,
                            @RequestParam("noSix") int number6,
                            @PathVariable("id") Long id) {
            Player player = playerService.getPlayerById(id);
            Ticket ticket = new Ticket(number1, number2, number3, number4, number5, number6);
            ticket.setPlayer(player);
            ticketRepository.save(ticket);
            return "redirect:/tickets";
    }

    @GetMapping("/players/{id}/add-random-ticket")
    public String randomTicketForm(Model model, @PathVariable("id") Long id){
        model.addAttribute("id", id);
        return "addRandomTicket";
    }

    @PostMapping("/players/{id}/add-random-ticket")
    public String addRandomTicket(Model model, @PathVariable("id") Long id) {
        Player player = playerService.getPlayerById(id);
        Random random = new Random();
        int[] generatedNumbers = new int[6];
        for (int i = 0; i <= 5; i++) {
            generatedNumbers[i] = random.nextInt(1, 49);
        }
        Ticket ticket = new Ticket(generatedNumbers[0], generatedNumbers[1], generatedNumbers[2], generatedNumbers[3], generatedNumbers[4], generatedNumbers[5]);
        ticket.setPlayer(player);
        ticketRepository.save(ticket);
        return "redirect:/tickets";
    }

    @RequestMapping("/players/delete/{id}")
    public String deleteById(@PathVariable("id") Long id){
        List<Ticket> tickets = ticketRepository.findAll();
        for (Ticket t:tickets){
            if (t.getPlayer().getId().equals(id)){
                ticketRepository.delete(t);
            }
        }
        playerRepository.deleteById(id);
        return "redirect:/players";
    }

    @GetMapping("/players/{id}/add-ticket-admin")
    public String ticketFormAdmin(Model model, @PathVariable("id") Long id){
        model.addAttribute("id", id);
        return "addTicketAdmin";
    }


    @PostMapping("/players/{id}/add-ticket-admin")
    public String addTicketAdmin(Model model,
                            @RequestParam("noOne") int number1,
                            @RequestParam("noTwo") int number2,
                            @RequestParam("noThree") int number3,
                            @RequestParam("noFour") int number4,
                            @RequestParam("noFive") int number5,
                            @RequestParam("noSix") int number6,
                            @PathVariable("id") Long id) {
        Player player = playerService.getPlayerById(id);
        Ticket ticket = new Ticket(number1, number2, number3, number4, number5, number6);
        ticket.setPlayer(player);
        ticketRepository.save(ticket);
        return "redirect:/all-tickets";
    }

    @RequestMapping("/statistics")
    public String getStatistics(Model model, Model model1, Principal principal){
        Set<Integer> winningNumbers = gameService.getWinningNumbers();
        Player player = playerRepository.findByName(principal.getName());
        List<Ticket> tickets = ticketRepository.findByPlayerId(player.getId());
        model.addAttribute("tickets", tickets);
        model1.addAttribute("numbers", winningNumbers);
        return "statistics";
    }

    @GetMapping("/players/{id}/add-random-ticket-admin")
    public String randomTicketFormAdmin(Model model, @PathVariable("id") Long id) {
        model.addAttribute("id", id);
        return "addRandomTicketAdmin";
    }

    @PostMapping("/players/{id}/add-random-ticket-admin")
    public String addRandomTicketAdmin(Model model, @PathVariable("id") Long id) {
        Player player = playerService.getPlayerById(id);
        Random random = new Random();
        int[] generatedNumbers = new int[6];
        for (int i = 0; i <= 5; i++) {
            generatedNumbers[i] = random.nextInt(1, 49);
        }
        Ticket ticket = new Ticket(generatedNumbers[0], generatedNumbers[1], generatedNumbers[2], generatedNumbers[3], generatedNumbers[4], generatedNumbers[5]);
        ticket.setPlayer(player);
        ticketRepository.save(ticket);
        return "redirect:/all-tickets";
    }

}
