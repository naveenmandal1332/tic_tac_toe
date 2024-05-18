import Model.Game;

public class Main {

  public static void main(String[] args) {
    System.out.println("Let's Play Tic-Tac-Toe");
    Game ticTacToe = new Game();
    ticTacToe.initializeGame();

    System.out.println("game winner is: " + ticTacToe.startGame());
  }
}