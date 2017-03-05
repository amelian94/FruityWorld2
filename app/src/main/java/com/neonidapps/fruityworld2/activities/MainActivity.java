package com.neonidapps.fruityworld2.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.neonidapps.fruityworld2.R;
import com.neonidapps.fruityworld2.adapters.RecyclerViewAdapter;
import com.neonidapps.fruityworld2.modelos.Fruit;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruits;

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fruits = this.getAllFruits();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewFruits);

        layoutManager = new LinearLayoutManager(this);
        recyclerViewAdapter = new RecyclerViewAdapter(fruits, this, new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Fruit fruit, int position) {
                fruit.addUnits(1);
                recyclerViewAdapter.notifyDataSetChanged();

            }
        });

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.addFruit:
                this.addFruit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    public List<Fruit> getAllFruits() {

        return new ArrayList<Fruit>() {{
            add(new Fruit("Strawberry", "Strawberry Description", 0, R.drawable.strawberry_bg, R.drawable.ic_strawberry));
            add(new Fruit("Orange", "Orange Description", 0, R.drawable.orange_bg, R.drawable.ic_orange));
            add(new Fruit("Apple", "Apple Description", 0, R.drawable.apple_bg, R.drawable.ic_apple));
            add(new Fruit("Banana", "Banana Description", 0, R.drawable.banana_bg, R.drawable.ic_banana));
            add(new Fruit("Chery", "Cherry Description", 0, R.drawable.cherry_bg, R.drawable.ic_cherry));
            add(new Fruit("Pear", "Pear Description", 0, R.drawable.pear_bg, R.drawable.ic_pear));
            add(new Fruit("Raspberry", "Raspberry Description", 0, R.drawable.raspberry_bg, R.drawable.ic_raspberry));
        }};
    }

    public void addFruit() {

        int position = fruits.size();

        this.fruits.add(position, new Fruit("Plum" + (++counter), "Fruit added by user", 0, R.drawable.plum_bg, R.drawable.ic_new_fruit));
        recyclerViewAdapter.notifyItemInserted(position);
        layoutManager.scrollToPosition(position);
    }

    public void removeFruit(int position) {

        this.fruits.remove(position);
        recyclerViewAdapter.notifyItemRemoved(position);
    }
}
