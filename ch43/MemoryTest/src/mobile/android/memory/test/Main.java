package mobile.android.memory.test;

import java.util.ArrayList;
import java.util.List;

import mobile.android.jx.memory.test.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Main extends Activity
{

	private List<Integer> list1 = new ArrayList<Integer>();

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void test1()
	{
		for (int i = 0; i < 10000; i++)
		{
			list1.add(i);
		}
	}

	public void test2()
	{
		for (int i = 0; i < 10000; i++)
			list1.get(i);
	}

	public void onClick_Test(View view)
	{
		try
		{
			// 获取调用test1方法之前的内存
			long start1 = Memory.used();
			// 调用test1方法
			test1();
			// 获取调用test1方法之后的内存
			long end1 = Memory.used();
			// 获取调用test2方法之前的内存
			long start2 = Memory.used();
			// 调用test2方法
			test2();
			// 获取调用test2方法之后的内存
			long end2 = Memory.used();
			// 显示内存测试结果
			Toast.makeText(
					this,
					"test1方法占用的内存：" + (end1 - start1) + "字节\ntest2方法的占用的内存："
							+ (end2 - start2) + "字节", Toast.LENGTH_LONG).show();

		}
		catch (Exception e)
		{

		}
	}
}
