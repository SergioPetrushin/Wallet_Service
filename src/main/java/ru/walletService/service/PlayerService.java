package ru.walletService.service;

import ru.walletService.repository.PlayerStorage;
import ru.walletService.repository.entity.Player;
/**
 * class PlayerService - логика игрока. Сохранение в БД(условно) и аутентификация
 */
public class PlayerService {
    PlayerStorage playerStorage = new PlayerStorage();
    public boolean addPlayer(Player player){
        return playerStorage.savePlayer(player);
//        if (isAdded){
//            System.out.println("Игрок " + player.getUsername() + " успешно зарегистрирован");
//        } else {
//            System.out.println("Игрок с никнеймом " + player.getUsername() + " уже существует");
//        }
    }

    public Player authenticationUser(String username, String password){
        Player player = playerStorage.getPlayerByUsername(username);

        if (player == null) return null;
        if (!player.getPassword().equals(password)) return null;

        return player;

    }

}
