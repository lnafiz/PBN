import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends Gambler{
  public ArrayList totalPlayers;

  public Player(ArrayList deckOfCards, ArrayList players){
    super(deckOfCards);
    totalPlayers = players; // pass-by-ref
  }

  public Player(ArrayList deckOfCards, String splitCard){
    super(deckOfCards, splitCard);
  }

  // true = turn ends
  // false = turn keeps going
  public boolean nextMove(){
    int input = 0;
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader in = new BufferedReader(isr);
    System.out.println("Your turn!");
    System.out.println("Your hand is... " +
 showHand());
    System.out.println("The value of your hand is " + inHand + ".");
    System.out.println("What would you like to do? Your options (right now) are:");
    System.out.println(" 1: hit \n 2: stand \n 3: double \n 4: split \n 5: count cards (eta s0n)");
    try { input = Integer.parseInt(in.readLine()); }
    catch (IOException e) {}

    if (input == 1){
      System.out.println("--------------------------------"); // divider to make it easier to read
      hit();
    }
    else if (input == 2){
      System.out.println("--------------------------------");
      return stand();
    }
    else if (input == 3 && hand.size() == 2){
      System.out.println("--------------------------------");
      return doubleDown();
    }
    else if (input == 4 && hand.size() == 2 &&
    (Deck.valueOf((String)hand.get(0)) == Deck.valueOf((String)hand.get(1)))){
      split();
    }
    else{
      System.out.println("Invalid choice! Try again!\n");
    }
    return false;
  }

  // invokes constructor to create a new Player, moves first card from original player to new player as the new hand value.
  public void split(){
    System.out.println("Splitting hand!\n");
    aceIndex = -1; // reset aceIndex
    String splitCard = (String)hand.remove(0);
    inHand = Deck.valueOf((String)hand.get(0));
    hit();

    totalPlayers.add(new Player(deckInPlay, splitCard));
  }

}
