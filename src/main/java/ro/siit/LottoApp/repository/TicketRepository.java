package ro.siit.LottoApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.siit.LottoApp.entity.Ticket;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByPlayerId(Long id);

}
