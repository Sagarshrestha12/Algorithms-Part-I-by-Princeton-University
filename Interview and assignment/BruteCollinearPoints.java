import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Arrays;


public class BruteCollinearPoints {

    private LineSegment[] segments;
    private int segmentCount;

    /**
     * @param points
     * @description finds all line segments containing 4 points
     */
    public BruteCollinearPoints(Point[] points) {

        // check to see if argument matches constraints
        checksPoints(points);

        Point[] points1 = points.clone();
        this.segments = new LineSegment[2];
        this.segmentCount = 0;

        // sort array
        Arrays.sort(points1);

        for (int i = 0; i < points1.length - 3; i++) {
            for (int j = i + 1; j < points1.length - 2; j++) {
                for (int k = j + 1; k < points1.length - 1; k++) {
                    for (int l = k + 1; l < points1.length; l++) {
                        if (points1[i].slopeTo(points1[j]) == points1[j].slopeTo(points1[k]) &&
                                points1[j].slopeTo(points1[k]) == points1[k].slopeTo(points1[l])) {

                            // add item to array
                            enqueue(new LineSegment(points1[i], points1[l]));

                            points1[i].drawTo(points1[l]);
                            StdDraw.show();
                        }
                    }
                }
            }
        }
    }

    /**
     * @param capacity
     * @description resize the underlying array holding the elements
     */
    private void resize(int capacity) {
        assert capacity >= this.segmentCount;

        // textbook implementation
        LineSegment[] temp = new LineSegment[capacity];
        System.arraycopy(this.segments, 0, temp, 0, this.segmentCount);
        this.segments = temp;

        // alternative implementation
        // a = java.util.Arrays.copyOf(a, capacity);
    }

    /**
     * @description add the item
     */
    private void enqueue(LineSegment item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (this.segmentCount == this.segments.length) {
            resize(2 * this.segments.length);
        }

        this.segments[this.segmentCount++] = item;
    }

    /**
     * @description do check on argument
     */
    private void checksPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {

                if (points[i] == null || points[j] == null) {
                    throw new IllegalArgumentException();
                }

                if (i != j && points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }
    }

    /**
     * @return number of segments
     * @description the number of line segments
     */
    public int numberOfSegments() {
        return this.segmentCount;
    }

    /**
     * @return segments array
     * @description the line segments
     */
    public LineSegment[] segments() {
        return Arrays.copyOf(this.segments, this.segmentCount);
    }

    public static void main(String[] args) {
        In in = new In("ftp://ftp.cs.princeton.edu/pub/cs226/collinear/rs1423.txt");      // input file
        int n = in.readInt();

        // padding for drawing
        int padding = 10000;

        // set draw scale
        StdDraw.setXscale(-padding, Short.MAX_VALUE + padding);
        StdDraw.setYscale(-padding, Short.MAX_VALUE + padding);

        // Index of array
        int index = 0;

        // turn on animation mode
        StdDraw.enableDoubleBuffering();

        // Create array
        Point[] points = new Point[n];

        points[index] = new Point(in.readInt(), in.readInt());
        points[index].draw();
        StdDraw.show();

        index++;

        while (!in.isEmpty()) {
            points[index] = new Point(in.readInt(), in.readInt());
            points[index].draw();
            StdDraw.show();

            index++;
        }

        BruteCollinearPoints bach = new BruteCollinearPoints(points);
        //LineSegment[] lines = bach.segments();
    }
}
