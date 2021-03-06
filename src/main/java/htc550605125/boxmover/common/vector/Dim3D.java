package htc550605125.boxmover.common.vector;

/**
 * Created with IntelliJ IDEA.
 * User: htc
 * Date: 11/19/13
 * Time: 10:42 AM
 */

import java.io.Serializable;

/**
 * Three-dimensional game's {@link Dim}
 */
public class Dim3D extends Dim implements Serializable, Cloneable {
    /**
     * Define the dimension
     */
    public final int x, y, z;

    public Dim3D(int x, int y, int z) {
        super();
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * You can only construct a vector from a dimension.
     *
     * @see Vector3D
     */
    public Vector3D newVector(int x, int y, int z) {
        return new Vector3D(x, y, z, this);
    }

    /**
     * @return The number of all the possible {@link Vector}
     */
    @Override
    public int getMax() {
        return x * y * z;
    }

    @Override
    public boolean equals(Dim dim) {
        return dim instanceof Dim3D && equals((Dim3D) dim);
    }

    public boolean equals(Dim3D dim) {
        return dim.x == x && dim.y == y && dim.z == z;
    }

    // return all the possible vector for the base class to decorate
    @Override
    protected Vector[] _getAllVectors() {
        Vector[] ret = new Vector[x * y * z];
        int p = 0;
        for (int i = 0; i < x; ++i)
            for (int j = 0; j < y; ++j)
                for (int k = 0; k < z; ++k)
                    ret[p++] = newVector(i, j, k);
        return ret;
    }

    // return all the possible directions as vector for the base class to decorate
    @Override
    protected Vector[] _getAllDirections() {
        return new Vector[]{Vector3D.UP, Vector3D.DOWN, Vector3D.LEFT, Vector3D.RIGHT, Vector3D.FORWARD, Vector3D.BACKWARD};
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + "," + z + "]";
    }
}