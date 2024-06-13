import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import org.xml.sax.SAXException;

import java.io.FileInputStream;

/**
 * Models a weighted graph of latitude-longitude points
 * and supports various distance and routing operations.
 * To do: Add your name(s) as additional authors
 * @author Brandon Fain
 * @author Ishan Madan 
 * @author Aryan Agarwal
 *
 */
public class GraphProcessor {
    private Map<Point, ArrayList<Point>> adjList = new HashMap<>();
    private List<Point> pointList = new ArrayList<>();
    private Map<int[], String> myMap = new HashMap<>();
    
    /**
     * Creates and initializes a graph from a source data
     * file in the .graph format. Should be called
     * before any other methods work.
     * @param file a FileInputStream of the .graph file
     * @throws Exception if file not found or error reading
     */
    public void initialize(FileInputStream file) throws Exception {
        Scanner s = new Scanner(file);
        int pts = s.nextInt();
        int edges = s.nextInt();

        for(int i = 0; i < pts; i++) {
            s.next();
            double latitude = s.nextDouble();
            double longitude = s.nextDouble();
            Point p = new Point(latitude, longitude);
            
            pointList.add(p);
            adjList.put(p, new ArrayList<>());
        }

        for(int j = 0; j < edges; j++) {
            int x = s.nextInt();
            int b = s.nextInt();

            Point firstPoint = pointList.get(x);
            Point lastPoint = pointList.get(b);

            adjList.get(firstPoint).add(lastPoint);
            adjList.get(lastPoint).add(firstPoint);

            if(s.hasNext() && !s.hasNextInt()) {
                String edge = s.next();
                int[] coor = new int[]{x, b};
                myMap.put(coor, edge);
            }
        }

        s.close();
    }


    /**
     * Searches for the point in the graph that is closest in
     * straight-line distance to the parameter point p
     * @param p A point, not necessarily in the graph
     * @return The closest point in the graph to p
     */
    public Point nearestPoint(Point p) {
        Point pt = pointList.get(0);
        double dist = p.distance(pt);
        for(int i = 1; i < pointList.size(); i++) {
            double newDist = p.distance(pointList.get(i));
            if(newDist < dist) {
                dist = newDist;
                pt = pointList.get(i);
            }
        }
        return pt;
    }


    /**
     * Calculates the total distance along the route, summing
     * the distance between the first and the second Points, 
     * the second and the third, ..., the second to last and
     * the last. Distance returned in miles.
     * @param start Beginning point. May or may not be in the graph.
     * @param end Destination point May or may not be in the graph.
     * @return The distance to get from start to end
     */
    public double routeDistance(List<Point> route) {
        double dist = route.get(0).distance(route.get(1));
        for(int i = 1; i < route.size()-1; i++) {
            dist = dist + route.get(i).distance(route.get(i+1));
        }
        return dist;
    }
    

    /**
     * Checks if input points are part of a connected component
     * in the graph, that is, can one get from one to the other
     * only traversing edges in the graph
     * @param p1 one point
     * @param p2 another point
     * @return true if p2 is reachable from p1 (and vice versa)
     */
    public boolean connected(Point p1, Point p2) {
        Stack<Point> toExplore = new Stack<>();
        Map<Point, Point> previous = new HashMap<>();
        Set<Point> visited = new HashSet<>();
        Point current = p1;
        toExplore.add(current);
        visited.add(current);
        while(!toExplore.isEmpty()) {
            current = toExplore.pop();
            for(Point neighbor: adjList.get(current)) {
                if(!visited.contains(neighbor)) {
                    previous.put(neighbor, current);
                    visited.add(neighbor);
                    toExplore.push(neighbor);
                }
            }
        }
        if(visited.contains(p2)) {
            return true;
        }
        return false;
    }


    /**
     * Returns the shortest path, traversing the graph, that begins at start
     * and terminates at end, including start and end as the first and last
     * points in the returned list. If there is no such route, either because
     * start is not connected to end or because start equals end, throws an
     * exception.
     * @param start Beginning point.
     * @param end Destination point.
     * @return The shortest path [start, ..., end].
     * @throws InvalidAlgorithmParameterException if there is no such route, 
     * either because start is not connected to end or because start equals end.
     */
    public List<Point> route(Point start, Point end) throws InvalidAlgorithmParameterException {
        if(start.equals(end) || !adjList.containsKey(start) || !adjList.containsKey(end) || !connected(start, end)) {
            throw new InvalidAlgorithmParameterException("No path between start and end");
        }
        Map<Point, Double> distance = new HashMap<>();
        Comparator<Point> comp = (a, b) -> Double.compare(distance.get(a), distance.get(b));
        PriorityQueue<Point> toExplore = new PriorityQueue<>(comp);
        Map<Point, Point> previous = new HashMap<>();
        Point current = start;
        distance.put(current, 0.0);
        toExplore.add(current);
        while(!toExplore.isEmpty()) {
            current = toExplore.remove();
            for(Point neighbor: adjList.get(current)) {
                double weight = neighbor.distance(current);
                if(!distance.containsKey(neighbor) || distance.get(neighbor) > distance.get(current) + weight) {
                    distance.put(neighbor, distance.get(current) + weight);
                    previous.put(neighbor, current);
                    toExplore.add(neighbor);
                }
            }
        }
        List<Point> ans = new ArrayList<>();
        ans.add(end);
        for(int i = 0; i < previous.size(); i++) {
            if(end == start) {
                break;
            }
            ans.add(previous.get(end));
            end = previous.get(end);
        }
        Collections.reverse(ans);
        return ans;
    }

    
}
