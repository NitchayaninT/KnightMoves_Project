import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.*;
public class Board {
    private int size;
    private SimpleGraph<Integer, DefaultEdge> knightGraph;
    private ArrayList<int[]> defaultKnightMoves; //why dont we use map for this? because the values inside are constant, we
    //dont have to change any values
    //POSITIONS
    private int[] CastlePosition;
    private LinkedHashMap<Integer, Bombs.Coordinate> BombsPosition; //stores set of bombs position
    private LinkedHashMap<Integer, Knight.Coordinate> KnightPosition;


    //Store the positions of the Knight, Castle, and Bombs.
    //Track visited cells during the search.
    //Validate whether a move is within bounds and safe.

    //set knight, castle, bombs positions
    public void setKnightGraph(Knight K)
    {
        KnightPosition = K.getCurrentKnightMoves();
        knightGraph = K.getKnightGraph(); //now has knight's vertices and edges
        defaultKnightMoves = K.getDefaultKnightMoves();
        System.out.println(knightGraph);
        System.out.println(KnightPosition);
    }
    public void setBombs(LinkedHashMap<Integer,Bombs.Coordinate> bombs)
    {
        BombsPosition = bombs;
    }
    public void setCastle(int id)
    {
        int count = 0;
        boolean found = false;
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                if(id == count) {
                    //j is column. so its x-axis
                    //i is row, so its y-axis
                    CastlePosition = new int[]{j, i};
                    found = true;
                    break;
                }
                count++;
            }
            if(found) break;
        }
        System.out.println("castle position : "+Arrays.toString(CastlePosition));
    }


    //getter
    public int getSize()
    {
        return this.size;
    }

    //Constructors
    public Board(int size)
    {
        this.size = size;
    }
    public Board()
    {
        this.size = 0;
    }

    public void printBoard()
    {
        System.out.printf("Cell IDs %7s"," ");
        int count = 0;
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                System.out.printf("%4d",count);
                count++;
            }
            System.out.println();
            System.out.printf("%16s"," ");
        }
    }

    //class coordinate, this is for accessing elements in Coordinate class when changing Knight Position
    public static class Coordinate {
        int x;
        int y;

        //insert x axis and y axis to Coordinate before inserting in LinkedHashMap
        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Bombs.Coordinate that = (Bombs.Coordinate) obj;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
}
