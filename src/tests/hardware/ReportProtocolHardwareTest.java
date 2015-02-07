package tests.hardware;

import firmata.FirmataWaiter;
import firmata.WaitException;
import firmata.message.ProtocolVersionMessage;
import firmata.message.ReportProtocolVersionMessage;
import serial.SerialException;
import org.junit.Test;

/**
 * Firmata protocol hardware test
 * (is NOT started by maven, should be started manually when the board is ready)
 */
public class ReportProtocolHardwareTest extends BaseHardwareTest {

    @Test
    public void testReportProtocol() throws WaitException, SerialException {
        firmata.send(new ReportProtocolVersionMessage());

        // wait ProtocolVersionMessage for 10 seconds max
        new FirmataWaiter(firmata).waitSeconds(10, ProtocolVersionMessage.class);
    }

}
