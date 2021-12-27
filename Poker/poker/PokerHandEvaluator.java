package poker;

import java.util.Arrays;

public class PokerHandEvaluator {

	public static boolean hasPair(Card[] cards) {
		for (int i = 0; i < cards.length - 1; i++) {
			for (int comparer = i + 1; comparer < cards.length; comparer++) {
				if (cards[comparer].getValue() == cards[i].getValue()) {
					return true;
				}
			}
		}
		return false;
	}
	public static boolean hasTwoPair(Card[] cards) {
		int[] cardsNum = new int[cards.length];
		for (int i = 0; i < cards.length; i++) {
			cardsNum[i] = cards[i].getValue();
		}
		int flag;
		int[] matching = new int[cards.length];
		int i, comparer;
		for (i = 0; i < cards.length; i++) {
			flag = 0;
			for (comparer = 0; comparer < cards.length; comparer++) {
				if (i != comparer && cardsNum[i] == cardsNum[comparer])
					flag++;
			}
			matching[i] = flag;

		}
		int counter = 0;
		for (int k = 0; k < cards.length; k++) {
			if (matching[k] == 1) {
				counter++;
			}
		}

		boolean bounds = false;

		Arrays.sort(matching);
		String test = Arrays.toString(matching);

		if (test.contains("4") == true) {
			bounds = false;
		} else if (test.contains("3") == true) {
			bounds = false;
		} else if (test.contains("1") == true && counter > 2) {
			bounds = true;
		} else if (test.contains("2") == true && test.contains("1") == true) {
			bounds = true;
		}
		return bounds;

	}
	

	public static boolean hasThreeOfAKind(Card[] cards) {
		for (int i = 0; i < cards.length - 1; i++) {
			for (int comparer1 = i + 1; comparer1 < cards.length; comparer1++) {
				for (int comparer2 = comparer1 + 1; comparer2 < cards.length; comparer2++) {
					if (cards[comparer1].getValue() == cards[i].getValue()
							&& cards[comparer2].getValue() == cards[comparer1].getValue()) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public static boolean hasStraight(Card[] cards) {
		int[] temp = new int[cards.length];
		for (int i = 0; i < cards.length; i++) {
			temp[i] = cards[i].getValue();
		}
		Arrays.sort(temp);
		if (temp[0] == 1 && temp[1] == 10 && temp[2] == 11 && temp[3] == 12 && temp[4] == 13) {
			return true;
		} else {
			int temp1 = temp[0];
			temp1++;
			for (int i = 1; i < temp.length; i++) {
				if (temp[i] != temp1) {
					return false;
				}
				temp1++;
			}
			return true;

		}
	}


	public static boolean hasFlush(Card[] cards) {

		int cardSuit = cards[0].getSuit();
		for (int i = 1; i < cards.length; i++) {
			if (cardSuit != cards[i].getSuit()) {
				return false;
			}
		}

		return true;
	}

	public static boolean hasFullHouse(Card[] cards) {
		int value = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < cards.length; j++)

				if (i != j && cards[i].getValue() == cards[j].getValue()) {
					value++;
				}

		}
		if (value == 8) {
			return true;
		}
		return false;
	}

	public static boolean hasFourOfAKind(Card[] cards) {
		for (int i = 0; i < cards.length - 1; i++) {
			for (int comparer1 = i + 1; comparer1 < cards.length; comparer1++) {
				for (int comparer2 = comparer1 + 1; comparer2 < cards.length; comparer2++) {
					for (int comparer3 = comparer2 + 1; comparer3 < cards.length; comparer3++) {
						if (cards[comparer1].getValue() == cards[i].getValue()
								&& cards[comparer2].getValue() == cards[comparer1].getValue()
								&& cards[comparer1].getValue() == cards[comparer3].getValue()) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	public static boolean hasStraightFlush(Card[] cards) {
		if (hasStraight(cards) && hasFlush(cards)) {
			return true;
		}

		return false;
	}
}
