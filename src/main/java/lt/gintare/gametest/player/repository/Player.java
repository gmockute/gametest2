package lt.gintare.gametest.player.repository;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import lt.gintare.gametest.challenge.repository.Challenge;

import java.util.List;

@Entity
@Table(name = "player")

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "player_id")
    private int playerId;
    @Column(name = "player_name")
    private String playerName;
    @Column(name = "player_experience")
    private int experience;
    @Column(name = "player_charisma")
    private int charisma;
    @Column(name = "player_luck")
    private int luck;
    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY)
    private List<Challenge> challenges;

}
