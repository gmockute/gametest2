package lt.gintare.gametest.player.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayerRepository extends CrudRepository <Player, Integer> {

    @Query(value = "SELECT * FROM player WHERE player_name = :name", nativeQuery = true)
    List<Player> findPlayerByName(@Param("name") String playerName);

    @Query(value = "INSERT INTO player (player_name) VALUES (:name)", nativeQuery = true)
    @Modifying
    void savePlayerName(@Param("name") String playerName);

    @Query(value = "UPDATE player (" +
            "player_experience, " +
            "player_charisma, " +
            "player_luck) " +
            "VALUES (:name, :exp, :cha, :luc)" +
            "WHERE player_name = :name",
            nativeQuery = true)
    @Modifying
    void updatePlayer (@Param("exp") int experience,
                       @Param("cha") int charisma,
                       @Param("luc") int luck);

}
