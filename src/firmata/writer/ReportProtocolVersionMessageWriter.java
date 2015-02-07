package firmata.writer;

import firmata.message.ReportProtocolVersionMessage;
import  serial.ISerial;
import serial.SerialException;

/**
 * MessageWriter for ReportProtocolVersionMessage
 */
public class ReportProtocolVersionMessageWriter implements IMessageWriter<ReportProtocolVersionMessage> {

    public static final int COMMAND = 0xF9;

    public void write(ReportProtocolVersionMessage message, ISerial serial) throws SerialException {
        serial.write(COMMAND);
    }
}
