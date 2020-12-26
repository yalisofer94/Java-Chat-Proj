package Chat;

import java.io.*;
import java.net.*;

public class ConnectionProxy extends Thread implements StringConsumer, StringProducer
{
    private InputStream is = null;
    private OutputStream os = null;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    private Socket socket = null;

    public ConnectionProxy(Socket socket) {
        this.socket = socket;
        try {
            is = socket.getInputStream();
            os = socket.getOutputStream();
            dis = new DataInputStream(is);
            dos = new DataOutputStream(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void consume(StringProducer str) {

    }

    @Override
    public void addConsumer(StringConsumer sc) {

    }

    @Override
    public void removeConsumer(StringConsumer sc) {

    }
    //…
}