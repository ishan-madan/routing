 # $\textsf{\color{lime} Routing}$

## $\textsf{\color{purple} Description}$
This project consists of several Java classes designed to provide functionality for graphics drawing, geographical calculations, and graph operations.

## $\textsf{\color{purple} Class Overview}$

### 1. StdDraw.java
**Overview:** Provides a simple graphics library for drawing shapes, lines, text, and handling mouse and keyboard interactions.
**Key Features:**
- Drawing basic shapes: squares, circles, polygons.
- Setting pen color and radius.
- Handling mouse events (clicks, movements) and keyboard events (presses).
- Loading and displaying images.
- Buffering and displaying graphics.


### 2. Point.java
**Overview:** Represents an immutable latitude-longitude coordinate point on Earth.
**Key Features:**
- Calculates straight-line distance between points using the Haversine formula.
- Implements Comparable interface for sorting.
- Overrides equals(), hashCode(), toString(), and compareTo() methods for proper object comparison and printing.

### 3. GraphProcessor.java
**Overview:** Manages a weighted graph of latitude-longitude points and supports various distance and routing operations.
**Key Features:**
- Reads graph data from a .graph file to initialize points and edges.
- Finds the nearest point to a given point in the graph.
- Calculates the total distance along a route of points in the graph.
- Checks connectivity between two points in the graph.
- Computes the shortest path between two points using Dijkstra's algorithm.
- Throws exceptions for invalid input or unreachable destinations.

### 4. GraphDemo.java
**Overview:** Demonstrates the usage of GraphProcessor and Point classes in a practical scenario.
**Key Features:**
- Reads input data from usa.graph and uscities.csv to populate graph data.
- Prompts user for starting and ending points via console input.
- Uses visualization (assumed Visualize class) to draw routes on a map.
- Measures and displays computational time for route calculation.

## $\textsf{\color{purple} Languages and Utilities Used}$
- Java
- Data Structures and Agorithms

# $\textsf{\color{lime} Autocomplete Program Walkthrough}$

## $\textsf{\color{purple} Cloning the Repository}$

1. **Clone the Repository:**
   - Open your terminal or command prompt.
   - Navigate to the directory where you want to clone the repository.
   - Run the following command:
     ```
     git clone <repository_url>
     ```
     Replace `<repository_url>` with the actual URL of your GitHub repository.

## $\textsf{\color{purple} Setting Up the Environment}$

2. **Environment Setup:**
   - Ensure you have Java installed on your system. You can check by running:
     ```
     java -version
     ```
   - Install any necessary IDE (Integrated Development Environment) such as IntelliJ IDEA, Eclipse, or use a text editor like Visual Studio Code.

## $\textsf{\color{purple} Program Walkthrough}$

3. **Program Walkthrough:**
   - **Step 1: Starting the Program**
     - Open the project folder, navigate to the `src` folder, open the `GraphDemo.java` file, and run the `main` method.
   <p align="center">
   <img src="ReadMe%20Images/step1.png" height="60%" width="60%" alt="Image Analysis Dataflow"/>
   </p>

   - **Step 2: Input Origin Location**
     - Input the origin location in the City State format in the terminal (e.g. New York NY)
   <p align="center">
   <img src="ReadMe%20Images/step2.png" height="60%" width="60%" alt="Image Analysis Dataflow"/>
   </p>

   - **Step 3: Input Destination Location**
     - Input the destination location in the City State format in the terminal (e.g. New York NY)
   <p align="center">
   <img src="ReadMe%20Images/step3.png" height="60%" width="60%" alt="Image Analysis Dataflow"/>
   </p>

   - **Step 4: Routing**
     - Watch as the program maps your route from the origin to the destination!
   <p align="center">
   <img src="ReadMe%20Images/step4.png" height="60%" width="60%" alt="Image Analysis Dataflow"/>
   </p>



