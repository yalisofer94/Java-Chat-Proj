package Chat;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;

public class DemoGUI {
    public static void main(String args[]) {

        try {
            Socket socket = new Socket("127.0.0.1", 1300);
            ConnectionProxy proxy = new ConnectionProxy(socket);
            ClientGUI gui = new ClientGUI();
            proxy.addConsumer(gui);
            gui.addConsumer(proxy);
            proxy.start();
            gui.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                ClientGUI gui = new ClientGUI();
                //gui.init();
            }
        };
        SwingUtilities.invokeLater(runnable);
    }
}
