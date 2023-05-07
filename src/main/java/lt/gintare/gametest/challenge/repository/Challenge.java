package lt.gintare.gametest.challenge.repository;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lt.gintare.gametest.player.repository.Player;

@Entity
@Table(name = "challenge")

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "challenge_id")
    private int challengeId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "opponent_id")
    private Opponent opponent;
    @Column(name = "battle_status")
    private String battleStatus;

}
