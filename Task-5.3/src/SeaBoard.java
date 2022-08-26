
public class SeaBoard {
	private String[][] field = new String[10][10];
	
	public SeaBoard() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				field[i][j] = ".";
			}
		}
	}
	
	public String[][] getField() {
		return field;
	}
	
	public void shoot(int line, int column, String issue) {
		if (issue.equals("m")) {
			field[line][column] = "#";
		} else {
			field[line][column] = "x";
			if (issue.equals("d")) {
				markEmptyCells(line, column);
			}
		}
	}
	
	public String check(int line, int column) {
		return field[line][column];
	}
	
	private void markEmptyCells(int line, int column) {
		int i;
		
		i = line - 1;
		while (i >= 0 && field[i][column].equals("x")) {
			if (column > 0 && field[i][column - 1].equals(".")) {
				field[i][column - 1] = "#";
			}
			if (column < 9 && field[i][column + 1].equals(".")) {
				field[i][column + 1] = "#";
			}
			i--;
		}
		if (i >= 0) {
			if (field[i][column].equals(".")) {
				field[i][column] = "#";
			}
			if (column > 0 && field[i][column - 1].equals(".")) {
				field[i][column - 1] = "#";
			}
			if (column < 9 && field[i][column + 1].equals(".")) {
				field[i][column + 1] = "#";
			}
		}
		
		i = line + 1;
		while (i < 10 && field[i][column].equals("x")) {
			if (column > 0 && field[i][column - 1].equals(".")) {
				field[i][column - 1] = "#";
			}
			if (column < 9 && field[i][column + 1].equals(".")) {
				field[i][column + 1] = "#";
			}
			i++;
		}
		if (i < 10) {
			if (field[i][column].equals(".")) {
				field[i][column] = "#";
			}
			if (column > 0 && field[i][column - 1].equals(".")) {
				field[i][column - 1] = "#";
			}
			if (column < 9 && field[i][column + 1].equals(".")) {
				field[i][column + 1] = "#";
			}		
		}
		
		i = column - 1;
		while (i >= 0 && field[line][i].equals("x")) {
			if (line > 0 && field[line - 1][i].equals(".")) {
				field[line - 1][i] = "#";
			}
			if (line < 9 && field[line + 1][i].equals(".")) {
				field[line + 1][i] = "#";
			}
			i--;
		}
		if (i >= 0) {
			if (field[line][i].equals(".")) {
				field[line][i] = "#";
			}
			if (line > 0 && field[line - 1][i].equals(".")) {
				field[line - 1][i] = "#";
			}
			if (line < 9 && field[line + 1][i].equals(".")) {
				field[line + 1][i] = "#";
			}
		}
		
		i = column + 1;
		while (i < 10 && field[line][i].equals("x")) {
			if (line > 0 && field[line - 1][i].equals(".")) {
				field[line - 1][i] = "#";
			}
			if (line < 9 && field[line + 1][i].equals(".")) {
				field[line + 1][i] = "#";
			}
			i++;
		}
		if (i < 10) { 
			if (field[line][i].equals(".")) {
				field[line][i] = "#";
			}
			if (line > 0 && field[line - 1][i].equals(".")) {
				field[line - 1][i] = "#";
			}
			if (line < 9 && field[line + 1][i].equals(".")) {
				field[line + 1][i] = "#";
			}
		}
	}
}
	
