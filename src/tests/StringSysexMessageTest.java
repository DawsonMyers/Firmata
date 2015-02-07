package tests;

import firmata.message.StringSysexMessage;
import  serial.SerialException;
import firmata.wrapper.MessageWithProperties;
import org.junit.Test;

/**
 * Test for StringSysexMessage
 */
public class StringSysexMessageTest extends BaseFirmataTest {

    private final StringSysexMessage originalMessage = new StringSysexMessage("abc");

    @Test
    public void testWriteRead() throws SerialException {
        serial.clear();

        firmata.send(originalMessage);
        byte[] output = serial.getOutputStream().toByteArray();

        serial.clear();
        feedToFirmata(output);

        MessageWithProperties actualMessage = historyFirmataWrapper.getLastReceivedMessageWithProperties();
        assertNotNull(actualMessage);
        assertEquals(originalMessage, actualMessage.getMessage());
    }
}
