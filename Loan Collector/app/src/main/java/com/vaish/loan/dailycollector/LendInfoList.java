package com.hackersdigital.loan.dailycollector;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by vaish on 09/02/19.
 */

public class LendInfoList extends ArrayAdapter<LendMoneyInfo> {

    private Activity context;
    private List<LendMoneyInfo> lendMoneyInfoList;

    public LendInfoList(Activity context,List<LendMoneyInfo> lendMoneyInfoList){
        super(context,R.layout.list_layout,lendMoneyInfoList);
        this.context = context;
        this.lendMoneyInfoList = lendMoneyInfoList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);

        Button status = listViewItem.findViewById(R.id.statusButton);

        System.out.println(position);

        LendMoneyInfo lendMoneyInfo = lendMoneyInfoList.get(position);

        TextView custAccNo = listViewItem.findViewById(R.id.txtViewPayeeAccNo);

        custAccNo.setText(lendMoneyInfo.getAccNo().toString());

        String stat = lendMoneyInfo.getStatus();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy/MM/dd");
        String strDate = mdformat.format(calendar.getTime());


        System.out.println(strDate);

        if(!(strDate.equals(lendMoneyInfo.getDate()))){
            status.setBackgroundColor(Color.BLUE);
        }

        else if(stat.equals("unpaid")){
            status.setBackgroundColor(Color.RED);
        }
        else{
            status.setBackgroundColor(Color.GREEN);
        }

        return listViewItem;
    }

}
