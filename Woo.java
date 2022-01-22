// Peanut Butter and Nutella - Prattay Dey + Winnie, Brian Li + Robert, Nafiz Labib + Martha
// APCS pd6
// FP -- Blackjack
// 2022-01-14
// time spent: 5.2 hrs

// TO DO:
// ansi (terminally ill)

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

    System.out.println("Unfortunately, you have run out of credits. Goodbye.\n");

  } // end main
} // end class
