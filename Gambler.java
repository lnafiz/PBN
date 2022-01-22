// Peanut Butter and Nutella - Prattay Dey + Winnie, Brian Li + Robert, Nafiz Labib + Martha
// APCS pd6
// FP -- Blackjack
// 2022-01-14
// time spent: 5.2 hrs

import java.util.ArrayList;

public class Gambler{
  protected ArrayList hand = new ArrayList<String>();
  protected ArrayList deckInPlay;
  protected int aceIndex = -1;
  protected int inHand = 0;
  protected int creditMultiplier = 1;
  protected boolean splitOrigin = false;

  // constr: Gambler is instantiated and draws 2 cards.
  public Gambler(ArrayList deckOfCards){
    deckInPlay = deckOfCards; // passses a ref
    silentHit(); silentHit();
    aceCheck();
    // System.out.println(hand); // diag
    // System.out.println("inHand: " + inHand); // diag
  }

  // overloaded constr: Gambler is instantiated with a card already in hand.
  public Gambler(ArrayList deckOfCards, String splitCard){
    splitOrigin = true;
    deckInPlay = deckOfCards; // passes a ref
    hand.add(splitCard);
    inHand = Deck.valueOf(splitCard);
    silentHit();
    aceCheck();
    // System.out.println(hand); // diag
    // System.out.println("inHand: " + inHand); // diag
  }

  // getter methods ===========================================================
  public boolean isFromSplit(){
    return splitOrigin;
  }

  public int getCredMultiplier(){
    return creditMultiplier;
  }

  public ArrayList getHand(){
    return hand;
  }

  public void resetCredMultiplier(){
    creditMultiplier = 1;
  }

  public int getInHand(){
    return inHand;
  }

  public String showHand(){
    return hand.toString();
  }
  // ==========================================================================

  // expected to be overwritten
  public boolean nextMove(){
    return true;
  }

  // expected to be overwritten
  public void playTurn(){
  }

  // gets the card count of a hand.
  // uses HI-LO card counting system.
  // 2-6 = +1, 7-9 = 0, 10-A = -1 (inclusive)
  public int cardCount(){
    int count = 0;
    for (int i = 0; i < hand.size(); i++){
      if (Deck.valueOf((String)hand.get(i)) <= 6){
        count += 1;
      }
      else if (Deck.valueOf((String)hand.get(i)) >= 10){
        count -= 1;
      }
    }
    return count;
  }

  // checks if we need to turn an Ace's value of 11 to a value of 1.
  // (soft vs. hard ace)
  protected void aceCheck(){
    int oldAceIndex = aceIndex;
    for (int i = aceIndex + 1; i < hand.size(); i++){
      if (Deck.valueOf((String)hand.get(i)) == 11 && inHand > 21){
        // at the first occurence of an Ace, lower inHand value by 10.
        inHand -= 10;
        aceIndex = i;
        break;
      }
    }
    if (aceIndex > oldAceIndex && inHand > 21){
      // if, after the first aceCheck, there is still a bust, check if you can
      // do another aceCheck on the remaining, unchecked cards.
      aceCheck();
    }
  }

  // draw a card
  protected void hit(){
    hand.add(deckInPlay.remove(0));
    inHand += Deck.valueOf((String)hand.get(hand.size() - 1));
    System.out.println("Hit! Drew a " + (String)hand.get(hand.size() - 1));
    System.out.println("New hand: " + hand);
    aceCheck();
    System.out.println("New hand value: " + inHand);
    System.out.println();
  }

  // only to be used when you don't want the "Hit!" message. used in constructor.
  protected void silentHit(){
    hand.add(deckInPlay.remove(0));
    inHand += Deck.valueOf((String)hand.get(hand.size() - 1));
    aceCheck();
  }

  // stand, no more moves made.
  protected boolean stand(){
    System.out.println("End turn.\n");
    return true;
  }

  // draws a card, then stands.
  protected boolean doubleDown(){
    System.out.println("Double!\n");
    hit();
    creditMultiplier *= 2;
    return stand();
  }

  protected void split(){
    // split is a Player-only option.
  }
}
