
public class Loader {

	public static void main(String[] args) {
		SeaBoard board = new SeaBoard();
		board.shoot(0, 0, "m");
		board.shoot(2, 0, "h");
		board.shoot(6, 9, "h");
		board.shoot(2, 1, "d");
		
//		board.shoot(4, 5, "h");
//		board.shoot(6, 5, "h");
//		board.shoot(5, 4, "h");
//		board.shoot(5, 6, "h");
//		board.shoot(5, 5, "d");
//		board.shoot(0, 9, "d");
//		board.shoot(9, 9, "d");
//		board.shoot(9, 0, "d");
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(board.getField()[i][j]);
			}
			System.out.println("");
		}
	}

}
