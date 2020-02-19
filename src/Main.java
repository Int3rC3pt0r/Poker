import java.util.ArrayList;

public class Main {

	static int color = 4;
	static int worth = 13;
	static int size = color * worth;
	static int handSize = 5;
	
	public static ArrayList<Integer> rdmCards() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < handSize; i++) {
			int rdm = (int) (Math.random() * size);
			if(list.contains(rdm)) {
				i--;
			}
			else {
				list.add(rdm);
			}
		}
		return list;
	}
	
	public static void ausgabe(ArrayList<Integer> list) {
		for(int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + "|");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> hand = new ArrayList<Integer>();
		hand = rdmCards();
		ausgabe(hand);
		
	}

}
