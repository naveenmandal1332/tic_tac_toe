package Model;

import java.util.ArrayList;
import java.util.List;

public class Board {

  public int size;
  public PlayingPiece[][] board;

  public Board(int size) {
    this.size = size;
    this.board = new PlayingPiece[size][size];
  }

  // Get Free Cell:
  public List<int[]> getFreeCell() {
    List<int[]> freeCell = new ArrayList<>();

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (board[i][j] == null)
          freeCell.add(new int[] { i, j });
      }
    }

    return freeCell;
  }

  // Add Symbol:
  public boolean addPiece(int row, int col, PlayingPiece playingPiece) {
    if (row >= board.length || col >= board.length || board[row][col] != null)
      return false;
    board[row][col] = playingPiece;
    return true;
  }

  // Print board:
  public void printGameBoard() {
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        PlayingPiece currCell = board[i][j];

        if (currCell == null) {
          System.out.print("  ");
        } else {
          System.out.print(currCell.pieceType.name() + " ");
        }

        System.out.print(" | ");
      }

      System.out.println();
    }
  }
}