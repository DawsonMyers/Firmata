package firmata;

import firmata.message.Message;

/**
 * Wait exception for FirmataWaiter
 */
public class WaitException extends Exception {

    private Class messageClass;

    public Class getMessageClass() {
        return messageClass;
    }

    public WaitException(Class<? extends Message> messageClass) {
        super("No expected incoming message");
        this.messageClass = messageClass;
    }
}
