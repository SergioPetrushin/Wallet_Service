package ru.walletService;

import ru.walletService.in.InputPLayer;
import ru.walletService.repository.entity.Player;
import ru.walletService.repository.entity.Transaction;
import ru.walletService.service.TransactionService;

/**
 * Это Main класс, в нем стартуем работу запускаем метод объекта InputPLayer
 */
public class Main {
    public static void main(String[] args) {

        new InputPLayer().lifeCycle();
    }
}