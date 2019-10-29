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

public class TrafficSimGUI extends JFrame implements ActionListener {
    BufferedImage roadImage;


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
        loadImage();
        setTitle("Traffic Simulator V2.0");
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 600);
        final int ROWS = 16, COLUMNS = 32;
        setJMenuBar(menuBar);
        setLayout(new GridLayout(ROWS, COLUMNS));
        for (int i = 0; i < ROWS * COLUMNS; i++) {
            ItemPanel panel = new ItemPanel();
            JLabel label = new JLabel();
            label.setVisible(true);
            panel.add(label);
            panel.setBackground(Color.ORANGE);
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    switch (e.getButton()) {
                        case 0:
                            break;
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    ImageIcon image = new ImageIcon(roadImage.getScaledInstance(
                            panel.getWidth(), panel.getHeight(), Image.SCALE_FAST));
                    label.setIcon(image);
                    panel.repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    label.setIcon(null);
                    panel.repaint();
                }
            });
            repaint();
            add(panel);
        }
        menuBar.add(file);
        menuBar.add(settings);
        menuBar.add(help);

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


    public void loadImage() {
        try {
            roadImage = ImageIO.read(new File(".\\Simulation\\View\\Images\\yeet.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
