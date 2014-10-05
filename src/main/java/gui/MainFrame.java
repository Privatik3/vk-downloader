package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainFrame extends JFrame {
    private JTextField searchTextFild;
    private JButton searchButton;
    private JLabel labelSearchHelper;
    private JPanel rootPanel;
    private JTextArea textAreaLog;

    LogicProgram logic;

    public MainFrame(LogicProgram logic) throws HeadlessException {
        super("Anime dowloader");
        this.logic = logic;

        installComponent();
        createAndShowGUI();
    }

    private void installComponent() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
        rootPanel.setPreferredSize(new Dimension(300, 200));

        labelSearchHelper = new JLabel("Enter here your search request:");
        labelSearchHelper.setMaximumSize(new Dimension(500, 200));
        rootPanel.add(labelSearchHelper);

        searchTextFild = new JTextField();
        searchTextFild.setMaximumSize(new Dimension(500, 20));
        rootPanel.add(searchTextFild);

        searchButton = new JButton("Search");
        searchButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        rootPanel.add(searchButton);

        textAreaLog = new JTextArea();
        textAreaLog.setEditable(false);
        rootPanel.add(textAreaLog);
    }

    public void createAndShowGUI() {


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    logic.eventButtonClick(searchTextFild.getText());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        setContentPane(rootPanel);

        pack();
        setVisible(true);
    }

    public void printAnimeList(List<String[]> animeList) {

        textAreaLog.setText("");

        if (animeList.size() == 0) {
            textAreaLog.append("Извините по вашему запросу нечего не найдено");
        }
        else {
            textAreaLog.append("По вашему запросу найдено " + animeList.size() + " видео");
        }

    }
}
