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
    System.out.println(" 0:Instructions \n\n 1: hit \n 2: stand \n 3: double \n 4: split \n 5: count cards (eta s0n)");
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
    else if (input == 0){
    System.out.println("The goal of Blackjack is to have a hand worth as close as possible to 21 points without going over 21 points. \n\nEach card's number corresponds to their point value. For example, a 5 is worth 5 points. The symbols have no effect on point value. \n Face cards (jack, queen, king) are worth 10 points. Aces are worth 11 points. \n In the case that you have a hand worth over 21 points but have an ace, the ace's value decreases from 11 to 1. \n\n You have 5 options for actions: hit, stand, double, split, and card count. \n Hit means you draw a card. \n Stand means that you end your turn with the number of points your hand is worth and the bots play. \n Double is assigning the next drawn card a value 2 times its original. For example, if you use double and the next card is a 5, it is now worth 10 points. This is only possible once in your turn. \n Split means dividing your hand into two hands, or two games. This is only possible once at the beginning of your turn and if you have two of the same card, such as two fives. \n Card count is a tool that uses simple mathematical equations to provide you with a probability of winning the current game based on the cards drawn and what's left in the deck. \n\n Once your turn ends, wait for the bots to play through their game to determine who's the winner. If your hand is not the greastest, it's a bust, and you lose. If it's the greatest, Congratulations! You just won the game! \n\n");  
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
