package ro.siit.LottoApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.siit.LottoApp.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    Player findByName(String name);

}
