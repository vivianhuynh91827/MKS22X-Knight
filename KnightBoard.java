public class KnightBoard {
  private int[][] board;
  private int[][] moves;

  public KnightBoard (int startingRows, int startingCols) {
    if (startingRows <= 0 || startingCols <= 0) {
      throw new IllegalArgumentException();
    }
    board = new int[startingRows][startingCols];
    moves = new int[startingRows][startingCols];
    for (int r = 0; r < startingRows; r++) {
      for (int c = 0; c < startingCols; c++) {
        board[r][c]=0;
      }
    }
    fillMoves();
  }

  private void fillMoves() {
    moves[0][0] = 2; //fill corners with 2
    moves[0][moves[0].length-1] = 2;
    moves[moves.length-1][0] = 2;
    moves[moves.length-1][moves[0].length-1] = 2;
    moves[0][1] = 3; //fill corners with 3
    moves[1][0] = 3;
    moves[0][moves[0].length-2] = 3;
    moves[1][moves[0].length-1] = 3;
    moves[moves.length-1][1] = 3;
    moves[moves.length-2][0] = 3;
    moves[moves.length-1][moves[0].length-2] = 3;
    moves[moves.length-2][moves[0].length-1] = 3;
    for (int i = 0; i < moves.length; i ++) { //fill rows outer rows with 4 and 6
      if (moves[i][0] == 0) moves[i][0] = 4;
      if (moves[i][1] == 0) moves[i][1] = 6;
      if (moves[i][board[0].length - 1] == 0) moves[i][board[0].length-1] = 4;
      if (moves[i][board[0].length - 2] == 0) moves[i][board[0].length-2] = 6;
    }
    for (int i = 0; i < moves[0].length; i ++) {
      if (moves[0][i] == 0) moves[0][i] = 4;
      if (moves[board.length - 1][i] == 0) moves[board[0].length - 1][i] = 4;
      if (moves[1][i] == 0) moves[1][i] = 6;
      if (moves[board.length - 2][i] == 0) moves[board[0].length - 2][i] = 6;
    }
    moves[1][1] = 4;
    moves[1][board[0].length-2] = 4;
    moves[board.length-2][1] = 4;
    moves[board.length-2][board[0].length-2] = 4;
    for (int r = 0; r < board.length; r++) { //fill rest of board with 8
      for (int c = 0; c < board[0].length; c++) {
        if (moves[r][c] == 0) moves[r][c] = 8;
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

  private String toStringMoves() {
    String boardString = "";
    for (int r = 0; r < moves.length; r ++) {
      for (int c = 0; c < moves[0].length; c++) {
        boardString += moves[r][c]+" ";
      }
      boardString += "\n";
    }
    return boardString;
  }

  public boolean solve(int startingRow, int startingCol) {
    if (startingRow < 0 || startingCol < 0) {
      throw new IllegalArgumentException();
    }
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[0].length; c++) {
        if (board[r][c] != 0) {
          throw new IllegalStateException();
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
    int[][] increments = {{1,1,-1,-1,2,2,-2,-2}, {2,-2,2,-2,1,-1,1,-1}};
    for (int i = 0; i < 8; i++) {
      int newR = startingR + increments[0][i];
      int newC = startingC + increments[1][i];
      board[startingR][startingC] = level;
      if (solveH(newR, newC, level+1)) return true;
      board[startingR][startingC] = 0;
    }
    return false;
  }

  public int countSolutions(int startingRow, int startingCol) {
    if (startingRow < 0 || startingCol < 0) {
      throw new IllegalArgumentException();
    }
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[0].length; c++) {
        if (board[r][c] != 0) {
          throw new IllegalStateException();
        }
      }
    }
    return countH(startingRow, startingCol, 1);
  }

  private int countH(int startingR, int startingC, int level) {
    int count = 0;
    int[][] increments = {{1,1,-1,-1,2,2,-2,-2}, {2,-2,2,-2,1,-1,1,-1}};
    if (addKnight(startingR, startingC, level)) {
      if (level == board.length*board[0].length) {
        removeKnight(startingR, startingC);
        return 1;
      }
      else {
        for (int i = 0; i < 8; i++) {
          count += countH(startingR + increments[0][i], startingC + increments[1][i], level+1);
        }
      }
      removeKnight(startingR, startingC);
    }
    return count;
  }

  private boolean addKnight(int row, int col, int level) {
    if (row >= board.length || row < 0) return false;
    if (col >= board[0].length || col < 0) return false;
    if (board[row][col] != 0) return false;
    board[row][col]=level;
    return true;
  }

  private void removeKnight(int row, int col) {
    board[row][col] = 0;
  }


  public static void main(String[] args) {
    // KnightBoard test = new KnightBoard(4,4);
    // System.out.println(test);
     KnightBoard test2 = new KnightBoard(10,10);
     // System.out.println(test2);
     // System.out.println(test2.countSolutions(0,0));
    // System.out.println(test2.solve(0,0));
    // System.out.println(test2);

  }
}
