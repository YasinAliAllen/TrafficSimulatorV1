package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TrafficSimGUI extends JFrame implements ActionListener {
    int rotations = 0;
    String[] roadTypes = {"Road Straight", "Road Three Way", "Road Four Way"};
    final int ROWS = 16, COLUMNS = 32;
    JLabel label = new JLabel("Status: Editor...");
    private ArrayList<ArrayList<ItemPanel>> itemPanelsArray = new ArrayList<>();//array storing itemPanels
    BufferedImage roadImage;
    private ArrayList<ItemPanel> itemPanelsRow = new ArrayList<>();
    JComboBox comboBox = new JComboBox(roadTypes);
    JMenuBar menuBar = new JMenuBar();
    JMenu file = new JMenu("File");
    JMenuItem save = new JMenuItem("Save");
    JMenuItem load = new JMenuItem("Load");
    JMenuItem exit = new JMenuItem("Exit");
    JMenu settings = new JMenu("Settings");
    JMenuItem run = new JMenuItem("Run");
    JMenuItem simulation = new JMenuItem("Simulation");
    JMenuItem editor = new JMenuItem("Editor");
    JMenu help = new JMenu("Help");
    JMenuItem about = new JMenuItem("About");

    public TrafficSimGUI() {

        loadImage(".\\Simulation\\View\\Images\\HorizontalRoad.jpg");
        setTitle("Traffic Simulator V2.0");
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 600);
        setJMenuBar(menuBar);
        setLayout(new GridLayout(ROWS, COLUMNS));

        menuBar.add(file);
        menuBar.add(settings);
        menuBar.add(help);
        menuBar.add(label);

        comboBox.addActionListener(actionEvent -> {
            rotations = 0;
            switch (comboBox.getSelectedItem().toString()) {
                case "Road Straight":
                    loadImage(".\\Simulation\\View\\Images\\HorizontalRoad.jpg");
                    break;
                case "Road Three Way":
                    loadImage(".\\Simulation\\View\\Images\\RoadThreeWayR0.jpg");
                    break;
                case "Road Four Way":
                    loadImage(".\\Simulation\\View\\Images\\RoadFourWay.jpg");
                    break;
            }
        });

        menuBar.add(comboBox);

        save.addActionListener(this);
        file.add(save);
        load.addActionListener(this);
        file.add(load);
        exit.addActionListener(this);
        file.add(exit);
        run.addActionListener(this);
        settings.add(run);
        simulation.addActionListener(this);
        settings.add(simulation);
        editor.addActionListener(this);
        settings.add(editor);
        help.addActionListener(this);
        help.add(about);

        createPanels();
    }

    public void addSaveActionListener(ActionListener actionListener) {
        save.addActionListener(actionListener);
    }

    public void addLoadActionListener(ActionListener actionListener) {
        load.addActionListener(actionListener);
    }

    public void addExitActionListener(ActionListener actionListener) {
        exit.addActionListener(actionListener);
    }

    public void addRunActionListener(ActionListener actionListener) {
        run.addActionListener(actionListener);
    }

    public void addSimulationActionListener(ActionListener actionListener) {
        simulation.addActionListener(actionListener);
    }

    public void addEditorActionListener(ActionListener actionListener) {
        editor.addActionListener(actionListener);
    }

    public void addAboutActionListener(ActionListener actionListener) {
        load.addActionListener(actionListener);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        repaint();
    }


    private void loadImage(String path) {
        try {
            roadImage = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createPanels() {
        int rowEndPos = COLUMNS;
        for (int i = 0; i < ROWS * COLUMNS; i++) {
            ItemPanel panel = new ItemPanel();
            JLabel label = new JLabel();
            label.setVisible(true);
            panel.add(label);
            panel.setBackground(Color.GREEN);
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    switch (e.getButton()) {
                        case 1: //place or remove road
                            if (panel.hasRoad()) {
                                panel.setHasRoad(false);
                                System.out.println("Road Removed!");
                                label.setIcon(null);
                                panel.repaint();
                            } else {
                                System.out.println("Road Placed!");
                                panel.setHasRoad(true);
                                panel.setRoadType(comboBox.getSelectedItem().toString(), rotations);

                                ImageIcon image = new ImageIcon(roadImage.getScaledInstance(
                                        panel.getWidth(), panel.getHeight(), Image.SCALE_FAST));
                                label.setIcon(image);
                                panel.repaint();
                            }
                            break;
                        case 2: //rotate road
                            System.out.println("Item Rotated");
                            switch (comboBox.getSelectedItem().toString()) {
                                case "Road Straight":
                                    if (rotations == 0) {
                                        loadImage(".\\Simulation\\View\\Images\\VerticalRoad.jpg");
                                        ImageIcon image = new ImageIcon(roadImage.getScaledInstance(
                                                panel.getWidth(), panel.getHeight(), Image.SCALE_FAST));
                                        label.setIcon(image);
                                        panel.repaint();
                                        rotations++;
                                    } else {
                                        if (rotations == 1) {
                                            loadImage(".\\Simulation\\View\\Images\\HorizontalRoad.jpg");
                                            ImageIcon image = new ImageIcon(roadImage.getScaledInstance(
                                                    panel.getWidth(), panel.getHeight(), Image.SCALE_FAST));
                                            label.setIcon(image);
                                            panel.repaint();
                                            rotations = 0;
                                        }
                                    }
                                    break;
                                case "Road Three Way":
                                    if (rotations == 0) {
                                        loadImage(".\\Simulation\\View\\Images\\RoadThreeWayR0.jpg");
                                        ImageIcon image = new ImageIcon(roadImage.getScaledInstance(
                                                panel.getWidth(), panel.getHeight(), Image.SCALE_FAST));
                                        label.setIcon(image);
                                        panel.repaint();
                                        rotations++;
                                    } else if (rotations == 1) {
                                        loadImage(".\\Simulation\\View\\Images\\RoadThreeWayR1.jpg");
                                        ImageIcon image = new ImageIcon(roadImage.getScaledInstance(
                                                panel.getWidth(), panel.getHeight(), Image.SCALE_FAST));
                                        label.setIcon(image);
                                        panel.repaint();
                                        rotations++;
                                    } else if (rotations == 2) {
                                        loadImage(".\\Simulation\\View\\Images\\RoadThreeWayR2.jpg");
                                        ImageIcon image = new ImageIcon(roadImage.getScaledInstance(
                                                panel.getWidth(), panel.getHeight(), Image.SCALE_FAST));
                                        label.setIcon(image);
                                        panel.repaint();
                                        rotations++;
                                    } else if (rotations == 3) {
                                        loadImage(".\\Simulation\\View\\Images\\RoadThreeWayR3.jpg");
                                        ImageIcon image = new ImageIcon(roadImage.getScaledInstance(
                                                panel.getWidth(), panel.getHeight(), Image.SCALE_FAST));
                                        label.setIcon(image);
                                        panel.repaint();
                                        rotations = 0;
                                    }
                                    break;
                            }
                            break;
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) { //show a preview of the item being placed
                    if (!panel.hasRoad()) {
                        super.mouseEntered(e);
                        ImageIcon image = new ImageIcon(roadImage.getScaledInstance(
                                panel.getWidth(), panel.getHeight(), Image.SCALE_FAST));
                        label.setIcon(image);
                        panel.repaint();
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) { //remove the preview of the item being placed
                    if (!panel.hasRoad()) {
                        super.mouseExited(e);
                        label.setIcon(null);
                        panel.repaint();
                    }
                }
            });
            if (i < COLUMNS || itemPanelsRow.isEmpty() || i == rowEndPos - 1 || i > ROWS * COLUMNS - COLUMNS) {
                //sets edge pieces
                panel.setIsEndPiece(true);
            }
            itemPanelsRow.add(panel);
            if (i == rowEndPos - 1) {
                itemPanelsArray.add(itemPanelsRow);
                itemPanelsRow = new ArrayList<>();
                rowEndPos += COLUMNS;
            }
            add(panel);
            repaint();
        }
    }

    public void updateStatus(String status) {
        label.setText("Status: " + status);
    }

    public ArrayList<ArrayList<ItemPanel>> getItems() {
        return itemPanelsArray;
    }
}