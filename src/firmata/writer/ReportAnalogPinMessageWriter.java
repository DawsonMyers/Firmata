package firmata.writer;

import firmata.message.ReportAnalogPinMessage;
import  serial.ISerial;
import  serial.SerialException;

import static firmata.BytesHelper.ENCODE_CHANNEL;

/**
 * MessageWriter for ReportAnalogPinMessage
 */
public class ReportAnalogPinMessageWriter implements IMessageWriter<ReportAnalogPinMessage> {

    public static final int COMMAND = 0xC0;

    public void write(ReportAnalogPinMessage message, ISerial serial) throws SerialException {
        serial.write(COMMAND | ENCODE_CHANNEL(message.getPin()));
        serial.write(message.isEnable() ? 1 : 0);
    }
}
