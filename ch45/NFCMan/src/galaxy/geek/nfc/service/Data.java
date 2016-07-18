package galaxy.geek.nfc.service;

import galaxy.geek.nfc.widget.NFCTag;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Data
{
	private static Map<String, Socket> mDeviceList = new HashMap<String, Socket>();
    private static Map<String, NFCTag> mTagList = new HashMap<String, NFCTag>();
	public static void putDevice(String deviceName, Socket socket) throws Exception
	{
		synchronized (mDeviceList)
		{
			if(mDeviceList.containsKey(deviceName))
				throw new Exception("<" + deviceName + "> 已经存在，请更换设备名");
			mDeviceList.put(deviceName, socket);	
			
		}
	}
	public static Socket getDevice(String deviceName) 
	{
		synchronized (mDeviceList)
		{
			return mDeviceList.get(deviceName);
		}
	}
	public static void removeDevice(String deviceName) 
	{
		synchronized (mDeviceList)
		{
			mDeviceList.remove(deviceName);
		}
	}
	public static NFCTag getTag(String tagName) 
	{
		synchronized (mTagList)
		{
			return mTagList.get(tagName);
		}
	}
	
	public static void putTag(String tagName,NFCTag tag) throws Exception
	{
		synchronized (mTagList)
		{
			if(mTagList.containsKey(tagName))
				throw new Exception("<" +tagName + "> 已经存在，请更换NFC标签名");
			mTagList.put(tagName, tag);
	
			
		}
	}
	public static int getTagCount()
	{
		return mTagList.size();
	}
}
