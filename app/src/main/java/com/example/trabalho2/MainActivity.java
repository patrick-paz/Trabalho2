package com.example.trabalho2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, ActionMode.Callback, ItemDialog.OnItemListener {

    private ListView list;
    private ItemAdapter adapter;
    private boolean insertMode;
    private int selectedItem;
    private String selectedItemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.lista);
        adapter = new ItemAdapter(this);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.act_add){
            ItemDialog dialog = new ItemDialog();
            dialog.show(getSupportFragmentManager(),"itemDialog");
            insertMode = true;

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectedItem = position;
        selectedItemName = (String) adapter.getItem(position);
        view.setBackgroundColor(Color.GREEN);
        startActionMode(this);
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_delete){
            adapter.deleteItem(selectedItem);
            mode.finish();
            return true;
        } else if (id == R.id.action_edit){
            ItemDialog dialog = new ItemDialog();
            dialog.setItem(selectedItemName);
            dialog.show(getSupportFragmentManager(), "itemDialog");
            insertMode = false;
            mode.finish();
            return true;
        } else{
            return false;
        }
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        View v = list.getChildAt(selectedItem);
        v.setBackgroundColor(Color.TRANSPARENT);

    }

    @Override
    public void onItem(String item) {
        if (insertMode) {
            adapter.insertItem(item);
        } else {
            adapter.updateItem(selectedItem, item);
        }
    }
}