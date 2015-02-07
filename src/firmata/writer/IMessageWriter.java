package firmata.writer;

import firmata.message.Message;
import serial.ISerial;
import serial.SerialException;
/**
 * Message writer
 */
public interface IMessageWriter<ConcreteMessage extends Message> {

    /**
     * Write command to Serial
     */
    void write(ConcreteMessage message, ISerial serial) throws SerialException;
}
