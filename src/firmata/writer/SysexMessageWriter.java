package firmata.writer;

import firmata.message.SysexMessage;
import serial.ISerial;
import serial.SerialException;

import static firmata.BytesHelper.ENCODE_STRING;

/**
 * MessageWriter for SysexMessage and inheritors
 */
public class SysexMessageWriter<ConcreteSysexMessage extends SysexMessage> implements IMessageWriter<ConcreteSysexMessage> {

    public static final int COMMAND_START = 0xF0;
    public static final int COMMAND_END   = 0xF7;

    public void write(ConcreteSysexMessage message, ISerial serial) throws SerialException {
        serial.write(COMMAND_START);
        writeCommand(message, serial);
        writeData(message, serial);
        serial.write(COMMAND_END);
    }

    protected void writeCommand(ConcreteSysexMessage message, ISerial serial) throws SerialException {
        serial.write(message.getCommand());
    }

    protected void writeData(ConcreteSysexMessage message, ISerial serial) throws SerialException {
        if (message.getData() != null) {
            byte[] dataBytes = ENCODE_STRING(message.getData());
            serial.write(dataBytes);
        }
    }


}
