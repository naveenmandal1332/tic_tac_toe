package Model;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Game {
  Deque<Player> players;
  Board board;

  // public Game() {
  // initializeGame();
  // }

  // Initialize game:
  public void initializeGame() {

    // Initialize player and bord:
    players = new LinkedList<>();
    board = new Board(3);

    // Handle Player:
    PlayingPieceX cross = new PlayingPieceX();
    Player playerOne = new Player("Naveen", cross);

    PlayingPieceO zero = new PlayingPieceO();
    Player playerTwo = new Player("Vishal", zero);

    players.add(playerOne);
    players.add(playerTwo);

  }

  // Start Game:
  public String startGame() {
    Scanner sc = new Scanner(System.in);

    while (true) {

      // Take out a Player whose turn is:
      Player playerTurn = players.removeFirst();

      // Print Board:
      board.printGameBoard();

      // check free cell;
      List<int[]> freeCell = board.getFreeCell();
      if (freeCell.size() == 0)
        break;

      // Take Input From the Player:
      System.out.print("Player: " + playerTurn.playerName + " Enter row,col: ");
      String playerInput = sc.nextLine();
      String value[] = playerInput.split(",");

      int row = Integer.valueOf(value[0]);
      int col = Integer.valueOf(value[1]);

      // Add Piece on the board:
      boolean isPiecePlaced = board.addPiece(row, col, playerTurn.playingPiece);
      if (!isPiecePlaced) {
        System.out.println("You have choosen incorrect position: Please try again!");
        players.addFirst(playerTurn);
        continue;
      }

      players.addLast(playerTurn);

      // check winner:
      boolean isWinner = checkWinner(row, col, playerTurn.playingPiece.pieceType);
      System.out.println("Winner check -> " + isWinner);
      if (isWinner) {
        sc.close();
        board.printGameBoard();
        return playerTurn.playerName;
      }
    }

    sc.close();
    board.printGameBoard();
    return "Game-Tie";
  }

  // check Winner:
  public boolean checkWinner(int row, int col, PieceType pieceType) {
    boolean rowMatch = true;
    boolean colMatch = true;
    boolean diagonalMatchOne = true;
    boolean diagonalMatchTwo = true;

    // Game Board:
    PlayingPiece gameBoard[][] = board.board;

    // Check Row:
    for (int i = 0; i < gameBoard.length; i++) {
      if (gameBoard[row][i] == null || gameBoard[row][i].pieceType != pieceType)
        rowMatch = false;
    }

    // Check Col:
    for (int i = 0; i < gameBoard.length; i++) {
      if (gameBoard[i][col] == null || gameBoard[i][col].pieceType != pieceType)
        colMatch = false;
    }

    // Diagonal Match Top Left-> Bottom Right:
    int i = 0, j = 0;
    while (i < gameBoard.length && j < gameBoard.length) {
      if (gameBoard[i][j] == null || gameBoard[i][j].pieceType != pieceType)
        diagonalMatchOne = false;
      i++;
      j++;
    }

    // Diagonal Match Bottom Right -> Top Left:
    i = gameBoard.length - 1;
    j = gameBoard.length - 1;
    while (i >= 0 && j >= 0) {
      if (gameBoard[i][j] == null || gameBoard[i][j].pieceType != pieceType)
        diagonalMatchTwo = false;

      i--;
      j--;
    }

    return (rowMatch || colMatch || diagonalMatchOne || diagonalMatchTwo);
  }
}
