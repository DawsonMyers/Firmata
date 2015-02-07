package firmata.writer;

import  firmata.message.AnalogMessage;
import   serial.ISerial;
import  serial.SerialException;

import static  firmata.BytesHelper.*;

/**
 * MessageWriter for AnalogMessage
 */
public class AnalogMessageWriter implements IMessageWriter<AnalogMessage> {

    public static final int COMMAND = 0xE0;

    public void write(AnalogMessage message, ISerial serial) throws SerialException {
        serial.write(COMMAND | ENCODE_CHANNEL(message.getPin()));
        serial.write(LSB(message.getValue()));
        serial.write(MSB(message.getValue()));
    }
}
