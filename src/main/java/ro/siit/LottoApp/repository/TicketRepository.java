package ro.siit.LottoApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.siit.LottoApp.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
