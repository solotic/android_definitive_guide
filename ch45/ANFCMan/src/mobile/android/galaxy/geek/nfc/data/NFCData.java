package mobile.android.galaxy.geek.nfc.data;

public class NFCData
{
    public NFCSimulationData simulation;
    
	//  NFC标签中的文本
	public String text;
	//  NFC标签中内容占用的字节数
	public int contentSize;
	
	
	
	//  NFC标签的最大尺寸（单位：字节）, 默认4KB
	public int maxSize = 4096;  
    //  NFC标签的类型
	public String type;
	
	
       
}
