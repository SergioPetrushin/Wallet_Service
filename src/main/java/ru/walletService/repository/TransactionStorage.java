package ru.walletService.repository;

import ru.walletService.repository.entity.Transaction;

import java.util.HashMap;
/**
 * class TransactionStorage - класс хранилища всех транзакций
 */
public class TransactionStorage {
    private HashMap<Integer, Transaction> transactions;

    public TransactionStorage(){
        transactions = new HashMap<>();
    }

    public void saveTransaction(Transaction transaction) throws IllegalArgumentException{
        int id = transaction.getId();
        if (transactions.containsKey(id))
            throw new IllegalArgumentException("Не уникальный идентификатор транзакции");

        transactions.put(transaction.getId(), transaction);
    }

}
