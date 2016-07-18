package mobile.android.galaxy.geek.nfc.interfaces;

public interface Const
{
    public String TAG_NFC_LOG = "ANFCMan"; 
    
	//  broadcast action
	public String NFC_MAN_BROADCAST_ACTION = "android.intent.action.ACTION_NFC_MAN";
	public String NFC_MAN_SERVICE_BROADCAST_ACTION = "android.intent.action.ACTION_NFC_MAN_SERVICE";
	
	//  操作类型
	//  向NFC标签写文本
	public int NFC_MAN_OPERATION_TYPE_WRITE_TEXT = 1;
	
	
	//  异常编码
	//  写入的数据超过NFC标签的最大尺寸
	public int NFC_MAN_EXCEPTION_MAX_SIZE = 1;
	//  只读标签无法写入
	public int NFC_MAN_EXCEPTION_READONLY_NFC_TAG = 2;
	//  格式化NFC标签异常
	public int NFC_MAN_EXCEPTION_FORMAT_NFC_TAG = 3;
	//  向NFC标签写入数据异常
	public int NFC_MAN_EXCEPTION_WRITE_NFC_TAG = 4;
	  

	
	
	
}
   