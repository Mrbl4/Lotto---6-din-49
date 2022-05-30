package ro.siit.LottoApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.siit.LottoApp.entity.Ticket;
import ro.siit.LottoApp.repository.TicketRepository;
import ro.siit.LottoApp.service.GameService;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private TicketRepository ticketRepository;

    @RequestMapping("/start")
    public String startGame(Model model){
        gameService.generateWinningNumbers();
        Set<Integer> winningNumbers = gameService.getWinningNumbers();
        model.addAttribute("numbers", winningNumbers);
        return "winning";
    }

    @RequestMapping("/all-statistics")
    public String getStatistics(Model model, Model model1, Principal principal){
        Set<Integer> winningNumbers = gameService.getWinningNumbers();
        List<Ticket> tickets = ticketRepository.findAll();
        model.addAttribute("tickets", tickets);
        model1.addAttribute("numbers", winningNumbers);
        return "statistics";
    }

    @RequestMapping("/winner")
    public String getWinningNumbers(Model model){
        Set<Integer> numbers = gameService.getWinningNumbers();
        model.addAttribute("numbers", numbers);
        return "winning";
    }
}
