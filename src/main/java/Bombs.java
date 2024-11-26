
import java.util.*;

public class Bombs {

    private ArrayList<Coordinate> BombsPosition;
    private ArrayList<Integer> BombsID;

    //why LinkedHashMap? because we want index as a key of each coordinate

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
        String input = sc.nextLine();
        if (input.isEmpty()) {
            System.out.println("you havent inserted anything");
            return new ArrayList<>(); // Return an empty set if input is empty
        } else if (input.equals("-1")) {
            return new ArrayList<>(); //return an empty set
        }
        String[] names = input.split(",");
        Integer[] bombIDS = new Integer[names.length];

        for (int i = 0; i < names.length; i++) {
            bombIDS[i] = Integer.valueOf(names[i].trim()); //bombIDS is an array that stores integers (bomb ids)
        }
        //return LinkedHashSet of bombIDS (the ids are stored based on user input)
        return new ArrayList<>(Arrays.asList(bombIDS));
    }

}
