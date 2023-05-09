package lt.gintare.gametest.challenge.bootstrapdata;

import lt.gintare.gametest.challenge.repository.Opponent;
import lt.gintare.gametest.challenge.repository.OpponentRepository;
import lt.gintare.gametest.challenge.service.ChallengeService;
import lt.gintare.gametest.player.repository.PlayerRepository;
import lt.gintare.gametest.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Random;

@Component
public class BootStrapData implements CommandLineRunner{


        @Autowired
        private OpponentRepository opponentRepository;

        @Override
        public void run(String... args) throws Exception {

            // System.out.println(playerRepository.findPlayerByName("John"));
            // System.out.println(challengeService.getOpponent().toString());

        }


    }
