// Peanut Butter and Nutella - Prattay Dey + Winnie, Brian Li + Robert, Nafiz Labib + Martha
// APCS pd6
// FP -- Blackjack
// 2022-01-14
// time spent: 1.2 hrs

import java.util.ArrayList;

public class Gambler{
  public ArrayList hand = new ArrayList<String>();
  public ArrayList deckInPlay;
  public int aceIndex = -1;
  public int inHand = 0;
  private int creditMultiplier = 1;

  public Gambler(ArrayList deckOfCards){
    deckInPlay = deckOfCards; // pass-by-ref
    silentHit(); silentHit();
    // System.out.println(hand); // diag
    aceCheck();
    // System.out.println("inHand: " + inHand); // diag
  }

  public Gambler(ArrayList deckOfCards, String splitCard){
    deckInPlay = deckOfCards; // pass-by-ref
    hand.add(splitCard);
    inHand = Deck.valueOf(splitCard);
    silentHit();
    // System.out.println(hand); // diag
    aceCheck();
    // System.out.println("inHand: " + inHand); // diag
  }

  // expected to be overwritten
  public boolean nextMove(){
    return false;
  }

  // expected to be overwritten
  public void playTurn(){
  }

  public int getCredMultiplier(){
    return creditMultiplier;
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

  // checks if we need to turn an Ace's value of 11 to a value of 1.
  public void aceCheck(){
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

  public void hit(){
    hand.add(deckInPlay.remove(0));
    inHand += Deck.valueOf((String)hand.get(hand.size() - 1));
    System.out.println("Hit! Drew a " + (String)hand.get(hand.size() - 1));
    System.out.println("New hand: " + hand);
    aceCheck();
    System.out.println("New hand value: " + inHand);
    System.out.println();
  }

  public void silentHit(){ // only to be used when you don't want the "Hit!" message.
    hand.add(deckInPlay.remove(0));
    inHand += Deck.valueOf((String)hand.get(hand.size() - 1));
  }

  public boolean stand(){
    System.out.println("End turn.\n");
    return true;
  }

  public boolean doubleDown(){
    System.out.println("Double!\n");
    hit();
    creditMultiplier *= 2;
    return stand();
  }

  public void split(){
    // split is a Player-only option.
  }
}
