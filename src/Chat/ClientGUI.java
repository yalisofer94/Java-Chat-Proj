package Chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Scanner;

public class ClientGUI extends Thread implements StringConsumer, StringProducer
{
    private static final long serialVersionUID = 1L;
    private StringConsumer consumer;
    private String name = "";
    private JFrame frame;
    private JPanel panelOne, panelTwo;
    private JButton btSend;
    private JTextArea ta1,ta2;

    public ClientGUI(String nam) {
        this.name = nam;
        frame = new JFrame("SimpleGUI Demo");
        panelOne = new JPanel();
        panelTwo = new JPanel();
        btSend = new JButton("Send!");
        ta1 = new JTextArea(10,20);
        ta2 = new JTextArea(10,20);
    }
    public void init(){
        frame.setLayout(new BorderLayout());
        panelOne.setLayout(new GridLayout(2,2));
        panelTwo.setLayout(new FlowLayout());
        panelOne.add(ta1);
        panelTwo.add(btSend);
        panelTwo.add(ta2);
        frame.add(panelOne,BorderLayout.NORTH);
        frame.add(panelTwo,BorderLayout.SOUTH);

        btSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tmp = ta1.getText();
                System.out.println(tmp);
            }
        });

        //handling the jframe closing
        frame.addWindowListener(new WindowAdapter() {
            /**
             * Invoked when a window is in the process of being closed.
             * The close operation can be overridden at this point.
             *
             * @param e
             */
            @Override
            public void windowClosing(WindowEvent e) {
                //super.windowClosing(e);
                //frame.setVisible(false);
                //frame.dispose();
                System.exit(0);
            }
        });
        //setting the jframe size
        frame.setSize(800,400);

        //turning on the jframe visibility
        frame.setVisible(true);
    }

    @Override
    public void consume(String str) {
        System.out.println(str);
    }
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String a = scanner.nextLine();
            consumer.consume(a);
        }
    }


    @Override
    public void addConsumer(StringConsumer sc) {
        consumer = sc;
    }

    @Override
    public void removeConsumer(StringConsumer sc) {

    }
}
