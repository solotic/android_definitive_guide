package galaxy.geek.nfc.util;

import galaxy.geek.nfc.service.Data;
import galaxy.geek.nfc.widget.NFCTag;
import mobile.android.galaxy.geek.nfc.data.NFCData;
import mobile.android.galaxy.geek.nfc.data.NFCSimulationData;
import mobile.android.galaxy.geek.nfc.exceptions.MaxSizeException;

//  用于处理在客户端和服务端之间传递的字符串
public class StringWorker
{
	public static String buildNfcTagString(NFCData data)
	{
		NFCSimulationData simulationData = data.simulation;

		if (simulationData != null)
		{
			StringBuilder sb = new StringBuilder();
			sb.append(simulationData.command);
			sb.append(":");
			sb.append(simulationData.type);
			sb.append(":");
			sb.append(simulationData.deviceName);

			sb.append(":");
			sb.append(simulationData.targetName);
			if (data.text != null)
			{
				sb.append("$");
				sb.append(data.maxSize);
				sb.append(",");
				sb.append(data.text);
				sb.append(",");
				sb.append(data.contentSize);
			}
			return sb.toString();
		}
		return null;
	}

	// clientText表示客户端传过来的字符串，该方法会解析clientText，并用解析后的到的数据装载data的部分属性
	public static void writeDataToTag(String clientText)
			throws MaxSizeException
	{
		String[] array = clientText.split(":");
		if (array.length < 5)
			return;
		String command = array[0]; // writetag、readtag等
		String type = array[1]; // text、url等
		String deviceName = array[2]; // 提供数据源的设备名称，在这里暂时没有该值
		String targetName = array[3]; // 接收数据的设备或Tag名称，需要根据该名称找到NFCTag
		String value = array[4]; // 根据command和target决定该值的意义
		NFCTag tag = null;
		if (command.contains("tag"))
		{
			if (targetName.contains("$"))
				tag = Data.getTag(targetName.substring(0,
						targetName.indexOf("$")));
			else
				tag = Data.getTag(targetName);
			if (tag == null)
				return;
		}
		if ("writetag".equals(command))
		{
			if ("text".equals(type))
			{

				int contentSize = 0;
				try
				{
					contentSize = value.getBytes("utf-8").length;
				}
				catch (Exception e) {
					// TODO: handle exception
				}

				if (contentSize > tag.maxSize)
				{
					throw new MaxSizeException("写入的数据超过NFC标签的最大容量");
				}

				tag.contentSize = contentSize;
			}

			tag.setText(value);

		}
		Storage.saveNFCTag(tag);
	}

}
