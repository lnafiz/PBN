import java.io.*;
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
    System.out.println("Your hand is... " + showHand());
    System.out.println("What would you like to do? Your options (right now) are:");
    System.out.println("1: hit \n 2: stand \n 3: double \n 4: split \n 5: count cards (eta s0n)");
    try { input = Integer.parseInt(in.readLine()); }
    catch (IOException e) {}

    if (input == 1){
      System.out.println("Hit!");
      hit();
    }
    else if (input == 2){
      return stand();
    }
    else if (input == 3 && hand.size() == 2){
      return doubleDown();
    }
    else if (input == 4 && hand.size() == 2 &&
    (Deck.valueOf((String)hand.get(0)) == Deck.valueOf((String)hand.get(1)))){
      split();
    }
    else{
      System.out.println("Invalid choice! Try again!");
    }
    return false;
  }

  public void split(){
    int temp = Deck.valueOf((String)hand.get(0));
    Gambler splitHand = new Player(deckInPlay, (String)hand.remove(0));
    inHand -= temp;
    totalPlayers.add(splitHand);
    hit();
  }

}
