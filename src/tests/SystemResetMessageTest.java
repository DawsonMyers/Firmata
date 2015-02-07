package tests;

import firmata.message.SystemResetMessage;
import  serial.SerialException;
import firmata.writer.SystemResetMessageWriter;
import org.junit.Test;

import java.util.Arrays;

/**
 * Test for SystemResetMessage
 */
public class SystemResetMessageTest extends BaseFirmataTest {

    private final SystemResetMessage message = new SystemResetMessage();

    @Test
    public void testWrite() throws SerialException {
        serial.clear();
        firmata.send(message);

        byte[] expectedOutput = new byte[] { (byte) SystemResetMessageWriter.COMMAND };
        byte[] actualOutput = serial.getOutputStream().toByteArray();

        assertTrue(Arrays.equals(expectedOutput, actualOutput));
    }
}
