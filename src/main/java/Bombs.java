import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;

public class Bombs {

    private LinkedHashMap<Integer,Coordinate> BombsPosition;
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
    public void setBomb(ArrayList<Integer> A, int size) {
        // Create a map to store bomb positions with the ID as the key

        // we convert these IDs to position in (x, y) format in BombPosition
        for (int id_index = 0; id_index < A.size(); id_index++) {
            int id = A.get(id_index);

            int x = id % size;  // x = column
            int y = id / size;  // y = row

            Coordinate position = new Coordinate(x, y);

            // Put the position and index in the map
            BombsPosition.put(id_index, position);
        }

        // Print the bomb positions
        System.out.println("Bomb positions: " + BombsPosition);

    }
    public LinkedHashMap<Integer,Coordinate> getBombsPosition()
    {
        return BombsPosition;
    }

}

