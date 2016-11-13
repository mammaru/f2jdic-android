package com.example.sample.dict.views;

import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.content.Context;

import com.example.sample.dict.R;
import com.example.sample.dict.models.*;

/**
 * Created by yuma on 2016/11/03.
 */
public class WordAdapter extends ArrayAdapter<Item> {

    private Context _context;
    private int _textViewResourceId;
    private Words _items;
    private LayoutInflater _inflater;

    public WordAdapter(Context context, int textViewResourceId, Words items) {
        super(context, textViewResourceId, items);

        _context = context;
        _textViewResourceId = textViewResourceId;
        _items = items;

        _inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView != null) {
            view = convertView;
        } else {
            view = _inflater.inflate(_textViewResourceId, parent, false);
        }

        Item item = _items.get(position);
        ((TextView) view.findViewById(R.id.list_item_textview)).setText(item.fr);

        return view;
    }
}