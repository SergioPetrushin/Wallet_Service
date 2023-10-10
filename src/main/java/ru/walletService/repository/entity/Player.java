package ru.walletService.repository.entity;

import java.util.ArrayList;
/**
 * class Player - шаблон объекта игрока
 */
public class Player {
    private String username;
    private String password;
    private double balance;
    private ArrayList<Transaction> transactions;
    private ArrayList<String> historyEvents;

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0;
        this.transactions = new ArrayList<>();
        this.historyEvents = new ArrayList<>();
    }

    public String getUsername() { return username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public void addTransaction(Transaction transaction){
        this.transactions.add(transaction);
    }
    public ArrayList<Transaction> getHistoryTransaction(){
        return new ArrayList<>(transactions);
    }

    public void addHistoryEvent(String historyEvent){
        historyEvents.add(historyEvent);
    }
    public ArrayList<String> getHistoryEvents(){
        return new ArrayList<>(historyEvents);
    }
}
