package views;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import controllers.RESTClient;
import jdk.nashorn.internal.parser.JSONParser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Robert on 2016-02-10.
 */
public class MainContent extends JFrame {
    private JButton button1;
    private JTabbedPane tabbedPane1;
    private JPanel rootPanel;
    private JTable table1;
    private JButton FBLoginButton;

    private int counter = 0;

    public MainContent() {
        super("hello");

        setFrameParameters();

        String[] headers = new String[]{"Counter", "Content"};

        DefaultTableModel dtm = new DefaultTableModel();

        dtm.setColumnIdentifiers(headers);

        table1.setModel(dtm);
        RESTClient client = new RESTClient();

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counter++;

                String a = client.get("/api");

                JsonParser parser = new JsonParser();
                JsonArray ja = parser.parse(a).getAsJsonArray();

                for (int i = 0; i < ja.size(); i++) {
                    JsonObject jo = ja.get(i).getAsJsonObject();

                    dtm.addRow(new Object[]{jo.get("id"), jo.get("name")});
                }

            }
        });

        FBLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Fb login works fine");

                FacebookLoginDialog fblogin = new FacebookLoginDialog();

                fblogin.setSize(300,300);
                fblogin.setLocationRelativeTo(null);
                fblogin.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
                fblogin.setVisible(true);
            }
        });
    }

    public void setFrameParameters() {
        setContentPane(rootPanel);
        pack();

        setSize(500, 300);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setVisible(true);
    }
}
