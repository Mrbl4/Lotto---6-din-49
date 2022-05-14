package ro.siit.LottoApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.siit.LottoApp.entity.Player;
import ro.siit.LottoApp.entity.Ticket;
import ro.siit.LottoApp.repository.PlayerRepository;
import ro.siit.LottoApp.repository.TicketRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PlayerRepository playerRepository;

//    @RequestMapping("/tickets")
//    public String list(Model model) {
//        List<Ticket> tickets = ticketRepository.findAll();
//        model.addAttribute("tickets", tickets);
//        return "list-tickets";
//    }

    @GetMapping("/tickets/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        ticketRepository.deleteById(id);
        return "redirect:/tickets";
    }

    @RequestMapping("/tickets")
    public String listTicketsByUser(Model model, Principal principal) {
        Player player = playerRepository.findByName(principal.getName());
        List<Ticket> repoTickets = ticketRepository.findAll();
        List<Ticket> tickets = new ArrayList<>();
        for (Ticket t:repoTickets){
            if (t.getPlayer().getId().equals(player.getId())){
                tickets.add(t);
            };
        }
        model.addAttribute("tickets", tickets);
        return "list-tickets";
    }
}
