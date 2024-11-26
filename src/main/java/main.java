import java.util.*;

public class main {

    public static void main(String args[])
    {
        String s = "";
        do{
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter N for N*N board (N must be at least 5)");
            int size = sc.nextInt();
            //create knight

            //create board
            Board B = new Board(size);
            B.printBoard();

            //Enter knight, castle and bombIDs
            System.out.println("\nEnter Knight ID");
            int knightID = sc.nextInt();
            //k.initialKnightMoves(knightID,size); //initialize knight moves based on current position and create
            //Knight Graph.
            Knight k = new Knight();
            k.setKnightPosition(knightID,size);
            System.out.println("Enter Castle ID");
            int castleID = sc.nextInt();
            B.setCastle(castleID);
            System.out.println("Enter bomb IDs separated by commas(invalid IDs will be ignored)");
            Bombs bombs = new Bombs();
            bombs.setBombCoordinate(size,castleID,knightID);
            B.setKnight(k);
            B.setBombs(bombs);
            System.out.printf("\nInitial --> knight at %d\n",knightID);
            B.printAns(knightID,castleID,bombs.getBombsID());
            B.bfs();
            for(int i=0;i<80;i++)System.out.printf("=");
            System.out.println();
            System.out.println("New game (y/n)?");
            Scanner sc2 = new Scanner(System.in);
            s = sc2.nextLine();
        }while(!s.equalsIgnoreCase("n"));
        //B.setKnightGraph(k); //return Knight's graph to the board

        /*System.out.println("Enter Castle ID");
        int castleID = sc.nextInt();
        B.setCastle(castleID);

        System.out.println("Enter bombs IDS separated by comma");
        Bombs bombs = new Bombs();
        bombs.setBoardSize(size); //set board size to bombs (for x,y coord calculation)
        bombs.setBomb(); //set bombs and convert them to coordinates

        //set these bombs to the Board class
        B.setBombs(bombs.getBombsPosition());/*

        //DONE SETTING BOMBS,CASTLE,KNIGHT COORDINATES*/
    }
}

