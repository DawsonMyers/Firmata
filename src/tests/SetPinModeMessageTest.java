package tests;

import firmata.message.SetPinModeMessage;
import  serial.SerialException;
import org.junit.Test;

import java.util.Arrays;

/**
 * Test for SetPinModeMessage
 */
public class SetPinModeMessageTest extends BaseFirmataTest {

    @Test
    // compare original impl output and new impl output
    public void testWrite() throws SerialException {
        serial.clear();

        for (int pin = 0; pin < PIN_MAX; pin++)
            for (int mode = 0; mode < SetPinModeMessage.PIN_MODE.SERVO.getMode() /* SERVO as max value*/; mode++) {

                // old Firmata impl
                serial.clear();
                originalFirmata.pinMode(pin, mode);
                final byte[] oldOutput = serial.getOutputStream().toByteArray();

                // new Firmata impl
                serial.clear();
                SetPinModeMessage message = new SetPinModeMessage(pin, mode);
                firmata.send(message);
                final byte[] newOutput = serial.getOutputStream().toByteArray();

                assertTrue(message.toString(), Arrays.equals(oldOutput, newOutput));
            }
    }
}
