// Peanut Butter and Nutella - Prattay Dey + Winnie, Brian Li + Robert, Nafiz Labib + Martha
// APCS pd6
// FP -- Blackjack
// 2022-01-14
// time spent: 1.2 hrs

import java.io.*;
import java.util.*;

public class Woo{
  public static void main(String[] args){
    Deck deck = new Deck();
    Gambler[] totalBots = new Bot[0];
    Gambler humanPlayer = new Gambler(deck.cardsRemaining. deck.cardValuesRemaining);
    ArrayList totalPlayers = new ArrayList<Gambler>();
    System.out.println(deck.cardsRemaining); // diag

    int numBots = 0;
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
    for (Gambler bot : totalBots){
      bot = new Bot(deck.cardsRemaining, deck.cardValuesRemaining); // draws 2 from cardsRemaining
    }

    for (Gambler bot : totalBots){
      totalPlayers.add(bot);
    }
    totalPlayers.add(humanPlayer);

    System.out.println("Remaining cards in the deck: " + deck.cardsRemaining); // diag
    System.out.println("Corresponding values: \t" + deck.cardValuesRemaining); // diag
    System.out.println("Remaining # of cards: " + deck.cardsRemaining.size()); // diag


  }
}
