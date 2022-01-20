import java.util.ArrayList;

public class Bot extends Gambler{
  public Bot(ArrayList deckOfCards){
    super(deckOfCards);
    System.out.println(hand); // diag
    System.out.println("inHand: " + inHand); // diag
  }

  public boolean nextMove(){
    System.out.println("This bot's hand is... " + showHand());
    System.out.println("The value of the bot's hand is " + inHand + ".");
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
}
