import java.util.*;

public class Bombs {

    private LinkedHashMap<Integer,Coordinate> BombsPosition;
    private int boardSize;
    //why LinkedHashMap? because we want index as a key of each coordinate

    public Bombs() {
        BombsPosition = new LinkedHashMap<>();
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
            Coordinate that = (Coordinate) obj;
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
    public void setBomb() {
        // Create a map to store bomb positions with the ID as the key
        ArrayList<Integer> A = readStrings();
        // we convert these IDs to position in (x, y) format in BombPosition
        for (int id_index = 0; id_index < A.size(); id_index++) {
            int id = A.get(id_index);

            int x = id % boardSize;  // x = column
            int y = id / boardSize;  // y = row

            Coordinate position = new Coordinate(x, y);

            // Put the position and index in the map
            BombsPosition.put(id_index, position);
        }

        // Print the bomb positions
        System.out.println("Bomb positions: " + BombsPosition);

    }
    //getter
    public LinkedHashMap<Integer,Coordinate> getBombsPosition()
    {
        return BombsPosition;
    }
    public void setBoardSize(int size)
    {
        boardSize = size;
    }
    public ArrayList<Integer> readStrings()
    {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        if (input.isEmpty()) {
            System.out.println("you havent inserted anything");
            return new ArrayList<>(); // Return an empty set if input is empty
        }
        else if(input.equals("-1"))
        {
            return new ArrayList<>(); //return an empty set
        }
        String[] names = input.split(",");
        Integer[] bombIDS = new Integer[names.length];

        for(int i=0;i<names.length;i++)
        {
            bombIDS[i]  = Integer.valueOf(names[i].trim()); //bombIDS is an array that stores integers (bomb ids)
        }
        //return LinkedHashSet of bombIDS (the ids are stored based on user input)
        return new ArrayList<>(Arrays.asList(bombIDS));
    }
}

