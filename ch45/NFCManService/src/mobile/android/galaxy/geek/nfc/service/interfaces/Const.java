package mobile.android.galaxy.geek.nfc.service.interfaces;


public interface Const
{
    // 设置文件的文件名
	public String NFC_MAN_SETTINGS_NAME = "nfc_man_service";
	
	//  服务器IP的Key
	public String NFC_MAN_KEY_SERVER_IP = "server_ip";
	//  Android设备名称的Key，该名称用于唯一标识Android设备，不能重复
	public String NFC_MAN_KEY_DEVICE_NAME = "device_name";
    
	//  服务端的端口号
	public int NFC_MAN_SERVER_PORT = 9119;
	
	//  broadcast action
	public String NFC_MAN_BROADCAST_ACTION = "android.intent.action.ACTION_NFC_MAN";
	public String NFC_MAN_SERVICE_BROADCAST_ACTION = "android.intent.action.ACTION_NFC_MAN_SERVICE";
	
	//  command
	public String NFC_MAN_COMMAND_CLOSE_SOCKET = "close_socket";

}
