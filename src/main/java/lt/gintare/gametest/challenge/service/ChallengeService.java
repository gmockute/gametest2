package lt.gintare.gametest.challenge.service;

import lt.gintare.gametest.challenge.repository.ChallengeRepository;
import lt.gintare.gametest.challenge.repository.Opponent;
import lt.gintare.gametest.challenge.repository.OpponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChallengeService {

    @Autowired
    OpponentRepository opponentRepository;
    @Autowired
    ChallengeRepository challengeRepository;

    //METHOD 1 -> get random opponent
    public Opponent getOpponent() {
        return opponentRepository.getRandomOpponentDetails();
    }

    // METHOD 2 -> generate random opponent stats to compare to player stats & puts them in list
    public java.util.List<Integer> getOpponentStats() {
        java.util.List<Integer> opponent = new ArrayList<>();
        opponent.add((int) (Math.random() * (100 - 20 + 1) + 20));
        opponent.add((int) (Math.random() * (60 - 40 + 1) + 40));
        opponent.add((int) (Math.random() * (50 - 10 + 1) + 10));
        return opponent;
    }

    // METHOD 3-> compare stats and assign scores (TakeChallenge button)
    public int executeChallenge1(List<Integer> opponent, List<Integer> player) {
        int result = 0;
        if (opponent.get(0) > player.get(0)) {
            result += 1;
        }
        if (opponent.get(1) > player.get(1)) {
            result += 1;
        }
        if (opponent.get(2) > player.get(2)) {
            result += 1;
        }
        return result;
    }

    // METHOD 4 -> ExecuteChallenge2 - compare stats and assign scores (DoNothing button)
    public int executeChallenge2(List<Integer> opponent, List<Integer> player) {
        int result = 0;
        if (opponent.get(0) > player.get(0)) {
            result += 1;
        }
        if (opponent.get(1) > player.get(1)) {
            result += 1;
        }
        if (opponent.get(2) > player.get(2)) {
            result += 1;
        }
        return result;

    }

    // METHOD 5 -> save challenge object to database
    public void saveChallenge(int playerId, int characterId, String battleStatus) {
        challengeRepository.saveChallenge(playerId, characterId, battleStatus);
    }


}
