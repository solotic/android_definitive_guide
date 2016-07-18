package mobile.android.galaxy.geek.nfc.activity;

import mobile.android.galaxy.geek.nfc.data.NFCData;
import mobile.android.galaxy.geek.nfc.exceptions.FormatNFCTagException;
import mobile.android.galaxy.geek.nfc.exceptions.MaxSizeException;
import mobile.android.galaxy.geek.nfc.exceptions.ReadOnlyNFCTagException;
import mobile.android.galaxy.geek.nfc.exceptions.WriteNFCTagException;
import mobile.android.galaxy.geek.nfc.interfaces.Const;
import mobile.android.galaxy.geek.nfc.library.TagExt;
import mobile.android.galaxy.geek.nfc.library.TextRecord;
import mobile.android.galaxy.geek.nfc.receiver.NFCManBroadcastReceiver;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

public class NFCActivity extends Activity implements Const
{
	// 系统要做什么操作，如向NFC标签写入文本，读取NFC标签中的文本等，常量在Const中定义
	protected int operationType = NFC_MAN_OPERATION_TYPE_WRITE_TEXT;
	private NfcAdapter mNfcAdapter;
	private PendingIntent mPendingIntent;
	private NFCManBroadcastReceiver mNfcManBroadcastReceiver;
	private IntentFilter mIntentFilter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		mNfcAdapter = mNfcAdapter.getDefaultAdapter(this);
		mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
				getClass()), 0);
		mNfcManBroadcastReceiver = new NFCManBroadcastReceiver();
		mIntentFilter = new IntentFilter(NFC_MAN_BROADCAST_ACTION);

	}

	@Override
	public void onResume()
	{
		super.onResume();
		registerReceiver(mNfcManBroadcastReceiver, mIntentFilter);
		if (mNfcAdapter != null)
			mNfcAdapter.enableForegroundDispatch(this, mPendingIntent, null,
					null);
	}

	@Override
	public void onPause()
	{
		super.onPause();
		unregisterReceiver(mNfcManBroadcastReceiver);
		if (mNfcAdapter != null)
			mNfcAdapter.disableForegroundDispatch(this);
	}

	@Override
	public void onNewIntent(Intent intent)
	{
		NFCData data = null;

		boolean simulate = intent.getBooleanExtra("simulate", false);
		String text = null;

		// 模拟测试NFC
		if (simulate)
		{
			String exception = intent.getStringExtra("exception");
			//  处理异常
			if(exception != null)
			{
				int error = Integer.parseInt(exception.substring(0, exception.indexOf(":")));
				String errorMsg = exception.substring(exception.indexOf(":") + 1);
				Log.d("errormsg", "lining11errormsg:" + errorMsg);
				onNFCFailed(error, errorMsg);
				return;
			}
			String success = intent.getStringExtra("success");
			if("true".equals(success))
			{
				onNFCSuccess();
				return;
			}
			String type = intent.getStringExtra("type");
			String deviceName = intent.getStringExtra("deviceName");
			String targetName = intent.getStringExtra("targetName");
			String[] array = null;
			int index = targetName.indexOf("$");
			if (index > 0)
			{
				array = targetName.substring(index + 1).split(",");
			}
			StringBuilder sb = new StringBuilder();
			data = new NFCData();
			if (array != null && array.length > 2)
			{
				data.maxSize = Integer.parseInt(array[0]);
				data.text = array[1];
				data.contentSize = Integer.parseInt(array[2]);
			}
						
			text = onNear(data);
			sb.append("writetag");
			sb.append(":");
			sb.append("text");
			sb.append(":");
			sb.append(deviceName); // 数据源设备名
			sb.append(":");
			sb.append(targetName); // NFC Tag或另外一个NFC设备的名称
			sb.append(":");
			sb.append(text); // 写到NFC Tag的文本

			try
			{
				Intent intent1 = new Intent(NFC_MAN_SERVICE_BROADCAST_ACTION);
				intent1.putExtra("result", sb.toString());
				sendBroadcast(intent1);
			}
			catch (Exception e)
			{

			}

		}
		else
		// 真机环境下测试NFC
		{
			data = TagExt.readNdefTag(intent);
			text = onNear(data);

			switch (operationType)
			{
				case NFC_MAN_OPERATION_TYPE_WRITE_TEXT:
					if (text != null)
					{
						Tag tag = intent
								.getParcelableExtra(mNfcAdapter.EXTRA_TAG);
						NdefMessage ndefMessage = new NdefMessage(
								new NdefRecord[]
								{ TextRecord.createTextRecord(text) });

						try
						{
							TagExt.writeNdefTag(ndefMessage, tag);
							onNFCSuccess();
			
						}
						catch (ReadOnlyNFCTagException e)
						{
							onNFCFailed(
									NFC_MAN_EXCEPTION_READONLY_NFC_TAG,
									e.getMessage());
						}
						catch (MaxSizeException e)
						{
							onNFCFailed(NFC_MAN_EXCEPTION_MAX_SIZE,
									e.getMessage());
						}
						catch (FormatNFCTagException e)
						{
							onNFCFailed(NFC_MAN_EXCEPTION_FORMAT_NFC_TAG,
									e.getMessage());
						}
						catch (WriteNFCTagException e)
						{
							onNFCFailed(NFC_MAN_EXCEPTION_WRITE_NFC_TAG,
									e.getMessage());
						}

					}
					break;

				default:
					break;
			}

		}
	}

	// event

	// data参数会返回NFC Tag或NFC Device的信息，如果onNear方法返回字符串，会将该字符串写入NFC Tag，
	// 如果是NFC设备，会将字符串发送给该设备
	// data可能为null，为null表示读取失败
	public String onNear(NFCData data)
	{
		return null;
	}

	// 处理NFC任务成功后调用该方法
	// 从NFC Tag中读取数据时成功要通过onNear方法判断
	public void onNFCSuccess()
	{

	}

	
	// 从NFC Tag中读取数据时失败要通过onNear方法判断
	public void onNFCFailed(int error, String errorMsg)
	{

	}
}
