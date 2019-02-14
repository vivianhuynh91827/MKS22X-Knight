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

  public boolean solve(int startingRow, int startingCol) {
    if (startingRow < 0 || startingCol < 0) {
      throw new IllegalArgumentException("startingRow and startingCol cannot be negative");
    }
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[0].length; c++) {
        if (board[r][c] != 0) {
          throw new IllegalStateException("Board must be empty in order to solve");
        }
      }
    }
    return solveH(startingRow, startingCol, 1);
  }

  private boolean solveH(int startingR, int startingC, int level) {
    if (level == board.length*board[0].length+1) return true;
    //if the level has reached the size of the board, return ture
    if (startingR < 0 || startingR > board.length-1) return false;
    //if the starting row is out of range return false;
    if (startingC < 0 || startingC > board[0].length-1) return false;
    //if the starting col is out of range return false;
    if (board[startingR][startingC] != 0) return false;
    //if the current space is not empty return false; 
    board[startingR][startingC]=level;
    int[] increment1 = {2,-2,1,-1};
    int[] increment2 = {1,-1,2,-2};
    for (int i = 0; i < 4; i ++) {
      int newR = startingR+increment1[i];
      int newC = startingC+increment2[i];
      if (solveH(newR, newC, level+1)) return true;
      // else if (newR > -1 && newC > -1 && newR < board.length && newC < board[0].length) {
      //   board[newR][newC] = 0;
      // }
    }
    for (int i = 0; i < 4; i ++) {
      int newR = startingR+increment2[i];
      int newC = startingC+increment1[i];
      if (solveH(newR, newC, level+1)) return true;
      // else if (newR > -1 && newC > -1 && newR < board.length && newC < board[0].length) {
      //   board[newR][newC] = 0;
      // }
    }
    return false;
    // return solveH(startingR+2, startingC+1,level+1) ||
    //        solveH(startingR+2, startingC-1,level+1) ||
    //        solveH(startingR-2, startingC+1,level+1) ||
    //        solveH(startingR-2, startingC-1,level+1) ||
    //        solveH(startingR+1, startingC+2,level+1) ||
    //        solveH(startingR+1, startingC-2,level+1) ||
    //        solveH(startingR-1, startingC+2,level+1) ||
    //        solveH(startingR-1, startingC-2,level+1);
  }

  public static void main(String[] args) {
    KnightBoard test = new KnightBoard(4,4);
    System.out.println(test);
    KnightBoard test2 = new KnightBoard(5,6);
    System.out.println(test2);
    System.out.println(test2.solve(0,0));
    System.out.println(test2);
  }
}
