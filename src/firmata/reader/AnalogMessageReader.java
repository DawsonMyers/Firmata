package firmata.reader;

import firmata.IFirmata;
import firmata.message.AnalogMessage;
import firmata.writer.AnalogMessageWriter;

import static firmata.BytesHelper.DECODE_BYTE;
import static firmata.BytesHelper.DECODE_CHANNEL;

/**
 * MessageReader for AnalogMessage
 */
public class AnalogMessageReader implements IMessageReader<AnalogMessage> {

    public boolean canRead(byte[] buffer, int bufferLength, int command) {
        return command == AnalogMessageWriter.COMMAND;
    }

    private boolean isHandling;

    public void startReading() {
        isHandling = true;
        message = new AnalogMessage();
    }

    public void read(byte[] buffer, int length) {
        if (length == 2) {
            message.setPin(DECODE_CHANNEL(buffer[0]));
        } else {
            message.setValue(DECODE_BYTE(buffer[1], buffer[2]));
            isHandling = false;
        }
    }

    public boolean finishedReading() {
        return !isHandling;
    }

    private AnalogMessage message;

    public AnalogMessage getMessage() {
        return message;
    }

    public void fireEvent(IFirmata.Listener listener) {
        listener.onAnalogMessageReceived(getMessage());
    }
}
