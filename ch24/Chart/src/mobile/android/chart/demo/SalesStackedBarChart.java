package mobile.android.chart.demo;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;

public class SalesStackedBarChart extends AbstractDemoChart
{

	public String getName()
	{
		return "同期销售额对比（水平条形图）";
	}

	public String getDesc()
	{
		return "在过去2年中同期的销售额对比";
	}

	public Intent execute(Context context)
	{
		String[] titles = new String[]
		{ "2008", "2007" };
		List<double[]> values = new ArrayList<double[]>();
		values.add(new double[]
		{ 14230, 12300, 14240, 15244, 15900, 19200, 22030, 21200, 19500, 15500,
				12600, 14000 });
		values.add(new double[]
		{ 5230, 7300, 9240, 10540, 7900, 9200, 12030, 11200, 9500, 10500,
				11600, 13500 });
		int[] colors = new int[]
		{ Color.BLUE, Color.CYAN };
		XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
		setChartSettings(renderer, "过去2年中同期的销售额对比", "月", "销售额", 0.5, 12.5, 0,
				24000, Color.GRAY, Color.LTGRAY);
		renderer.setXLabels(12);
		renderer.setYLabels(10);
				
		renderer.setYLabelsAlign(Align.LEFT);
		renderer.setBarSpacing(0.5);
		return ChartFactory.getBarChartIntent(context,
				buildBarDataset(titles, values), renderer, Type.STACKED);
	}

}
