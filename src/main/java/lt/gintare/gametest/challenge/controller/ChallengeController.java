package lt.gintare.gametest.challenge.controller;

import lt.gintare.gametest.challenge.service.ChallengeService;
import lt.gintare.gametest.player.repository.Player;
import lt.gintare.gametest.player.service.PlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
// @RequestMapping("/game/challenge")
// http://localhost:8080/game/challenge
public class ChallengeController {

    @Autowired
    ChallengeService challengeService;
    @Autowired
    PlayerService playerService;

    int playerChoice;

    // 1. Home Page -> Enter Player Name
    // http://localhost:8080/start_my_page.html or http://localhost:8080/index.html
    @GetMapping({"/", "/start_mygame_page"})
    public String getHomePage (Model model) {
        return "start_mygame_page";
    }

    // 2. Enter player name
    // http://localhost:8080/enterName.html
    @RequestMapping(value = "/enterName")
    public String getEnterPlayerName(Model model) {
        return "enter_player_name";
    }

    // 3. Submit player name & start selecting character
    // http://localhost:8080/create_new_player.html
    @RequestMapping(value = "/selectCharacter")
    public String getYourCharacter(Model model, @RequestParam("playerName") String playerName) {
        // * load player from database
        List<Player> players = playerService.searchPlayerByName(playerName);
        // * check if name exists
        if (!players.isEmpty()){
            playerService.getPlayerOptions().add(players.get(0));
            playerChoice = 1;
            // * load random opponent details
            model.addAttribute("key_opponent_list", challengeService.getOpponentDetails());
            return "execute_challenge";
        } else {
            // * link to random number generator (returns stats list)
            playerService.setPlayerOptions(playerService.generateAllPlayers(playerName));
            model.addAttribute("players_list", playerService.getPlayerOptions());
            return "create_new_player";
        }
    }

    @GetMapping("/getResult")
    public String startChallenge(Model model, @RequestParam("selectChar") String choice) {
        // * return user choice
        switch(choice) {
            case "choice1":
                playerChoice = 1;
                break;
            case "choice2":
                playerChoice = 2;
                break;
            case "choice3":
                playerChoice = 3;
                break;
            default:
                playerChoice = 0;
        }
        // * load opponent details from database
        model.addAttribute("key_opponent_list", challengeService.getOpponentDetails());
        return "execute_challenge";
    }

    @GetMapping("/getChallengeResult")
    public String getChallengeResult(Model model) {
        int result;
        model.addAttribute("key_player", playerService.getPlayerOptions().get(playerChoice-1));
        // * player object stats v. random opponent stats comparison


        result = challengeService.executeChallenge1(challengeService.getOpponentStats(),
                 playerService.getPlayerOptions().get(playerChoice-1));
        model.addAttribute("result", result);
        // * issaugoti i duombaze - player i player (vardas + stats)
        if (result >= 2) {
            return "get_result_win";
        } else {
            return "get_result_loose";
        }
    }

}



