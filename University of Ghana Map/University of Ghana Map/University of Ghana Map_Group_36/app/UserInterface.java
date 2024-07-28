package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import static app.Main.graph;

public class UserInterface extends JFrame {

    private final JComboBox<String> sourceCombo;
    private final JComboBox<String> destinationCombo;
    private final JLabel shortestPathDisplay;
    private final JLabel distanceDisplay;
    private final JButton landMarkButton;

    UserInterface(){
        this.setTitle("Discovering available routes to your destination");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1300, 550);
        this.setLayout(null);
        this.setResizable(false);
        // this.getContentPane().setBackground(Color.lightGray);
         ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/app/images/Ug.png"));
         JLabel backgroundLabel = new JLabel(backgroundIcon);
         backgroundLabel.setBounds(350, -110, this.getWidth(), this.getHeight());
         this.add(backgroundLabel);

        JLabel currentLocation = new JLabel();
        currentLocation.setText("Present location:");
        currentLocation.setBounds(300, 50, 200, 40);
        currentLocation.setFont(new Font("Serif", Font.BOLD, 20));
        this.add(currentLocation);

        JLabel landMarkLocation = new JLabel();
        landMarkLocation.setText("Opt for a landmark:");
        landMarkLocation.setBounds(300, 300, 250, 40);
        landMarkLocation.setFont(new Font("Serif", Font.BOLD, 20));
        this.add(landMarkLocation);

        JLabel destinationLocation = new JLabel();
        destinationLocation.setText("Target destination:");
        destinationLocation.setBounds(300, 150, 200, 40);
        destinationLocation.setFont(new Font("Serif", Font.BOLD, 20));
        this.add(destinationLocation);

        String[] places =
                {"Main Gate","Law school", "Night Market", "UG Fire Service", "Computer Science Department", "Athletic Oval"
                        ,"Diaspora","Volta Hall","Akuafo Hall","Business School", "Legon Hall", "Great Hall",
                        "NNB", "N-Block", "Common Wealth Hall", "JQB", "Balme Library","UGCS", "Pent Hall"};

        sourceCombo = new JComboBox<>(places);
        sourceCombo.setBounds(550, 50, 200, 30);
        this.add(sourceCombo);

        destinationCombo = new JComboBox<>(places);
        destinationCombo.setBounds(550, 150, 200, 30);
        this.add(destinationCombo);

        JLabel info = new JLabel();
        info.setText("Shortest Route: ");
        info.setBounds(300, 400, 250, 40);
        info.setFont(new Font("Serif",Font.BOLD, 20));
        this.add(info);

        shortestPathDisplay = new JLabel();
        shortestPathDisplay.setBounds(550, 400, 600, 40);
        shortestPathDisplay.setBorder(new RoundedBorder(10));
        shortestPathDisplay.setFont(new Font("Serif",Font.BOLD, 15));
        this.add(shortestPathDisplay);

        distanceDisplay = new JLabel();
        distanceDisplay.setBounds(250, 470, 400, 40);
        distanceDisplay.setFont(new Font("Serif", Font.BOLD, 20));
        this.add(distanceDisplay);

        JButton getPossiblePaths = new JButton("Discover routes");
        getPossiblePaths.setBounds(580, 230, 130, 25);
        getPossiblePaths.setBorder(new RoundedBorder(10));
        getPossiblePaths.setFocusable(false);

        landMarkButton = new JButton("Landmark button");
        landMarkButton.setBounds(580, 300, 130, 25);
        landMarkButton.setFocusable(false);
        landMarkButton.setBorder(new RoundedBorder(10));

        this.add(getPossiblePaths);
        this.add(landMarkButton);
        this.setVisible(true);

        getPossiblePaths.addActionListener(this::getPaths);
        landMarkButton.addActionListener(this::landMarkPath);

    }

    private void getPaths(ActionEvent actionEvent) {
        try {
            String theOrigin = sourceCombo.getSelectedItem().toString();
            String theEnd = destinationCombo.getSelectedItem().toString();

            Locations sourceDijkstra = graph.getNodeByName(theOrigin);
            Locations destinationDijkstra = graph.getNodeByName(theEnd);

            Dijkstra.findShortestPath(graph, sourceDijkstra, destinationDijkstra);
            String path = Dijkstra.getShortestPath(sourceDijkstra, destinationDijkstra);
            shortestPathDisplay.setText(path);

            distanceDisplay.setText("         Estimated distance: " + "               " + Dijkstra.getTotalDistance(destinationDijkstra) + "km");
        }catch (NullPointerException exception){
            System.out.println(exception.getMessage());
        }
    }

    private void landMarkPath(ActionEvent actionEvent){

        if (actionEvent.getSource() == landMarkButton){
            this.dispose();
            new LandMarkPage();
        }
    }
}
