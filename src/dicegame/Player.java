/*
 * THE PLAYER CLASS
 */
package dicegame;

/**
 * This class sets the attributes for each player. It also gives players their starting balance of 500.
 * @author Chase Scallion
 * @version 10/4/2019
 */
public class Player {
    private String name;
    private int balance;
    private int betAmount;
    private int guess;
    private int guess1;
    private int guess2;

    /**
     *This variable sets all player's initial balance to 500
     */
    public static int INITIAL_BALANCE = 500;

    /**
     * sets the name and initial balance for player
     * sets balance to the initial balance variable
     * @param name this sets name to name
     */
    public Player(String name) {
        this.name = name;
        //starting balance is 500
        this.balance = INITIAL_BALANCE;
    }

    /**
     * returns the name of the player
     * @return returns name of player
     */
    public String getName() {
        return name;
    }

    /**
     * returns current balance of player
     * @return returns balance of player
     */
    public int getBalance() {
        return balance;
    }

    /**
     * returns bet amount of player
     * @return returns bet amount
     */
    public int getBetAmount() {
        return betAmount;
    }

    /**
     * returns the guess of the player
     * @return returns the guess of player
     */
    public int getGuess() {
        return guess;
    }
    
    /**
     * returns guess of dice 1 of player
     * @return returns guess of dice 1
     */
    public int getGuess1() {
        return guess1;
    }
    
    /**
     * returns guess of dice 2 of player
     * @return returns guess of dice 2
     */
    public int getGuess2() {
        return guess2;
    }

    /**
     * sets the balance of the player
     * @param balance sets balance of player is set to balance
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * sets the bet amount of the player
     * @param betAmount sets bet amount of player to betAmount
     */
    public void setBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }

    /**
     * sets the guess of the player
     * @param guess sets the guess of the player to guess
     */
    public void setGuess(int guess) {
        this.guess = guess;
    }
    
    /**
     * sets the guess of dice 1
     * @param guess1 sets the guess of dice 1 to guess1
     */
    public void setGuess1(int guess1) {
        this.guess1 = guess1;
    }
    
    /**
      sets the guess of dice 2
     * @param guess2 sets the guess of dice 2 to guess2
     */
    public void setGuess2(int guess2) {
        this.guess2 = guess2;
    }
}