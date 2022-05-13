package ro.siit.LottoApp.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.siit.LottoApp.IncorrectNumberException;
import ro.siit.LottoApp.entity.Player;
import ro.siit.LottoApp.entity.Ticket;
import ro.siit.LottoApp.repository.PlayerRepository;
import ro.siit.LottoApp.repository.TicketRepository;
import ro.siit.LottoApp.service.PlayerService;

import java.util.List;

@Controller
//@RequestMapping("/players")
public class PlayerController {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    PlayerService playerService;

//    @RequestMapping("/")
    @RequestMapping("/players")
    public String list(Model model){
        List<Player> players = playerRepository.findAll();
        model.addAttribute("players", players);
        return "list";
    }

//    @GetMapping("/add")
    @GetMapping("/players/add")
    public String form(){
        return "addUser";
    }


//    @PostMapping("/add")
    @PostMapping("/players/add")
    public String addPlayer(Model model, @RequestParam("name") String name){
        playerRepository.save(new Player(name));
      return "redirect:/players/";
    }

//    @GetMapping("/{id}/add-ticket")
    @GetMapping("/players/{id}/add-ticket")
    public String ticketForm(Model model, @PathVariable("id") Long id){
        System.out.println("add ticket id = " + id);
        model.addAttribute("id", id);
        return "addTicket";
    }

//    @PostMapping("/{id}/add-ticket")
    @PostMapping("/players/{id}/add-ticket")
    public String addTicket(Model model,
                            @RequestParam("noOne") int number1,
                            @RequestParam("noTwo") int number2,
                            @RequestParam("noThree") int number3,
                            @RequestParam("noFour") int number4,
                            @RequestParam("noFive") int number5,
                            @RequestParam("noSix") int number6,
                            @PathVariable("id") Long id) {
//        try {
            Player player = playerService.getPlayerById(id);
            Ticket ticket = new Ticket(number1, number2, number3, number4, number5, number6);
            ticket.setPlayer(player);
            ticketRepository.save(ticket);
            return "redirect:/tickets";
            //daca introduc nr corecte imi creaza tichetele, dar nu mai merge list-ul de pe tickets
            //daca introduc nr incorect imi afiseaza "incorrect number"
//        } catch (IncorrectNumberException e) {
//            return "ticket-exception";
//        }
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

}
