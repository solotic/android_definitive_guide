package galaxy.geek.nfc.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import galaxy.geek.nfc.interfaces.Const;
import galaxy.geek.nfc.widget.NFCTag;

public class Storage
{
	// 存储NFC标签
	public static void saveNFCTag(NFCTag tag)
	{
		File file = new File(Const.NFC_MAN_TAG_PATH);
		if (!file.exists())
			file.mkdirs();
	
		try
		{
			FileOutputStream fos = new FileOutputStream(file.getAbsolutePath()
					+ File.separatorChar + tag.name + ".tag");
			StringBuilder sb = new StringBuilder();
			sb.append("name=");
			sb.append(tag.name);
			sb.append("\r\n");

			sb.append("maxSize=");
			sb.append(tag.maxSize);
			sb.append("\r\n");

			if (tag.text != null)
			{
				sb.append("text=");
				sb.append(tag.text);
				sb.append("\r\n");
			}
			fos.write(sb.toString().getBytes("utf-8"));
			fos.close();
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}

	}

	// 装载所有的NFC Tag
	public static List<NFCTag> loadNFCTags(Composite parent)
	{
		List<NFCTag> tags = new ArrayList<NFCTag>();
		File file = new File(Const.NFC_MAN_TAG_PATH);
		if (!file.exists())
			return null;
		String[] tagNames = file.list(new FilenameFilter()
		{

			@Override
			public boolean accept(File file, String name)
			{
				name = name.toLowerCase();
				if (name.endsWith(".tag"))
					return true;
				return false;
			}
		});
		for (int i = 0; i < tagNames.length; i++)
		{

			try
			{
				NFCTag nfcTag = new NFCTag(parent, parent.getStyle(), null);
				
				FileInputStream fis = new FileInputStream(
						file.getAbsolutePath() + File.separatorChar
								+ tagNames[i]);
				InputStreamReader isr = new InputStreamReader(fis, "utf-8");
				BufferedReader br = new BufferedReader(isr);
				String line = null;

				while ((line = br.readLine()) != null)
				{

					int index = line.indexOf("=");
					if (index > 0)
					{
						String key = line.substring(0, index);
						String value = line.substring(index + 1).trim();

						key = key.toLowerCase();
						
						if ("name".equals(key))
						{
							nfcTag.setName(value);

						}
						else if ("maxsize".equals(key))
						{
							nfcTag.maxSize = Integer.parseInt(value);
						}
						else if ("text".equals(key))
						{
							
							if (!value.equals(""))
								nfcTag.setText(value);
						}
						
					}
				}
				
				tags.add(nfcTag);
				br.close();
			}
			catch (Exception e)
			{

			}

		}
		return tags;
	}
}
