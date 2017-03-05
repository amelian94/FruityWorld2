package com.neonidapps.fruityworld2.adapters;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.neonidapps.fruityworld2.R;
import com.neonidapps.fruityworld2.modelos.Fruit;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Neonidas on 05/03/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Fruit> fruits;

    private Activity activity;

    private OnItemClickListener itemClickListener;

    public RecyclerViewAdapter(List<Fruit> fruits, Activity activty, OnItemClickListener listener) {
        this.fruits = fruits;
        this.activity = activty;
        this.itemClickListener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(fruits.get(position), this.itemClickListener);

    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return fruits.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        // each data item is just a string in this case
        public TextView textViewName;
        public TextView textViewDesc;
        public TextView textViewUnits;
        public ImageView imageViewIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            textViewDesc = (TextView) itemView.findViewById(R.id.textViewDesc);
            textViewUnits = (TextView) itemView.findViewById(R.id.textViewUnits);
            imageViewIcon = (ImageView) itemView.findViewById(R.id.imageViewIcon);
            itemView.setOnCreateContextMenuListener(this);
        }

        public void bind(final Fruit fruit, final OnItemClickListener listener) {
            this.textViewName.setText(fruit.getName());
            this.textViewDesc.setText(fruit.getDesc());
            this.textViewUnits.setText(fruit.getUnits() + "");

            if (fruit.getUnits() == Fruit.MAX_UNITS) {

                textViewUnits.setTextColor(ContextCompat.getColor(activity, R.color.limitAlert));
                textViewUnits.setTypeface(null, Typeface.BOLD);

            } else {
                textViewUnits.setTextColor(ContextCompat.getColor(activity, R.color.defaultTextColor));
                textViewUnits.setTypeface(null, Typeface.NORMAL);
            }

            Picasso.with(activity).load(fruit.getImageResource()).fit().into(imageViewIcon);
            //this.imageViewIcon.setImageResource(fruit.getImageResource());

            this.imageViewIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(fruit, getAdapterPosition());
//                    Toast.makeText(activity, "itemId= " + v.toString(), Toast.LENGTH_LONG).show();
                }
            });

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            Fruit fruitSelected = fruits.get(getAdapterPosition());
            menu.setHeaderTitle(fruitSelected.getName());
            menu.setHeaderIcon(fruitSelected.getIconResourse());

            MenuInflater menuInflater = activity.getMenuInflater();
            menuInflater.inflate(R.menu.context_menu_fruits, menu);

            for (int i = 0; i < menu.size(); i++) {
                menu.getItem(i).setOnMenuItemClickListener(this);
            }

        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {

            switch (item.getItemId()) {

                case R.id.resetUnits:
                    fruits.get(getAdapterPosition()).resetUnits();
                    notifyDataSetChanged();
                    return true;
                case R.id.removeFruit:
                    fruits.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return true;
                default:
                    return false;

            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Fruit fruit, int position);
    }
}