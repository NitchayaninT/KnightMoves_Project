import java.util.*;

public class main {

    public static void main(String args[])
    {

        Knight k = new Knight();
        Board B = new Board(5);
        B.printBoard();
        int size = B.getSize();
        k.setKnightPosition(1,size);
        k.initialKnightMoves();
    }
}
