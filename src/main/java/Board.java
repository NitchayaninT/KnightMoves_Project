import java.util.*;
public class Board {
    private int size;
    //Store the positions of the Knight, Castle, and Bombs.
    //Track visited cells during the search.
    //Validate whether a move is within bounds and safe.

    //set knight, castle, bombs
    public void setKnight(Knight K)
    {

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
}
