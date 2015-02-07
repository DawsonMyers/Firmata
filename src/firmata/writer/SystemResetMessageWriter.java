package firmata.writer;

import firmata.message.SystemResetMessage;
import  serial.ISerial;
import  serial.SerialException;

/**
 * MessageWriter for SystemResetMessage
 */
public class SystemResetMessageWriter implements IMessageWriter<SystemResetMessage> {

    public static final int COMMAND = 0xFF;

    public void write(SystemResetMessage message, ISerial serial) throws SerialException {
        serial.write(COMMAND);
    }
}
