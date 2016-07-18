package mobile.android.galaxy.geek.nfc.library;

import mobile.android.galaxy.geek.nfc.data.NFCData;
import mobile.android.galaxy.geek.nfc.exceptions.FormatNFCTagException;
import mobile.android.galaxy.geek.nfc.exceptions.MaxSizeException;
import mobile.android.galaxy.geek.nfc.exceptions.ReadOnlyNFCTagException;
import mobile.android.galaxy.geek.nfc.exceptions.WriteNFCTagException;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Parcelable;

public class TagExt
{
	public static void writeNdefTag(NdefMessage message, Tag tag)
			throws ReadOnlyNFCTagException, MaxSizeException,
			FormatNFCTagException, WriteNFCTagException
	{
		int size = message.toByteArray().length;

		try
		{

			Ndef ndef = Ndef.get(tag);
			if (ndef != null)
			{
				ndef.connect();

				if (!ndef.isWritable())
				{
					throw new ReadOnlyNFCTagException("只读NFC标签，无法写入！");
				}
				if (ndef.getMaxSize() < size)
				{

					throw new MaxSizeException("写入的数据超过NFC标签的最大容量");
				}
				ndef.writeNdefMessage(message);
			}
			else
			{
				NdefFormatable format = NdefFormatable.get(tag);
				if (format != null)
				{
					try
					{
						format.connect();
						format.format(message);
					}
					catch (Exception e)
					{

					}
				}
				else
				{

					throw new FormatNFCTagException("无法用NDEF格式化NFC标签");

				}
			}
		}
		catch (Exception e)
		{
			
			throw new WriteNFCTagException("无法将数据写入NFC标签");
		}

	}

	public static NFCData readNdefTag(Intent intent)
	{
		Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
		Parcelable[] rawMsgs = intent
				.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
		if (tag == null || rawMsgs == null)
			return null;
		NFCData data = new NFCData();
		Ndef ndef = Ndef.get(tag);
		data.type = ndef.getType();
		data.maxSize = ndef.getMaxSize();

		NdefMessage msgs[] = null;
		int contentSize = 0;
		if (rawMsgs != null)
		{
			msgs = new NdefMessage[rawMsgs.length];

			for (int i = 0; i < rawMsgs.length; i++)
			{
				msgs[i] = (NdefMessage) rawMsgs[i];
				contentSize += msgs[i].toByteArray().length;
			}
		}
		try
		{

			if (msgs != null)
			{
				NdefRecord record = msgs[0].getRecords()[0];
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < msgs.length; i++)
				{
					NdefRecord[] records = msgs[i].getRecords();
					for (int j = 0; j < records.length; j++)
					{
						TextRecord textRecord = TextRecord.parse(records[j]);
						sb.append(textRecord.getText());
					}
				}

				data.text = sb.toString();
				data.contentSize = contentSize;
				return data;
			}

		}
		catch (Exception e)
		{

		}
		return null;
	}

}
