// Peanut Butter and Nutella - Prattay Dey + Winnie, Brian Li + Robert, Nafiz Labib + Martha
// APCS pd6
// FP -- Blackjack
// 2022-01-14
// time spent: 1.2 hrs

import java.util.ArrayList;

public class Gambler{
  public ArrayList hand = new ArrayList<String>();
  public ArrayList deckInPlay;
  public int inHand = 0;

  public Gambler(ArrayList deckOfCards){
    deckInPlay = deckOfCards; // pass-by-ref
    hit(); hit();
    // System.out.println(hand); // diag
    aceCheck();
    // System.out.println("inHand: " + inHand); // diag
  }

  public Gambler(ArrayList deckOfCards, String splitCard){
    deckInPlay = deckOfCards; // pass-by-ref
    hand.add(splitCard);
    inHand = Deck.valueOf(splitCard);
    hit();
    System.out.println(hand); // diag
    aceCheck();
    System.out.println("inHand: " + inHand); // diag
  }

  // SET THIS TO DEALER'S NEXTMOVE
  public boolean nextMove(){

    // later: remove inHand == 21 and inHand > 21, since they should be checked
    // outside of nextMove. Blackjack or bust should be determined prior to
    // selecting a move.

    // if (input.equals("hit")){// in development
    //   hit();
    // }
    // else if (input.equals("stand")){
    //   return stand();
    // }
    // else if ((input.equals("double")) && (hand.size() == 2)){
    //   return doubleDown();
    // }
    // // split is a Player-only option
    return false;
  }

  public int getInHand(){
    return inHand;
  }

  public String showHand(){
    return hand.toString();
  }

  public void aceCheck(){
    for (int i = 0; i < hand.size(); i++){
      if (Deck.valueOf((String)hand.get(i)) == 11 && inHand > 21){
        inHand -= 10;
        break;
      }
    }
    if (inHand > 21){
      aceCheck();
    }
  }

  public void hit(){
    hand.add(deckInPlay.remove(0));
    inHand += Deck.valueOf((String)hand.get(hand.size() - 1));
  }

  public boolean stand(){
    return true;
  }

  public boolean doubleDown(){
    hit();
    return stand();
  }

  // split is a Player-only option.
}
