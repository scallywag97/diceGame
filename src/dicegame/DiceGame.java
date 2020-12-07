/*
 * THE DICE GAME
 */
package dicegame;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * This class contains the code that generates the Dice Game itself and its processes. Players place their bet amounts and their guesses. If players guess correctly they win. Game ends when a player balance hits 0.
 * @author Chase Scallion
 * @version 10/4/2019
 */
public class DiceGame {
    private ArrayList<Player> players = new ArrayList<>();
    private int numPlayers;
    private int pot;
    private boolean gameOver;
    private boolean gameMode;

    /**
     * This constructs a dice game instance that the users can participate in.
     */
    public DiceGame() {
        //calls the setUpGame method to start the game
        setUpGame();
    }

    /**
     * This method gets the current list of players and their attributes in the game.
     * @return returns the array list of players.
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * This method gets the current number of players participating in the game.
     * @return returns the number of players in the game
     */
    public int getNumPlayers() {
        return numPlayers;
    }

    /**
     * This method is called to check if the game is over.
     * Game is over when variable gameOver is returned as true.
     * @return returns gameOver as true or false.
     */
    public boolean isGameOver() {
        return gameOver;
    }
    
    /**
     * This method is called to determine what game mode the players want to play.
     * @return returns gameMode as true if players want hard mode, false if players want standard mode.
     */
    public boolean isGameMode() {
        return gameMode;
    }
    
    /**
     * This method sets up the game by asking players if they want easy or hard mode, the number of players, and their names.
     */
    public void setUpGame(){
        //variable to determine what version of the game the user wants to play
        int modeCheck;
        //loop variable
        boolean loop = true; 
        
        //scanner for input
        Scanner input = new Scanner(System.in);
        System.out.println("Type '0' for easy mode or '1' for hard mode.");
        
        //loops until user enters a correct input
        while(loop){
            modeCheck = input.nextInt();    //gets user input
            switch (modeCheck) {
                case 1:
                    //if user types 1, user will play hard version of game
                    gameMode = true;
                    loop = false;
                    break;
                case 0:
                    //if user types 0, user will play easy version of game
                    gameMode = false;
                    loop = false;
                    break;
                default:
                    //will ask user to type in the correct input if they did not type 0 or 1
                    System.out.println("Please type '0' for easy mode or '1' for hard mode.");
                    break;
            }
        }    
        
        //gets number of players in the game
        System.out.println("How many players are in this game?");
        numPlayers = input.nextInt();
        
        //loops until all players have entered their name
        for(int i = 0; i < numPlayers; i++){
            System.out.println("What is the player's name?");
            String name = input.next();
            players.add(new Player(name));  //sets the players name to the current index of the players array list
        }
        //call this method to display the rules in respect to the game mode chosen
        displayRules();
    }
    
    /**
     * This method simply displays the rules for each game mode. It will display the following rules depending on which game mode the user chooses.
     */
    public void displayRules(){
        //the following rules are displayed if the user chose the hard version of the game
        if(isGameMode()){
            System.out.println("RULES!");
            System.out.println("Each player places a bet and chooses a number bewteen 1 and 6 for each dice.");
            System.out.println("The total of all of the bets forms a \"pot.\"");
            System.out.println("Then, two dice are rolled.");
            System.out.println("In order to win, the players must guess the exact numbers of the individual dice(Dice 1 and Dice 2).");
            System.out.println("If one of the players bets on the results correctly, he or she wins the entire pot.");
            System.out.println("If more than one player bets on the results correctly, the one who bet the most wins the entire pot.");
            System.out.println("If there is a tie, they split the pot.");
            System.out.println("If nobody bet on the correct numbers, the money remains in the pot for the next round.");
            System.out.println("The game is over if one of the players runs out of money.");
        }
        //the following rules are displayed if the user chose the easy version of the game
        else{
            System.out.println("RULES!");
            System.out.println("Each player places a bet and chooses a number bewteen 2 and 12.");
            System.out.println("The total of all of the bets forms a \"pot.\"");
            System.out.println("Then, two dice are rolled.");
            System.out.println("If one of the players bet on the result correctly, he or she wins the entire pot.");
            System.out.println("If more than one player bet on that number, the one who bet the most wins the entire pot.");
            System.out.println("If there is a tie, they split the pot.");
            System.out.println("If nobody bet on the number, the money remains in the pot for the next round.");
            System.out.println("The game is over if one of the players runs out of money.");
        }
    }

