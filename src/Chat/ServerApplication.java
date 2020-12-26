package Chat;

import java.net.*;
import java.io.*;

public class ServerApplication
{
    public static void main(StringProducer args[])
    {
        ServerSocket server = null;
        MessageBoard mb = new MessageBoard();
        try
        {
            server = new ServerSocket(1300,5);
        }
        catch(IOException e)
        {
        }
        Socket socket = null;
        ClientDescriptor client = null;
        ConnectionProxy connection = null;

        while(true)
        {
            try
            {
                socket = server.accept();
                connection =new ConnectionProxy(socket);
                client = new ClientDescriptor();
                connection.addConsumer(client);
                client.addConsumer(mb);
                mb.addConsumer(connection);
                connection.start();
            }
            catch(IOException e)
            {
            }
        }
    }


}




