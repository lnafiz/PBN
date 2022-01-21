import java.util.ArrayList;

public class Dealer extends Gambler{
  private String cardHolder = "???";
  private int valueHolder = 0;

  public Dealer(ArrayList cardsRemaining){
    super(cardsRemaining);

    // hides a card from the player
    cardHolder = (String)hand.get(0);
    hand.set(0, "???");
    valueHolder = inHand;
    inHand = Deck.valueOf((String)hand.get(1));

    System.out.println("\nDealer's Hand: " + hand);
    System.out.println("Value of Dealer's Hand: " + inHand);
    System.out.println();
  }

  public void reveal(){
    hand.set(0, cardHolder);
    inHand = valueHolder;

    System.out.println("Dealer's Hand: " + hand);
    System.out.println("Value of Dealer's Hand: " + inHand);
    System.out.println();
  }

  public void playTurn(){
    aceCheck();
    while (inHand < 17){
      hit();
    }
    if (inHand == 21){
      System.out.println("Dealer gets Blackjack!");
    }
    else if (inHand > 21){
      System.out.println("Dealer Busts!");
    }
    else{
      System.out.println("Dealer stands at " + inHand + "!");
    }
  }

}
