package lt.gintare.gametest.challenge.controller;

import lt.gintare.gametest.challenge.repository.Opponent;
import lt.gintare.gametest.challenge.service.ChallengeService;
import lt.gintare.gametest.player.repository.Player;
import lt.gintare.gametest.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("/game/challenge")
// http://localhost:8080/game/challenge
public class ChallengeController {

    @Autowired
    ChallengeService challengeService;
    @Autowired
    PlayerService playerService;

    // METHOD 0 -> display text in challenge page
    // http://localhost:8080/game/challenge/execute_challenge.html
    @GetMapping(path = "/execute_challenge")
    public @ResponseBody String challengeDescription(){
        return "Challenge description";
    }

    // METHOD 1 -> get random opponent details
    // http://localhost:8080/game/challenge/execute_challenge.html
    @GetMapping(path = "/execute_challenge.html")
    public Opponent getRandomOpponent (Model model){
        return challengeService.getOpponent();
    }

    // METHOD 2 -> displays player name (entry in page 2) if new player or select from database if existing player

    // METHOD 3 -> upon button1 press calls method in ChallengeService to execute challenge1
    // if result is 2 or 3 - loads win page and if 0 or 1 - loads loose page
    // http://localhost:8080/game/challenge/execute_challenge.html
    public String startChallenge() {
        String result;
        java.util.List<Player> players;
        java.util.List<Integer> stats = new ArrayList<>();

        players = playerService.generateAllPlayers();

        stats.add(players.get(1).getExperience());
        stats.add(players.get(1).getCharisma());
        stats.add(players.get(1).getLuck());

        if (challengeService.executeChallenge1(challengeService.getOpponentStats(), stats) >= 2) {
            result = "/get_result_win.html";
            challengeService.saveChallenge(players.get(1).getPlayerId(),
                                            challengeService.getOpponent().getOpponentId(),
                                           "Win");
        } else {
            result = "/get_result_loose.html";
            challengeService.saveChallenge(players.get(1).getPlayerId(),
                                           challengeService.getOpponent().getOpponentId(),
                                           "Loose");
        }

        return result;
    }

    // METHOD 5: displays result in win and loose pages

    // METHOD 6: displays player name (entry in page 2) if new player or select from database if existing player
    // win page and loose page


}
