package galaxy.geek.nfc;

import galaxy.geek.nfc.service.Data;
import galaxy.geek.nfc.util.Dialogs;
import galaxy.geek.nfc.util.Storage;
import galaxy.geek.nfc.widget.NFCTag;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ModifyNFCTag extends Dialog
{

	protected NFCTag result;
	protected Shell shell;
	private Text tagName;
	private Text tagMaxSize;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public ModifyNFCTag(Shell parent, int style)
	{
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public NFCTag open()
	{
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch())
			{
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents()
	{
		shell = new Shell(getParent(), getStyle() | SWT.APPLICATION_MODAL);
		// 居中显示

		shell.setSize(308, 135);

		// 在父窗口中心
		shell.setLocation(
				(getParent().getShell().getClientArea().width - shell
						.getClientArea().width)
						/ 2
						+ getParent().getShell().getLocation().x,
				(getParent().getShell().getClientArea().height - shell
						.getClientArea().height)
						/ 2
						+ getParent().getShell().getLocation().y);
		shell.setText(getText());

		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(37, 13, 48, 14);
		lblNewLabel.setText("\u6807\u7B7E\u540D\uFF1A");

		tagName = new Text(shell, SWT.BORDER);
		tagName.setBounds(91, 10, 170, 19);

		tagMaxSize = new Text(shell, SWT.BORDER);
		tagMaxSize.setBounds(91, 45, 142, 19);

		Label label = new Label(shell, SWT.NONE);
		label.setText("\u6807\u7B7E\u5BB9\u91CF\uFF1A");
		label.setBounds(26, 48, 59, 14);

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(239, 48, 59, 14);
		label_1.setText("\u5B57\u8282");

		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				int maxSize = 0;
				String name = tagName.getText().trim();
				if("".equals(name))
				{
					Dialogs.openError(shell, "必须输入NFC标签名称");
					return;
				}
				try
				{
					maxSize = Integer.parseInt(tagMaxSize.getText());
				}
				catch (Exception ee)
				{
					Dialogs.openError(shell, "错误", ee.getMessage());
					return;
				}
				if(Data.getTag(name) != null)
				{
					Dialogs.openError(shell, "该标签已经存在，请另起一个标签名！");
					return;
				}
				result = new NFCTag(shell, getStyle(), name);
				result.maxSize = maxSize;

				shell.close();
			}
		});
		button.setBounds(164, 79, 59, 26);
		button.setText("\u786E\u5B9A");

		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		button_1.setText("\u53D6\u6D88");
		button_1.setBounds(221, 79, 59, 26);

	}
}
