package firmata.writer;

import firmata.message.DigitalMessage;
import  serial.ISerial;
import  serial.SerialException;

import static firmata.BytesHelper.*;

/**
 * MessageWriter for DigitalMessage
 */
public class DigitalMessageWriter implements IMessageWriter<DigitalMessage> {

    public static final int COMMAND = 0x90;

    public void write(DigitalMessage message, ISerial serial) throws SerialException {
        serial.write(COMMAND | ENCODE_CHANNEL(message.getPort()));
        serial.write(LSB(message.getValue()));
        serial.write(MSB(message.getValue()));
    }
}
