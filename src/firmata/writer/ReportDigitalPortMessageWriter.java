package firmata.writer;

import firmata.message.ReportDigitalPortMessage;
import  serial.ISerial;
import  serial.SerialException;

import static firmata.BytesHelper.ENCODE_CHANNEL;

/**
 * MessageWriter for ReportDigitalPortMessage
 */
public class ReportDigitalPortMessageWriter implements IMessageWriter<ReportDigitalPortMessage> {

    public static final int COMMAND = 0xD0;

    public void write(ReportDigitalPortMessage message, ISerial serial) throws SerialException {
        serial.write(COMMAND | ENCODE_CHANNEL(message.getPort()));
        serial.write(message.isEnable() ? 1 : 0);
    }
}
