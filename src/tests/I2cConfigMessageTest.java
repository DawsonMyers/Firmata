package tests;

import firmata.message.I2cConfigMessage;
import  serial.SerialException;
import firmata.writer.SysexMessageWriter;
import org.junit.Test;

import java.util.Arrays;

import static firmata.BytesHelper.*;

/**
 * Test for I2cConfigMessage
 */
public class I2cConfigMessageTest extends BaseFirmataTest {

    @Test
    public void testWrite() throws SerialException {
        serial.clear();

        I2cConfigMessage message = new I2cConfigMessage();
        message.setOn(true);
        message.setDelay(300);
        firmata.send(message);

        byte[] expected_output = new byte[] {
            (byte)SysexMessageWriter.COMMAND_START,
            (byte)I2cConfigMessage.COMMAND,

            // on/off
            (byte)(message.isOn() ? 1 : 0),

            // delay
            (byte)LSB(message.getDelay()),
            (byte)MSB(message.getDelay()),

            (byte)SysexMessageWriter.COMMAND_END
        };
        byte[] actual_output = serial.getOutputStream().toByteArray();

        assertTrue(Arrays.equals(expected_output, actual_output));
    }
}
