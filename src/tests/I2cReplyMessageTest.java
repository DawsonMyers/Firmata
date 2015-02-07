package tests;

import firmata.message.I2cReplyMessage;
import  serial.SerialException;
import firmata.wrapper.MessageWithProperties;
import firmata.writer.SysexMessageWriter;
import org.junit.Test;

import static firmata.BytesHelper.*;

/**
 * Test for I2cReplyMessage
 */
public class I2cReplyMessageTest extends BaseFirmataTest {

    private static final int DATA_BYTE = 300;
    private final I2cReplyMessage originalMessage = new I2cReplyMessage(1, 2, new int[] { DATA_BYTE });

    @Test
    public void testRead() throws SerialException {
        byte[] output = new byte[] {
            (byte)SysexMessageWriter.COMMAND_START,
            (byte)I2cReplyMessage.COMMAND,

            // slave address
            (byte)1,
            (byte)0,

            // register
            (byte)2,
            (byte)0,

            // data
            (byte)LSB(DATA_BYTE),
            (byte)MSB(DATA_BYTE),

            (byte)SysexMessageWriter.COMMAND_END
        };

        serial.clear();
        feedToFirmata(output);

        MessageWithProperties actualMessage = historyFirmataWrapper.getLastReceivedMessageWithProperties();
        assertNotNull(actualMessage);
        assertEquals(originalMessage, actualMessage.getMessage());
    }
}
