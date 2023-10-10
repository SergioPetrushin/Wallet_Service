package ru.walletService.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.walletService.repository.entity.Player;
import ru.walletService.repository.entity.Transaction;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionServiceTest {
    TransactionService transactionService;
    @BeforeEach
    public void init(){
        transactionService = new TransactionService();
    }

    @Test
    public void testDebet(){
        Player player = new Player("Razor", "Razor");
        player.setBalance(20_000);

        Transaction transaction = new Transaction(1, 5000);

        Assertions.assertDoesNotThrow(() -> {
            transactionService.debet(player, transaction);
        });
    }

    @Test
    public void testDebetIfWrongValue(){
        Player player = new Player("Razor", "Razor");
        Transaction transaction = new Transaction(1, -5000);

        transactionService.debet(player, transaction);

        assertEquals(0, player.getBalance());

    }

    @Test
    public void testDebetIfBigValue(){
        double currentBalance = 100_000;
        Player player = new Player("Razor", "Razor");
        player.setBalance(currentBalance);
        Transaction transaction = new Transaction(1, 500_000);

        transactionService.debet(player, transaction);

        assertEquals(currentBalance, player.getBalance());

    }

    @Test
    public void testCredit(){
        double creditPrice = 100_000;
        Player player = new Player("Razor", "Razor");
        Transaction transaction = new Transaction(1, creditPrice);

        transactionService.credit(player, transaction);

        assertEquals(creditPrice, player.getBalance());
    }

    @Test
    public void testCreditIfWrongValue(){
        double creditPrice = -100_000;
        Player player = new Player("Razor", "Razor");
        Transaction transaction = new Transaction(1, creditPrice);

        transactionService.credit(player, transaction);

        assertEquals(0, player.getBalance());
    }

    @Test
    public void testSameTransactionId(){
        Transaction transaction1 = new Transaction(1, 100);
        Transaction transaction2 = new Transaction(1, 100);

        Player player = new Player("Rocket", "111");

        transactionService.credit(player, transaction1);

    }

}