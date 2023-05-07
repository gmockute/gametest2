package lt.gintare.gametest.challenge.repository;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "opponent")

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Opponent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "opponent_id")
    private int opponentId;
    @Column(name = "opponent_name")
    private String opponentName;
    @Column(name = "opponent_role")
    private String opponentRole;
    @Column(name = "opponent_description")
    private String opponentDescription;
    @OneToMany(mappedBy = "opponent", fetch = FetchType.LAZY)
    private List<Challenge> challenges;

}

