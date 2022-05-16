package ro.siit.LottoApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.siit.LottoApp.entity.Player;
import ro.siit.LottoApp.entity.Ticket;
import ro.siit.LottoApp.repository.PlayerRepository;
import ro.siit.LottoApp.repository.TicketRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class GameService {

    private Set<Integer> winningNumbers = new HashSet<>();

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public Set<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public void generateWinningNumbers() {
        Random randomizer = new Random();
        int numberOfNumbers = 0;
        while (numberOfNumbers < 6) {
            int number = randomizer.nextInt(1, 49);
            if (!winningNumbers.contains(number)) {
                numberOfNumbers++;
                winningNumbers.add(number);
            }
        }

        System.out.println("The winning numbers are : ");
        for (Integer i : winningNumbers) {
            System.out.print(i + " ");
        }
    }

    public void getStatistics(Player player){
//        List<Player> players = playerRepository.findAll();
//        for (Player p: players){
            List<Ticket> tickets = ticketRepository.findByPlayerId(player.getId());
            for (Ticket t: tickets){
                int n = 0;
                for (Integer i: winningNumbers){
                    if (Integer.valueOf(t.getNoOne()).equals(i)){
                        t.setWinningNumbers(n++);}
                    if (Integer.valueOf(t.getNoTwo()).equals(i)){
                            t.setWinningNumbers(n++);}
                    if (Integer.valueOf(t.getNoThree()).equals(i)){
                                t.setWinningNumbers(n++);}
                    if (Integer.valueOf(t.getNoFour()).equals(i)){
                                    t.setWinningNumbers(n++);}
                    if (Integer.valueOf(t.getNoFive()).equals(i)){
                                        t.setWinningNumbers(n++);}
                    if (Integer.valueOf(t.getNoSix()).equals(i)){
                                            t.setWinningNumbers(n++);
                    }
                }
            }
        }
    }
