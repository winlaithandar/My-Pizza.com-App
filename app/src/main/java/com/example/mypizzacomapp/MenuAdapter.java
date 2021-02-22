package com.example.mypizzacomapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends ArrayAdapter<Menu> {
    int resource;
    ArrayList<Menu> menuArrayList;
    Context context;

    public MenuAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Menu> menuList) {
        super(context, resource, menuList);
        this.context = context;
        this.resource = resource;
        this.menuArrayList = menuList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(resource, parent, false);
        }

        ImageView menu1ImageView = view.findViewById(R.id.menu1ImageView);
        TextView menuTextView = view.findViewById(R.id.menuTextView);
        TextView menuDesTextView = view.findViewById(R.id.menuDesTextView);

        Menu menu = menuArrayList.get(position);
        menu1ImageView.setImageResource(menu.getPhoto());
        menuTextView.setText(menu.getMenu());
        menuDesTextView.setText(menu.getDescriptions());

        return view;
    }
}
