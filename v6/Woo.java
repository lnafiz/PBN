// Peanut Butter and Nutella - Prattay Dey + Winnie, Brian Li + Robert, Nafiz Labib + Martha
// APCS pd6
// FP -- Blackjack
// 2022-01-14
// time spent: 5.7 hrs

public class Woo{
  public static void main(String[] args){
    int startingCredits = 10; // how many play credits will you start with?

    System.out.println(TerminallyIll.color(TerminallyIll.ITALICS, TerminallyIll.BRIGHT) + "Welcome to Blackjack!"
    + TerminallyIll.RESET);
    System.out.println(TerminallyIll.HIDE_CURSOR);
    Blackjack game = new Blackjack(startingCredits);

    while (game.getCredits() > 0){
      game.welcome();
      game.playTurns();
      game.playDealer();
      game = new Blackjack(game.getCredits()); // reset Blackjack
    }

    if (game.getCredits() < 0){
      System.out.println("You have managed to go into debt with the casino. Impressive!");
    }

    System.out.println("Unfortunately, you have run out of credits. Goodbye.\n");
    System.out.println(TerminallyIll.RESET);

  } // end main
} // end class
