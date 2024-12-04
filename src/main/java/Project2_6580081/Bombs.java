package Project2_6580081;

import java.util.*;

public class Bombs {

    private ArrayList<Coordinate> BombsPosition;
    private ArrayList<Integer> BombsID;

    public Bombs() {
        this.BombsPosition = new ArrayList<Coordinate>();
        this.BombsID = new ArrayList<Integer>();

    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str); // Try to parse the string as an integer
            return true; // If successful, it's an integer
        } catch (NumberFormatException e) {
            return false; // If parsing fails, it's not an integer
        }
    }
    public void setBombCoordinate(int size, int castleID,int knightID) {
        // Create a map to store bomb positions with the ID as the key
        ArrayList<Integer> A = readStrings(size);
        // we convert these IDs to position in (x, y) format in BombPosition
        for (int id_index = 0; id_index < A.size(); id_index++) {
            int id = A.get(id_index);
            if (id >= 0 && id <= (Board.getSize() * Board.getSize() - 1) && id != castleID && id != knightID) {
                int x = id % size;  // x = column
                int y = id / size;  // y = row

                // Put the position and index in the map
                BombsID.add(id);
                BombsPosition.add(new Coordinate(x, y));
            }
        }
    }

    //getter
    public ArrayList<Integer> getBombsID() {
        return BombsID;
    }

    public ArrayList<Coordinate> getBombsPosition() {
        return BombsPosition;
    }

    public ArrayList<Integer> readStrings(int size) {
        Scanner sc = new Scanner(System.in);
        boolean valid = false;
        boolean isEmpty = false;
        boolean exceedsBoard = false;
        ArrayList<Integer> bombIDs = null;

        while (!valid) {
            exceedsBoard = false;
            System.out.println("Enter bomb IDs separated by commas(invalid IDs will be ignored)");
            String input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("you havent inserted anything");
                continue;
            }
            else if (isInteger(input)) { // Check if the input is an integer
                if (Integer.parseInt(input) < 0 || Integer.parseInt(input)>=size) {
                    isEmpty = true;
                    break; // Break the loop for numbers less than 0
                }
            }
            String[] names = input.split(",");
            bombIDs = new ArrayList<>();

            for (String name : names) {
                try {
                    // Try to convert the trimmed string to an integer
                    int bombID = Integer.parseInt(name.trim());
                    if(bombID >= size*size || bombID < 0)
                    {
                        System.out.println("Some IDs are exceeding the board, please enter the full list again\n");
                        exceedsBoard = true;
                        break; //break from for loop, but not the big !valid loop because its still not valid
                    }
                    bombIDs.add(bombID);
                } catch (NumberFormatException e) {
                    if(names.length > 1)
                    {
                        System.out.println("Some IDs are not integer, please enter the full list again\n");
                        exceedsBoard = true;
                        break; //break from for loop, but not the big !valid loop because its still not valid
                    }
                    isEmpty = true;
                    break; //return an empty set if invalid

                }
            }
            if(isEmpty)break;
            if(exceedsBoard) continue;
            valid = true;

        }
        if(isEmpty)
        {
            return new ArrayList<>();
        }
        return bombIDs;
    }
}
