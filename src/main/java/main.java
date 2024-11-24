import java.util.*;

public class main {

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter N for N*N board (N must be at least 5)");
        int size = sc.nextInt();
        //create knight
        Knight k = new Knight();
        //create board
        Board B = new Board(size);
        B.printBoard();

        //Enter knight, castle and bombIDs
        System.out.println("\nEnter Knight ID");
        int knightID = sc.nextInt();
        k.initialKnightMoves(knightID,size); //initialize knight moves based on current position and create
        //Knight Graph.
        B.setKnightGraph(k); //return Knight's graph to the board

        System.out.println("Enter Castle ID");
        int castleID = sc.nextInt();
        B.setCastle(castleID);

        System.out.println("Enter bombs IDS separated by comma");
        Bombs bombs = new Bombs();
        ArrayList<Integer> IDS = B.readStrings();

        bombs.setBomb(IDS, IDS.size());
        B.setBombs(bombs.getBombsPosition());
    }
}

