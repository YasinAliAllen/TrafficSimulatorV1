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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TrafficSimGUI extends JFrame implements ActionListener {
    private final int ROWS = 16, COLUMNS = 32;
    private Map<String, String> imagesMap = new HashMap<>();
    private int rotations = 0;
    private String[] roadTypes = {"Road Straight", "Road Three Way", "Road Four Way"};
    private JLabel label = new JLabel("Status: Editor...");
    private ArrayList<ArrayList<ItemButton>> itemButtonsArray = new ArrayList<>();//array storing itemPanels
    private BufferedImage roadImage;
    private ArrayList<ItemButton> itemButtonRow = new ArrayList<>();
    private JComboBox<String> comboBox = new JComboBox<>(roadTypes);
    private JMenuItem save = new JMenuItem("Save");
    private JMenuItem load = new JMenuItem("Load");
    private JMenuItem exit = new JMenuItem("Exit");
    private JMenuItem run = new JMenuItem("Run");
    private JMenuItem simulation = new JMenuItem("Simulation");
    private JMenuItem editor = new JMenuItem("Editor");

    public TrafficSimGUI() {
        imagesMap.put("Road Straight0", ".\\Simulation\\View\\Images\\HorizontalRoad.jpg");
        imagesMap.put("Road Straight1", ".\\Simulation\\View\\Images\\VerticalRoad.jpg");
        imagesMap.put("Road Three Way0", ".\\Simulation\\View\\Images\\RoadThreeWayR0.jpg");
        imagesMap.put("Road Three Way1", ".\\Simulation\\View\\Images\\RoadThreeWayR1.jpg");
        imagesMap.put("Road Three Way2", ".\\Simulation\\View\\Images\\RoadThreeWayR2.jpg");
        imagesMap.put("Road Three Way3", ".\\Simulation\\View\\Images\\RoadThreeWayR3.jpg");
        imagesMap.put("Road Four Way", ".\\Simulation\\View\\Images\\RoadFourWay.jpg");
        loadImage(imagesMap.get("Road Straight" + rotations));
        setTitle("Traffic Simulator V2.0");
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 600);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        setLayout(new GridLayout(ROWS, COLUMNS));

        JMenu file = new JMenu("File");
        menuBar.add(file);
        JMenu settings = new JMenu("Settings");
        menuBar.add(settings);
        JMenu help = new JMenu("Help");
        menuBar.add(help);
        menuBar.add(label);

        comboBox.addActionListener(actionEvent -> {
            rotations = 0;
            switch (Objects.requireNonNull(comboBox.getSelectedItem()).toString()) {
                case "Road Straight":
                    loadImage(imagesMap.get("Road Straight" + rotations));
                    break;
                case "Road Three Way":
                    loadImage(imagesMap.get("Road Three Way" + rotations));
                    break;
                case "Road Four Way":
                    loadImage(imagesMap.get("Road Four Way"));
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
        JMenuItem about = new JMenuItem("About");
        help.add(about);

        createButtons();
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

    private void createButtons() {
        int rowEndPos = COLUMNS;
        for (int i = 0; i < ROWS * COLUMNS; i++) {
            ItemButton button = new ItemButton();

            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    switch (e.getButton()) {
                        case 1: //place or remove road
                            if (button.hasRoad()) {
                                button.setHasRoad(false);
                                button.setRoadType("", 0);
                                button.setNorthConnection(false);
                                button.setEastConnection(false);
                                button.setSouthConnection(false);
                                button.setWestConnection(false);
                                System.out.println("Road Removed!");
                                button.setIcon(null);
                                button.repaint();
                            } else {
                                System.out.println("Road Placed!");
                                button.setHasRoad(true);
                                button.setRoadType(Objects.requireNonNull(comboBox.getSelectedItem()).toString(), rotations);

                                ImageIcon image = new ImageIcon(roadImage.getScaledInstance(
                                        button.getWidth(), button.getHeight(), Image.SCALE_FAST));
                                button.setIcon(image);
                                button.repaint();
                            }
                            break;
                        case 2: //rotate road
                            System.out.println("Item Rotated");
                            if (!(Objects.requireNonNull(comboBox.getSelectedItem()).toString().equals("Four Four Way"))) {
                                if ((rotations == 1 && comboBox.getSelectedItem().toString().equals("Road Straight"))
                                        || (rotations == 3 &&
                                        comboBox.getSelectedItem().toString().equals("Road Three Way"))) {
                                    rotations = 0;
                                } else {
                                    rotations++;
                                }
                            }
                            loadImage(imagesMap.get(comboBox.getSelectedItem().toString() + rotations));
                            ImageIcon image = new ImageIcon(roadImage.getScaledInstance(
                                    button.getWidth(), button.getHeight(), Image.SCALE_FAST));
                            button.setIcon(image);
                            button.repaint();
                            break;
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) { //show a preview of the item being placed
                    if (!button.hasRoad()) {
                        super.mouseEntered(e);
                        ImageIcon image = new ImageIcon(roadImage.getScaledInstance(
                                button.getWidth(), button.getHeight(), Image.SCALE_FAST));
                        button.setIcon(image);
                        button.repaint();
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) { //remove the preview of the item being placed
                    if (!button.hasRoad()) {
                        super.mouseExited(e);
                        button.setIcon(null);
                        button.repaint();
                    }
                }
            });
            if (i < COLUMNS || itemButtonRow.isEmpty() || i == rowEndPos - 1 || i > ROWS * COLUMNS - COLUMNS) {
                //sets edge pieces
                button.setIsEndPiece(true);
            }
            itemButtonRow.add(button);
            if (i == rowEndPos - 1) {
                itemButtonsArray.add(itemButtonRow);
                itemButtonRow = new ArrayList<>();
                rowEndPos += COLUMNS;
            }
            add(button);
            repaint();
        }
    }

    public void updateStatus(String status) {
        label.setText("Status: " + status);
    }

    public ArrayList<ArrayList<ItemButton>> getItems() {
        return itemButtonsArray;
    }
}