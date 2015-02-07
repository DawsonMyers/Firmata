package firmata.reader;

import firmata.IFirmata;
import firmata.message.SysexMessage;

/**
 * Created by Dawson on 2/7/2015.
 */
public class BaseSysexMessageReaderImpl<ConcreteSysexMessage extends SysexMessage> extends BaseSysexMessageReader<ConcreteSysexMessage> {
	public BaseSysexMessageReaderImpl(Byte sysexCommand) {
		super(sysexCommand);
	}

	/**
	 * Build SysexMessage from incoming buffer
	 *
	 * @param buffer       buffer (start from COMMAND_START byte, ends with COMMAND_END byte)
	 * @param bufferLength buffer length
	 * @return SysexMessage command or inherited message
	 */
	@Override
	protected ConcreteSysexMessage buildSysexMessage(byte[] buffer, int bufferLength) {
		return null;
	}

	/**
	 * Invoke Firmata listener
	 *
	 * @param listener
	 */
	@Override
	public void fireEvent(IFirmata.Listener listener) {

	}
}
