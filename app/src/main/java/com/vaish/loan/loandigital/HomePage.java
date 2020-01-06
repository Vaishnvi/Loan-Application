package com.hackersdigital.loan.loandigital;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BubbleChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private LineChart lineChart;
    private LinearLayout linearLayout;
    private BarChart barchart;
    private ScatterChart scatterChart;
    private PieChart pieChart;
    private BubbleChart bubbleChart;
    private Button btline,btbar,btpie;
    String xValues[]={"first","second","third","fourth","fifth","sixth"};
    GridLayout maingrid;
    //drawLinechart(yValues,xValues);
    float yValues[]={10,20,30,0,40,60};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        lineChart=(LineChart)findViewById(R.id.linechart);
        barchart= (BarChart) findViewById(R.id.barchart);
        btline=(Button)findViewById(R.id.linechartbtn);
        btpie=(Button)findViewById(R.id.barbtn);
        linearLayout=findViewById(R.id.linearlayout);
        maingrid=(GridLayout) findViewById(R.id.View);

        setSingleEvent(maingrid);
        //cust = findViewById(R.id.cu)
        drawBarchart(yValues,xValues);
        btline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lineChart.setVisibility(View.VISIBLE);
                barchart.setVisibility(View.GONE);
                drawLinechart(yValues,xValues);
            }
        });
        btpie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lineChart.setVisibility(View.GONE);
                barchart.setVisibility(View.VISIBLE);
                drawBarchart(yValues,xValues);
            }
        });
    }
    private void setSingleEvent(GridLayout maingrid)
    {
        for(int i=0;i<maingrid.getChildCount();i++)
        {
            CardView cardView=(CardView) maingrid.getChildAt(i);
            final int finalI=i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Intent intent=new Intent(MainActivity.this,places.class);
                    String tosend="";
                    if(finalI==0)
                    {
                        tosend="Customers";
                        Intent intent=new Intent(HomePage.this,CustomerPage.class);
                        intent.putExtra("info",tosend);
                        startActivity(intent);
                    }
                    else if(finalI==1)
                    {
                        tosend="Employee";
                        Intent intent=new Intent(HomePage.this,EmployeePage.class);
                        intent.putExtra("info",tosend);
                        startActivity(intent);
                    }
                    else if(finalI==2)
                    {
                        tosend="LendPage";
                        Intent intent=new Intent(HomePage.this,LendOrFirstPayPage.class);
                        intent.putExtra("info",tosend);
                        startActivity(intent);
                    }
                    else if(finalI==3) {
                        tosend = "History";
                        Intent intent=new Intent(HomePage.this,HistoryPage.class);
                        intent.putExtra("info",tosend);
                        startActivity(intent);
                    }

                }
            });
        }
    }
    private void drawBarchart(float yValues[],String xValues[] )
    {
        barchart.setContentDescription("Bar Chart");
        ArrayList<BarEntry> yData=new ArrayList<>();
        for(int i=0;i<yValues.length;i++)
        {
            yData.add(new BarEntry(yValues[i],i));
        }
        ArrayList<String> xData=new ArrayList<>();
        for(int i=0;i<xValues.length;i++)
        {
            xData.add(xValues[i]);
        }

        BarDataSet barDataSet=new BarDataSet(yData,"Collection");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData barData=new BarData(xData,barDataSet);
        barData.setValueTextColor(Color.BLACK);
        barData.setValueTextSize(13f);
        barchart.setData(barData);
        barchart.invalidate();

    }
    private void drawLinechart(float yValues[],String xValues[] )
    {
        lineChart.setContentDescription("Line Chart");
        ArrayList<Entry> yData=new ArrayList<>();
        for(int i=0;i<yValues.length;i++)
        {
            yData.add(new Entry(yValues[i],i));
        }
        ArrayList<String> xData=new ArrayList<>();
        for(int i=0;i<xValues.length;i++)
        {
            xData.add(xValues[i]);
        }

        LineDataSet lineDataSet=new LineDataSet(yData,"Collection");
        lineDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        LineData lineData=new LineData(xData,lineDataSet);
        lineData.setValueTextColor(Color.BLACK);
        lineData.setValueTextSize(13f);
        lineChart.setData(lineData);
        lineChart.invalidate();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
