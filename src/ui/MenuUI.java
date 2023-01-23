package ui;

import menu.Food;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class MenuUI extends JFrame {
    private DefaultListModel<String> model = new DefaultListModel<>();
    private JList<String> list = new JList<>(model);

    public MenuUI() {
        setSize(1080, 720);
        setLayout(new BorderLayout());

        Food.addCategory("Test");

        JTextField categoryName = new JTextField(20);
        categoryName.setEditable(true);


        JButton addButton = new JButton("Aggiungi");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Food.addCategory(categoryName.getText());
                updateList();
            }
        });

        updateList();

        JPanel panel = new JPanel();
        panel.add(categoryName);
        panel.add(addButton);
        panel.add(list);

        add(panel);
        setVisible(true);
    }

    private void updateList() {
        model.clear();

        Set<String> keys = Food.foods.keySet();
        System.out.println(keys);

        for(String key: keys) {
            model.addElement(key);
        }
    }
}
