// Peanut Butter and Nutella - Prattay Dey + Winnie, Brian Li + Robert, Nafiz Labib + Martha
// APCS pd6
// FP -- Blackjack
// 2022-01-14
// time spent: 5.2 hrs

import java.util.ArrayList;

public class Bot extends Gambler{
  public Bot(ArrayList deckOfCards){
    super(deckOfCards);
    // System.out.println(hand); // diag
    // System.out.println("inHand: " + inHand); // diag
  }

  // chooses to hit, stand, or double based on Math.random()
  public boolean nextMove(){
    System.out.println("This bot's hand is... " + showHand());
    System.out.println("The value of the bot's hand is " + inHand + ".\n");
    int rng = (int)(Math.random() * 3);
    if (rng == 0 && hand.size() == 2){
      return doubleDown();
    }
    else if (rng == 0 || rng == 1){
      hit();
    }
    else{
      return stand();
    }
    return false;
  }

  // keeps choosing nextMove until blackjack, bust, stand, or double is used.
  public void playTurn(){
    while (true){
      aceCheck();
      if (inHand == 21){
        System.out.println("This bot's hand is... " + showHand());
        System.out.println("The value of the bot's hand is " + inHand + ".\n");
        System.out.println("Blackjack!\n");
        break;
      }
      else if (inHand > 21){
        System.out.println("Bust!\n");
        break;
      }
      else if (nextMove()){ // if either stand or double was used:
        if (inHand == 21){
          System.out.println("Blackjack!\n");
        }
        else if (inHand > 21){
          System.out.println("Bust!\n");
        }
        break; // break regardless
      }
    }
  }

}
