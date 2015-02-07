package tests.hardware;

import junit.framework.TestCase;
import firmata.*;
import firmata.IFirmata;
import firmata.message.FirmwareVersionMessage;
import firmata.message.ProtocolVersionMessage;
import serial.ISerial;
import serial.IndepProcessingSerialAdapter;
import firmata.wrapper.MessagesHistoryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import serial.IndepProcessingSerial;

/**
 * Hardware (board) interaction test
 */
public abstract class BaseHardwareTest extends TestCase {

    protected Logger log = LoggerFactory.getLogger(getClass());
    
    public static final String PORT = "COM10";
    public static final int BAUD_RATE = 57600;

    protected ISerial serial;
    protected Firmata firmata;
    protected MessagesHistoryWrapper historyFirmataWrapper;
    protected InitListener initListener;

    @Override
    protected void setUp() throws Exception {
        IndepProcessingSerial serialImpl = new IndepProcessingSerial(PORT, BAUD_RATE);
        serial = new IndepProcessingSerialAdapter(serialImpl);
        firmata = new Firmata(serial);
        historyFirmataWrapper = new MessagesHistoryWrapper(firmata);
        initListener = new InitListener(new InitListener.Listener() {
            public void onInitialized() {
                log.info("Initialized");
            }
        });

        firmata.addListener(new IFirmata.StubListener() {
            @Override
            public void onUnknownByteReceived(int byteValue) {
                log.info("Received byte: {}('{}')", byteValue, (char)byteValue);
            }
        });

        firmata.addListener(initListener);

        serial.start();
        log.info("Connected to {} at {}", PORT, BAUD_RATE);

        // waiting for default messages (protocol version and firmware)
        new FirmataWaiter(firmata).waitSeconds(5, ProtocolVersionMessage.class);
        new FirmataWaiter(firmata).waitSeconds(5, FirmwareVersionMessage.class);
        assertTrue(initListener.isInitialized());

        Thread.sleep(10 * 1000);
        log.info("Listening to default messaged finished");
    }

    @Override
    protected void tearDown() throws Exception {
        serial.stop();
    }
}
