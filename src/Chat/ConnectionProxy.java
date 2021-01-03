package Chat;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionProxy extends Thread implements StringConsumer, StringProducer
{
    private InputStream is = null;
    private OutputStream os = null;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    private Socket socket = null;
    private StringConsumer client;

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
    public void run() {
        String recieved = "";
        while(!socket.isClosed()){
            try {
                recieved = dis.readUTF();
                client.consume(recieved);
                if( recieved.equals("Leaving meeting")){
                    this.socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.removeConsumer(client);
    }

    @Override
    public void consume(String str) {
        try {
            dos.writeUTF(str);
        } catch (IOException e) {
        }
    }

    @Override
    public void addConsumer(StringConsumer sc) {
        client = sc;
    }

    @Override
    public void removeConsumer(StringConsumer sc) {
        client = null;

    }
}