// Peanut Butter and Nutella - Prattay Dey + Winnie, Brian Li + Robert, Nafiz Labib + Martha
// APCS pd6
// FP -- Blackjack
// 2022-01-14
// time spent: 1.2 hrs

import java.util.ArrayList;

public class Deck{
  public ArrayList cardsRemaining = new ArrayList<String>();

  public Deck(){
    for (String element : DECK){ cardsRemaining.add(element); }
    shuffle(cardsRemaining);
  }

  private static void shuffle( ArrayList cr ) {
    int randomIndex;
    for( int i = cr.size()-1; i > 0; i-- ) {
      //pick an index at random
      randomIndex = (int)( (i+1) * Math.random() );
      //swap the values at position i and randomIndex
      cr.set( i, cr.set( randomIndex, cr.get(i) ) );
    }
  }

  public static int valueOf(String card){
    String value = card.substring(0, card.length() - 2); // -3 because the suit counts as 3 characters.
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
      } catch(NumberFormatException ex){}
    }
    return i; // if it gets to this, there is something wrong.
  }


// should be in Woo.java
  // public int countCards(ArrayList cr){
  //   int total = 0;
  //   for (String element : DECK){
  //     if
  //   }
  //
  // }

  private final String[] DECK = {
  "A♠", "2♠", "3♠", "4♠", "5♠", "6♠", "7♠", "8♠", "9♠", "10♠", "J♠", "Q♠", "K♠",
  "A♣", "2♣", "3♣", "4♣", "5♣", "6♣", "7♣", "8♣", "9♣", "10♣", "J♣", "Q♣", "K♣",
  "A♥", "2♥", "3♥", "4♥", "5♥", "6♥", "7♥", "8♥", "9♥", "10♥", "J♥", "Q♥", "K♥",
  "A♦", "2♦", "3♦", "4♦", "5♦", "6♦", "7♦", "8♦", "9♦", "10♦", "J♦", "Q♦", "K♦"};

}
