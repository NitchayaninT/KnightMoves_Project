
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.*;

public class Board {

    private static int size;
    private Knight knight;
    private Bombs bombs;
    //POSITIONS
    private Coordinate CastlePosition;

    public Knight getKnight() {
        return knight;
    }

    public Bombs getBombs() {
        return bombs;
    }

    public void setKnight(Knight k) {
        knight = k;
    }

    public void setBombs(Bombs b) {
        bombs = b;
    }

    public void setCastle(int id) {
        int count = 0;
        boolean found = false;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (id == count) {
                    //j is column. so its x-axis
                    //i is row, so its y-axis
                    CastlePosition = new Coordinate(j, i);
                    found = true;
                    break;
                }
                count++;
            }
            if (found) {
                break;
            }
        }
    }



    //getter
    public static int getSize() {
        return size;
    }

    //Constructors
    public Board(int size) {
        this.size = size;
        this.knight = new Knight();
        this.bombs = new Bombs();
        this.CastlePosition = null;
    }

    public Board() {
        this.size = 0;
    }

    public void printBoard() {
        System.out.printf("Cell IDs %7s", " ");
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("%4d", count);
                count++;
            }
            System.out.println();
            System.out.printf("%16s", " ");
        }
    }
    public void printAns(int knightID,int castleID,ArrayList<Integer>BombsID)
    {
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                String name = "   ";
                if(count==knightID) name = "K* ";
                if(count==castleID) name = "C* ";
                if(count==castleID&&count==knightID) name = "C+K";
                for(int n:BombsID)if(count==n) name = "b  ";
                System.out.printf("%8d:%s", count,name);
                count++;
            }
            System.out.println();
        }
    }

    public void bfs() {
        ArrayDeque<Coordinate> q = new ArrayDeque<>();
        HashMap<Coordinate, Coordinate> parent = new LinkedHashMap<>();
        Coordinate knightPosition = knight.getKnightPosition();
        q.add(knightPosition);
        parent.put(knightPosition, null);
        int[][] visited = new int[size][size];
        visited[knightPosition.getX()][knightPosition.getY()] = 1;
        int[] knightXMoves = knight.getKnightXMoves();
        int[] knightYMoves = knight.getKnightYMoves();
        while (!q.isEmpty()) {
            Coordinate current = q.poll();
            if (current.equals(CastlePosition)) {
                break;
            }
            for (int i = 0; i < 8; i++) {
                int x = current.getX() + knightXMoves[i];
                int y = current.getY() + knightYMoves[i];
                Coordinate neighborKnightPosition = new Coordinate(x, y);
                if (isSafe(visited,neighborKnightPosition)) {
                    visited[x][y] = 1;
                    q.add(neighborKnightPosition);
                    parent.put(neighborKnightPosition, current);
                }
            }
        }
        ArrayList<Coordinate> path = new ArrayList<>();
        Coordinate node = CastlePosition;

        // Check if the Castle was reached
        int countRoute = -1;
        if (parent.containsKey(CastlePosition)) {
            while (node != null) {
                path.add(node);
                node = parent.get(node);
                countRoute++;
            }
            Collections.reverse(path);

            System.out.printf("\nBest route to Castle = %d %s\n\n",countRoute,(countRoute>1)?"moves":"move");
            int count = 0;
            for(Coordinate coord:path)
            {
                int id = coord.getY()*size+coord.getX();
                if(count==0)
                {
                    count++;
                    continue;
                }
                else{
                    System.out.printf("Move %d --> jump to %d\n",count,id);
                    int knightID = coord.getY() * size + coord.getX();
                    int castleID = CastlePosition.getY() * size + CastlePosition.getX();
                    printAns(knightID,castleID,bombs.getBombsID());
                    count++;
                }
            }
        } else {
            System.out.println("\nNo route from Knight to Castle");
        }

    }
    public boolean isSafe(int[][]visited,Coordinate neighborKnightPosition)
    {
        int x = neighborKnightPosition.getX();
        int y = neighborKnightPosition.getY();
        if(x<0||y<0)return false;
        if(x>=size||y>=size)return false;
        if(visited[x][y]==1)return false;
        ArrayList<Coordinate> bombsList = bombs.getBombsPosition();
        for(Coordinate bombs:bombsList)
        {
            if(bombs.equals(neighborKnightPosition))return false;
        }
        return true;
    }
}