    /**
     * This method is essentially the game itself. It shows the pot and the dice rolled.
     * There are two parts of this method: the first part is the easy game mode and the second is the hard mode.
     */
    public void playGame(){
        playTurn();
        
        //the following code is used for the easy version of the game
        if(!isGameMode()){
            //this loop gets each players bet for the round to form pot
            for(int i = 0; i < players.size(); i++){
                //adds the players bet to the current pot ammount
                pot = pot + players.get(i).getBetAmount();
            }
            //displays pot amount to the console
            System.out.println("There is $" + pot + " in the pot for this round!");
            
            //these are the simulated dice rolls(random numbers from 1-6)
            int dice1 = 7;//(int)(Math.random() * (6 - 1) + 1);
            int dice2 = 0;//(int)(Math.random() * (6 - 1) + 1);
            
            //adds the previous rolls together
            int outcome = dice1 + dice2;
        
            //displays the total of dice to the console
            System.out.println("The outcome is " + outcome + " for this round!");
            //calls this method to see who won
            checkWinner(outcome);
        
            //loop checks the balances of players, if any balances are 0, then game is over
            for(int i = 0; i < players.size(); i++){
                //if player balance is 0, sets gameOver to true to end the game
                if(players.get(i).getBalance() <= 0){
                    gameOver = true;
                    //method checks to see if game is over
                    isGameOver();
                }
            }
        }
        //the following code is used for the hard version of the game
        else{
            //this loop gets each players bet for the round to form pot
            for(int i = 0; i < players.size(); i++){
                //adds the players bet to the current pot ammount
                pot = pot + players.get(i).getBetAmount();
            }
            //displays pot amount to the console
            System.out.println("There is $" + pot + " in the pot for this round!");
        
            //these are the simulated dice rolls(random numbers from 1-6)
            int dice1 = 1;//(int)(Math.random() * (6 - 1) + 1);
            int dice2 = 2;//(int)(Math.random() * (6 - 1) + 1);
        
            //displays the individual rolls of the dice to the console
            System.out.println("The outcomes are... 'Dice 1: " + dice1 + " and Dice 2: " + dice2 + "' for this round!");
            //calls this method to see who won
            checkWinner(dice1, dice2);
        
            //loop checks the balances of players, if any balance are 0, then game is over
            for(int i = 0; i < players.size(); i++){
                //if player balance is 0, sets gameOver to false to end the game
                if(players.get(i).getBalance() <= 0){
                    gameOver = true;
                    //method checks to see if game is over
                    isGameOver();
                }
            }
        }
    }
    
