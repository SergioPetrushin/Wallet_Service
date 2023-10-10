package ru.walletService.service;

import ru.walletService.repository.TransactionStorage;
import ru.walletService.repository.entity.Player;
import ru.walletService.repository.entity.Transaction;
/**
 * class TransactionService - логика транзакций. Операции дебета и кредита
 */
public class TransactionService {
    private TransactionStorage transactionStorage;

    public TransactionService() {
        this.transactionStorage = new TransactionStorage();
    }

    /**
     * Снятие денежных средств со счёта игрока
     *
     * @param player      Игрок
     * @param transaction Транзакция
     */
    public void debet(Player player, Transaction transaction) throws IllegalArgumentException {
        double currentBalance = player.getBalance();
        double transactionPrice = transaction.getPrice();
        try {
            if (currentBalance - transactionPrice < 0) throw new IllegalArgumentException("Недостаточно средств");
            if (transactionPrice < 0) throw new IllegalArgumentException("Некорректное значение");

            transactionStorage.saveTransaction(transaction);
            player.addTransaction(transaction);
            player.setBalance(currentBalance - transactionPrice);
            player.addHistoryEvent("Снятие денежных средств в размере " + transactionPrice);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Пополнение баланса игрока
     *
     * @param player      Игрок
     * @param transaction Транзакция
     */
    public void credit(Player player, Transaction transaction) {
        double currentBalance = player.getBalance();
        double transactionPrice = transaction.getPrice();

        try {
            if (transactionPrice < 0) throw new IllegalArgumentException("Отрицательное значение при пополнении");

            transactionStorage.saveTransaction(transaction);
            player.addTransaction(transaction);
            player.setBalance(currentBalance + transactionPrice);
            player.addHistoryEvent("Пополнение баланса на " + transactionPrice);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
