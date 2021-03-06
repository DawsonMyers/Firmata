package tests;

import firmata.message.SetPinModeMessage;
import firmata.message.factory.BoardMessageFactory;
import firmata.message.factory.MessageValidationException;
import firmata.message.factory.arduino.Duemilanove;
import  serial.SerialException;
import org.junit.Test;

/**
 * MessageFactory validation test
 */
public class MessageValidationTest extends BaseFirmataTest {

    private BoardMessageFactory board;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // for example ..
        board = new Duemilanove();
    }

    @Test
    public void testInvalidPin_negative() throws SerialException {
        final int invalid_pin = board.getMinPin() - 1;

        try {
            firmata.send(board.analogRead(invalid_pin));
            fail("negative pin");
        } catch (MessageValidationException e) {
        }
    }

    @Test
    public void testInvalidPin_tooBig() throws SerialException {
        final int invalid_pin = board.getMaxPin() + 1;

        try {
            firmata.send(board.analogRead(invalid_pin));
            fail("too big pin");
        } catch (MessageValidationException e) {
        }
    }

    @Test
    public void testInvalidPin_analogIn() throws SerialException {
        final int invalid_pin = board.getAnalogInPins()[board.getAnalogInPins().length-1] + 1;

        try {
            firmata.send(board.pinMode(invalid_pin, SetPinModeMessage.PIN_MODE.ANALOG.getMode()));
            fail("not allowed analog in pin");
        } catch (MessageValidationException e) {
        }
    }

    @Test
    public void testInvalidPin_analogOut() throws SerialException {
        final int invalid_pin = 1;

        try {
            firmata.send(board.pinMode(invalid_pin, SetPinModeMessage.PIN_MODE.PWM.getMode()));
            fail("not allowed analog out pin");
        } catch (MessageValidationException e) {
        }
    }

    @Test
    public void testInvalidValue_analog_negative() throws SerialException {
        int invalid_analog_value = -1;
        try {
            firmata.send(board.analogWrite(board.getAnalogOutPins()[0], invalid_analog_value));
            fail("negative analog value");
        } catch (MessageValidationException e) {
        }
    }

    @Test
    public void testInvalidValue_analog_tooBig() throws SerialException {
        int invalid_analog_value = 512;
        try {
            firmata.send(board.analogWrite(board.getAnalogOutPins()[0], invalid_analog_value));
            fail("too big analog value");
        } catch (MessageValidationException e) {
        }
    }

    @Test
    public void testInvalidValue_digitalMask() throws SerialException {
        int invalid_digital_value = -1;
        try {
            firmata.send(board.digitalWrite(1, invalid_digital_value));
        } catch (MessageValidationException e) {
        }
    }

    @Test
    public void testInvalidValue_digitalPort() throws SerialException {
        int invalid_digital_port = 3;
        try {
            firmata.send(board.digitalWrite(invalid_digital_port, 1));
        } catch (MessageValidationException e) {
        }
    }

    @Test
    public void testValid_analogIn() throws MessageValidationException, SerialException {
        int allowed_pin = board.getAnalogInPins()[0];

        firmata.send(board.analogRead(allowed_pin));
        firmata.send(board.pinMode(allowed_pin, SetPinModeMessage.PIN_MODE.ANALOG.getMode()));
    }

    @Test
    public void testValidValue_analogOut() throws MessageValidationException, SerialException {
        int allowed_pin = board.getAnalogOutPins()[0];
        int allowed_analog_value = 128;

        firmata.send(board.analogRead(allowed_pin));
        firmata.send(board.analogWrite(allowed_pin, allowed_analog_value));
    }

    @Test
    public void testValidValue_digital() throws MessageValidationException, SerialException {
        int allowed_pin = 1;
        int allowed_digital_value = 1;

        firmata.send(board.digitalRead(allowed_pin));
        firmata.send(board.digitalWrite(allowed_pin, allowed_digital_value));
    }
}
