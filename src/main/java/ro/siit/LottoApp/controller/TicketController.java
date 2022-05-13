package ro.siit.LottoApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.siit.LottoApp.entity.Ticket;
import ro.siit.LottoApp.repository.TicketRepository;

import java.util.List;
import java.util.UUID;

@Controller
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @RequestMapping("/tickets")
    public String list(Model model) {
        List<Ticket> tickets = ticketRepository.findAll();
        model.addAttribute("tickets", tickets);
        return "list-tickets";
    }

    @GetMapping("/tickets/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        ticketRepository.deleteById(id);
        return "redirect:/tickets";
    }
}
