
import java.util.Objects;

public class Coordinate {

    int x;
    int y;

    public void setX(int n) {
        x = n;
    }

    public void setY(int n) {
        y = n;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setCoodintae(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        // Check if the current object and the obj are the same reference
        if (this == obj) {
            return true;
        }

        // Check if the obj is null or not of the same class
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        // Cast the object to Coordinate and compare the fields
        Coordinate other = (Coordinate) obj;
        return x == other.x && y == other.y;
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
