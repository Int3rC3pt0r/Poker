import java.util.ArrayList;

public class Main {

	private static double pHigh = 50.12;
	private static double pPair = 42.26;
	private static double pTwoPair = 4.75;
	private static double pThree = 2.11;
	private static double pStraight = 0.392;
	private static  double pFlush = 0.197;
	private static double pFullHouse = 0.144;
	private static double pFour = 0.024;
	private static double pStraightFlush = 0.00139;
	private static double pRoyalFlush = 0.000154;
	
	
	private static int color = 4;
	private static int worth = 13;
	private static int size = color * worth;
	private static int handSize = 5;
	private static int repeat = 10000000;
	
	static ArrayList<Integer> cardPack = new ArrayList<Integer>();
	
	public static ArrayList<Integer> rdmCardsOld() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < handSize; i++) {
			int rdm = (int) (Math.random() * (size-1));
			if(list.contains(rdm)) {
				i--;
				System.out.println(rdm + " ist doppelt");
			}
			else {
				list.add(rdm);
			}
		}
		return list;
	}
	
	public static ArrayList<Integer> rdmCards() {
		ArrayList<Integer> hand = new ArrayList<Integer>();
		for(int i=0; i < handSize; i++) {
			int rdm = (int) (Math.random() * (size-i));
			hand.add(cardPack.get(rdm));
			cardPack.add(cardPack.get(rdm));
			cardPack.remove(rdm);
		}
		return hand;
	}
	
	public static void print(ArrayList<Integer> list) {
		for(int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + "|");
		}
		System.out.println();
	}
	public static void printColor(ArrayList<Integer> list) {
		for(int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i)/worth + "|");
		}
		System.out.println();
	}
	public static void printWorth(ArrayList<Integer> list) {
		for(int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i)%worth + "|");
		}
		System.out.println();
	}
	
	public static ArrayList<Integer> insertionSort(ArrayList<Integer> list) {
		for (int i = 1; i < list.size(); i++) {
			int v1 = list.get(i);
			for (int j = 0; j < i; j++) {
				if(v1 < list.get(j)){
					list.remove(i);
					list.add(j,v1);
					j = i;
				}
			}
		}
		return list;
	}
	public static ArrayList<Integer> insertionSortWorth(ArrayList<Integer> list) {
		for (int i = 1; i < list.size(); i++) {
			int v1 = list.get(i);
			for (int j = 0; j < i; j++) {
				if(v1%worth < list.get(j)%worth){
					list.remove(i);
					list.add(j,v1);
					j = i;
				}
			}
		}
		return list;
	}
	
	public static int countSame(ArrayList<Integer> list) {
		int same = 0;
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				if(i != j && list.get(i)%worth == list.get(j)%worth) {
					same++;
				}
			}
		}
		return same;
	}
	
	public static int isStraight(ArrayList<Integer> list) {
		boolean street = true;
		boolean sameColor = true;
		list = insertionSortWorth(list);
		for(int i = 0; i < list.size()-1; i++) {
			if(i == list.size()-2 && list.get(i)%worth == handSize - 1 && list.get(i+1)%worth == worth - 1) {
				street = true;
				break;
			}
			else if(list.get(i)%worth != list.get(i+1)%worth-1) {
				street = false;
				break;
			}
		}
		for(int i = 0; i < list.size()-1; i++) {
			if(list.get(i)/worth != list.get(i+1)/worth) {
				sameColor = false;
			}
		}
		if(street) {
			if(sameColor && list.get(list.size()-1)%worth == worth-1 && list.get(list.size()-2)%worth == worth-2) {
				return 4;
			}
			else if(sameColor){
				return 3;
			}
			else {
				return 2;
			}
		}
		else if(sameColor){
			return 1;
		}
		else {
			return 0;
		}
	}
	
	public static void main(String[] args) {
		
		for(int i=0; i < size; i++) {
			cardPack.add(i);
		}
		
		ArrayList<Integer> hand = new ArrayList<Integer>();
		hand = rdmCards();
		hand = insertionSort(hand);
		
		int highCard = 0;
		int onePair = 0;
		int twoPair = 0;
		int three = 0;
		int four = 0;
		int house = 0;
		int straight = 0;
		int flush = 0;
		int straightFlush = 0;
		int royalFlush = 0;

		for(int i = 0; i < repeat; i++) {
			hand = rdmCards();
			hand = insertionSort(hand);
			int same = countSame(hand);
			int row = isStraight(hand);
			if(countSame(hand) != 0) {
				if(same == 2) {
					onePair++;
				}
				else if(same == 4) {
					twoPair++;
				}
				else if(same == 6) {
					three++;
				}
				else if(same == 8) {
					house++;
				}
				else if(same == 12) {
					four++;
				}
			}
			else if(row != 0) {
				if(row == 1) {
					flush++;
				}
				else if(row == 2) {
					straight++;
				}
				else if(row == 3) {
					straightFlush++;
				}
				else if(row == 4) {
					royalFlush++;
				}
			}
			else {
				highCard++;
			}
		}
//		System.out.println("Pokertrys: " + repeat);
//		System.out.println("High Card:\t" + highCard + "	\t" + ((double) highCard)/repeat * 100 + "%\t" + pHigh + "%\t" + (((double) highCard)/repeat * 100 - pHigh));
//		System.out.println("Pair:\t\t" + onePair + "	\t" + ((double) onePair)/repeat * 100 + "%\t" + pPair + "%\t" + (((double) onePair)/repeat * 100 - pPair));
//		System.out.println("Two Pairs:\t" + twoPair + "	\t" + ((double) twoPair)/repeat * 100 + "%\t" + pTwoPair + "%\t" + (((double) twoPair)/repeat * 100 - pTwoPair));
//		System.out.println("Three:\t\t" + three + "	\t" + ((double) three)/repeat * 100 + "%\t" + pThree + "%\t" + (((double) three)/repeat * 100 - pThree));
//		System.out.println("Straight:\t" + straight + "	\t" + ((double) straight)/repeat * 100 + "%\t" + pStraight + "%\t" + (((double) straight)/repeat * 100 - pStraight));
//		System.out.println("Flush:\t\t" + flush + "	\t" + ((double) flush)/repeat * 100 + "%\t" + pFlush + "%\t" + (((double) flush)/repeat * 100 - pFlush));
//		System.out.println("Full House:\t" + house + "	\t" + ((double) house)/repeat * 100 + "%\t" + pFullHouse + "%\t" + (((double) house)/repeat * 100 - pFullHouse));
//		System.out.println("Four:\t\t" + four + "	\t" + ((double) four)/repeat * 100 + "%\t" + pFour + "%\t" + (((double) four)/repeat * 100 - pFour));
//		System.out.println("Straight Flush:\t" + straightFlush + "	\t" + ((double) straightFlush)/repeat * 100 + "%\t" + pStraightFlush + "%\t" + (((double) straightFlush)/repeat * 100 - pStraightFlush));
//		System.out.println("Royal Flush:\t" + royalFlush + "	\t" + ((double) royalFlush)/repeat * 100 + "%\t" + pRoyalFlush + "%\t" + (((double) royalFlush)/repeat * 100 - pRoyalFlush));
		
		System.out.printf("Pokertrys: %d\n", repeat);
		System.out.println("Kombination\tAnzahl\tHaeufigkeit[%]\tWikiWerte[%]\tAbweichung\t% Abweichung");
		System.out.printf("High Card:\t%d\t%.4f\t\t%.3f\t\t%.6f\t%.4f\n",highCard, ((double) highCard)/repeat * 100, pHigh,(((double) highCard)/repeat * 100) - pHigh,(((double) highCard)/repeat * 100)/(pHigh/100)-100);
		System.out.printf("Pair:\t\t%d\t%.4f\t\t%.3f\t\t%.6f\t%.4f\n",onePair, ((double) onePair)/repeat * 100, pPair,(((double) onePair)/repeat * 100) - pPair,(((double) onePair)/repeat * 100)/(pPair/100)-100);
		System.out.printf("Two Pairs:\t%d\t%.4f\t\t%.4f\t\t%.6f\t%.4f\n",twoPair, ((double) twoPair)/repeat * 100, pTwoPair,(((double) twoPair)/repeat * 100) - pTwoPair,(((double) twoPair)/repeat * 100)/(pTwoPair/100)-100);
		System.out.printf("Three:\t\t%d\t%.4f\t\t%.4f\t\t%.6f\t%.4f\n",three, ((double) three)/repeat * 100, pThree,(((double) three)/repeat * 100) - pThree,(((double) three)/repeat * 100)/(pThree/100)-100);
		System.out.printf("Straight:\t%d\t%.4f\t\t%.4f\t\t%.6f\t%.4f\n",straight, ((double) straight)/repeat * 100, pStraight,(((double) straight)/repeat * 100) - pStraight,(((double) straight)/repeat * 100)/(pStraight/100)-100);
		System.out.printf("Flush:\t\t%d\t%.4f\t\t%.4f\t\t%.6f\t%.4f\n",flush, ((double) flush)/repeat * 100, pFlush,(((double) flush)/repeat * 100) - pFlush,(((double) flush)/repeat * 100)/(pFlush/100)-100);
		System.out.printf("Full House:\t%d\t%.4f\t\t%.4f\t\t%.6f\t%.4f\n",house, ((double) house)/repeat * 100, pFullHouse,(((double) house)/repeat * 100) - pFullHouse,(((double) house)/repeat * 100)/(pFullHouse/100)-100);
		System.out.printf("Four:\t\t%d\t%.4f\t\t%.4f\t\t%.6f\t%.4f\n",four, ((double) four)/repeat * 100, pFour,(((double) four)/repeat * 100) - pFour,(((double) four)/repeat * 100)/(pFour/100)-100);
		System.out.printf("Straight Flush:\t%d\t%.4f\t\t%.5f\t\t%.6f\t%.4f\n",straightFlush, ((double) straightFlush)/repeat * 100, pStraightFlush,(((double) straightFlush)/repeat * 100) - pStraightFlush,(((double) straightFlush)/repeat * 100)/(pStraightFlush/100)-100);
		System.out.printf("Royal Flush:\t%d\t%.4f\t\t%.6f\t%.6f\t%.4f\n",royalFlush, ((double) royalFlush)/repeat * 100, pRoyalFlush,(((double) royalFlush)/repeat * 100) - pRoyalFlush,(((double) royalFlush)/repeat * 100)/(pRoyalFlush/100)-100);
		
	}

}