    /**
     * This method is called when the players need to place their bets and guesses.
     * The method has two parts: first part is for easy mode and the second part for hard mode.
     */
    public void playTurn(){
        //each of these loops is used to force users to enter proper input before breaking
        boolean loop1 = true;
        boolean loop2 = true;
        boolean loop3 = true;
        
        //the following code is used for the easy version of the game
        if(!isGameMode()){
            //this loop gets the amount of money players want to bet this round
            for(int i = 0; i < players.size(); i++){
                //these reset the loops for each individual player
                loop1 = true;
                loop2 = true;
                //console displays how money the user has and asks how much they want to bet this round
                System.out.println(players.get(i).getName() + ", you have $" + players.get(i).getBalance() + " left. How much are you going to bet?");
            
                //loops until user enters a number greater than 0 or less than or equal to their balance
                while(loop1){
                    //gets user input
                    Scanner input = new Scanner(System.in);
                    int bet = input.nextInt();
                
                    //checks to see if player enters legitimate input
                    if(bet <= players.get(i).getBalance() && bet > 0){
                        //subtracts bet amount from users current balance
                        players.get(i).setBalance(players.get(i).getBalance() - bet);
                        //sets bet amount for the round
                        players.get(i).setBetAmount(bet);
                        //breaks loop to move onto next part of code
                        loop1 = false;
                    }
                    //displays if user did not enter proper input, user will try again
                    else
                        System.out.println("Bet balance exceeds available funds.");
                }
            
                //loops until user enters a number between 2 and 12
                while(loop2){
                    //displays message and gets user input
                    System.out.println("Which number between(including) 2 and 12 are you betting on?");
                    Scanner input = new Scanner(System.in);
                    int guess = input.nextInt();
            
                    //checks to see if user input is legitimate
                    if(guess <= 12 && guess >= 2){
                        //sets input as guess
                        players.get(i).setGuess(guess);
                        //breaks loop to move onto next part of code
                        loop2 = false;
                    }
                    //displays if user did not enter a viable number, user will try again
                    else
                        System.out.println("Guess needs to be bewteen(including) 2 and 12.");
                }
            }
        }
        //the following code is used for the hard version of the game
        else{
            //this loop gets the amount of money players want to bet this round
            for(int i = 0; i < players.size(); i++){
                //these reset the loops for each individual player
                loop1 = true;
                loop2 = true;
                loop3 = true;
                //console displays how money the user has and asks how much they want to bet this round
                System.out.println(players.get(i).getName() + ", you have $" + players.get(i).getBalance() + " left. How much are you going to bet?");
            
                //loops until user enters a number greater than 0 or less than or equal to their balance
                while(loop1){
                    //gets user input
                    Scanner input = new Scanner(System.in);
                    int bet = input.nextInt();
                
                    //checks to see if player enters legitimate input
                    if(bet <= players.get(i).getBalance() && bet > 0){
                        //subtracts bet amount from users current balance
                        players.get(i).setBalance(players.get(i).getBalance() - bet);
                        //sets bet amount for the round
                        players.get(i).setBetAmount(bet);
                        //breaks loop to move onto next part of code
                        loop1 = false;
                    }
                    //displays if user did not enter a viable number, user will try again
                    else
                        System.out.println("Bet balance exceeds available funds.");
                }
                
                //loops until user enters a number between 1 and 6
                while(loop2){
                    //displays message and gets user input
                    System.out.println("Which number between(including) 1 and 6 is Dice 1?");
                    Scanner input = new Scanner(System.in);
                    int guess = input.nextInt();
            
                    //checks to see if user input is legitimate
                    if(guess <= 6 && guess >= 1){
                        //sets input as guess for 1st dice
                        players.get(i).setGuess1(guess);
                        //breaks loop to move onto next part of code
                        loop2 = false;
                    }
                    //displays if user did not enter a viable number, user will try again
                    else
                        System.out.println("Guess needs to be bewteen(including) 1 and 6.");
                }
                
                //loops until user enters a number between 1 and 6
                while(loop3){
                    //displays message and gets user input
                    System.out.println("Which number between(including) 1 and 6 is Dice 2?");
                    Scanner input = new Scanner(System.in);
                    int guess = input.nextInt();
            
                    //checks to see if user input is legitimate
                    if(guess <= 6 && guess >= 1){
                        //sets input as guess for 2nd dice
                        players.get(i).setGuess2(guess);
                        //breaks loop to move onto next part of code
                        loop3 = false;
                    }
                    //displays if user did not enter a viable number, user will try again
                    else
                        System.out.println("Guess needs to be bewteen(including) 1 and 6.");
                }
            }
        }
    }
    
    /**
     * This method checks the winner(s) for the easy version of the game.
     * @param outcome This is the sum of the 2 dice rolled from the playGame method.
     */
    public void checkWinner(int outcome){
        //the following local variables help determine who has the highest bet and number of ties if any
        int highBet = 0;
        int currentBet = 0;
        int ties = 0;
        //this array list is filled with any players who won the round
        ArrayList<Player> winners = new ArrayList<>();
        
        //fills winners array list with anyone who guessed the correct number
        for(int i = 0; i < players.size(); i++){
            //checks to see if player guessed the correct output, then is added to the winners array list
            if(players.get(i).getGuess() == outcome){
                winners.add(players.get(i));
            }
        }
        //the following code is if there is was only 1 winner this round
        if(winners.size() == 1){
            //the winner gets the pot added to their current balance and is shown a winning message
            winners.get(0).setBalance(winners.get(0).getBalance() + pot);
            System.out.println(winners.get(0).getName() + " won $" + pot + "!");
            
            //this loop shows a losing message if users did not enter the correct output
            for(int i = 0; i < players.size(); i++){
                //checks to make sure the winner is not called a loser
                if(players.get(i).getGuess() != outcome){
                    //losing message displays name and amount of money lost
                    System.out.println(players.get(i).getName() + " lost $" + players.get(i).getBetAmount() + "!");
                }
            }
            //resets the pot for the next round
            pot = 0;
        }
        //following code is used if multiple users guessed the correct output
        else if(winners.size() > 1){
            //this loop determines how many users bet the same amount
            for(int i = 0; i < winners.size(); i++){
                //gets the current bet of a user
                currentBet = winners.get(i).getBetAmount();
              
                //if current user bet matches the current high bet, the number of ties is incremented
                if(currentBet == highBet){
                    ties++;
                }
                //if current users bet is higher than the current high bet, then the number of ties is set to 0
                else if(currentBet > highBet){
                    //new high bet
                    highBet = winners.get(i).getBetAmount();
                    //ties is reset
                    ties = 0;
                }
            }
            
            //this loops through all of the players to determine winners/ties, losers, and winnings
            for(int i = 0; i < players.size(); i++){
                //if player had the correct guess and their bet was the high bet, user gets a part of the pot
                if((players.get(i).getGuess() == outcome) && (players.get(i).getBetAmount() == highBet)){
                    //pot winning is added to the players current balance(the pot payout for ties is the total pot divided by the ties + 1)
                    players.get(i).setBalance((players.get(i).getBalance()) + (pot / (ties + 1)));
                    //diplays player name that won and the amount of money they won
                    System.out.println(players.get(i).getName() + " won $" + (pot / (ties + 1)) + "!");
                }
                //displays message if player did not guess correct output or did not have a high enough bet, shows name and amount lost
                else
                    System.out.println(players.get(i).getName() + " lost $" + players.get(i).getBetAmount() + "!");
            }
            //resets pot
            pot = 0;
        }
        //if no one guessed the correct output, everyone loses their bets, but the pot is not reset
        else{
            System.out.println("No one won this round!");
            //loops through each player to display losing messages
            for(int i = 0; i < players.size(); i++){
                if(players.get(i).getGuess1() != outcome){
                    //losing message shows name and amount lost
                    System.out.println(players.get(i).getName() + " lost $" + players.get(i).getBetAmount() + "!");
                }
            }
        }
    }
    
