// Peanut Butter and Nutella - Prattay Dey + Winnie, Brian Li + Robert, Nafiz Labib + Martha
// APCS pd6
// FP -- Blackjack
// 2022-01-14
// time spent: 5.7 hrs

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends Gambler{
  private ArrayList totalPlayers;
  private Dealer dealerRef;

  public Player(ArrayList deckOfCards, ArrayList players, Dealer dealer){
    super(deckOfCards);
    totalPlayers = players; // passes a ref
    dealerRef = dealer; // passes a ref

    // why is ArrayList players and Dealer dealer necessary?
    // because in the case that we want to split, Player needs to have a
    // reference to the ArrayList storing all players to add a new Player.
    // also, it, along with dealer, is used in getting the running count.
  }

  public Player(ArrayList deckOfCards, String splitCard, ArrayList players, Dealer dealer){
    super(deckOfCards, splitCard);
    totalPlayers = players; // passes a ref
    dealerRef = dealer; // passes a ref
  }

  // Player is prompted to choose their next move.
  public boolean nextMove(){
    int input = 0;
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader in = new BufferedReader(isr);

    System.out.println(TerminallyIll.color(35,TerminallyIll.BRIGHT) + "Your turn!");
    System.out.println("Your hand is... " + showHand());
    System.out.println("The value of your hand is " + inHand + "." + TerminallyIll.RESET);
    System.out.println("What would you like to do? Your options (right now) are:");
    System.out.println(" 0: instructions \n 1: hit \n 2: stand \n 3: double \n 4: split \n 5: count cards ");
    try { input = Integer.parseInt(in.readLine()); }
    catch (IOException e) {}

    if (input == 0){
      System.out.println("--------------------------------"); // divider to make it easier to read
      System.out.println(" In Blackjack, you draw cards which add to a total amount of points you have. \nGoal: Have a hand worth as close as possible to 21 points without going over 21 points. \n\n Each card's number corresponds to their point value. For example, a 5 is worth 5 points. The symbols have no effect on point value. \n Face cards (jack, queen, king) are worth 10 points. Aces are worth 11 points. \n If you have a hand worth over 21 points but have an ace, the ace's value decreases from 11 to 1. \n\n You have 5 options for actions: hit, stand, double, split, and card count."); 
      System.out.println(TerminallyIll.color(31,TerminallyIll.BRIGHT) + "\n Hit means you draw a card." + TerminallyIll.RESET); 
      System.out.println(TerminallyIll.color(32,TerminallyIll.BRIGHT) + "\n Stand means that you end your turn with the number of points your hand is worth and the bots play." + TerminallyIll.RESET); 
      System.out.println(TerminallyIll.color(33,TerminallyIll.BRIGHT) + "\n Double is assigning the next drawn card a value 2 times its original. For example, if you use double and the next card is a 5, it is now worth 10 points. This is only possible once in your turn." + TerminallyIll.RESET); 
      System.out.println(TerminallyIll.color(34,TerminallyIll.BRIGHT) + "\n Split means dividing your hand into two hands, or two games. This is only possible once at the beginning of your turn and if you have two of the same card, such as two fives." + TerminallyIll.RESET); 
      System.out.println(TerminallyIll.color(35,TerminallyIll.BRIGHT) + "\n Card count is a tool that uses simple high-low probability to provide you with a probability of drawing a high value card. \n If you have a positive card count, you will most likely get a high-value card. \n On the other hand, if it's a negative card count, you will most likely NOT get a high-value card." + TerminallyIll.RESET); 
      System.out.println("\n\n Once your turn ends, wait for the bots to play through their game to determine who's the winner. If your hand is not the greastest, it's a bust, and you lose. If it's the greatest, Congratulations! You just won the game!"); 
      System.out.println("\n\n To play this game, you spend credits. Credits work similar to tokens. You start out every game after java compile and run with 10 credits. Every game you play in a session requires 1 token as payment."); 
      System.out.println(TerminallyIll.color(32,TerminallyIll.BRIGHT) + "\n If you win a game, you get 2 tokens, 1 for the token you just spent, and an extra as a reward."  + TerminallyIll.RESET); 
      System.out.println(TerminallyIll.color(31,TerminallyIll.BRIGHT) + "\n If you lose, you get no tokens." + TerminallyIll.RESET); 
      System.out.println(TerminallyIll.color(33,TerminallyIll.BRIGHT) + "\n If you tie with the dealer, you keep the token you used." + TerminallyIll.RESET);                   
      System.out.println("\n When you reach 0 tokens, you lose and cannot play anymore.");
      System.out.println("--------------------------------"); // divider to make it easier to read
    }
    else if (input == 1){ // hit
      System.out.println("--------------------------------"); // divider to make it easier to read
      hit();
    }
    else if (input == 2){ // stand
      System.out.println("--------------------------------"); // divider to make it easier to read
      return stand();
    }
    else if (input == 3 && hand.size() == 2){ // double
      System.out.println("--------------------------------"); // divider to make it easier to read
      return doubleDown();
    }
    else if (input == 4 && hand.size() == 2 && // split
    (Deck.valueOf((String)hand.get(0)) == Deck.valueOf((String)hand.get(1)))){
      System.out.println("--------------------------------"); // divider to make it easier to read
      split();
    }
    else if (input == 5){ // count cards
      System.out.println("--------------------------------"); // divider to make it easier to read
      for (int i = 0; i < totalPlayers.size(); i++){
        System.out.print("Hand of Gambler " + (i+1) + " of " + totalPlayers.size() + ": ");
        System.out.println(((Gambler)(totalPlayers.get(i))).getHand());
        System.out.println();
      }
      System.out.print("Hand of Dealer: ");
      System.out.println(dealerRef.getHand());
      System.out.println();

      System.out.println("Running card count: " + getRunningCount());
      System.out.println();
    }
    else{
      System.out.println("Invalid choice! Try again!\n");
    }
    return false;
  }

  // gets the running count (card count of everyone's hand combined)
  private String getRunningCount(){
    int runningCount = 0;
    for (int i = 0; i < totalPlayers.size(); i++){
      runningCount += ((Gambler)(totalPlayers.get(i))).cardCount();
    }
    runningCount += dealerRef.cardCount();
    if (runningCount > 0){
      return "+" + runningCount;
    }
    return runningCount + "";
  }

  // playTurn() holds a loop of nextMove(), until blackjack, bust, double, or stand is used.
  public void playTurn(){
    while (true){
      aceCheck();
      if (inHand == 21){
        System.out.println("This player's hand is... " + showHand());
        System.out.println("The value of the player's hand is " + inHand + ".\n");
        System.out.println(TerminallyIll.color(32,TerminallyIll.BRIGHT) + "Blackjack!\n" + TerminallyIll.RESET);
        break;
      }
      else if (inHand > 21){
        System.out.println(TerminallyIll.color(31,TerminallyIll.BRIGHT) + "Bust!\n" + TerminallyIll.RESET);
        break;
      }
      else if (nextMove()){ // stand or double was used
        if (inHand == 21){
          System.out.println(TerminallyIll.color(32,TerminallyIll.BRIGHT) + "Blackjack!\n" + TerminallyIll.RESET);
        }
        else if (inHand > 21){
          System.out.println(TerminallyIll.color(31,TerminallyIll.BRIGHT) + "Bust!\n" + TerminallyIll.RESET);
        }
        break; // break regardless
      }
    }
    TerminallyIll.wait(250);
  }

  // invokes overloaded constructor to create a new Player, moves first card
  // from original player to new player as the new hand value.
  protected void split(){
    System.out.println("Splitting hand! Using another play credit.\n");
    aceIndex = -1; // reset aceIndex
    String splitCard = (String)hand.remove(0);
    inHand = Deck.valueOf((String)hand.get(0));
    hit();

    // adds the new split hand as the last player in the ArrayList of players.
    totalPlayers.add(new Player(deckInPlay, splitCard, totalPlayers, dealerRef));
  }

}
