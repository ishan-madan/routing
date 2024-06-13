import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class GraphDemo {
    public static void main(String[] args) throws FileNotFoundException, Exception {

        FileInputStream file = new FileInputStream("./data/usa.graph");
        GraphProcessor use = new GraphProcessor();
        
        use.initialize(file);
        HashMap<String, Point> city = new HashMap<>(18);
        make(city);

        System.out.println(city.containsKey("New York NY"));

        Scanner input = new Scanner(System.in);
        System.out.println("Traveling from where?");

        System.out.println("Enter state & abbreviation (Format: 'New York NY')");


        String access = input.nextLine();
        Point starter = city.get(access);
        System.out.println("Where are you going to?");
        System.out.println("Enter state & abbreviation (in the format 'New York NY')");


        Point end = city.get(input.nextLine());
        Visualize creation = new Visualize("./data/usa.vis", "./images/usa.png");
        long starting = System.nanoTime();
        Point first = use.nearestPoint(starter);


        Point last = use.nearestPoint(end);
        List<Point> returner = use.route(first, last);
        System.out.println("Total Distance:" + " " + (int) use.routeDistance(returner) + " miles");
        long done = System.nanoTime() - starting;
        System.out.println("Total Computational Time:" + " " + done/1E6 + "ms");
        
        
        creation.drawRoute(returner);
        input.close();
    }

    private static void make(HashMap<String, Point> city) throws FileNotFoundException {
        FileInputStream add = new FileInputStream("./data/uscities.csv");

        Scanner input = new Scanner(add);

        String line = input.nextLine();
        String[] data = line.split(",");

        String a = data[0].substring(1, data[0].length()) + " " + data[1];
        city.put(a, new Point(Double.parseDouble(data[2]), Double.parseDouble(data[3])));

        while (input.hasNextLine()) {
            line = input.nextLine();

            data = line.split(",");
            String b = data[0] + " " + data[1];
            city.put(b, new Point(Double.parseDouble(data[2]), Double.parseDouble(data[3])));
        }

        input.close();
    }
}