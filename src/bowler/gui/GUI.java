package bowler.gui;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class GUI extends JFrame {

    private DefaultListModel model = new DefaultListModel();
    private JList<String> players;

    public GUI() {
        setLayout(new MigLayout("", "[grow,fill]", "[grow,fill]"));
        setSize(500, 500);
        players = new JList<>(model);
        add(players);
    }

    public void addPlayer(String player) {
        model.addElement(player);
    }

    public void removePlayer(String player) {
        if(model.contains(player)) {
            model.removeElement(player);
        }
        else {
            System.out.println("ELEMENT DOESN'T EXIST");
        }
    }
}
