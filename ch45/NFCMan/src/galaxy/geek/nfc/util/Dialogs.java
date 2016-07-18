package galaxy.geek.nfc.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class Dialogs
{
    public static void openInformation(Shell shell, String title, String message)
    {
    	MessageBox messageBox  = new MessageBox(shell, SWT.ICON_INFORMATION);
    	messageBox.setMessage(message);
    	messageBox.setText(title);
    	messageBox.open();
    }
    public static void openInformation(Shell shell, String message)
    {
    	openInformation(shell, "–≈œ¢", message);
    }
    public static void openError(Shell shell, String title, String message)
    {
    	MessageBox messageBox  = new MessageBox(shell, SWT.ERROR);
    	messageBox.setMessage(message);
    	messageBox.setText(title);
    	messageBox.open();
    }
    public static void openError(Shell shell, String message)
    {
    	openError(shell, "¥ÌŒÛ", message);
    }
}
