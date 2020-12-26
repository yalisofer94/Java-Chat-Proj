package Chat;

import java.util.List;

public class ClientDescriptor implements StringConsumer, StringProducer
{
    private String name;
    private List<StringConsumer> messageBoards;

    @Override
    public void consume(String str) {
        String received = str;
        StringBuffer sb = new StringBuffer();
        sb.append("Message from ");
        sb.append(this.name);
        sb.append(": ");
        sb.append(received);
        String finalMessage = sb.toString();
        for(int i = 0; i < messageBoards.size(); i++){
            messageBoards.get(i).consume(finalMessage);
        }
    }

    @Override
    public void addConsumer(StringConsumer sc) {
        messageBoards.add(sc);
    }

    @Override
    public void removeConsumer(StringConsumer sc) {
        messageBoards.remove(sc);
    }

    //…
}
