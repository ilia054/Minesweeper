package mines;

import java.util.ArrayList;
import java.util.Random;

public class Mines {
	

	private Place[][] board;// a matrix of "Places" that is absently our game board
	private int column, rows;// Will hold our mXn matrix size
	private Boolean showAll = false;// default;

	public class Place {// Our game board will be compiled from Places.
		private int x, y;
		ArrayList<Place> neighbours = new ArrayList<Place>();// List of the Place neigbour's
		private Boolean opened = false;// if flag is false it is unopened "Place" on the board,if 1 is opened;
		private Boolean mine = false;// Indicate's what is on the Board in the [i][j]
		private Boolean flag = false;// Indicates if a flag has been placed
		private int minecnt = 0;// How many neighbour's of the "Place" are bomb's
		
		public Place(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public Boolean isMine()
		{
			return mine;
		}

		public boolean setMine() {// Set a Mine at this Place
			if (mine)
				return false;
			mine = true;
			return true;
		}

		public void setOpen()// Has been opened;
		{
			opened = true;
		}

		void mineAdder() {// adding 1 to our bomb count
			minecnt++;
		}

		void setFlag() {
			if (flag)
				flag = false;
			else
				flag = true;

		}

		boolean getFlag() {
			return flag;
		}

	}

	@Override
	public String toString() {// Custom toString
		StringBuilder tmp = new StringBuilder();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < column; j++)
				tmp.append(get(i, j));
			tmp.append("\n");
		}
		return tmp.toString();

	}

	public Mines(int height, int width, int numMines) {
//		mine=true
		int x, y;// used to add mine's at same RNG location
		int mines = numMines;
		column = width;
		rows = height;
		Random dice = new Random();// new RNG for mines
		board = new Place[height][width];// Generate board by [Width X Height] size
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				board[i][j] = new Place(i, j);
		while (mines > 0) {// we will add numMines
			if (board[x = dice.nextInt(height)][y = dice.nextInt(width)].setMine()) {
				mineAdder(x, y);// activate mine at board[i][j]
				mines--;
			}
		}
		for (int i = 0; i < height; i++)// Create an Array list to each Place with it's neigbour's
			for (int j = 0; j < width; j++)
				neigboursAdder(i, j);

	}

	public Place getPlace(int i, int j) {// return the Place[i][j]
		return board[i][j];
	}

	public boolean addMine(int i, int j) {// add mine at place [i][j]
		mineAdder(i, j);
		return board[i][j].setMine();

	}

	public boolean open(int i, int j) {// Opens the game board by the set rules
		if (board[i][j].opened)// if we already opened it we don't need to do it again
			return true;
		if (board[i][j].mine || board[i][j].flag)// its a mine
			return false;
		board[i][j].setOpen();// set Place[i][j] to be "opened"
		if (board[i][j].minecnt == 0) {
			for (Place e : board[i][j].neighbours) {// Iterate over our neigbour's neigbour's etc
				open(e.x, e.y);// open them
			}

		}
		return true;// Not a bomb and opened the neigbour's
	}

	public void toggleFlag(int x, int y) {// set a flag to true
		board[x][y].setFlag();
	}

	private void mineAdder(int i, int j)// this functions adds 1 bomb to all the bombs neighbour's
	{// We will go over every bombs neigbour's and add 1 to their bomb count.
		if (i - 1 >= 0 && j - 1 >= 0)

			board[i - 1][j - 1].mineAdder();

		if (j - 1 >= 0)

			board[i][j - 1].mineAdder();

		if (i + 1 < rows && j - 1 >= 0)

			board[i + 1][j - 1].mineAdder();

		if (i - 1 >= 0)

			board[i - 1][j].mineAdder();

		if (i + 1 < rows)

			board[i + 1][j].mineAdder();

		if (j + 1 < column)

			board[i][j + 1].mineAdder();

		if (i + 1 < rows && j + 1 < column)

			board[i + 1][j + 1].mineAdder();

		if (i - 1 >= 0 && j + 1 < column)

			board[i - 1][j + 1].mineAdder();

	}

	private void neigboursAdder(int i, int j)// Creates the neigbour's of the board
	{
		if (i - 1 >= 0 && j - 1 >= 0)

			board[i - 1][j - 1].neighbours.add(board[i][j]);

		if (j - 1 >= 0)

			board[i][j - 1].neighbours.add(board[i][j]);

		if (i + 1 < rows && j - 1 >= 0)

			board[i + 1][j - 1].neighbours.add(board[i][j]);

		if (i - 1 >= 0)

			board[i - 1][j].neighbours.add(board[i][j]);

		if (i + 1 < rows)

			board[i + 1][j].neighbours.add(board[i][j]);

		if (j + 1 < column)

			board[i][j + 1].neighbours.add(board[i][j]);

		if (i + 1 < rows && j + 1 < column)

			board[i + 1][j + 1].neighbours.add(board[i][j]);

		if (i - 1 >= 0 && j + 1 < column)

			board[i - 1][j + 1].neighbours.add(board[i][j]);

	}

	public boolean isDone() // Looking if we are "Done" (Winner Winner chicken dinner)
	{
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < column; j++)
				if (board[i][j].opened==false && !board[i][j].mine)
					return false;
		return true;
	}

	public String get(int i, int j) {// Will depend on opened status and showAll status
		Place tmp = board[i][j];// cleaner code

		if (!tmp.opened && tmp.flag && !showAll)
			return "F";
		if (!tmp.opened && !tmp.flag && !showAll)
			return ".";
		if (tmp.opened && tmp.mine || showAll && tmp.mine)
			return "X";
		if (tmp.minecnt == 0)
			return " ";
		return "" + tmp.minecnt;

	}

	public void setShowAll(boolean showAll) {// set showAll value
		this.showAll = showAll;

	}
	
}
