// Peanut Butter and Nutella - Prattay Dey + Winnie, Brian Li + Robert, Nafiz Labib + Martha
// APCS pd6
// FP -- Blackjack
// 2022-01-14
// time spent: 1.2 hrs

import java.util.ArrayList;

public class Deck{
  private ArrayList cardsRemaining = new ArrayList<String>(); // ArrayList version of DECK, will be changed through the course of the game.

  public Deck(){
    for (String element : DECK){ cardsRemaining.add(element); }
    shuffle(cardsRemaining);
  }

  public ArrayList getCardsRemaining(){
    return cardsRemaining;
  }

  // HELPER METHODS ============================================================
  // shuffles an ArrayList
  public static void shuffle( ArrayList al ) {
    int randomIndex;
    for( int i = al.size()-1; i > 0; i-- ) {
      //pick an index at random
      randomIndex = (int)( (i+1) * Math.random() );
      //swap the values at position i and randomIndex
      al.set( i, al.set( randomIndex, al.get(i) ) );
    }
  }

  // used to find the integer value of each card in the deck, suit does not matter
  public static int valueOf(String card){
    String value = card.substring(0, card.indexOf(" ")); // isolates only the numerical value of each card, excluding its suit
    // System.out.println(card + "'s value is " + value + ". card's length() is " + card.length()); // diag
    int i = -1;
    if (value.equals("J") || value.equals("Q") || value.equals("K")){
      return 10;
    }
    else if (value.equals("A")){
      return 11;
    }
    else{
      try{
        i = Integer.parseInt(value);
      } catch(NumberFormatException error){}
    }
    return i; // if i is still -1, there is something wrong.
  }
  // ===========================================================================

  private final String[] DECK = {
  "A ♠", "2 ♠", "3 ♠", "4 ♠", "5 ♠", "6 ♠", "7 ♠", "8 ♠", "9 ♠", "10 ♠", "J ♠", "Q ♠", "K ♠",
  "A ♣", "2 ♣", "3 ♣", "4 ♣", "5 ♣", "6 ♣", "7 ♣", "8 ♣", "9 ♣", "10 ♣", "J ♣", "Q ♣", "K ♣",
  "A ♥", "2 ♥", "3 ♥", "4 ♥", "5 ♥", "6 ♥", "7 ♥", "8 ♥", "9 ♥", "10 ♥", "J ♥", "Q ♥", "K ♥",
  "A ♦", "2 ♦", "3 ♦", "4 ♦", "5 ♦", "6 ♦", "7 ♦", "8 ♦", "9 ♦", "10 ♦", "J ♦", "Q ♦", "K ♦"};

  // deck for testing
  // private final String[] DECK = {
  // "A ♠", "A ♠", "A ♠", "A ♠", "A ♠", "A ♠", "A ♠", "A ♠", "A ♠", "A ♠", "2 ♠", "3 ♠", "4 ♠",
  // "A ♣", "A ♣", "A ♣", "A ♣", "A ♣", "A ♣", "A ♣", "A ♣", "A ♣", "A ♣", "2 ♣", "3 ♣", "4 ♣",
  // "A ♥", "A ♥", "A ♥", "A ♥", "A ♥", "A ♥", "A ♥", "A ♥", "A ♥", "A ♥", "2 ♥", "3 ♥", "4 ♥",
  // "A ♦", "A ♦", "A ♦", "A ♦", "A ♦", "A ♦", "A ♦", "A ♦", "A ♦", "A ♦", "2 ♦", "3 ♦", "4 ♦"};

}
