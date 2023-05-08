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
// @RequestMapping("/game/challenge")
// http://localhost:8080/game/challenge
public class ChallengeController {

    @Autowired
    ChallengeService challengeService;
    @Autowired
    PlayerService playerService;

    // Home Page -> Enter Player Name
    // http://localhost:8080/start_my_page.html
    @GetMapping({"/", "/start_mygame_page"})
    public String getHomePage (Model model) {
        return "start_mygame_page";
    }

    @RequestMapping(value = "/enterName", method = RequestMethod.GET)
    public String getEnterPlayerName(Model model) {
        return "enter_player_name";
    }

    // Get Player Name -> Create New Player OR Execute Challenge
    // http://localhost:8080/create_new_player.html
    @RequestMapping(value = "/selectCharacter", method = RequestMethod.GET)
    public String getSelectClass(Model model, @RequestParam("playerName") String playerName) {


        return "create_new_player";
    }

    @RequestMapping(value = "/doChallenge", method = RequestMethod.GET)
    public String getYourCharacter(Model model, @RequestParam("playerName") String playerName) {

        //

        return "execute_new_challenge";

    }

    @PostMapping(value = "/get_opponent")
    public String getOpponent (Model model){
          Opponent opponent = challengeService.getOpponent();
          model.addAttribute("opponent", opponent);
       return "/execute_challenge.html";
    }


        // http://localhost:8080/game/challenge/execute_challenge.html
        //@RequestMapping(value = "/get_result", method = RequestMethod.GET)
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

}



