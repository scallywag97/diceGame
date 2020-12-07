/*
 * THE START CLASS
 */
package dicegame;

/**
 * This is the main method. It creates the instance of the Dice Game. Ends when the loop breaks. Shows the ending balances for each player when game ends.
 * @author Chase Scallion
 * @version 10/4/2019
 */
public class Start {

    /**
     * this is the main method
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //welcome message
        System.out.println("Welcome to the Dice Game!");
        //creates a dice game instance
        DiceGame dg = new DiceGame();
        
        //sets round to 1
        int round = 1;
        
        //loops until gameOver is false
        while(!dg.isGameOver()) {
            //prints the following messages and current round
            System.out.println("***********************");
            System.out.println("      ROUND " + round);
            System.out.println("***********************");
            System.out.println();
            
            //plays game
            dg.playGame();
            
            //increments round number
            round++;
        
        }
        //game over message
        System.out.println("Game is over. Everyone's balance is as below: ");
        
        //show ending balances for all players
        for(int i = 0; i < dg.getNumPlayers(); i++) {
            System.out.println(dg.getPlayers().get(i).getName() + ": $" + dg.getPlayers().get(i).getBalance());
        }
    }
}
