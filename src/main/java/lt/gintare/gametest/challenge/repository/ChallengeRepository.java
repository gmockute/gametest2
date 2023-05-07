package lt.gintare.gametest.challenge.repository;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ChallengeRepository extends CrudRepository <Challenge, Integer> {

    // QUERY 1 -> save challenge object to game_repository.challenge
    @Query(value = "INSERT INTO challenge (" +
            "player_id, " +
            "character_id, " +
            "battle_status) " +
            "VALUES (:playerId, :charId, :status)",
            nativeQuery = true)
    @Modifying
    void addChallenge(@Param("playerId") int playerId,
                      @Param("charId") int characterId,
                      @Param("status") String battleStatus);


}
