import org.jgrapht.alg.shortestpath.BFSShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.*;


//first element : x-axis
//second element : y-axis
public class Knight {
    private int[] KnightXMoves = new int[]{2,2,1,-1,-2,-2,-1,1};
    private int[] KnightYMoves = new int[]{1,-1,-2,-2,-1,1,2,2};
    private int index;
    private Coordinate knightPosition; //store current position

    public int[] getKnightXMoves()
    {
        return KnightXMoves;
    }
    public int[] getKnightYMoves()
    {
        return KnightYMoves;
    }
    public Coordinate getKnightPosition()
    {
        return knightPosition;
    }
    public void setKnightCoordinate(int x, int y)
    {
        knightPosition.setX(x);
        knightPosition.setY(y);
    }

    //Constructor
    public Knight() {
        this.index = 0;
        knightPosition = null;
    }

    //set knightID
    public void setKnightPosition(int knightID,int boardSize)
    {
        this.index = knightID;
        int count = 0;
        boolean found = false;
        for(int i=0;i<boardSize;i++)
        {
            for(int j=0;j<boardSize;j++)
            {
                if(knightID == count) {
                    //j is column. so its x-axis
                    //i is row, so its y-axis
                    knightPosition = new Coordinate(j,i);
                    found = true;
                    break;
                }
                count++;
            }
            if(found) break;
        }
    }

}



