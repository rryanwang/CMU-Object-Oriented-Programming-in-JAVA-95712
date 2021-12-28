// Zhenxi Wang zhenxiw
package hw3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableMap;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.util.StringConverter;

/**CCChart creates the bar-chart of years and counts
 * It is invoked in showChartView() method of CCView
 */
public class CCChart {

	final CategoryAxis xAxis = new CategoryAxis();
	final NumberAxis yAxis = new NumberAxis();
	XYChart.Series<String, Number> yearSeries = new XYChart.Series<String, Number>();

	/**updateChart() is invoked at the beginning of creating 
	 * the chart
	 * @param yearMap
	 * @return
	 */
	BarChart<String,Number> updateChart(ObservableMap<String, List<Case>> yearMap) {
		BarChart<String,Number> barChart = new BarChart<String, Number>(xAxis,yAxis);
		yearSeries.setName("Case Count");
		List<YearCount> yearCountList = new ArrayList<>();
		for (Map.Entry<String, List<Case>> e : yearMap.entrySet()) {
			yearCountList.add(new YearCount(e.getKey(), e.getValue().size()));
		}
		Collections.sort(yearCountList);  //this will sort the list on year in ascending order
		
		//populate chart with years on x-axis and count on y-axis
		for (YearCount yc : yearCountList)  {
			yearSeries.getData().add(new XYChart.Data<String, Number>(yc.year, yc.count));
		}
		barChart.getData().add(yearSeries);
		barChart.setTitle("FTC Case Count");
		xAxis.setLabel("Years");
		yAxis.setLabel("FTC Case Count");
		yAxis.setAutoRanging(false);
		
		//format the tick labels
		yAxis.setTickLabelFormatter(new StringConverter<Number>() {
	        @Override
	        public String toString(Number object) {
	            return (String.format("%s", object.intValue()));
	        }

	        @Override
	        public Number fromString(String string) {
	            return Integer.parseInt(string);
	        }
	    });
		return barChart;
	}
	
	/**YearCount is used to create the bar chart
	 * with years arranged in ascending order
	 * Each year has count of cases in that year
	 */
	class YearCount implements Comparable<YearCount> {
		String year;
		int count;
		YearCount (String year, int count) {
			this.year = year;
			this.count = count;
		}
		@Override
		public int compareTo(YearCount o) {
			return year.compareTo(o.year);
		}
	}
}
