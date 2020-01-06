package com.hackersdigital.loan.dailycollector;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by vaish on 09/02/19.
 */

public class CustomerList extends ArrayAdapter<Customers> {

    private Activity context;
    private List<Customers> customersList;

    public CustomerList(Activity context,List<Customers> customersList){
        super(context,R.layout.list_layout,customersList);
        this.context=context;
        this.customersList=customersList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);

        TextView custName = listViewItem.findViewById(R.id.txtViewPayeeAccNo);

        Customers customers = customersList.get(position);

        custName.setText(customers.getName());

        return listViewItem;
    }
}
