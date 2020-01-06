package com.hackersdigital.loan.loandigital;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vaish on 01/02/19.
 */

public class CustomerList extends ArrayAdapter<Customers>{

    private Activity context;
    private List<Customers> customersList;

    public CustomerList(Activity context, List<Customers> customersList){

        super(context , R.layout.list_layout,customersList);
        this.context = context;
        this.customersList = customersList;
    }

 /*   @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);

        TextView textViewCategory = (TextView) listViewItem.findViewById(R.id.txt1);
        TextView textViewValue = (TextView) listViewItem.findViewById(R.id.txt2);

        Customers customer = customersList.get(position);

        textViewCategory.setText(customer.get);

    }*/
}
