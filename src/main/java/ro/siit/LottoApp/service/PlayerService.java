package ro.siit.LottoApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.siit.LottoApp.entity.Player;
import ro.siit.LottoApp.repository.PlayerRepository;

import java.util.*;

@Service
public class PlayerService {

    @Autowired //
    private PlayerRepository playerRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player getPlayerById(Long id) {
        List<Player> players = playerRepository.findAll();
        for (Player p : players) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public Set<Integer> generateWinningNumbers() {
        Set<Integer> winningNumbers = new HashSet<>();
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
        return winningNumbers;
    }

    public Player savePlayer(String name, String password) {
        return playerRepository.save(new Player(name, passwordEncoder.encode(password)));
    }
}
