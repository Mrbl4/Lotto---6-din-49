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
import java.util.Random;


@Controller
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerService playerService;

        /**
     * lists the Tickets of the logged in Player
     * @param model
     * @param principal
     * @return
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
     * lists all the registered Tickets, for all the Players
     * @param model
     * @return
     */
    @RequestMapping("/all-tickets")
    public String listAllTickets(Model model) {
        List<Ticket> tickets = ticketRepository.findAll();
        model.addAttribute("tickets", tickets);
        return "list-all-tickets";
    }

    @GetMapping("/players/{id}/add-random-ticket-admin")
    public String randomTicketForm(Model model, @PathVariable("id") Long id) {
        model.addAttribute("id", id);
        return "addRandomTicketAdmin";
    }

    @PostMapping("/players/{id}/add-random-ticket-admin")
    public String addRandomTicket(Model model, @PathVariable("id") Long id) {
//        try {
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

    @RequestMapping("/tickets/delete-admin/{id}")
    public String deleteByIdAdmin(@PathVariable("id") Long id){
        ticketRepository.deleteById(id);
        return "redirect:/all-tickets";
    }

    @RequestMapping("/tickets/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        ticketRepository.deleteById(id);
        return "redirect:/tickets";
    }


}
