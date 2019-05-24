<template>
	<div id="chartdiv" ref="chartdiv"></div>  
</template>

<style>
#chartdiv {
	width: 90%;
	height: 90%;
}

</style>

<script>
import * as am4core from "@amcharts/amcharts4/core";
import * as am4charts from "@amcharts/amcharts4/charts";
import am4themes_kelly from "@amcharts/amcharts4/themes/kelly";
am4core.useTheme(am4themes_kelly);


export default {
	name: 'user-statistic',
	mounted() {
    let chart = am4core.create(this.$refs.chartdiv, am4charts.XYChart);
    chart.hiddenState.properties.opacity = 0;
		// chart.paddingRight = 20;
		//从后端获取数据
		var url = "/deals/admin/userSpecify";
		this.getRequest(url)
			.then((response)=>{
				console.log(response.data);
				var tmp = new Array();
				for(var i = 0;i<response.data.length;i++)
				{
					var item ={
						"user": response.data[i].dateOrBuyername,
						"科技类": response.data[i].subject_KEJI_SALES,
						"文学类": response.data[i].subject_WENXUE_SALES,
						"生活类": response.data[i].subject_SHENGHUO_SALES
					}
					tmp.push(item);
				}
				chart.data = tmp;
				console.log(chart.data);
			}).catch(error => {
				JSON.stringify(error);
				console.log(error);
			});


// chart.dateFormatter.inputDateFormat = "yyyyMMdd";

    let categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
    categoryAxis.renderer.grid.template.location = 0;
    categoryAxis.renderer.minGridDistance=40;
    categoryAxis.dataFields.category = "user";
    categoryAxis.fontSize = 11;
		
		let valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
    valueAxis.tooltip.disabled = true;

		let series = chart.series.push(new am4charts.ColumnSeries());
		series.dataFields.categoryX = "user";
		series.name = "科技类";
		series.dataFields.valueY = "科技类";
		series.tooltipHTML = "<img src='/static/img/4.jpg' style='vertical-align:bottom; margin-right: 10px; width:28px; height:21px;'><span style='font-size:14px; color:#000000;'><b>{valueY.value}</b></span>";
		series.tooltipText = "[#000]{valueY.value}[/]";
		series.tooltip.background.fill = am4core.color("#FFF");
		series.tooltip.getStrokeFromObject = true;
		series.tooltip.background.strokeWidth = 3;
		series.tooltip.getFillFromObject = false;
		series.fillOpacity = 0.6;
		series.strokeWidth = 2;
		series.stacked = true;

		let series2 = chart.series.push(new am4charts.ColumnSeries());
		series2.name = "文学类";
		series2.dataFields.categoryX = "user";
		series2.dataFields.valueY = "文学类";
		series2.tooltipHTML = "<img src='/static/img/5.jpg' style='vertical-align:bottom; margin-right: 10px; width:28px; height:21px;'><span style='font-size:14px; color:#000000;'><b>{valueY.value}</b></span>";
		series2.tooltipText = "[#000]{valueY.value}[/]";
		series2.tooltip.background.fill = am4core.color("#FFF");
		series2.tooltip.getFillFromObject = false;
		series2.tooltip.getStrokeFromObject = true;
		series2.tooltip.background.strokeWidth = 3;
		series2.sequencedInterpolation = true;
		series2.fillOpacity = 0.6;
		series2.stacked = true;
		series2.strokeWidth = 2;

		var series3 = chart.series.push(new am4charts.ColumnSeries());
		series3.name = "生活类";
		series3.dataFields.categoryX = "user";
		series3.dataFields.valueY = "生活类";
		series3.tooltipHTML = "<img src='/static/img/8.jpg' style='vertical-align:bottom; margin-right: 10px; width:28px; height:21px;'><span style='font-size:14px; color:#000000;'><b>{valueY.value}</b></span>";
		series3.tooltipText = "[#000]{valueY.value}[/]";
		series3.tooltip.background.fill = am4core.color("#FFF");
		series3.tooltip.getFillFromObject = false;
		series3.tooltip.getStrokeFromObject = true;
		series3.tooltip.background.strokeWidth = 3;
		series3.sequencedInterpolation = true;
		series3.fillOpacity = 0.6;
		series3.stacked = true;
		series3.strokeWidth = 2;

		chart.cursor = new am4charts.XYCursor();
		chart.cursor.xAxis = categoryAxis;
		chart.scrollbarX = new am4core.Scrollbar();

		chart.legend = new am4charts.Legend();
		chart.legend.position = "top";

		this.chart = chart;
	},

	beforeDestroy() {
		if (this.chart) {
			this.chart.dispose();
		}
	}
}
</script>

