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

@Controller
// @RequestMapping("/game/challenge")
// http://localhost:8080/game/challenge
public class ChallengeController {

    @Autowired
    ChallengeService challengeService;
    @Autowired
    PlayerService playerService;
    @Autowired
    Player player;
    @Autowired
    Opponent opponent;

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

        // * load player from SQL
        playerOptions = new ArrayList<>();
        Player player = new Player("", 1,1,1);

        System.out.println("Create new player request");
        System.out.println(playerName);

        // * check if name exists

        if (player.getPlayerName().equals(playerName)){
            opponents = new ArrayList<>();
            opponents.add(new Opponent("Kavita","EVP","Hell spawn"));

            playerOptions.add(player);
            playerChoice = 1;

            model.addAttribute("key_opponent_list", opponents);

            return "execute_challenge";
        } else {
            // * link to random number generator
            playerOptions.add(new Player(playerName, 100, 5, 2));
            playerOptions.add(new Player(playerName, 150, 15, 22));
            playerOptions.add(new Player(playerName, 1000, 1000, 1000));

            model.addAttribute("players_list", playerOptions);

            return "create_new_player";
        }
    }

    @GetMapping("/getResult")
    public String startChallenge(Model model, @RequestParam("selectChar") String choice) {

        // * returns user choice

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
                playerChoice = 1;
        }

        // * load opponnent from database
        opponents = new ArrayList<>();
        opponents.add(new Opponent("Kavita","EVP","Hell spawn"));

        model.addAttribute("key_opponent_list", opponents);

        return "execute_challenge";
    }

    @GetMapping("/getChallengeResult")
    public String getChallengeResult(Model model) {
        int result;

        Player player = playerOptions.get(playerChoice-1);

        model.addAttribute("key_player", player);

        // čia iš esmės lyginam player objekto stats vs random opponnnent stats

        opponents.clear();
        playerOptions.clear();

        result = 1;

        if (result == 1) {
            return "get_result_win";
        }else {
            return "get_result_loose";
        }
    }

}



