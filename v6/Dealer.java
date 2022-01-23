// Peanut Butter and Nutella - Prattay Dey + Winnie, Brian Li + Robert, Nafiz Labib + Martha
// APCS pd6
// FP -- Blackjack
// 2022-01-14
// time spent: 5.7 hrs

import java.util.ArrayList;

public class Dealer extends Gambler{
  private String cardHolder = "???";
  private int valueHolder = 0;

  public Dealer(ArrayList cardsRemaining){
    super(cardsRemaining);
    hide();
  }

  // shows Dealer's hand
  public void dealerHand(){
    System.out.println("\nDealer's Hand: " + hand);
    System.out.println("Value of Dealer's Hand: " + inHand);
    System.out.println();
  }

  // overwritten: gets card count of dealer's hand (the value of card at index 1)
  // uses HI-LO card counting system.
  // 2-6 = +1, 7-9 = 0, 10-A = -1 (inclusive)
  public int cardCount(){
    int count = 0;
    int value = (Deck.valueOf((String)hand.get(1)));
    if (value <= 6){
      count += 1;
    }
    else if (value >= 10){
      count -= 1;
    }
    return count;

    // why only at index one?
    // since the dealer always goes last, players are only able to see the
    // dealer's card count as the card count of its non-hidden card.
  }

  // hides the first card in the dealer's hand
  public void hide(){
    cardHolder = (String)hand.get(0);
    hand.set(0, "???");
    valueHolder = inHand;
    inHand = Deck.valueOf((String)hand.get(1));
  }

  // reveals the first card in the dealer's hand
  public void reveal(){
    hand.set(0, cardHolder);
    inHand = valueHolder;
    dealerHand();
  }

  // repeatedly hits until reaching 17, getting blackjack, or busting.
  public void playTurn(){
    aceCheck();
    while (inHand < 17){
      TerminallyIll.wait(500);
      hit();
    }
    if (inHand == 21){
      System.out.println("Dealer gets Blackjack!");
    }
    else if (inHand > 21){
      System.out.println("Dealer Busts!");
    }
    else{
      System.out.println("Dealer stands at " + inHand + "!");
    }
    TerminallyIll.wait(250);
  }

}
