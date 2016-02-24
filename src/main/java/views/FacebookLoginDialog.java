package views;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FacebookLoginDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel webViewJPanel;
    private WebEngine webEngine;

    JFXPanel javafxPanel;
    WebView webComponent;
    JPanel mainPanel;
    JTextField urlField;
    JButton goButton;


    public FacebookLoginDialog() {
        javafxPanel = new JFXPanel();
        initSwingComponents();
        loadJavaFXScene();

        /*setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        SwingFXWebView webView = new SwingFXWebView();

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);*/
    }

    private void onOK() {
// add your code here
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        /*FacebookLoginDialog dialog = new FacebookLoginDialog();

        final JFXPanel fxPanel = new JFXPanel();

        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);*/

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FacebookLoginDialog dialog = new FacebookLoginDialog();
                dialog.pack();
                dialog.setVisible(true);
            }
        });

    }

    private void initSwingComponents(){
        mainPanel = new JPanel();

        mainPanel.setLayout(new BorderLayout());

        mainPanel.add(javafxPanel, BorderLayout.CENTER);

        JPanel urlPanel = new JPanel(new FlowLayout());
        urlField = new JTextField();
        urlField.setColumns(50);
        urlPanel.add(urlField);
        goButton = new JButton("Go");
        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        String url = urlField.getText();
                        if ( url != null && url.length() > 0){
                            webComponent.getEngine().load(url);
                        }
                    }
                });

            }
        });
        urlPanel.add(goButton);
        mainPanel.add(urlPanel, BorderLayout.NORTH);

        this.add(mainPanel);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getPreferredSize();
    }

    private void loadJavaFXScene(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                BorderPane borderPane = new BorderPane();
                webComponent = new WebView();
                webComponent.getEngine().load("http://google.com/");
                borderPane.setCenter(webComponent);
                Scene scene = new Scene(borderPane,450,450);
                javafxPanel.setScene(scene);
            }
        });
    }



}
