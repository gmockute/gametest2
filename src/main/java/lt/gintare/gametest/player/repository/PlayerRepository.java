package lt.gintare.gametest.player.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayerRepository extends CrudRepository <Player, Integer> {

    // QUERY 1 -> searches player by name in game_repository.player
    @Query(value = "SELECT * FROM player WHERE player_name = :name", nativeQuery = true)
    List<Player> findPlayerByName(@Param("name") String playerName);

    // QUERY 2 -> saves player name to table in game_repository.player
    @Query(value = "INSERT INTO player (player_name) VALUES (:name)", nativeQuery = true)
    @Modifying
    void savePlayerName(@Param("name") String playerName);

    // QUERY 3 -> saves player stats to tbale in game_repository.player
    @Query(value = "INSERT INTO player (" +
            "player_name, " +
            "player_experience, " +
            "player_charisma, " +
            "player_luck) " +
            "VALUES (:name, :exp, :cha, :luc)",
            nativeQuery = true)
    @Modifying
    void addPlayer (@Param("name") String playerName,
                    @Param("exp") int experience,
                    @Param("cha") int charisma,
                    @Param("luc") int luck);

}
