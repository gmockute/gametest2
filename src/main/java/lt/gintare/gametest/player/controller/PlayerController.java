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
// http://localhost:8080/game/player
public class PlayerController {

    @Autowired
    PlayerService playerService;

    // METHOD (get&post) 1 -> search for name entered by user in database &
    // (1) save name if not found or (2) retrieve player if found
    // http://localhost:8080/game/player/enter_player_name
    @RequestMapping(value = "/enter_player_name", method = RequestMethod.GET)
    public String getPlayerByName (Model model) {
          model.addAttribute("key_player", new Player());
          model.addAttribute("key_player_list", Collections.emptyList());
       return "/enter_player_name";
    }

    @RequestMapping(value = "/enter_player_name", method = RequestMethod.POST)
    public String postPlayerByName (Model model, @ModelAttribute(value = "key_player") Player player){
           List<Player> userEntry = playerService.searchPlayerByName(player.getPlayerName());
          if (userEntry.isEmpty()) {
              playerService.savePlayerByName(player.getPlayerName());
          return "/create_new_player";
          } else {
              model.addAttribute("player", player);
          return "/execute_challenge";
          }
       }


}
