package mobile.android.runtime.test;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
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
    //  第1个测试方法
    public void test1() 
    {
        //  向List对象中添加10000个数
        for (int i = 0; i < 10000; i++)
        {
            list1.add(i);
        }
    }
    //  第2个测试方法
    public void test2()
    {
        //  依次获得List对象中的元素
        for (int i = 0; i < 10000; i++)
            list1.get(i);
    }
    public void onClick_Test(View view)
    {
        try
        {
            //  获取执行test1方法前的时间点（单位：毫秒）
            long start1 = System.currentTimeMillis();
            //  执行test1方法
            test1();
            //  获取执行test1方法后的时间点（单位：毫秒）
            long end1 = System.currentTimeMillis();
            //  获取执行test2方法前的时间点（单位：毫秒）
            long start2 = System.currentTimeMillis();
            //  执行test2方法
            test2();
            //  获取执行test2方法后的时间点（单位：毫秒）
            long end2 = System.currentTimeMillis();
            //  显示测试结果
            Toast.makeText(
                    this,
                    "test1方法的执行时间：" + (end1 - start1) + "毫秒\ntest2方法的执行时间：" + (end2 - start2) + "毫秒", Toast.LENGTH_LONG).show();
        }
        catch (Exception e)
        {
        }
    }
}
