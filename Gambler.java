// Peanut Butter and Nutella - Prattay Dey + Winnie, Brian Li + Robert, Nafiz Labib + Martha
// APCS pd6
// FP -- Blackjack
// 2022-01-14
// time spent: 1.2 hrs

import java.util.ArrayList;

public class Gambler{
  public ArrayList hand = new ArrayList<String>();
  public ArrayList handValues = new ArrayList<Integer>();
  public int inHand = 0;
  public ArrayList deckInPlay;
  public ArrayList deckValuesInPlay;

  public Gambler(ArrayList deckOfCards, ArrayList valueOfCards){
    deckInPlay = deckOfCards; // pass-by-ref
    deckValuesInPlay = valueOfCards; // pass-by-ref
    hit(); hit();
    System.out.println(hand); // diag
    aceCheck();
    System.out.println("inHand: " + inHand); // diag
  }

  // true = turn ends
  // false = turn keeps going
  public boolean nextMove(String input){
    input = input.toLowerCase();

    // later: remove inHand == 21 and inHand > 21, since they should be checked
    // outside of nextMove. Blackjack or bust should be determined prior to
    // selecting a move.

    if (inHand == 21){
      System.out.println("Blackjack!");
      return true;
    }
    else if (inHand > 21){
      aceCheck();
      if (inHand > 21){ // if hand is still >21 after the aceCheck, bust
        System.out.println("Bust!");
        return true;
      }
      return false; // successful aceCheck, continue playing.
    }
    else{ // inHand must be < 21
      if (input.equals("hit")){
        hit();
      }
      else if (input.equals("stand")){
        return stand();
      }
      else if ((input.equals("double")) && (hand.size() == 2)){
        return doubleDown();
      }
      else if ((input.equals("split")) && (hand.size() == 2) &&
      (handValues.get(0)).equals(handValues.get(1))){
        split();
      }
      return false;
    }
  }

  public void aceCheck(){
    for (int i = 0; i < handValues.size(); i++){
      if (((int)(handValues.get(i)) == 11) && inHand > 21){
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
    handValues.add(deckValuesInPlay.remove(0));
    inHand += (int)handValues.get(handValues.size() - 1);
  }

  public boolean stand(){
    return true;
  }

  public boolean doubleDown(){
    hit();
    return stand();
  }

  public void split(){
    // in development
  }
}
