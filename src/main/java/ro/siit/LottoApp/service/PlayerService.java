package ro.siit.LottoApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.siit.LottoApp.ExistingUserException;
import ro.siit.LottoApp.entity.Player;
import ro.siit.LottoApp.repository.PlayerRepository;

import java.util.*;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Player getPlayerById(Long id) {
        List<Player> players = playerRepository.findAll();
        for (Player p : players) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public Player savePlayer(String username, String password) {
        Player existingUser = playerRepository.findByName(username);
        if(existingUser != null) {
            throw new ExistingUserException();
        }

        Player entity = new Player(username, passwordEncoder.encode(password));
        entity.setRole("ROLE_USER");
        return playerRepository.save(entity);
    }


}
