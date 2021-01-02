package Chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.SplittableRandom;


 public class ClientGUI implements StringConsumer, StringProducer
 {
 //  Chat fields
     private static final long serialVersionUID = 1L;
     private StringConsumer consumer;
     private String name = "";
     private Socket socket = null;

 //    Login chat
     private JFrame loginFrame;
     private JPanel loginPanel;
     private JLabel loginLabel;
     private JTextField logintf;
     private JButton enter;

     //    Real chat
     private JFrame chatFrame;
     private JPanel chatPanel;
     private JLabel chatLabel;
     private JTextField chattf;
     private JButton send;
     private JButton reset;
     private JTextArea ta;

     public ClientGUI() {
        init();
     }

     public void init(){
   //       Login init
        loginFrame = new JFrame("Login");
        //loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(400, 400);
        loginPanel = new JPanel();
        loginLabel = new JLabel("Enter name:");
        logintf = new JTextField(50);
        enter = new JButton("Enter");
        loginPanel.add(loginLabel);
        loginPanel.add(logintf);
        loginPanel.add(enter);
        loginFrame.getContentPane().add(BorderLayout.CENTER, loginPanel);
        loginFrame.setVisible(false);

         //       Chat init
         chatFrame = new JFrame("Chat Frame");
         //chatFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         chatFrame.setSize(800,500);
         chatPanel = new JPanel(); // the panel is not visible in output
         chatLabel = new JLabel("Enter Text");
         chattf = new JTextField(10); // accepts upto 10 characters
         send = new JButton("Send");
         reset = new JButton("Reset");
         ta = new JTextArea();
         ta.setEditable(false);
         chatPanel.add(chatLabel); // Components Added using Flow Layout
         chatPanel.add(chattf);
         chatPanel.add(send);
         chatPanel.add(reset);
         chatFrame.getContentPane().add(BorderLayout.SOUTH, chatPanel);
         chatFrame.getContentPane().add(BorderLayout.CENTER, ta);
         chatFrame.setVisible(false);

     }
     public void start() {
         loginFrame.setVisible(true);
         enter.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 name = logintf.getText();
                 try {
                     startChat();
                 } catch (IOException ioException) {
                     ioException.printStackTrace();
                 }
             }
         });
     }
     public void startChat() throws IOException {
         loginFrame.setVisible(false);
         chatFrame.setVisible(true);

         try {
             socket = new Socket("127.0.0.1", 1300);
             ConnectionProxy proxy = new ConnectionProxy(socket);
             addConsumer(proxy);
             proxy.addConsumer(this);
             proxy.consume(name);
             proxy.start();

             send.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     String Message = chattf.getText();
                     proxy.consume(Message);
                     chattf.setText("");
                 }
             });
             reset.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     chattf.setText("");
                 }
             });

             chatFrame.addWindowListener(new WindowAdapter() {
                 @Override
                 public void windowClosing(WindowEvent e) {
                     super.windowClosing(e);
                     try {
                         socket.close();
                     } catch (IOException ioException) {
                         ioException.printStackTrace();
                     }
                 }
             });
         } catch (IOException e) {
            e.printStackTrace();
         }
     }

     @Override
     public void consume(String str) {
         ta.append(str + '\n');
     }

     @Override
     public void addConsumer(StringConsumer sc) {
         consumer = sc;
     }

     @Override
     public void removeConsumer(StringConsumer sc) {
         consumer = null;
     }
 }
















//class ClientGUI {
//   public static void main(String args[]) {
//
//
//       //Login Page creation
//        JFrame frame = new JFrame("Login");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(800, 500);
//
//        // frame.setSize(300,300);
//        // frame.getContentPane().add(button); // Adds Button to content pane of frame
//
//
//        JPanel panel = new JPanel(); // the panel is not visible in output
//        JLabel label = new JLabel("Enter name:");
//        JTextField tf = new JTextField(50); // accepts upto 10 characters
//        JButton enter = new JButton("Enter");
//        panel.add(label);
//        panel.add(tf);
//        panel.add(enter);
//
//        frame.getContentPane().add(BorderLayout.CENTER, panel);
//        frame.setVisible(true);







       //Creating the Frame
//       JFrame frame = new JFrame("Chat Frame");
//       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//       frame.setSize(400, 400);


       //Creating the panel at bottom and adding components
//       JPanel panel = new JPanel(); // the panel is not visible in output
//       JLabel label = new JLabel("Enter Text");
//       JTextField tf = new JTextField(10); // accepts upto 10 characters
//       JButton send = new JButton("Send");
//       JButton reset = new JButton("Reset");
//       panel.add(label); // Components Added using Flow Layout
//       panel.add(tf);
//       panel.add(send);
//       panel.add(reset);

       // Text Area at the Center
//       JTextArea ta = new JTextArea();
//       ta.setEditable(false);

       //Adding Components to the frame.
//       frame.getContentPane().add(BorderLayout.SOUTH, panel);
//       frame.getContentPane().add(BorderLayout.CENTER, ta);
//       frame.setVisible(true);