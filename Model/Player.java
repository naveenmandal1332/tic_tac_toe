package Model;

public class Player {
  public String playerName;
  public PlayingPiece playingPiece;

  Player(String playerName, PlayingPiece playingPiece) {
    this.playerName = playerName;
    this.playingPiece = playingPiece;
  }
}