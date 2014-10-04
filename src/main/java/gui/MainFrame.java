package gui;

import javafx.beans.binding.StringBinding;

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
    private JTextArea textAreaAnimeList;

    LogicProgram logic;

    public MainFrame(LogicProgram logic) throws HeadlessException {
        super("Anime dowloader");
        this.logic = logic;

        createAndShowGUI();
    }

    private void createAndShowGUI() {


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

        textAreaAnimeList.setText("");

        if (animeList.size() == 0) {
            textAreaAnimeList.append("Извините по вашему запросу нечего не найдено");
        }

        int index = 1;
        for (String[] anime : animeList) {
            String newline = " " + index + ". " + anime[0] + "\n";
            textAreaAnimeList.append(newline);

            index++;
        }
    }
}
