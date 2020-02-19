import java.util.ArrayList;

public class Main {

	private static int color = 4;
	private static int worth = 13;
	private static int size = color * worth;
	private static int handSize = 5;
	
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
	
	public static int isStreet(ArrayList<Integer> list) {
		boolean street = true;
		boolean sameColor = true;
		for(int i = 0; i < list.size()-1; i++) {
			if(list.get(i)%worth != list.get(i+1)%worth-1) {
				street = false;
			}
		}
		if(street) {
			for(int i = 0; i < list.size()-1; i++) {
				if(list.get(i)/worth != list.get(i+1)/worth) {
					sameColor = false;
				}
			}
			if(sameColor && list.get(list.size()-1)%13 == 12) {
				return 3;
			}
			else if(sameColor){
				return 2;
			}
			else {
				return 1;
			}
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
		while(isStreet(hand) < 1) {
			hand = rdmCards();
			hand = insertionSort(hand);
		}
		print(hand);
		printColor(hand);
		printWorth(hand);
		System.out.println(isStreet(hand));
		
		
	}

}
