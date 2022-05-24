package ro.siit.LottoApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.siit.LottoApp.entity.Player;
import ro.siit.LottoApp.entity.Ticket;
import ro.siit.LottoApp.repository.PlayerRepository;
import ro.siit.LottoApp.repository.TicketRepository;

import java.util.*;

@Service
public class GameService {

    private Set<Integer> winningNumbers = new HashSet<>();
    private Set<Integer> lastWinningNumbers = new HashSet<>();

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public Set<Integer> getWinningNumbers() {
        return new HashSet<>(winningNumbers);
    }

    public void generateWinningNumbers() {
        lastWinningNumbers = winningNumbers;
        winningNumbers.clear();
        resetStatistics();
        Random randomizer = new Random();
        int numberOfNumbers = 0;
        while (numberOfNumbers < 6) {
            int number = randomizer.nextInt(1, 49);
            if (!winningNumbers.contains(number)) {
                numberOfNumbers++;
                winningNumbers.add(number);
            }
        }
        setAllStatistics();
    }

    public void setAllStatistics(){
        List<Player> players= playerRepository.findAll();
        for (Player p:players){
            calculateStatistics(p);
        }
    }

    public void calculateStatistics(Player player){
            List<Ticket> tickets = ticketRepository.findByPlayerId(player.getId());
            for (Ticket t: tickets){
                int n = 0;
                for (Integer i: winningNumbers){
//                    if (Integer.valueOf(t.getNoOne()).equals(i)){
                    if (t.getNoOne()==i){
                        n++;
                        t.setWinningNumbers(n);}
                    if (t.getNoTwo()==i){
                            n++;
                            t.setWinningNumbers(n);}
                    if (t.getNoThree()==i){
                                n++;
                                t.setWinningNumbers(n);}
                    if (t.getNoFour()==i){
                                    n++;
                                    t.setWinningNumbers(n);}
                    if (t.getNoFive()==i){
                                        n++;
                                        t.setWinningNumbers(n);}
                    if (t.getNoSix()==i){
                                            n++;
                                            t.setWinningNumbers(n);
                    }
                }
                ticketRepository.save(t);
            }
        }

        private void resetStatistics(){
        List<Ticket> tickets = ticketRepository.findAll();
        for (Ticket t:tickets){
            t.setWinningNumbers(0);
            ticketRepository.save(t);
        }
    }
}
