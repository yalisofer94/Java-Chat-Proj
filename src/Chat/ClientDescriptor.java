package Chat;

import java.util.List;

public class ClientDescriptor implements StringConsumer, StringProducer
{
    private String name = "";
    private StringConsumer messageBoard;

    @Override
    public void consume(String str) {
        String received = str;
        if (name.equals("")) {
            this.name = str ;
            received = this.name + " has joined the conversation!";
        } else {
            received = this.name + ":" + str;
        }
        messageBoard.consume(received);
    }

    @Override
    public void addConsumer(StringConsumer sc) {
        messageBoard = sc;
    }

    @Override
    public void removeConsumer(StringConsumer sc) {
        messageBoard = null;
    }
}