    /**
     * This method checks the winner(s) for the hard version of this game.
     * @param dice1 This is the first dice rolled from the playGame method.
     * @param dice2 This is the second dice rolled from the playGame method.
     */
    public void checkWinner(int dice1, int dice2){
        //the following local variables help determine who has the highest bet and number of ties if any
        int highBet = 0;
        int currentBet = 0;
        int ties = 0;
        //this array list is filled with any players who won the round
        ArrayList<Player> winners = new ArrayList<>();
        
        //fills winners array list with anyone who guessed the correct number
        for(int i = 0; i < players.size(); i++){
            //checks to see if player guessed both dice numbers correctly, then gets added to winners list
            if(players.get(i).getGuess1() == dice1 && players.get(i).getGuess2() == dice2){
                winners.add(players.get(i));
            }
        }
        //the following code is if there is was only 1 winner this round
        if(winners.size() == 1){
            //the winner gets the pot added to their current balance and is shown a winning message
            winners.get(0).setBalance(winners.get(0).getBalance() + pot);
            System.out.println(winners.get(0).getName() + " won $" + pot + "!");
            
            //this loop shows a losing message if users did not enter the correct output
            for(int i = 0; i < players.size(); i++){
                //checks to make sure the winner is not called a loser
                if(players.get(i).getGuess1() != dice1 || players.get(i).getGuess2() != dice2){
                    System.out.println(players.get(i).getName() + " lost $" + players.get(i).getBetAmount() + "!");
                }
            }
            //pot is reset to 0
            pot = 0;
        }
        //the following code is used if there are multiple players who guessed the correct output
         else if(winners.size() > 1){
            //this loop determines how many users bet the same amount
            for(int i = 0; i < winners.size(); i++){
                //gets the current bet of a user
                currentBet = winners.get(i).getBetAmount();
              
                //if current user bet matches the current high bet, the number of ties is incremented
                if(currentBet == highBet){
                    ties++;
                }
                //if current users bet is higher than the current high bet, then the number of ties is set to 0
                else if(currentBet > highBet){
                    //new high bet
                    highBet = winners.get(i).getBetAmount();
                    //ties is reset
                    ties = 0;
                }
            }
            
            //loops through each player to detemine the winners/ties, losers, and winnings
            for(int i = 0; i < players.size(); i++){
                //if current player guessed the 2 dice correctly and their bet is the high bet, they win a part of the pot
                if(((players.get(i).getGuess1() == dice1) && (players.get(i).getGuess2() == dice2)) && (players.get(i).getBetAmount() == highBet)){
                    //pot winning is added to the players current balance(the pot payout for ties is the total pot divided by the ties + 1)
                    players.get(i).setBalance((players.get(i).getBalance()) + (pot / (ties + 1)));
                    //winning message shows name and amount won
                    System.out.println(players.get(i).getName() + " won $" + (pot / (ties + 1)) + "!");
                }
                //losing message printed shows name and amount lost
                else
                    System.out.println(players.get(i).getName() + " lost $" + players.get(i).getBetAmount() + "!");
            }
            //pot is reset
            pot = 0;
        }
        //the following code is used if no one guessed the correct output
        else{
            System.out.println("No one won this round!");
            //loops through the players array list to show amount lost for each player
            for(int i = 0; i < players.size(); i++){
                //checks to make sure players lost
                if(players.get(i).getGuess1() != dice1 || players.get(i).getGuess2() != dice2){
                    //losing message shows name and amount lost
                    System.out.println(players.get(i).getName() + " lost $" + players.get(i).getBetAmount() + "!");
                }
            }
        }
    }
}