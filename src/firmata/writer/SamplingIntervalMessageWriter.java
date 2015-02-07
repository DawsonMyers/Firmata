package firmata.writer;

import firmata.message.SamplingIntervalMessage;
import  serial.ISerial;
import  serial.SerialException;

import static firmata.BytesHelper.*;

/**
 * MessageWriter for SamplingIntervalMessage
 */
public class SamplingIntervalMessageWriter extends SysexMessageWriter<SamplingIntervalMessage> {

    @Override
    protected void writeData(SamplingIntervalMessage message, ISerial serial) throws SerialException {
        // can not use super.writeData() because it works with String
        writeIntervalData(message, serial);
    }

    private void writeIntervalData(SamplingIntervalMessage message, ISerial serial) throws SerialException {
        byte[] buffer = new byte[2];

        buffer[0] = (byte)LSB(message.getInterval());
        buffer[1] = (byte)MSB(message.getInterval());

        serial.write(buffer);
    }

}
