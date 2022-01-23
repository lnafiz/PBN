// Peanut Butter and Nutella - Prattay Dey + Winnie, Brian Li + Robert, Nafiz Labib + Martha
// APCS pd6
// FP -- Blackjack
// 2022-01-14
// time spent: 5.7 hrs

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
    dealer = new Dealer(deck.getCardsRemaining());
    numBots = 0;
    totalBots = new Bot[0];
    totalGamblers = new ArrayList<Gambler>();
    humanPlayer = new Player(deck.getCardsRemaining(), totalGamblers, dealer);
    isr = new InputStreamReader(System.in);
    in = new BufferedReader(isr);
  }

  public Blackjack(int credits){
    this();
    playCredits = credits;
  }

  // getter method
  public int getCredits(){
    return playCredits;
  }

  // prompts for number of bots to play against, and sets up accordingly.
  // introduces dealer's hand
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

    dealer.dealerHand();

    for (int i = 0; i < totalBots.length; i++){
      totalBots[i] = new Bot(deck.getCardsRemaining()); // draws 2 from cardsRemaining
      totalGamblers.add(totalBots[i]);
    }
    totalGamblers.add(humanPlayer);

    // shuffle order of gamblers
    Deck.shuffle(totalGamblers);
  }

  // makes every Gambler play until blackjack, bust, stand, or double.
  public void playTurns(){
    for (int i = 0; i < totalGamblers.size(); i++){
      Gambler gambler;
      if (totalGamblers.get(i) instanceof Bot){
        gambler = (Bot)(totalGamblers.get(i));
      }
      else{
      // if not Bot, then it is a Player.
        gambler = (Player)(totalGamblers.get(i));
      }

      System.out.println("--------------------------------");
      System.out.println("Now on gambler " + (i+1) + " of " + totalGamblers.size() + ".");
      TerminallyIll.wait(250);
      System.out.println();

      // play moves
      gambler.playTurn();
    }
  }

  // Dealer reveals hand, plays turn. Then determines if you won.
  public void playDealer(){
    TerminallyIll.wait(250);
    System.out.println("--------------------------------");
    System.out.println("Dealer's turn!");
    dealer.reveal();
    dealer.playTurn();

    determineOutcome();
  }

  // determines if Player wins, pushes, or loses.
  public void determineOutcome(){
    for (int i = 0; i < totalGamblers.size(); i++){
      if (totalGamblers.get(i) instanceof Player){
        Player guy = ((Player)(totalGamblers.get(i)));
        // if there was a split, use up another play credit
        if (guy.isFromSplit()){
          playCredits -= 1;
        }

        int handTotal = guy.getInHand();
        if ((handTotal > dealer.getInHand() && handTotal <= 21) ||
        (dealer.getInHand() > 21 && handTotal <= 21)){
          playCredits += 2 * (guy.getCredMultiplier()); // if doubled, then win 2x
          System.out.println(TerminallyIll.color(32, TerminallyIll.BRIGHT) + "Win! Your new number of play credits is " +
          playCredits + "!\n" + TerminallyIll.RESET);
        }

        else if (handTotal == dealer.getInHand() && handTotal <= 21){
          playCredits += 1;
          System.out.println(TerminallyIll.color(33, TerminallyIll.BRIGHT) + "Push! Keep your play credit.\n" +
          TerminallyIll.RESET);
        }

        else{
          // if doubled, then lose another credit
          if (guy.getCredMultiplier() != 1){
            playCredits -= 1;
          }
          System.out.println(TerminallyIll.color(31,TerminallyIll.BRIGHT) + "Lose! You have " + playCredits + " credits!\n" +
          TerminallyIll.RESET);
        }

        guy.resetCredMultiplier(); // if doubled, reset to 1x

      }
    }
  }

} // end class
