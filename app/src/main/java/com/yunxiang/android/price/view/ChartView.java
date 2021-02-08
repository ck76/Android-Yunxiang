package com.yunxiang.android.price.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.yunxiang.android.R;
import com.yunxiang.android.price.model.PriceModel;

import java.util.ArrayList;
import java.util.List;


public class ChartView extends LinearLayout {

    private Context mContext;

    private TextView mLeftTxt;
    private TextView mRightTxt;
    private LineChart mChart;

    private String mLeftLabel;
    private String mRightLabel;
    private int mChartColor;

    private boolean mWithLegend;
    private List<PriceModel> mPriceList;


    public ChartView(Context context) {
        super(context);
        mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_view_chart, this);
        mLeftTxt = view.findViewById(R.id.chart_left_label);
        mRightTxt = view.findViewById(R.id.chart_right_label);
        mChart = view.findViewById(R.id.chart);
    }

    /**
     * 设置是否需要底部标签
     *
     * @param withLegend true:需要
     * @return ChartView
     */
    public ChartView setWithLegend(boolean withLegend) {
        mWithLegend = withLegend;
        return this;
    }

    public ChartView setPriceList(List<PriceModel> list) {
        mPriceList = list;
        return this;
    }

    public ChartView setLeftLabel(String leftLabel) {
        mLeftLabel = leftLabel;
        return this;
    }


    public ChartView setRightLabel(String rightLabel) {
        mRightLabel = rightLabel;
        return this;
    }

    public ChartView setChartColor(int chartColor) {
        mChartColor = chartColor;
        return this;
    }

    public void init() {
        mLeftTxt.setText(mLeftLabel);
        mRightTxt.setText(mRightLabel);
        mLeftTxt.setTextColor(mChartColor);
        mRightTxt.setTextColor(mChartColor);
        initChart();
    }

    private void initChart() {

        mChart.setTouchEnabled(false);
        mChart.getXAxis().setEnabled(mWithLegend);
        //去掉纵向网格线和顶部边线
        mChart.getXAxis().setDrawAxisLine(false);
        mChart.getXAxis().setDrawGridLines(false);
        // 设置横向标签位置
        mChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        // 设置底部标签数量
        mChart.getXAxis().setLabelCount(mPriceList.size(), true);
        mChart.getXAxis().setValueFormatter((value, axis) -> {
            String str = mContext.getResources().getString(R.string.price_chart_label);
            return String.format(str, (int) value);
        });

        // 隐藏图例
        mChart.getLegend().setEnabled(false);
        mChart.setDescription(null);
        // 去掉左右边线
        mChart.getAxisLeft().setEnabled(false);
        mChart.getAxisRight().setEnabled(false);
        // 添加数据
        mChart.setData(getData());
    }

    /**
     * 构造绘制数据
     *
     * @return LineData
     */
    private LineData getData() {
        List<Entry> entries = new ArrayList<>();
        for (PriceModel price : mPriceList) {
            Entry entry = new Entry();
            entry.setX(Float.valueOf(price.getTime()));
            entry.setY(price.getPrice());
            entries.add(entry);
        }
        LineDataSet lineDataSet = new LineDataSet(entries, "");
        lineDataSet.setFillColor(ColorStateList.valueOf(mChartColor).getDefaultColor());
        lineDataSet.setDrawCircles(false);
        return new LineData(lineDataSet);
    }


}
