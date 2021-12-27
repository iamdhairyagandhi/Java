package poker;

import static org.junit.Assert.*;

import org.junit.Test;

public class StudentTests {

	/* Use the @Test annotation for JUnit 4 
	 * [This is just an example, please erase
	 * it and write some real tests.]    */
	
	@Test
	public void testOverall()
	{
		Card [] cards= {new Card(2,0),new Card(2,0),new Card(2,0),new Card(12,0),new Card(12,0)};
		assertTrue(PokerHandEvaluator.hasTwoPair(cards)==true);
		assertTrue(PokerHandEvaluator.hasPair(cards)==true);
	//	assertTrue(PokerHandEvaluator.hasThreeOfAKind(cards)==true);
		assertTrue(PokerHandEvaluator.hasStraight(cards)==false);
	//	assertTrue(PokerHandEvaluator.hasFlush(cards)==true);
		assertTrue(PokerHandEvaluator.hasFullHouse(cards)==true);
		assertTrue(PokerHandEvaluator.hasFourOfAKind(cards)==false);
		assertTrue(PokerHandEvaluator.hasStraightFlush(cards)==false);
		
	}
	
	@Test
	public void has4OfAKind() {
		Card [] cards= {new Card(1,0),new Card(1,0),new Card(1,0),new Card(11,0),new Card(1,0)};
		assertTrue(PokerHandEvaluator.hasFourOfAKind(cards)==true);
	}
	@Test
	public void hasStraight1() {
		Card [] cards= {new Card(1,0),new Card(10,0),new Card(11,0),new Card(12,0),new Card(13,0)};
		assertTrue(PokerHandEvaluator.hasStraight(cards)==true);
	}
	@Test
	public void hasStraight2() {
		Card [] cards= {new Card(1,0),new Card(4,0),new Card(3,0),new Card(2,0),new Card(5,0)};
		assertTrue(PokerHandEvaluator.hasStraight(cards)==true);
	}
	@Test
	public void hasPair() {
		Card [] cards= {new Card(1,0),new Card(1,0),new Card(1,0),new Card(11,0),new Card(11,0)};
		assertTrue(PokerHandEvaluator.hasPair(cards)==true);
	}
	@Test
	public void hasTwoPair1() {
		Card [] cards= {new Card(4,0),new Card(4,0),new Card(5,0),new Card(11,0),new Card(11,0)};
		assertTrue(PokerHandEvaluator.hasTwoPair(cards)==true);
	}
	
	@Test
	public void hasTwoPair2() {
		Card [] cards= {new Card(4,0),new Card(4,0),new Card(11,0),new Card(11,0),new Card(11,0)};
		assertTrue(PokerHandEvaluator.hasTwoPair(cards)==true);
	}
	
	@Test
	public void hasTwoPair3() {
		Card [] cards= {new Card(4,0),new Card(11,0),new Card(4,0),new Card(11,0),new Card(5,0)};
		assertTrue(PokerHandEvaluator.hasTwoPair(cards)==true);
	}
	@Test
	public void hasTwoPair4() {
		Card [] cards= {new Card(4,0),new Card(4,0),new Card(4,0),new Card(4,0),new Card(4,0)};
		assertTrue(PokerHandEvaluator.hasTwoPair(cards)==false);
	}
	@Test
	public void hasTwoPair5() {
		Card [] cards= {new Card(4,0),new Card(4,0),new Card(4,0),new Card(5,0),new Card(4,0)};
		assertTrue(PokerHandEvaluator.hasTwoPair(cards)==false);
	}
	@Test
	public void hasFullHouse1() {
		Card [] cards= {new Card(4,0),new Card(4,0),new Card(11,0),new Card(11,0),new Card(11,0)};
		assertTrue(PokerHandEvaluator.hasFullHouse(cards)==true);
	}
	@Test
	public void hasFullHouse2() {
		Card [] cards= {new Card(4,0),new Card(11,0),new Card(4,0),new Card(11,0),new Card(11,0)};
		assertTrue(PokerHandEvaluator.hasFullHouse(cards)==true);
	}
	@Test
	public void hasFullHouse4() {
		Card [] cards= {new Card(4,0),new Card(5,0),new Card(4,0),new Card(11,0),new Card(11,0)};
		assertTrue(PokerHandEvaluator.hasFullHouse(cards)==false);
	}
	
}
