package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrafficSimGUI extends JFrame implements ActionListener {
    final int ROWS = 28, COLUMNS = 28;
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
        setTitle("Traffic Simulator V2.0");
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setJMenuBar(menuBar);
        setLayout(new GridLayout(ROWS, COLUMNS));
        for (int i = 0; i < ROWS * COLUMNS; i++) {
            JPanel panel = new JPanel();
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
}
