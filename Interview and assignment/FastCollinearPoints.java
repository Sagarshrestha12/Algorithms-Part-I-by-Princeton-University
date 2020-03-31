import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.LinkedList;

public class FastCollinearPoints {

    private LineSegment[] segments;
    private int segmentCount;

    /**
     * @param points
     * @description finds all line segments containing 4 or more points
     */
    public FastCollinearPoints(Point[] points) {

        // check to see if argument matches constraints
        checksPoints(points);

        Point[] points1 = points.clone();
        this.segments = new LineSegment[2];
        this.segmentCount = 0;
        LinkedList<Point> collinearPoints = new LinkedList<Point>();// Point is data type which is used as an linked list

        //Arrays.sort(this.points);
        // check to see if argument matches constraints
        for (Point point : points1) {
            Arrays.sort(points1, point.slopeOrder());
            double prevSlope = 0.0;

            for (int j = 0; j < points1.length; j++) {
                double currentSlope = point.slopeTo(points1[j]);
                if (j == 0 || currentSlope != prevSlope) {

                    if (collinearPoints.size() >= 3) {
                        //Collections.sort(collinearPoints);
                        this.enqueue(new LineSegment(collinearPoints.getFirst(), collinearPoints.getLast()));
                        // collinearPoints.getFirst().drawTo(collinearPoints.getLast());


                        //   StdDraw.show();
                    }

                    collinearPoints.clear();
                }

                collinearPoints.add(points1[j]);
                prevSlope = currentSlope;
            }
        }

    }

    /**
     * @return
     * @description the number of line segments
     */
    public int numberOfSegments() {
        return this.segmentCount;
    }

    /**
     * @return
     * @description the line segments
     */
    public LineSegment[] segments() {
        return Arrays.copyOf(this.segments, this.segmentCount);
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

    public static void main(String[] args) {
        In in = new In("ftp://ftp.cs.princeton.edu/pub/cs226/collinear/rs1423.txt");      // input file
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        LineSegment[] kk = collinear.segments();
        LineSegment segment = kk[0];
        StdOut.println(segment);
        segment.draw();
        StdDraw.show();
    }
}

