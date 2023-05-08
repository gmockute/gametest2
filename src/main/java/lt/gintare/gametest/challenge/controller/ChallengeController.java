package lt.gintare.gametest.challenge.controller;

import lt.gintare.gametest.challenge.repository.Opponent;
import lt.gintare.gametest.challenge.service.ChallengeService;
import lt.gintare.gametest.player.repository.Player;
import lt.gintare.gametest.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/game/challenge")
// http://localhost:8080/game/challenge
public class ChallengeController {

    @Autowired
    ChallengeService challengeService;
    @Autowired
    PlayerService playerService;

    @GetMapping({"/", "/start_mygame_page"})
    public String getHomePage (Model model) {
        return "start_mygame_page.html";
    }

    @RequestMapping(value = "/enter_player_name", method = RequestMethod.GET)
    public String gateEnterPlayerName(Model model) {
        return "enter_player_name.html";
    }

    // !!! metodo viduj pasiimam reiksmes is formos ir next actions ->

    @RequestMapping(value = "/enter_player_name", method = RequestMethod.GET)
    public String getSelectClass(Model model, @RequestParam("playerName") String playerName) {

        //čia jau galim pasiimt reikšmes iš formos ir susiet su beansais, db, kuo tik nori

        return "create_new_player.html";
    }

    @RequestMapping(value = "/execute_challenge", method = RequestMethod.GET)
    public String getYourCharacter(Model model, @RequestParam("playerName") String playerName) {

        //čia jau galim pasiimt reikšmes iš formos ir susiet su beansais, db, kuo tik nori
        //čia tada galima jeigu yra tai iškart eit į challenge, jei nėra, tai select char

        return "execute_new_challenge.html";

    // METHOD 1 -> get random opponent details
    // http://localhost:8080/game/challenge/execute_challenge.html
    // @GetMapping(path = "/execute_challenge/old")
   // public String getOpponent(Model model) {
        // Opponent opponent = challengeService.getOpponent();
        //model.addAttribute("opponent", opponent);
        //return "/execute_challenge";
    }

    // METHOD 2 -> displays player name (entry in page 2) if new player or select from database if existing player

    // METHOD 3 -> upon button1 press calls method in ChallengeService to execute challenge1
    // if result is 2 or 3 - loads win page and if 0 or 1 - loads loose page
    // http://localhost:8080/game/challenge/execute_challenge.html
    @RequestMapping(value = "/getResult", method = RequestMethod.GET)
    public String getGameResult(Model model, @RequestParam("selectChar") String choice) {

        String result;
        List<Player> players;
        List<Integer> stats = new ArrayList<>();

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
