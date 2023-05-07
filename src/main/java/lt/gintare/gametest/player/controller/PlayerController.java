package lt.gintare.gametest.player.controller;

import lt.gintare.gametest.player.repository.Player;
import lt.gintare.gametest.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/game/player")
// http://localhost:8080/game
public class PlayerController {

    @Autowired
    PlayerService playerService;

    // METHOD (get&post) 1 -> search for name entered by user in database &
    // (1) save name if not found or (2) retrieve player if found
    // URL: http://localhost:8080/game/player/player_name
    @RequestMapping(value = "/enter_player_name", method = RequestMethod.GET)
    public String getPlayerByName (Model model) {
        model.addAttribute("key_player", new Player());
        model.addAttribute("key_player_list", Collections.emptyList());
        return "/enter_player_name";
    }

    @RequestMapping(value = "/enter_player_name", method = RequestMethod.POST)
    public String postPlayerByName (Model model, @ModelAttribute(value = "key_player") Player player){
        List<Player> userEntry = playerService.searchPlayerByName(player.getPlayerName());
        if (userEntry.size() == 0) {
            if(player.getPlayerName() == null || player.getPlayerName().isEmpty()) {
                player.setPlayerName("Default Player");
            }
            playerService.savePlayerByName(String.valueOf(player));
            playerService.generateAllPlayers();
            model.addAttribute("player", player);
            return "/game/challenge/execute_challenge";
        } else {
            model.addAttribute("player", userEntry.get(0));
            return "/game/challenge/execute_challenge";
        }
    }

    // http://localhost:8080/game/query/{name}
    @GetMapping(path = "/query/{name}")
    public @ResponseBody List<Player> getPlayerByName (@PathVariable String name){
        return playerService.searchPlayerByName(name);
    };


}
