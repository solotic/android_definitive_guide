package galaxy.geek.nfc;

import galaxy.geek.nfc.service.Data;
import galaxy.geek.nfc.service.NFCManServer;
import galaxy.geek.nfc.service.threads.SendMessageToClient;
import galaxy.geek.nfc.util.Dialogs;
import galaxy.geek.nfc.util.Storage;
import galaxy.geek.nfc.util.StringWorker;
import galaxy.geek.nfc.widget.MobileDevice;
import galaxy.geek.nfc.widget.NFCTag;

import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import mobile.android.galaxy.geek.nfc.data.NFCData;
import mobile.android.galaxy.geek.nfc.data.NFCSimulationData;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

public class NFCMain
{
	private NFCManServer mNfcManServer;
	private ScrolledComposite mDevicePanel;
	private ScrolledComposite mNfcTagPanel;
	private List<NFCTag> mNFCTagList = new ArrayList<NFCTag>();

	public Shell shell;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			NFCMain window = new NFCMain();
			window.open();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open()
	{
		Display display = Display.getDefault();
		createContents();
		init();
		shell.open();
		shell.layout();

		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch())
			{
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents()
	{
		shell = new Shell(SWT.CLOSE | SWT.MIN);
		// 居中显示
		shell.setLocation(Display.getCurrent().getClientArea().width / 2
				- shell.getShell().getSize().x / 2, Display.getCurrent()
				.getClientArea().height / 2 - shell.getSize().y / 2);
		shell.setSize(650, 419);
		shell.setText("NFC Man");
		shell.setLayout(null);

		Button btnNewButton_2 = new Button(shell, SWT.NONE);
		btnNewButton_2.setBounds(10, 354, 117, 33);
		btnNewButton_2.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				ModifyNFCTag modifyNFCTag = new ModifyNFCTag(shell, SWT.CLOSE);
				modifyNFCTag.setText("新建NFC标签");
				modifyNFCTag.open();
				if (modifyNFCTag.result != null)
				{
					addNFCTag(modifyNFCTag.result.name,
							modifyNFCTag.result.maxSize);
					Storage.saveNFCTag(modifyNFCTag.result);
				}

			}
		});
		btnNewButton_2.setText("\u65B0\u5EFANFC\u6807\u7B7E");

		mNfcTagPanel = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL
				| SWT.V_SCROLL);
		mNfcTagPanel.setBounds(10, 232, 630, 116);

		final Composite composite1 = new Composite(mNfcTagPanel, SWT.NONE);
		mNfcTagPanel.setContent(composite1);
		GridLayout layout = new GridLayout();

		layout.numColumns = 7;
		composite1.setLayout(layout);
		NFCTag nfcTag = null;

		composite1.setSize(composite1.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		mDevicePanel = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL
				| SWT.V_SCROLL);
		mDevicePanel.setBounds(10, 30, 630, 171);

		final Composite composite2 = new Composite(mDevicePanel, SWT.NONE);
		mDevicePanel.setContent(composite2);
		layout = new GridLayout();

		layout.numColumns = 5;
		composite2.setLayout(layout);

		Label lblNfcTag = new Label(shell, SWT.NONE);
		lblNfcTag.setBounds(10, 207, 59, 19);
		lblNfcTag.setText("NFC Tag");

		Label lblAvailableMobileDevice = new Label(shell, SWT.NONE);
		lblAvailableMobileDevice.setText("Available Mobile Device");
		lblAvailableMobileDevice.setBounds(10, 10, 169, 24);

		Label lblIPAddress = new Label(shell, SWT.NONE);
		lblIPAddress.setAlignment(SWT.RIGHT);
		lblIPAddress.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblIPAddress.setBounds(427, 363, 213, 32);
		try
		{
			InetAddress address = InetAddress.getLocalHost();
			lblIPAddress.setText("IP地址：" + address.getHostAddress());
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
	}

	private void init()
	{
		mNfcManServer = new NFCManServer(this);
		mNfcManServer.start();

		List<NFCTag> tags = Storage.loadNFCTags((Composite) mNfcTagPanel
				.getContent());

		for (NFCTag tag : tags)
		{

			try
			{

				addNFCTag(tag);

			}
			catch (Exception e)
			{

			}
		}

	}

	public ScrolledComposite getDevicePanel()
	{
		return mDevicePanel;
	}

	// 当客户端成功连接后，将设备添加到面板中，并显示设备名，该方法比将device添加到数据结构中，需要单独添加
	public MobileDevice addDevice(String deviceName)
	{
		
		Composite composite = (Composite) mDevicePanel.getContent();

		final MobileDevice mobileDevice = new MobileDevice(composite, SWT.NONE,
				"res/images/phone.png", deviceName);
		
		DropTarget dropTarget = new DropTarget(mobileDevice, DND.DROP_MOVE);
		dropTarget.setTransfer(new Transfer[]
		{ TextTransfer.getInstance() });

		dropTarget.addDropListener(new DropTargetAdapter()
		{
			public void dragEnter(DropTargetEvent event)
			{

			}

			public void dropAccept(DropTargetEvent event)
			{

			}

			public void drop(DropTargetEvent event)
			{
				String tagName = (String) event.data;
				NFCTag tag = Data.getTag(tagName);

				String deviceName = mobileDevice.getName();
				NFCData data = new NFCData();
				data.simulation = new NFCSimulationData();
				data.simulation.command = "near";
				data.simulation.type = "tag";
				data.simulation.deviceName = deviceName;
				data.simulation.targetName = tagName;
				data.maxSize = tag.maxSize;
				data.text = tag.text;
				data.contentSize = tag.contentSize;
				String text = StringWorker.buildNfcTagString(data);
				// 向客户端发送消息
				new SendMessageToClient(deviceName, text).start();

			}
		});

		composite.setSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		composite.layout();
		return mobileDevice;
	}

	
	public void addNFCTag(String tagName)
	{
		addNFCTag(tagName, 48);
	}

	public void addNFCTag(final NFCTag nfcTag)
	{
		Composite composite = (Composite) mNfcTagPanel.getContent();
		try
		{
			Data.putTag(nfcTag.name, nfcTag);
		}
		catch (Exception e)
		{
			return;
		}

		nfcTag.canvas.addMouseListener(new MouseListener()
		{

			@Override
			public void mouseUp(MouseEvent arg0)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDown(MouseEvent arg0)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDoubleClick(MouseEvent arg0)
			{
				String msg = "标签名称：" + nfcTag.name + "\r\n" + "最大容量："
						+ nfcTag.maxSize + " Bytes\r\n";
				if (nfcTag.text == null)
					msg += "空标签";
				else
					msg += "标签内容：" + nfcTag.text;
				Dialogs.openInformation(shell, "NFC标签", msg);

			}
		});

		DragSource dragSource = new DragSource(nfcTag.canvas, DND.DROP_MOVE);

		dragSource.setTransfer(new Transfer[]
		{ TextTransfer.getInstance() });

		dragSource.addDragListener(new DragSourceAdapter()
		{
			public void dragStart(DragSourceEvent event)
			{

			}

			public void dragSetData(DragSourceEvent event)
			{
				event.data = String.valueOf(nfcTag.name);

			}
		});

		composite.setSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));

	}

	public void addNFCTag(final String tagName, final int maxSize)
	{
		Composite composite = (Composite) mNfcTagPanel.getContent();
		NFCTag nfcTag = new NFCTag(composite, SWT.NONE, tagName, maxSize);
		addNFCTag(nfcTag);
	}
}
