package lt.gintare.gametest.player.service;

import lt.gintare.gametest.player.repository.Player;
import lt.gintare.gametest.player.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    // METHOD 1 -> returns list of search results by name
    public List<Player> searchPlayerByName (String name) {
        return playerRepository.findPlayerByName(name);
    }

    // METHOD 2 -> saves user entry of name to database
    public void savePlayerByName (String playerName) {
        playerRepository.savePlayerName(playerName);
    }

    // METHOD 3 -> generate 3 Player objects with stats only
    public List<Player> generateAllPlayers () {
        List<Player> randomPlayers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Player newPlayer = new Player();
            newPlayer.setExperience((int) (Math.random() * (100 - 20 + 1) + 20));
            newPlayer.setCharisma((int) (Math.random() * (60 - 40 + 1) + 40));
            newPlayer.setLuck((int) (Math.random() * (50 - 10 + 1) + 10));
            randomPlayers.add(newPlayer);
        }
        return randomPlayers;
    }
    
    // METHOD 4 -> save player object to database
    public void savePlayer(String name, int experience, int charisma, int luck) {
        playerRepository.addPlayer(name, experience, charisma, luck);
    }

}
