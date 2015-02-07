package tests;

import firmata.message.ReportProtocolVersionMessage;
import  serial.SerialException;
import firmata.writer.ReportProtocolVersionMessageWriter;
import org.junit.Test;

import java.util.Arrays;

/**
 * Test for ReportProtocolVersionMessage
 */
public class ReportProtocolVersionMessageTest extends BaseFirmataTest {

    @Test
    public void testWrite() throws SerialException {
        serial.clear();
        firmata.send(new ReportProtocolVersionMessage());

        byte[] expected_output = new byte[] { (byte) ReportProtocolVersionMessageWriter.COMMAND };
        byte[] actual_output = serial.getOutputStream().toByteArray();

        assertTrue(Arrays.equals(expected_output, actual_output));
    }
}
