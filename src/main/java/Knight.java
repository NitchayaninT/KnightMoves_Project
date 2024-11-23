import org.jgrapht.alg.shortestpath.BFSShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

//Model the Problem as a Graph:
//Each cell of the board is a vertex.
//Each valid Knight move from one cell to another forms an edge.
//first element : x-axis
//second element : y-axis
public class Knight {
    private ArrayList<int[]> defaultKnightMoves = new ArrayList<>(Arrays.asList(
            new int[]{2, 1}, new int[]{2, -1}, new int[]{-2, 1}, new int[]{-2, -1},
            new int[]{1, 2}, new int[]{-1, 2}, new int[]{1, -2}, new int[]{-1, -2}
    ));
    private ArrayList<int[]> currentKnightMoves;
    int[] knightPosition = new int[2]; //store current position
    int boardSize;

    public Knight() {
        currentKnightMoves = new ArrayList<>();
        boardSize= 0;
    }

    //set knightID
    public void setKnightPosition(int knightID,int boardSize)
    {
        this.boardSize = boardSize;
        int count = 0;
        boolean found = false;
        for(int i=0;i<boardSize;i++)
        {
            for(int j=0;j<boardSize;j++)
            {
                if(knightID == count) {
                    //j is column. so its x-axis
                    //i is row, so its y-axis
                    knightPosition = new int[]{j, i};
                    found = true;
                    break;
                }
                count++;
            }
            if(found) break;
        }
        System.out.println("knight position : "+Arrays.toString(knightPosition));
    }
    public void initialKnightMoves()
    {
        Board B = new Board(boardSize);
        SimpleGraph<Integer, DefaultEdge> boardGraph = new SimpleGraph<>(DefaultEdge.class);
        //insert all vertices
        int count = 0;
        for(int i=0;i<boardSize;i++)
        {
            for(int j=0;j<boardSize;j++)
            {
                boardGraph.addVertex(count);
                count++;
            }
        }
        //edge represents a valid move the Knight can make from one cell to another
        //insert all edges. current position plus all possible knight moves (from default)
        for(int i=0; i<8 ;i++)
        {
            int []defaultEdge = defaultKnightMoves.get(i); //get default edge (ex. 0, 0)
            //add defaultEdge to knightPosition and stores each edge in currentKnightMoves
            int []addedEdge = new int[2];
            for(int j=0;j<2;j++)
            {
                addedEdge[j]=defaultEdge[j]+knightPosition[j];
            }
            //add possible edges that a knight can move.
            //if either x-axis or y-axis is negative, that means its out of the board
            if(addedEdge[0]<0 || addedEdge[1]<0) continue;
            currentKnightMoves.add(addedEdge);
            //boardGraph.addEdge(addedEdge[0],addedEdge[1]);
            System.out.println("AddedEdge reference: " + Arrays.toString(addedEdge));
        }
        BFSShortestPath<Integer, DefaultEdge> BFS = new BFSShortestPath<>(boardGraph);
    }
    public void findShortestPath()
    {

    }
    //Represents the Knight and its movement.
    //Responsibilities:
    //Store the Knight's current position.
    //Calculate possible moves.
}
