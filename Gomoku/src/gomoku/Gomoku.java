package gomoku;

import java.util.Scanner;

public class Gomoku {
	
	public static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		
		char[][] board = new char[19][19];

		System.out.println("Are you ready for some Gomoku?!?!");
		System.out.println();

		fillBoard(board);

		displayBoard(board);

		System.out.println();

		int turn = 1;
		int newrow = 0;
		int newcolumn = 0;

		//game runs in a loop
		do
		{
			int player = playerTurn(turn);

			//loop to validate move selections as valid
			do
			{
				newrow = (isValidRow(board, newrow));

				newcolumn = (isValidColumn(board, newrow, newcolumn));
			}
			while (isValidMove(board, newrow, newcolumn) == false);

			placeStone(board, player, newrow, newcolumn);

			System.out.println();

			if (isBoardFull(board) == true)
			{
				System.out.println("Unfortunately the board is full and there was no winner. Draw!");
			}
			else if (hasPlayerWon(board) == true)
			{
				System.out.println("Congratulations! Player" + player + ", you have won the game!!!");
			}
			else
			{
				turn++;
			}
		}
		while ((isBoardFull(board) == false) && (hasPlayerWon(board)) == false);

		input.close();
		System.out.println("Game Over, Man! Game Over!");
	}

	//create the board matrix
	public static void fillBoard(char[][] board)
	{
		for(int row = 0; row < board.length; row++)
			for(int column = 0; column < board[row].length; column++)
					board[row][column] = '.';
	}

	//print out the board matrix
	public static void displayBoard(char[][] board)
	{
		for(int row = 0; row < board.length; row++)
		{
			for(int column = 0; column < board[row].length; column++)
				System.out.print(" " + board[row][column]);
			System.out.println();
		}
	}

	//alternate, notify and return player turn
		public static int playerTurn(int turn)
		{
			int player = 0;

			if(turn % 2 != 0)
			{
				player = 1;
			}
			else
			{
				player = 2;
			}
			System.out.println("Player " + player + "'s turn!");
			return player;
		}

	//validate intended row is within boundaries of array/board
	public static int isValidRow(char[][] board, int newrow)
	{

		boolean rowtest = true;

		//get row placement from player
		System.out.print("Enter row: ");
		newrow = input.nextInt();
		
		//check if intended row is valid
		do
		{
		
			if (newrow > -1 && newrow < board.length)
			{
				rowtest = true;
			}
			else
			{
				rowtest = false;
				System.out.print("Enter a valid row (0 - " + (board.length - 1) + "): ");
				newrow = input.nextInt();
			}
		}
		while (rowtest == false);

		return newrow;
	}

	//validate intended column is within boundaries of array/board
	public static int isValidColumn(char[][] board, int newrow, int newcolumn)
	{
		boolean columntest = true;

		//get column placement from player
		System.out.print("Enter column: ");
		newcolumn = input.nextInt();

		//check if intended column is valid
		do
		{
			if (newcolumn > -1 && newcolumn < board.length)
			{
				columntest = true;
				
			}
			else
			{
				columntest = false;
				System.out.print("Enter a valid column (0 - " + (board.length - 1) + "): ");
				newcolumn = input.nextInt();
			}
		}
		while(columntest == false);

		return newcolumn;
	}


	//validate intended row & column is a valid placement
	public static boolean isValidMove(char[][] board, int newrow, int newcolumn)
	{

		boolean availabletest = true;

		//test for intended placement availability
		if (board[newrow][newcolumn] != '@' && board[newrow][newcolumn] != '#')
		{
			availabletest = true;
		}
		else
		{
			System.out.println();
			System.out.println("Sorry, can't play there. It's already occupied. Please choose again.");
			availabletest = false;
		}
		
		return availabletest;
	}

	//per player turn, assign & place different icon to each player
	public static void placeStone(char[][] board, int player, int newrow, int newcolumn)
	{
		if(player == 1)
		{
			//player 1 icon [@]
			board[newrow][newcolumn] = 64;
		}
		else
		{
			//player 2 icon [#]
			board[newrow][newcolumn] = 35;
		}

		displayBoard(board);
	}

	//determine if the board has any remaining available spots to move
	public static boolean isBoardFull(char[][] board)
	{
		boolean full = false;
		int emptyspace = (19 * 19);

		//use for loop to run through the 2d array reducing total number of spaces by those in use
		for(int countR = 0; countR < board.length; countR++)
			for(int countC = 0; countC < board.length; countC++)
			{
				if (board[countR][countC] == 64 || board[countR][countC] == 35)
				{
					emptyspace--;
				}
			}

		if (emptyspace == 0)
		{
			full = true;
		}
		else
		{
			full = false;
		}
		return full;
	}

	//determine if either player has won the game
	public static boolean hasPlayerWon(char[][] board)
	{
		boolean win = false;

		if (isHorizontalWin(board) == true)
		{
			win = true;
		}
		else if (isVerticalWin(board) == true)
		{
			win = true;
		}
		else if (isDiagonalWin(board) == true)
		{
			win = true;
		}
		else
		{
			win = false;
		}

		return win;
	}

	//determine if either player has won with a horizontal win
	public static boolean isHorizontalWin(char[][] board)
	{
		boolean horizontal = false;
		int totalH = 0;

		for(int countR = 0; countR < board.length; countR++)
			for(int countC = 0; countC < board.length - 4; countC++)
			{
				if (board[countR][countC] == 64 &&
					board[countR][countC + 1] == 64 &&
					board[countR][countC + 2] == 64 &&
					board[countR][countC + 3] == 64 &&
					board[countR][countC + 4] == 64)
				{
					totalH++;
				}
				else if (board[countR][countC] == 35 &&
					board[countR][countC + 1] == 35 &&
					board[countR][countC + 2] == 35 &&
					board[countR][countC + 3] == 35 &&
					board[countR][countC + 4] == 35)
				{
					totalH++;
				}
				else
				{
					horizontal = false;
				}
			}

		if (totalH > 0)
		{
			horizontal = true;
		}
		else
		{
			horizontal = false;
		}

		return horizontal;
	}

	//determine if either player has won with a horizontal win
	public static boolean isVerticalWin(char[][] board)
	{
		boolean vertical = false;
		int totalV = 0;

		for(int countR = 0; countR < board.length - 4; countR++)
			for(int countC = 0; countC < board.length; countC++)
			{
				if (board[countR][countC] == 64 &&
					board[countR + 1][countC] == 64 &&
					board[countR + 2][countC] == 64 &&
					board[countR + 3][countC] == 64 &&
					board[countR + 4][countC] == 64)
				{
					totalV++;
				}
				else if (board[countR][countC] == 35 &&
					board[countR + 1][countC] == 35 &&
					board[countR + 2][countC] == 35 &&
					board[countR + 3][countC] == 35 &&
					board[countR + 4][countC] == 35)
				{
					totalV++;
				}
				else
				{
					vertical = false;
				}
			}
		if (totalV > 0)
		{
			vertical = true;
		}
		else
		{
			vertical = false;
		}
		return vertical;
	}

	//determine if either player has won with a diagonal win
	public static boolean isDiagonalWin(char[][] board)
	{
		boolean diagonal = false;
		int totalD = 0;

		for(int countR = 0; countR < board.length - 4; countR++)
			for(int countC = 0; countC < board.length - 4; countC++)
			{
				if (board[countR][countC] == 64 &&
					board[countR + 1][countC + 1] == 64 &&
					board[countR + 2][countC + 2] == 64 &&
					board[countR + 3][countC + 3] == 64 &&
					board[countR + 4][countC + 4] == 64)
				{
					totalD++;
				}
				else if (board[countR][countC] == 35 &&
					board[countR + 1][countC + 1] == 35 &&
					board[countR + 2][countC + 2] == 35 &&
					board[countR + 3][countC + 3] == 35 &&
					board[countR + 4][countC + 4] == 35)
				{
					totalD++;
				}
				else
				{
					diagonal = false;
				}
			}

		for(int countR = 0; countR < board.length - 4; countR++)
			for(int countC = 4; countC < board.length; countC++)
				{
					if (board[countR][countC] == 64 &&
						board[countR + 1][countC - 1] == 64 &&
						board[countR + 2][countC - 2] == 64 &&
						board[countR + 3][countC - 3] == 64 &&
						board[countR + 4][countC - 4] == 64)
					{
						totalD++;
					}
					else if (board[countR][countC] == 35 &&
						board[countR + 1][countC - 1] == 35 &&
						board[countR + 2][countC - 2] == 35 &&
						board[countR + 3][countC - 3] == 35 &&
						board[countR + 4][countC - 4] == 35)
					{
						totalD++;
					}
					else
					{
						diagonal = false;
					}
				}
		if (totalD > 0)
		{
			diagonal = true;
		}
		else
		{
			diagonal = false;
		}
		return diagonal;
	}

}
