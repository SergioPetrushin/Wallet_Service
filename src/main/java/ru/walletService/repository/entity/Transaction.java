package ru.walletService.repository.entity;
/**
 * class Transaction - шаблон объекта транзакции
 */
public class Transaction {
    private final int id;
    private final double price;

    public Transaction(int id, double price) {
        this.id = id;
        this.price = price;
    }

    public int getId() { return id; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", price=" + price +
                '}';
    }
}
