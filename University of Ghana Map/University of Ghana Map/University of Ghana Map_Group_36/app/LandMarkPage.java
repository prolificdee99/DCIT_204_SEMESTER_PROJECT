package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import static app.Main.graph;

public class LandMarkPage extends JFrame {

    private final JButton backButton;
    private final JComboBox<String> sourceCombo;
    private final JComboBox<String> landMarkCombo;
    private final JComboBox<String> destinationCombo;
    private final JLabel firstHalfPath;
    private final JLabel secondHalfPath;
    private final JLabel distanceDisplay;

    LandMarkPage(){

        backButton = new JButton();
        backButton.setText("Privious");
        backButton.setBounds(20,600,100,30);
        backButton.setFocusable(false);
        backButton.setBorder(new RoundedBorder(5));
        backButton.addActionListener(this::mainPage);
        this.add(backButton);

        JLabel currentLocation = new JLabel();
        currentLocation.setText("Present location:");
        currentLocation.setBounds(300, 50, 200, 40);
        currentLocation.setFont(new Font("Serif", Font.BOLD, 20));
        this.add(currentLocation);

        JLabel landMarkLocation = new JLabel();
        landMarkLocation.setText("Desired landmark:");
        landMarkLocation.setBounds(300, 150, 250, 40);
        landMarkLocation.setFont(new Font("Serif", Font.BOLD, 20));
        this.add(landMarkLocation);

        JLabel destinationLocation = new JLabel();
        destinationLocation.setText("Target destination:");
        destinationLocation.setBounds(300, 250, 200, 40);
        destinationLocation.setFont(new Font("Serif", Font.BOLD, 20));
        this.add(destinationLocation);

        String[] places =
                {"Common wealth hall", "Main Gate","Law school", "Night Market", "UG Fire Service", "CS Department", "Athletic Oval"
                        ,"Diaspora","Volta Hall","Akuafo Hall","Business School", "Legon Hall", "Great Hall",
                        "NNB", "N Block", "JQB", "Balme+ Library","UGCS", "Pentagon Hall"};

        sourceCombo = new JComboBox<>(places);
        sourceCombo.setBounds(550, 50, 200, 30);
        this.add(sourceCombo);

        landMarkCombo = new JComboBox<>(places);
        landMarkCombo.setBounds(550,150,200,30);
        this.add(landMarkCombo);

        destinationCombo = new JComboBox<>(places);
        destinationCombo.setBounds(550, 250, 200, 30);
        this.add(destinationCombo);

        JLabel initialPath = new JLabel();
        initialPath.setText("Initial path: ");
        initialPath.setBounds(300, 400, 250, 40);
        initialPath.setFont(new Font("Serif",Font.BOLD, 20));
        this.add(initialPath);

        JLabel secondPath = new JLabel();
        secondPath.setText("Final path: ");
        secondPath.setBounds(300, 500, 250, 40);
        secondPath.setFont(new Font("Serif",Font.BOLD, 20));
        this.add(secondPath);

        firstHalfPath = new JLabel();
        firstHalfPath.setBounds(450, 400, 730, 40);
        firstHalfPath.setBorder(new RoundedBorder(10));
        firstHalfPath.setFont(new Font("Serif",Font.BOLD, 15));
        this.add(firstHalfPath);

        secondHalfPath = new JLabel();
        secondHalfPath.setBounds(450, 500, 730, 40);
        secondHalfPath.setBorder(new RoundedBorder(10));
        secondHalfPath.setFont(new Font("Serif",Font.BOLD, 15));
        this.add(secondHalfPath);

        distanceDisplay = new JLabel();
        distanceDisplay.setBounds(250, 600, 400, 40);
        distanceDisplay.setFont(new Font("Serif", Font.BOLD, 20));
        this.add(distanceDisplay);

        JButton getPossiblePaths = new JButton("Get possible paths");
        getPossiblePaths.setBounds(580, 350, 130, 25);
        getPossiblePaths.setBorder(new RoundedBorder(10));
        getPossiblePaths.setFocusable(false);
        this.add(getPossiblePaths);

        getPossiblePaths.addActionListener(this::getPaths);

        this.setLayout(null);
        this.setSize(1300,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.yellow);
        this.setTitle(" Discovering available routes using a specified landmark");
        this.setVisible(true);
    }

    // Handles switching from landmark page to UI page.
    private void mainPage(ActionEvent actionEvent){
        if (actionEvent.getSource() == backButton){
            this.dispose();
            new UserInterface();
        }
    }

    // Prints paths and total distance.
    private void getPaths(ActionEvent actionEvent) {
        try {
            String theOrigin = sourceCombo.getSelectedItem().toString();
            String theLandMark = landMarkCombo.getSelectedItem().toString();
            String theEnd = destinationCombo.getSelectedItem().toString();

            // Gets item of source, landmark and destination.
            Locations sourceDijkstra = graph.getNodeByName(theOrigin);
            Locations landMarkDijkstra = graph.getNodeByName(theLandMark);
            Locations destinationDijkstra = graph.getNodeByName(theEnd);

            //  Print path from source to landmark and landmark to destination.

            // Finds path and get distance between source and landmark.
            Dijkstra.findShortestPath(graph, sourceDijkstra, landMarkDijkstra);
            String firstPath = Dijkstra.getShortestPath(sourceDijkstra, landMarkDijkstra);
            float firstPathDistance = Dijkstra.getTotalDistance(landMarkDijkstra);

            // Finds path and get distance between landmark and destination.
            Dijkstra.findShortestPath(graph, landMarkDijkstra, destinationDijkstra);
            String secondPath = Dijkstra.getShortestPath(landMarkDijkstra, destinationDijkstra);
            float secondPathDistance = Dijkstra.getTotalDistance(destinationDijkstra);

            float totalPathDistance = firstPathDistance + secondPathDistance;

            firstHalfPath.setText(firstPath);
            secondHalfPath.setText(secondPath);

            distanceDisplay.setText("         Approximate distance: " + "           " + totalDistance(totalPathDistance) + "km");
        }catch (NullPointerException exception){
            System.out.println(exception.getMessage());
        }
    }
    private static String totalDistance(float total){
        return String.format("%.3f", total);
    }

}
