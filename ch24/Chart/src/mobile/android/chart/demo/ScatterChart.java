package mobile.android.chart.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class ScatterChart extends AbstractDemoChart
{

	public String getName()
	{
		return "离散点图";
	}

	public String getDesc()
	{
		return "随机产生一些离散点";
	}

	public Intent execute(Context context)
	{
		String[] titles = new String[]
		{ "颜色1", "颜色2", "颜色3", "颜色4", "颜色5" };
		List<double[]> x = new ArrayList<double[]>();
		List<double[]> values = new ArrayList<double[]>();
		int count = 20;
		int length = titles.length;
		Random r = new Random();
		for (int i = 0; i < length; i++)
		{
			double[] xValues = new double[count];
			double[] yValues = new double[count];
			for (int k = 0; k < count; k++)
			{
				xValues[k] = k + r.nextInt() % 10;
				yValues[k] = k * 2 + r.nextInt() % 10;
			}
			x.add(xValues);
			values.add(yValues);
		}
		int[] colors = new int[]
		{ Color.BLUE, Color.CYAN, Color.MAGENTA, Color.LTGRAY, Color.GREEN };
		PointStyle[] styles = new PointStyle[]
		{ PointStyle.X, PointStyle.DIAMOND, PointStyle.TRIANGLE,
				PointStyle.SQUARE, PointStyle.CIRCLE };
		XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
		setChartSettings(renderer, "离散点图", "X", "Y", -10, 30, -10, 51,
				Color.GRAY, Color.LTGRAY);
		renderer.setXLabels(10);
		renderer.setYLabels(10);
		length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++)
		{
			((XYSeriesRenderer) renderer.getSeriesRendererAt(i))
					.setFillPoints(true);
		}
		return ChartFactory.getScatterChartIntent(context,
				buildDataset(titles, x, values), renderer);
	}

}
