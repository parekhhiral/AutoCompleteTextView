package com.example.autocompletetextviewproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.autocompletetextviewproject.adapters.CustomAdapter;
import com.example.autocompletetextviewproject.model.MyObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView autoTextView;
    private List<String> stores;
    private List<MyObject> myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createList();
        createMyList();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, stores);
        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.list_row, myList);

        autoTextView = findViewById(R.id.atv);
        autoTextView.setAdapter(customAdapter);
        autoTextView.setOnItemClickListener((adapterView, view, position, id) -> {
            MyObject myObject = (MyObject) adapterView.getItemAtPosition(position);
            Toast.makeText(MainActivity.this,
                    "Clicked Store: "
                            + myObject.getStoreName()
                    , Toast.LENGTH_SHORT).show();
        });
    }

    private void createMyList() {
        myList = new ArrayList<>();
        myList.add(new MyObject("TMobile", "Tampa"));
        myList.add(new MyObject("Target", "Topeka"));
        myList.add(new MyObject("Amp", "Alabama"));
        myList.add(new MyObject("Ahold", "Arkansas"));
    }

    private void createList() {
        stores = new ArrayList<>();
        stores.add("TMobile");
        stores.add("Target");
        stores.add("Amp");
        stores.add("Ahold");
    }
}
