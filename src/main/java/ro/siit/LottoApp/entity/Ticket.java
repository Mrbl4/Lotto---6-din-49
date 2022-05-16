package ro.siit.LottoApp.entity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ro.siit.LottoApp.IncorrectNumberException;

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
    private int noSix;
    //it automatically created the "playerId column
    private int winningNumbers;

    @ManyToOne
    @JoinColumn(name = "playerId", nullable = false)
    private Player player;

    public Ticket() {
    }

    public Ticket(int noOne, int noTwo, int noThree, int noFour, int noFive, int noSix){
//        try {
//            checkValidNumber(noOne);
//            checkValidNumber(noTwo);
//            checkValidNumber(noThree);
//            checkValidNumber(noFour);
//            checkValidNumber(noFive);
//            checkValidNumber(noSix);
//            this.noOne = noOne;
//            this.noTwo = noTwo;
//            this.noThree = noThree;
//            this.noFour = noFour;
//            this.noFive = noFive;
//            this.noSix = noSix;
//        }
//        catch (IncorrectNumberException e){};

        if (checkValidNumber(noOne)){
        this.noOne = noOne;}
        else throw new IncorrectNumberException();

        if (checkValidNumber(noTwo)){
            this.noTwo = noTwo;}
        else throw new IncorrectNumberException();

        if (checkValidNumber(noThree)){
        this.noThree = noThree;}
        else throw new IncorrectNumberException();

        if (checkValidNumber(noFour)){
        this.noFour = noFour;}
        else throw new IncorrectNumberException();

        if (checkValidNumber(noFive)){
        this.noFive = noFive;}
        else throw new IncorrectNumberException();

        if (checkValidNumber(noSix)){
        this.noSix = noSix;}
        else throw new IncorrectNumberException();

    }

    public Ticket(int noOne, int noTwo, int noThree, int noFour, int noFive, int noSix, Player player) {
        this.noOne = noOne;
        this.noTwo = noTwo;
        this.noThree = noThree;
        this.noFour = noFour;
        this.noFive = noFive;
        this.noSix = noSix;
        this.player = player;
    }

    public Ticket(int noOne, int noTwo, int noThree, int noFour, int noFive, int noSix, Long id) {
        this.noOne = noOne;
        this.noTwo = noTwo;
        this.noThree = noThree;
        this.noFour = noFour;
        this.noFive = noFive;
        this.noSix = noSix;
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

    private boolean checkValidNumber(int i){
//        if ((i>0) && (i<50)) throw new IncorrectNumberException();
        if ((i>0) && (i<50)) return true;
        return false;
    }

    public int getNoSix() {
        return noSix;
    }

    public void setNoSix(int noSix) {
        this.noSix = noSix;
    }

    public int getWinningNumbers() {
        return winningNumbers;
    }

    public void setWinningNumbers(int winningNumbers) {
        this.winningNumbers = winningNumbers;
    }
}
