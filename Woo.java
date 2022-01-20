// Peanut Butter and Nutella - Prattay Dey + Winnie, Brian Li + Robert, Nafiz Labib + Martha
// APCS pd6
// FP -- Blackjack
// 2022-01-14
// time spent: 1.2 hrs

// TO DO:
// change visibility of stuff, add mutator methods.
// move the stuff from main method to another file? maybe?
// add scores/balance
// HI-LO CARD COUNTING

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Woo{
  public static void main(String[] args){
    Deck deck = new Deck();
    int numBots = 0;
    Gambler[] totalBots = new Bot[0];
    ArrayList totalGamblers = new ArrayList<Gambler>();
    Gambler humanPlayer = new Player(deck.cardsRemaining, totalGamblers);
    // System.out.println(deck.cardsRemaining); // diag

    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader in = new BufferedReader(isr);

    System.out.println("Welcome to Blackjack!");
    System.out.println("How many do you want to play against? (0-7)");
    try {
	    numBots = Integer.parseInt(in.readLine());
    }
    catch ( IOException e ) { }

    if (numBots > -1 && numBots < 8){
      totalBots = new Gambler[numBots];
    }
    else{
      System.out.println("Invalid number of bots! Playing alone.");
    }

    Dealer dealer = new Dealer(deck.cardsRemaining);

    for (int i = 0; i < totalBots.length; i++){
      totalBots[i] = new Bot(deck.cardsRemaining); // draws 2 from cardsRemaining
      totalGamblers.add(totalBots[i]);
    }
    totalGamblers.add(humanPlayer);

    // shuffle order of gamblers
    Deck.shuffle(totalGamblers);

    // System.out.println("diag: " + totalGamblers); // diag

    // System.out.println("Remaining cards in the deck: " + deck.cardsRemaining); // diag
    // System.out.println("Remaining # of cards: " + deck.cardsRemaining.size()); // diag

    for (int i = 0; i < totalGamblers.size(); i++){
      Gambler gambler;
      // System.out.println(totalGamblers.get(i)); // diag
      if (totalGamblers.get(i) instanceof Bot){
        gambler = (Bot)(totalGamblers.get(i));
      }
      else{
      // if not Bot, then it is a Player.
        gambler = (Player)(totalGamblers.get(i));
      }

      System.out.println("Now on gambler " + (i+1) + " of " + totalGamblers.size() + ".");
      System.out.println("Current hand: " + gambler.hand);
      System.out.println();

      while (true){
        gambler.aceCheck();
        if (gambler.inHand == 21){
          System.out.println("Blackjack!");
          break;
        }
        else if (gambler.inHand > 21){
          System.out.println("Bust!");
          break;
        }
        if (gambler.nextMove() == true){
          if (gambler.inHand == 21){
            System.out.println("Blackjack!");
          }
          else if (gambler.inHand > 21){
            System.out.println("Bust!");
          }
          break;
        }
      }
    }

    System.out.println("\nDealer's turn!");
    dealer.reveal();
    dealer.nextMove();

    for (int i = 0; i < totalGamblers.size(); i++){
      if (totalGamblers.get(i) instanceof Player){
        int handTotal = ((Player)(totalGamblers.get(i))).getInHand();
        if ((handTotal > dealer.getInHand() && handTotal < 21) ||
            (dealer.getInHand() > 21 && handTotal < 21)){
          System.out.println("Win!\n");
        }
        else if (handTotal == dealer.getInHand()){
          System.out.println("Push!\n");
        }
        else{
          System.out.println("Lose!\n");
        }
      }
    }

  } // end main
} // end class

