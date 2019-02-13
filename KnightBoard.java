public class KnightBoard {
  private int[][] board;

  public KnightBoard (int startingRows, int startingCols) {
    if (startingRows <= 0 || startingCols <= 0) {
      throw new IllegalArgumentException("Dimensions cannot be negative");
    }
    board = new int[startingRows][startingCols];
    for (int r = 0; r < startingRows; r++) {
      for (int c = 0; c < startingCols; c++) {
        board[r][c]=0;
      }
    }
  }

  public String toString() {
    String boardString = "";
    boolean doubDig = board.length * board[0].length > 9;
    for (int r = 0; r < board.length; r ++) {
      for (int c = 0; c < board[0].length; c ++) {
        if (board[r][c]==0) {
          if (doubDig) boardString += " _ ";
          else boardString += "_ ";
        }
        else {
          if (doubDig) {
            if (board[r][c]<10) boardString += " " + board[r][c] + " ";
            else boardString+= board[r][c] + " ";
          }
          else {
            boardString+=board[r][c]+" ";
          }
        }
      }
      boardString += "\n";
    }
    return boardString.substring(0,boardString.length()-1);
  }
  
  public static void main(String[] args) {
    KnightBoard test = new KnightBoard(2,2);
    System.out.println(test);
    KnightBoard test2 = new KnightBoard(4,6);
    System.out.println(test2);
  }
}
