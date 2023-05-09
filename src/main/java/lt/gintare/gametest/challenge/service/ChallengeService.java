package lt.gintare.gametest.challenge.service;

import lt.gintare.gametest.challenge.repository.ChallengeRepository;
import lt.gintare.gametest.challenge.repository.Opponent;
import lt.gintare.gametest.challenge.repository.OpponentRepository;

import lt.gintare.gametest.player.repository.Player;

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

    // * get random opponent details
    public List<Opponent> getOpponentDetails() {
        return opponentRepository.getRandomOpponentDetails();
    }

    // * get random opponent statistics
    public java.util.List<Integer> getOpponentStats() {
        java.util.List<Integer> opponent = new ArrayList<>();
        opponent.add((int) (Math.random() * (80 - 20 + 1) + 20));
        opponent.add((int) (Math.random() * (60 - 40 + 1) + 40));
        opponent.add((int) (Math.random() * (50 - 10 + 1) + 10));
        return opponent;
    }

    // * compare player scores list with opponent scores list
    public int executeChallenge1(List<Integer> opponent, Player player) {
        int result = 0;
        if (opponent.get(0) > player.getExperience()) {
            result += 1;
        }
        if (opponent.get(1) > player.getCharisma()) {
            result += 1;
        }
        if (opponent.get(2) > player.getLuck()) {
            result += 1;
        }
        return result;
    }

    // * saves challenge details to database
    public void saveChallenge(int playerId, int characterId, String battleStatus) {
        challengeRepository.saveChallenge(playerId, characterId, battleStatus);
    }

}
