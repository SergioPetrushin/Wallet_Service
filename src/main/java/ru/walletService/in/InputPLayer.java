package ru.walletService.in;

import ru.walletService.repository.entity.Player;
import ru.walletService.repository.entity.Transaction;
import ru.walletService.service.PlayerService;
import ru.walletService.service.TransactionService;

import java.util.List;
import java.util.Scanner;
/**
 * Это InputPLayer, класс ввода команд
 */
public class InputPLayer {
    private PlayerService playerService;
    private TransactionService transactionService;
    private Player player = null;

    public InputPLayer() {
        this.playerService = new PlayerService();
        this.transactionService = new TransactionService();
    }
    /**
     * lifeCycle() - блок выбора команд
     */
    public void lifeCycle(){
        while (true){
            printCommand();
            int command = inputInt("Введите номер команды");

            if (command == 1){
                registration();
            }
            if (command == 2){
                authentication();
            }

            if (command >= 3 && player == null){
                System.out.println("Access denied");
                continue;
            }

            if (command == 3){
                System.out.println(player.getBalance());
                player.addHistoryEvent("Запрос получения баланса");
            }
            if (command == 4){
                int idTransaction = inputInt("Введите идентификатор транзакции");
                double price = inputDouble("Введите сумму снятия денежных средств");
                transactionService.debet(player, new Transaction(idTransaction, price));
            }
            if (command == 5){
                int idTransaction = inputInt("Введите идентификатор транзакции");
                double price = inputDouble("Введите сумму пополнения денежных средств");
                transactionService.credit(player, new Transaction(idTransaction, price));
            }
            if (command == 6){
                List<Transaction> history = player.getHistoryTransaction();
                for (Transaction transaction : history) {
                    System.out.println(transaction);
                }
            }
            if (command == 7){
                List<String> historyEvent = player.getHistoryEvents();
                for (String event : historyEvent) {
                    System.out.println(event);
                }
            }
        }
    }
    /**
     * registration() - Метод регистрации игрока. Создание объекта Player
     */
    public void registration(){
        String username = inputString("Введите имя игрока");
        String password = inputString("Введиет пароль");
        String repeatPassword = inputString("Повторите пароль");

        while (!password.equals(repeatPassword)){
            System.out.println("Пароли не совпадают");
            password = inputString("Введиет пароль");
            repeatPassword = inputString("Повторите пароль");
        }

        Player player = new Player(username, password);
        playerService.addPlayer(player);
    }

    /**
     * authentication() - Метод аутентификации
     */
    public void authentication(){
        String username = inputString("Введите имя игрока");
        String password = inputString("Введиет пароль");

        player = playerService.authenticationUser(username, password);
        player.addHistoryEvent("Аутентификация");
    }

    private void printCommand(){
        System.out.println("1 - Регистрация");
        System.out.println("2 - Авторизация");
        if (player != null){
            System.out.println("3 - Узнать баланс");
            System.out.println("4 - Снятие средств");
            System.out.println("5 - Пополнение средств");
            System.out.println("6 - История транзакций");
            System.out.println("7 - История действий");
        }
    }

    private int inputInt(String message){
        Scanner scanner = new Scanner(System.in);
        System.out.print(message + ": ");
        return scanner.nextInt();
    }

    private String inputString(String message){
        Scanner scanner = new Scanner(System.in);
        System.out.print(message + ": ");
        return scanner.nextLine();
    }

    private Double inputDouble(String message){
        Scanner scanner = new Scanner(System.in);
        System.out.print(message + ": ");
        return scanner.nextDouble();
    }
}
