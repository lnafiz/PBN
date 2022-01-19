import java.util.ArrayList;

public class Dealer extends Gambler{
  public Dealer(ArrayList input){
    super(input);
  }

  public boolean nextMove(){
    System.out.println("The dealer's hand is..." + showHand());
    while (inHand < 17){
      hit();
    }
    stand();
  }


}
