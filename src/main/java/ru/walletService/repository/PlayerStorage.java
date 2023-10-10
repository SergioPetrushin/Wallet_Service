package ru.walletService.repository;

import ru.walletService.repository.entity.Player;

import java.util.HashMap;
/**
 * class PlayerStorage - класс хранилища всех игроков
 */
public class PlayerStorage {
    private HashMap<String, Player> players;

    public PlayerStorage() {
        this.players = new HashMap<>();
    }

    public boolean savePlayer(Player player){
        String username = player.getUsername();
        if (players.containsKey(username)) return false;

        players.put(username, player);
        return true;
    }

    public Player getPlayerByUsername(String username){
        if (players.containsKey(username)) return players.get(username);
        return null;
    }
}
