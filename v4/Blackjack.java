// Peanut Butter and Nutella - Prattay Dey + Winnie, Brian Li + Robert, Nafiz Labib + Martha
// APCS pd6
// FP -- Blackjack
// 2022-01-14
// time spent: 1.2 hrs

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Blackjack{
  private Deck deck;
  private int numBots;
  private Gambler[] totalBots;
  private ArrayList totalGamblers;
  private Gambler humanPlayer;
  private InputStreamReader isr;
  private BufferedReader in;
  private Dealer dealer;
  private int playCredits;

  public Blackjack(){
    deck = new Deck();
    numBots = 0;
    totalBots = new Bot[0];
    totalGamblers = new ArrayList<Gambler>();
    humanPlayer = new Player(deck.getCardsRemaining(), totalGamblers);
    isr = new InputStreamReader(System.in);
    in = new BufferedReader(isr);
  }

  public Blackjack(int credits){
    this();
    playCredits = credits;
  }

  public int getCredits(){
    return playCredits;
  }

  public void welcome(){
    playCredits -= 1;
    System.out.println("Using one play credit. Your number of play credits remaining is " + playCredits + ".");

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

    dealer = new Dealer(deck.getCardsRemaining());

    for (int i = 0; i < totalBots.length; i++){
      totalBots[i] = new Bot(deck.getCardsRemaining()); // draws 2 from cardsRemaining
      totalGamblers.add(totalBots[i]);
    }
    totalGamblers.add(humanPlayer);

    // shuffle order of gamblers
    Deck.shuffle(totalGamblers);
  }

  public void playTurns(){
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
      // System.out.println("Current hand: " + gambler.hand); // diag
      System.out.println();

      // play moves
      gambler.playTurn();
    }
  }

  public void playDealer(){
    System.out.println("\nDealer's turn!");
    dealer.reveal();
    dealer.playTurn();

    for (int i = 0; i < totalGamblers.size(); i++){
      if (totalGamblers.get(i) instanceof Player){
        if (((Player)(totalGamblers.get(i))).isFromSplit()){
          playCredits -= 1;
        } // if there was a split, use up another play credit

        int handTotal = ((Player)(totalGamblers.get(i))).getInHand();
        if ((handTotal > dealer.getInHand() && handTotal <= 21) ||
        (dealer.getInHand() > 21 && handTotal <= 21)){
          playCredits += 2 * (((Player)(totalGamblers.get(i))).getCredMultiplier()); // if doubled, then win 2x
          System.out.println("Win! Your new number of play credits is " + playCredits + "!\n");
        }
        else if (handTotal == dealer.getInHand()){
          playCredits += 1;
          System.out.println("Push! Keep your play credit.\n");
        }
        else{
          // if doubled, then lose another credit
          if ((((Player)(totalGamblers.get(i))).getCredMultiplier()) != 1){
            playCredits -= 1;
          }
          System.out.println("Lose!\n");
        }
        ((Player)(totalGamblers.get(i))).resetCredMultiplier(); // if doubled, reset to 1x
      }
    }
  }

} // end class
