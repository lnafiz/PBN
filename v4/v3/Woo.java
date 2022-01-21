// Peanut Butter and Nutella - Prattay Dey + Winnie, Brian Li + Robert, Nafiz Labib + Martha
// APCS pd6
// FP -- Blackjack
// 2022-01-14
// time spent: 1.2 hrs

// TO DO:
// change visibility of stuff, add mutator methods. (done for some)
// HI-LO CARD COUNTING

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Woo{
  public static void main(String[] args){
    int startingCredits = 10; // how many play credits will you start with?

    System.out.println("Welcome to Blackjack!");
    Blackjack game = new Blackjack(startingCredits);

    while (game.getCredits() > 0){
      game.welcome();
      game.playTurns();
      game.playDealer();
      game = new Blackjack(game.getCredits()); // reset Blackjack
    }

  } // end main
} // end class
