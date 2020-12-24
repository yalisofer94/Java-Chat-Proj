package Chat;

import Chat.StringConsumer;

public interface StringProducer {

    public void addConsumer(StringConsumer sc);
    public void removeConsumer(StringConsumer sc);

}
