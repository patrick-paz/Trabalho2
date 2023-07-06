package com.example.trabalho2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends BaseAdapter {

    private List<String> items = new ArrayList<>();
    private Context context;

    public ItemAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public int insertItem (String item){
        items.add(item);
        notifyDataSetChanged();
        return items.size() - 1;
    }

    public void updateItem (int pos, String item){
        items.set(pos, item);
        notifyDataSetChanged();
    }

    public void deleteItem (int pos){
        items.remove(pos);
        notifyDataSetChanged();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_adapter, parent,false);
        TextView textoItem = (TextView) v.findViewById(R.id.txtItem);
        textoItem.setText(items.get(position));

        return v;
    }
}
