
import java.util.*;

public class Bombs {

    private ArrayList<Coordinate> BombsPosition;
    private ArrayList<Integer> BombsID;

    public Bombs() {
        this.BombsPosition = new ArrayList<Coordinate>();
        this.BombsID = new ArrayList<Integer>();

    }

    public void setBombCoordinate(int size, int castleID,int knightID) {
        // Create a map to store bomb positions with the ID as the key
        ArrayList<Integer> A = readStrings();
        // we convert these IDs to position in (x, y) format in BombPosition
        for (int id_index = 0; id_index < A.size(); id_index++) {
            int id = A.get(id_index);
            if (id > 0 && id < (Board.getSize() * Board.getSize() - 1) && id != castleID && id != knightID) {
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

    public ArrayList<Integer> readStrings() {
        Scanner sc = new Scanner(System.in);
        boolean valid = false;
        boolean isEmpty = false;
        ArrayList<Integer> bombIDs = null;

        while (!valid) {
            int invalidCount = 0;
            System.out.println("Enter bomb IDs separated by commas(invalid IDs will be ignored)");
            String input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("you havent inserted anything");
                continue;
            } else if (input.equals("-1")) {
                isEmpty = true;
                break; //return an empty set
            }
            String[] names = input.split(",");
            bombIDs = new ArrayList<>();

            for (String name : names) {
                try {
                    // Try to convert the trimmed string to an integer
                    int bombID = Integer.parseInt(name.trim());
                    bombIDs.add(bombID);
                } catch (NumberFormatException e) {
                    isEmpty = true;
                    invalidCount++;
                    break; //return an empty set if invalid

                }
            }
            if(isEmpty)break;
            valid = true;

        }
        if(isEmpty)
        {
            return new ArrayList<>();
        }
        return bombIDs;
    }
}
