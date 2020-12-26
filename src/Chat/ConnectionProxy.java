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
    List<StringConsumer> clients;

    public ConnectionProxy(Socket socket) {
        this.socket = socket;
        try {
            clients = new ArrayList<>();
            is = socket.getInputStream();
            os = socket.getOutputStream();
            dis = new DataInputStream(is);
            dos = new DataOutputStream(os);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(socket!=null) {
                try {
                    socket.close();
                } catch(IOException e) {}
            }
            if(dis!=null) {
                try {
                    dis.close();
                } catch(IOException e) {}
            }
            if(dos!=null) {
                try {
                    dos.close();
                } catch(IOException e) {}
            }
        }
    }

    @Override
    public void run() {
        while(true){
            try {
                String recieved = "";
                recieved = dis.readUTF();
                if(recieved != ""){
                    for (int i = 0 ; i < clients.size(); i++){
                        clients.get(i).consume(recieved);
                    }
                    recieved = "";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void consume(String str) {
        try {
            dos.writeUTF(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addConsumer(StringConsumer sc) {
        clients.add(sc);
    }

    @Override
    public void removeConsumer(StringConsumer sc) {
        clients.remove(sc);
    }
}