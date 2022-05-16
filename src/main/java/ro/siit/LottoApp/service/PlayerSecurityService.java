package ro.siit.LottoApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.siit.LottoApp.entity.Player;
import ro.siit.LottoApp.repository.PlayerRepository;
import ro.siit.LottoApp.service.model.CustomUserDetails;

import java.util.Collections;
import java.util.Set;

@Service
public class PlayerSecurityService implements UserDetailsService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Player player = playerRepository.findByName(name);
        if(player == null) {
            throw new UsernameNotFoundException(name);
        }
        return new CustomUserDetails(player.getId(), player.getName(),
                player.getPassword(),
                Set.of(new SimpleGrantedAuthority(player.getRole())));
    }
}