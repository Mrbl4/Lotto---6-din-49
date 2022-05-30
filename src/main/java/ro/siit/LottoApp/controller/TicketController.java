package ro.siit.LottoApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.siit.LottoApp.entity.Player;
import ro.siit.LottoApp.entity.Ticket;
import ro.siit.LottoApp.repository.PlayerRepository;
import ro.siit.LottoApp.repository.TicketRepository;
import ro.siit.LottoApp.service.PlayerService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerService playerService;

        /**
     * lists the Tickets of the logged-in Player
     */
    @RequestMapping("/tickets")
    public String listTicketsByUser(Model model, Model model1, Principal principal) {
        Player player = playerRepository.findByName(principal.getName());
        Long id = player.getId();
        List<Ticket> repoTickets = ticketRepository.findAll();
        List<Ticket> tickets = new ArrayList<>();
        for (Ticket t : repoTickets) {
            if (t.getPlayer().getId().equals(player.getId())) {
                tickets.add(t);
            }
        }
        model.addAttribute("tickets", tickets);
        model1.addAttribute("id", id);
        return "list-tickets";
    }

    /**
     * lists all the registered Tickets, by all Players
     */
    @RequestMapping("/all-tickets")
    public String listAllTickets(Model model) {
        List<Ticket> tickets = ticketRepository.findAll();
        model.addAttribute("tickets", tickets);
        return "list-all-tickets";
    }

/**
 * deletes a Ticket by id and redirects to the page that lists all the registered Tickets, by all Player
 */
    @RequestMapping("/tickets/delete-admin/{id}")
    public String deleteByIdAdmin(@PathVariable("id") Long id){
        ticketRepository.deleteById(id);
        return "redirect:/all-tickets";
    }

    /**
     * deletes a Ticket by id and redirects to the page that list all the Tickets of the logged-in Player
     */
    @RequestMapping("/tickets/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        ticketRepository.deleteById(id);
        return "redirect:/tickets";
    }


}
