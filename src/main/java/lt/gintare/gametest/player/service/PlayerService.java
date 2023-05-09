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

    // * initialize playerOptions list
    private List<Player> playerOptions = new ArrayList<>();

    // * playerOptions getter
    public List<Player> getPlayerOptions() {
        return playerOptions;
    }

    // * playerOptions setter
    public void setPlayerOptions(List<Player> playerOptions) {
        this.playerOptions = playerOptions;
    }

    // * returns list of database search results by player name
    public List<Player> searchPlayerByName (String name) {
        return playerRepository.findPlayerByName(name);
    }

    // * saves player name in database
    public void savePlayerByName (String playerName) {
        playerRepository.savePlayerName(playerName);
    }

    // * updates all player details in database
    public void updatePlayer (String playerName, int experience, int charisma, int luck) {
        playerRepository.updatePlayer(playerName, experience, charisma, luck);
    }

    // * generates 3 Player objects
    public List<Player> generateAllPlayers (String playerName) {
        List<Player> randomPlayers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Player newPlayer = new Player();
            newPlayer.setPlayerName(playerName);
            newPlayer.setExperience((int) (Math.random() * (100 - 20 + 1) + 20));
            newPlayer.setCharisma((int) (Math.random() * (60 - 40 + 1) + 40));
            newPlayer.setLuck((int) (Math.random() * (50 - 10 + 1) + 10));
            randomPlayers.add(newPlayer);
        }
        return randomPlayers;
    }

}
