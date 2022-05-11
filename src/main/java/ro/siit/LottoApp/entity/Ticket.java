package ro.siit.LottoApp.entity;

import javax.persistence.*;

@Entity
@Table(name="Tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int noOne;
    private int noTwo;
    private int noThree;
    private int noFour;
    private int noFive;
    //it automatically created the "playerId column

    @ManyToOne
    @JoinColumn(name = "playerId", nullable = false)
    private Player player;

    public Ticket() {
    }

    public Ticket(int noOne, int noTwo, int noThree, int noFour, int noFive) {
        this.noOne = noOne;
        this.noTwo = noTwo;
        this.noThree = noThree;
        this.noFour = noFour;
        this.noFive = noFive;
    }

    public Ticket(int noOne, int noTwo, int noThree, int noFour, int noFive, Player player) {
        this.noOne = noOne;
        this.noTwo = noTwo;
        this.noThree = noThree;
        this.noFour = noFour;
        this.noFive = noFive;
        this.player = player;
    }

    public Ticket(int noOne, int noTwo, int noThree, int noFour, int noFive, Long id) {
        this.noOne = noOne;
        this.noTwo = noTwo;
        this.noThree = noThree;
        this.noFour = noFour;
        this.noFive = noFive;
        this.player = player;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNoOne() {
        return noOne;
    }

    public void setNoOne(int noOne) {
        this.noOne = noOne;
    }

    public int getNoTwo() {
        return noTwo;
    }

    public void setNoTwo(int noTwo) {
        this.noTwo = noTwo;
    }

    public int getNoThree() {
        return noThree;
    }

    public void setNoThree(int noThree) {
        this.noThree = noThree;
    }

    public int getNoFour() {
        return noFour;
    }

    public void setNoFour(int noFour) {
        this.noFour = noFour;
    }

    public int getNoFive() {
        return noFive;
    }

    public void setNoFive(int noFive) {
        this.noFive = noFive;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
