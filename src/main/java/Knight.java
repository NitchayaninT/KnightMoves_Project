import org.jgrapht.alg.shortestpath.BFSShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.*;

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
    private LinkedHashMap<Integer,Coordinate> currentKnightMoves; //LinkedHashMap because its easier to access index
    int[] knightPosition = new int[2]; //store current position
    int boardSize;
    SimpleGraph<Integer, DefaultEdge> boardGraph;

    //getter
    public SimpleGraph<Integer,DefaultEdge> getKnightGraph()
    {
        return boardGraph;
    }
    public ArrayList<int[]> getDefaultKnightMoves()
    {
        return defaultKnightMoves;
    }
    public LinkedHashMap<Integer, Coordinate> getCurrentKnightMoves()
    {
        return currentKnightMoves;
    }

    //Constructor
    public Knight() {
        boardGraph = new SimpleGraph<>(DefaultEdge.class);
        boardSize= 0;
        currentKnightMoves = new LinkedHashMap<>();
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
    public void initialKnightMoves(int knightID,int boardSize)
    {
        setKnightPosition(knightID,boardSize);
        boardGraph = new SimpleGraph<>(DefaultEdge.class);

        //insert vertices. (from 0 to N*N)
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
        int countNoOfEdges = 0;
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
            //its the edges are also inserted in boardGraph
            if(addedEdge[0]<0 || addedEdge[1]<0) continue;

            Coordinate position = new Coordinate(addedEdge[0], addedEdge[1]);
            currentKnightMoves.put(countNoOfEdges, position);//set current knight moves
            countNoOfEdges++;

            //ADDING EDGES TO THE GRAPH//
            //Converted (x, y) coordinates to vertex indices
            //knightPosition coordinates is the source node of the knight (beginning mode before moving)
            //addedEdge coordinates represents each of the destination node in L shape.
            int source = knightPosition[0] * boardSize + knightPosition[1];
            int destination = addedEdge[0] * boardSize + addedEdge[1];
            boardGraph.addEdge(source,destination); //add the edges to the graph
            System.out.println("AddedEdge reference: " + Arrays.toString(addedEdge));
        }
    }
    public static class Coordinate {
        int x;
        int y;

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
    //Represents the Knight and its movement.
    //Responsibilities:
    //Store the Knight's current position.
    //Calculate possible moves.
}
