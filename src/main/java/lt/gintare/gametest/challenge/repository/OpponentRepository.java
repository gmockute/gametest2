package lt.gintare.gametest.challenge.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OpponentRepository extends CrudRepository <Opponent, Integer> {

    // QUERY 1 -> gets random opponent and puts it in a list
    @Query(value = "SELECT opponent_id, " +
                   "opponent_name, " +
                   "opponent_role, " +
                   "opponent_description" +
            " FROM game_repository.opponent ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Opponent getRandomOpponentDetails();

}
