package lt.gintare.gametest.challenge.controller;

import lt.gintare.gametest.challenge.repository.Opponent;
import lt.gintare.gametest.challenge.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/game/challenge")
// http://localhost:8080/game/challenge
public class ChallengeController {

    @Autowired
    ChallengeService challengeService;

    // METHOD 1 -> get random opponent details
    // http://localhost:8080/game/challenge/random/opponent
    @GetMapping(path = "/random/opponent")
    public Opponent getRandomOpponent (Model model){
        return challengeService.getOpponent();
    }

    // METHOD 2 -> displays player name (entry in page 2) if new player or select from database if existing player
    // http://localhost:8080/game/challenge/player

    // METHOD 3 -> upon button1 press calls method in ChallengeService to execute challenge1
    // if result is 2 or 3 - loads win page and if 0 or 1 - loads loose page
    // http://localhost:8080/game/challenge/execute_action

    // METHOD 4: upon button2 press calls method in ChallengeService to execute challenge2
    // if result is 2 or 3 - loads win page and if 0 or 1 - loads loose page
    // http://localhost:8080/game/challenge/execute_noaction

    // METHOD 5: displays result in win and loose pages
    // http://localhost:8080/game/challenge/result

    // METHOD 6: displays player name (entry in page 2) if new player or select from database if existing player
    // win page and loose page
    // http://localhost:8080/game/challenge/name

}
