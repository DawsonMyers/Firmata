package tests.hardware;

import firmata.FirmataWaiter;
import firmata.WaitException;
import firmata.message.DigitalMessage;
import firmata.message.ReportDigitalPortMessage;
import  serial.SerialException;
import org.junit.Test;

/**
 * Firmata digital port read hardware test
 * (is NOT started by maven, should be started manually when the board is ready)
 */
public class ReadDigitalPortHardwareTest extends BaseHardwareTest {

    public static final int PORT = 0;

    @Test
    public void testReportDigitalPort() throws WaitException, SerialException {
        // disabled port reporting
        firmata.send(new ReportDigitalPortMessage(PORT, true));

        // wait incoming DigitalMessage for 5 seconds max
        new FirmataWaiter(firmata).waitSeconds(5, DigitalMessage.class);
    }

    private void disablePortReporting() throws SerialException {
        firmata.send(new ReportDigitalPortMessage(PORT, false));
    }

    @Override
    protected void tearDown() throws Exception {
        disablePortReporting();
        super.tearDown();
    }
}
