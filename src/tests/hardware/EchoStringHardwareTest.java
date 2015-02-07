package tests.hardware;

import firmata.FirmataWaiter;
import firmata.WaitException;
import firmata.message.StringSysexMessage;
import  serial.SerialException;
import firmata.wrapper.MessageWithProperties;
import org.junit.Test;

/**
 * Test to receive the string sent
 * (upload EchoString firmware to the board)
 */
public class EchoStringHardwareTest extends BaseHardwareTest {

    private static final String STRING = "ping";
    
    @Test
    public void testEcho() throws WaitException, SerialException {
        firmata.send(new StringSysexMessage(STRING));
        new FirmataWaiter(firmata).waitSeconds(10, StringSysexMessage.class);

        MessageWithProperties message = historyFirmataWrapper.getLastReceivedMessageWithProperties();
        assertEquals(STRING, ((StringSysexMessage) message.getMessage()).getData());
    }
}
