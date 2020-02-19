import java.util.ArrayList;

public class Main {

	private static int color = 4;
	private static int worth = 13;
	private static int size = color * worth;
	private static int handSize = 5;
	private static int repeat = 1000000;
	
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
			int rdm = (int) (Math.random() * (size-1-i));
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
			if(list.get(i)%worth != list.get(i+1)%worth-1) {
				street = false;
			}
		}
		for(int i = 0; i < list.size()-1; i++) {
			if(list.get(i)/worth != list.get(i+1)/worth) {
				sameColor = false;
			}
		}
		if(street) {
			if(sameColor && list.get(list.size()-1)%13 == 12) {
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
		System.out.println("High Card:\t" + highCard);
		System.out.println("Pair:\t\t" + onePair);
		System.out.println("Two Pairs:\t" + twoPair);
		System.out.println("Three:\t\t" + three);
		System.out.println("Straight:\t" + straight);
		System.out.println("Flush:\t\t" + flush);
		System.out.println("Full House:\t" + house);
		System.out.println("Four:\t\t" + four);
		System.out.println("Straight Flush:\t" + straightFlush);
		System.out.println("Royal Flush:\t" + royalFlush);
		
	}

}
