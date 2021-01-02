package Chat;

import java.util.ArrayList;
import java.util.List;

public class MessageBoard implements StringConsumer, StringProducer
{
    private List<StringConsumer> proxies;

    public MessageBoard() {
        this.proxies = new ArrayList<>();
    }

    @Override
    public void consume(String str) {
        for(int i =0; i < proxies.size(); i++){
            proxies.get(i).consume(str);
        }
    }

    @Override
    public void removeConsumer(StringConsumer sc) {
        proxies.remove(sc);
    }

    @Override
    public void addConsumer(StringConsumer sc) {
        proxies.add(sc);
    }
}
