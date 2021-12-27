package poker;

public class Deck {

	public Card[] cards;   //private but changed to public for tests
	//This constructor initializes the Deck with 52 card objects, representing the 52 cards that are in a standard deck.
	public Deck() {
		cards = new Card[52];
		for (int i = 0; i < cards.length; i++) {
			int value = (i+1) % 13;
			if (value == 0) {
				value = 13;
			}
			int suit = i / 13;
			cards[i] = new Card(value, suit);
		}
	}
	//Standard copy constructor.
	public Deck(Deck other) {
		cards = new Card[other.getNumCards()];
		for (int i = 0; i < cards.length; i++) {
			cards[i] = other.getCardAt(i);
		}
	}
	//Returns the card that is at the specified position in the array. 
	public Card getCardAt(int position) {
		int value = cards[position].getValue();
		int suit = cards[position].getSuit();
		Card cardIn = new Card(value, suit);
		return cardIn;
	}
	//Returns the size of the array of Cards.
	public int getNumCards() {
		return cards.length;
	}
	//This method will re-arrange the cards that are in the deck.  
	//The idea is that the deck will be divided into two "packets"
	//-- the top half and the bottom half.
	//The new array of cards will consist of: one card from the top packet,
	//followed by one card from the bottom packet,
	 public void shuffle() {
		    Card[] top;
		    if (cards.length % 2 == 0) {
		      top = new Card[cards.length/2];
		    }
		    else {
		      top = new Card[(cards.length/2) + 1];
		    }
		    
		    for (int i = 0; i < top.length; i++) {
		      top[i] = cards[i];
		    }
		    
		    Card[] bottom = new Card[cards.length/2];
		    int counter = top.length;
		    
		    for (int i = 0; i < bottom.length; i++) {
		      bottom[i] = cards[counter];
		      counter++;
		    }
		    
		    int topCounter = 0;
		    int bottomCounter = 0;
		    
		    for (int i = 0; i < cards.length; i++) {
		      if (i % 2 == 0) {
		        cards[i] = top[topCounter];
		        topCounter++;
		      }
		      else {
		        cards[i] = bottom[bottomCounter];
		        bottomCounter++;
		      }
		    }
		  }

	// This method divides the deck into two subpackets: 
	//The part above the specified position, 
	//and the part that is at the specified position or below. 
	public void cut(int position) {
		Card[] top=new Card[position];
		Card[] bottom=new Card[cards.length-top.length];
		for (int i = 0; i < position; i++) {
			top[i]=cards[i];
		}
		int bottomCounter=position;
		for (int i = 0; i < bottom.length; i++) {
			bottom[i]=cards[bottomCounter];
			bottomCounter++;
		}
		int topCounter=0;
		for (int i = 0; i < cards.length; i++) {
			if(i<bottom.length) {
				cards[i]=bottom[i];
			} else {
				cards[i]=top[topCounter];
				topCounter++;
			}
		}
	}

	//This method will remove the specified number of cards 
	//from the top of the deck and return them as an array.
	  public Card[] deal(int numCards) {
		    
		  Card[] dealing = new Card[numCards];
		  for (int i = 0; i < dealing.length; i++) {
			  dealing[i] = cards[i];
			  }
		    
		    Card[] smaller = new Card[cards.length - numCards];
		    for (int i = 0; i < smaller.length; i++) {
		      smaller[i] = cards[numCards];
		      numCards++;
		    }
		    
		    cards = new Card[smaller.length];
		    for (int i = 0; i < cards.length; i++) {
		      cards[i] = smaller[i];
		    }
		    
		    return dealing;
		  }

	  public static void show(Card [] cards)	{
			for(int i=0;i<cards.length;i++)
			{
				System.out.println(cards[i].toString());
			}
		}
	  
}
