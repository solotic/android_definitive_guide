package mobile.android.chart.demo;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;

public class AverageTemperatureChart extends AbstractDemoChart
{
	public String getName()
	{
		return "温度变化（曲线图）";
	}

	public String getDesc()
	{
		return "4个地区的温度变化.";
	}

	public Intent execute(Context context)
	{
		String[] titles = new String[]
		{ "北京", "沈阳", "西安", "南宁" };
		List<double[]> x = new ArrayList<double[]>();
		for (int i = 0; i < titles.length; i++)
		{
			x.add(new double[]
			{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });
		}
		List<double[]> values = new ArrayList<double[]>();
		values.add(new double[]
		{ 12.3, 12.5, 13.8, 16.8, 20.4, 24.4, 26.4, 26.1, 23.6, 20.3, 17.2,
				13.9 });
		values.add(new double[]
		{ 10, 10, 12, 15, 20, 24, 26, 26, 23, 18, 14, 11 });
		values.add(new double[]
		{ 5, 5.3, 8, 12, 17, 22, 24.2, 24, 19, 15, 9, 6 });
		values.add(new double[]
		{ 9, 10, 11, 15, 19, 23, 26, 25, 22, 18, 13, 10 });
		int[] colors = new int[]
		{ Color.BLUE, Color.GREEN, Color.CYAN, Color.YELLOW };
		PointStyle[] styles = new PointStyle[]
		{ PointStyle.CIRCLE, PointStyle.DIAMOND, PointStyle.TRIANGLE,
				PointStyle.SQUARE };
		XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
		int length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++)
		{
			((XYSeriesRenderer) renderer.getSeriesRendererAt(i))
					.setFillPoints(true);
		}

		setChartSettings(renderer, "温度变化", "月", "温度", 0.5, 12.5, 0, 32,
				Color.LTGRAY, Color.LTGRAY);
		renderer.setXLabels(12);
		renderer.setYLabels(8);
		renderer.setShowGrid(true);

		renderer.setYLabelsAlign(Align.RIGHT);

		Intent intent = ChartFactory.getLineChartIntent(context,
				buildDataset(titles, x, values), renderer, "温度变化");
		return intent;
	}

}
