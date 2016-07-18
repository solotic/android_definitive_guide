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
import android.graphics.Typeface;

public class SalesComparisonChart extends AbstractDemoChart
{
	/**
	 * Returns the chart name.
	 * 
	 * @return the chart name
	 */
	public String getName()
	{
		return "销售比较（曲线和区域图）";
	}

	public String getDesc()
	{
		return "过去2年销售额比较";
	}

	public Intent execute(Context context)
	{
		String[] titles = new String[]
		{ "2010年销售额", "2009年销售额", "2010年和2009年销售额的差异" };
		List<double[]> values = new ArrayList<double[]>();
		values.add(new double[]
		{ 14230, 12300, 14240, 15244, 14900, 12200, 11030, 12000, 12500, 15500,
				14600, 15000 });
		values.add(new double[]
		{ 10230, 10900, 11240, 12540, 13500, 14200, 12530, 11200, 10500, 12500,
				11600, 13500 });
		int length = values.get(0).length;
		double[] diff = new double[length];
		for (int i = 0; i < length; i++)
		{
			diff[i] = values.get(0)[i] - values.get(1)[i];
		}
		values.add(diff);
		int[] colors = new int[]
		{ Color.BLUE, Color.CYAN, Color.GREEN };
		PointStyle[] styles = new PointStyle[]
		{ PointStyle.POINT, PointStyle.POINT, PointStyle.POINT };
		XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
		setChartSettings(renderer, "过去2年的月销售额", "月", "销售额", 0.75, 12.25, -5000,
				19000, Color.GRAY, Color.LTGRAY);
		renderer.setXLabels(12);
		renderer.setYLabels(10);
		renderer.setDisplayChartValues(true);
		renderer.setChartTitleTextSize(20);
		renderer.setTextTypeface("sans_serif", Typeface.BOLD);
		renderer.setChartValuesTextSize(10f);
		renderer.setLabelsTextSize(14f);
		renderer.setAxisTitleTextSize(15);
		
		length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++)
		{
			XYSeriesRenderer seriesRenderer = (XYSeriesRenderer) renderer
					.getSeriesRendererAt(i);
			seriesRenderer.setFillBelowLine(i == length - 1);
			seriesRenderer.setFillBelowLineColor(colors[i]);
			seriesRenderer.setLineWidth(2.5f);
		}
		return ChartFactory.getLineChartIntent(context,
				buildBarDataset(titles, values), renderer);
	}

}
