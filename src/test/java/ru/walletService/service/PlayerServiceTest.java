package ru.walletService.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.walletService.repository.entity.Player;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerServiceTest {
    PlayerService playerService;
    @BeforeEach
    public void init(){
        playerService = new PlayerService();
    }


    @Test
    public void testAddPlayer(){
        Player player = new Player("Kraken", "123");
        boolean isAdded = playerService.addPlayer(player);

        assertTrue(isAdded);
    }

    @Test
    public void testAddPlayerIfExist(){
        Player player1 = new Player("Kraken", "123");
        Player player2 = new Player("Kraken", "qwerty");
        boolean isAdded = playerService.addPlayer(player2);

        assertTrue(isAdded);
    }

    @Test
    public void testAuthentication(){
        Player player1 = new Player("Kraken", "123");
        Player player2 = new Player("lego", "qwerty");
        playerService.addPlayer(player1);
        playerService.addPlayer(player2);

        Player resultPlayer = playerService.authenticationUser("Kraken", "123");
        assertTrue(player1 == resultPlayer);

        resultPlayer = playerService.authenticationUser("Kraken", "1234");
        assertNull(resultPlayer);

        resultPlayer = playerService.authenticationUser("admin", "admin");
        assertNull(resultPlayer);
    }

}