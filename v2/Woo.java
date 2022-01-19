// Peanut Butter and Nutella - Prattay Dey + Winnie, Brian Li + Robert, Nafiz Labib + Martha
// APCS pd6
// FP -- Blackjack
// 2022-01-14
// time spent: 1.2 hrs

// TO DO:
// change visibility of stuff, add mutator methods.
// move the stuff from main method to another file?
// shuffle player order
// add scores/balance
// use dealer
// HI-LO CARD COUNTING

import java.io.*;
import java.util.*;

public class Woo{
  public static void main(String[] args){
    Deck deck = new Deck();
    int numBots = 0;
    Gambler[] totalBots = new Bot[0];
    ArrayList totalPlayers = new ArrayList<Gambler>();
    Gambler humanPlayer = new Player(deck.cardsRemaining, totalPlayers);
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

    // cannot use foreach loop because it would create a copy of reference.
    for (int i = 0; i < totalBots.length; i++){
      totalBots[i] = new Bot(deck.cardsRemaining); // draws 2 from cardsRemaining
    }

    // redundant?
    for (int i = 0; i < totalBots.length; i++){
      totalPlayers.add(totalBots[i]);
    }
    totalPlayers.add(humanPlayer);

    // System.out.println("diag: " + totalPlayers); // diag

    // System.out.println("Remaining cards in the deck: " + deck.cardsRemaining); // diag
    // System.out.println("Remaining # of cards: " + deck.cardsRemaining.size()); // diag

    for (int i = 0; i < totalPlayers.size(); i++){
      Gambler gambler;
      int gamblerHand;
      // System.out.println(totalPlayers.get(i)); // diag
      if (totalPlayers.get(i) instanceof Bot){
        gambler = (Bot)(totalPlayers.get(i));
      }
      else{
      // if (totalPlayers.get(i) instanceOf Player){
        gambler = (Player)(totalPlayers.get(i));
      }

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
          break;
        }
      }

    }
  }
}
