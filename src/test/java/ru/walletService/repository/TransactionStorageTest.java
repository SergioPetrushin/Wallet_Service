package ru.walletService.repository;

import org.junit.jupiter.api.Test;
import ru.walletService.repository.entity.Player;
import ru.walletService.repository.entity.Transaction;

import static org.junit.jupiter.api.Assertions.*;

class TransactionStorageTest {
    @Test
    public void testTransactionIfSame(){
        TransactionStorage transactionStorage = new TransactionStorage();
        Transaction transaction1 = new Transaction(1, 100);
        Transaction transaction2 = new Transaction(1, 100);


        transactionStorage.saveTransaction(transaction1);

        assertThrows(
                IllegalArgumentException.class,
                () -> transactionStorage.saveTransaction(transaction2)
        );

    }

}