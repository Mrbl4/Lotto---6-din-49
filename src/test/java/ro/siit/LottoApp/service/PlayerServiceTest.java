package ro.siit.LottoApp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ro.siit.LottoApp.entity.Player;
import ro.siit.LottoApp.repository.PlayerRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private PlayerService playerService;

    @Test
    void savePlayer() {

        // given
        Mockito.when(playerRepository.findByName("Ana")).thenReturn(null);
        Mockito.when(passwordEncoder.encode("abc")).thenReturn("abcEncoded");

        Player toBeSaved = new Player("Ana", "abcEncoded");
        toBeSaved.setRole("ROLE_USER");

        Player expected = new Player("Ana", "abcEncoded");
        expected.setRole("ROLE_USER");

        Mockito.when(playerRepository.save(toBeSaved)).thenReturn(expected);

        // when
        Player actual = playerService.savePlayer("Ana", "abc");


        // then
        assertEquals(expected, actual);
    }

    @Test
    void saveUser_ExistingEntry() {
        // given
        Mockito.when(playerRepository.findByName("Mira")).thenReturn(new Player("a","a"));

        // when
        RuntimeException actual = assertThrows(RuntimeException.class,
                () -> playerService.savePlayer("Mira", "1234"));

        // then
        assertEquals("duplicate username", actual.getMessage());
    }
}