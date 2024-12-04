import java.util.*;

public class main {
    int size;
    int knightID;
    int castleID;
    public static void main(String args[]) {
        main game = new main();  // Create an instance of Main to hold the game state
        game.knightMoves();
    }
    public void knightMoves()
    {
        String s = "";

        do {
            Scanner sc = new Scanner(System.in);

            boolean Lessthan5 = true;
            while (Lessthan5 == true) {
                System.out.println("Enter N for N*N board (N must be at least 5)");

                try {
                    Scanner ans = new Scanner(System.in);
                    size = ans.nextInt(); //if size not int, go to catch
                    if (size > 4) Lessthan5 = false;
                    else {
                        System.out.println("=======================================");
                        System.out.println("N should be at least 5, insert again!");
                        System.out.println("=======================================\n");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("=======================================");
                    System.out.println("Invalid input! Please enter an integer.");
                    System.out.println("=======================================\n");
                }

            }

            //create board
            Board B = new Board(size);
            B.printBoard();

            //Enter knight, castle and bombIDs
            Knight k = new Knight();
            boolean valid = false;
            while (valid == false) {
                System.out.println("\nEnter Knight ID");

                try {
                    Scanner ans = new Scanner(System.in);
                    knightID = ans.nextInt();
                    if (knightID < size * size && knightID >= 0) {
                        k.setKnightPosition(knightID, size);
                        valid = true;
                    } else {
                        System.out.println("=======================================");
                        System.out.println("Invalid Position!! Please insert again");
                        System.out.println("=======================================");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("=======================================");
                    System.out.println("Invalid input! Please enter an integer.");
                    System.out.println("=======================================");
                }
            }

            valid = false;
            while (valid == false) {
                System.out.println("Enter Castle ID");
                try {
                    Scanner ans = new Scanner(System.in);
                    castleID = ans.nextInt();
                    if (castleID < size * size && castleID >= 0) {
                        B.setCastle(castleID);
                        valid = true;
                    } else {
                        System.out.println("=======================================");
                        System.out.println("Invalid Position!! Please insert again");
                        System.out.println("=======================================\n");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("=======================================");
                    System.out.println("Invalid input! Please enter an integer.");
                    System.out.println("=======================================\n");
                }
            }
            //inserting Bombs
            Bombs bombs = new Bombs();
            bombs.setBombCoordinate(size, castleID, knightID);
            B.setKnight(k);
            B.setBombs(bombs);

            System.out.printf("\nInitial --> knight at %d\n", knightID);
            B.printAns(knightID, castleID, bombs.getBombsID());
            B.bfs();
            for (int i = 0; i < 80; i++) System.out.printf("=");

            System.out.println();

            while(true) {
                System.out.println("New game (y/n)?");
                Scanner sc2 = new Scanner(System.in);
                s = sc2.nextLine();
                if(!Objects.equals(s, "n") && !Objects.equals(s,"y"))
                {
                    System.out.println("Please insert y or n!");
                }
                else break;
            }

        }while(!s.equalsIgnoreCase("n"));

    }
}

