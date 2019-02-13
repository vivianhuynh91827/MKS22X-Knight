public class KnightBoard {
  private int[][] board;

  public KnightBoard (int startingRows, int startingCols) {
    if (startingRows < 0 || startingCols < 0) {
      throw new IllegalArgumentException("Dimensions cannot be negative");
    }
    board = new int[row][col];
    for (int r = 0; r < startingRows; r++) {
      for (int c = 0; c < startingCols; c++) {
        board[r][c]=0;
      }
    }
  }
}
