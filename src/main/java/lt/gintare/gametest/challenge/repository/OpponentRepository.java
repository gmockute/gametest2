package lt.gintare.gametest.challenge.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OpponentRepository extends CrudRepository <Opponent, Integer> {

    // QUERY 1 -> gets random opponent and puts it in a list
    @Query(value = "SELECT * FROM game_repository.opponent ORDER BY RAND() LIMIT 1", nativeQuery = true)
    List<Opponent> findRandomOpponent();

}
