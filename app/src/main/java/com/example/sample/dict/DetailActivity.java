package com.example.sample.dict;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.content.Intent;
import android.util.TypedValue;
//import java.util.ArrayList;

import com.example.sample.dict.models.*;

public class DetailActivity extends AppCompatActivity {

    private Item item;
    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);
        container = (LinearLayout) findViewById(R.id.detail_linearlayout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        item = (Item) intent.getSerializableExtra("item");
        setWordDetail();
        setIdioms();
        setFab();
    }

    private void setWordDetail() {
        ((TextView) findViewById(R.id.word_textview)).setText(item.label);
        ((TextView) findViewById(R.id.translation_textview)).setText(item.translation);
        //((TextView) findViewById(R.id.expression_textview)).setText(item.example);

        if(!item.example.isEmpty()) {
            Space sp = new Space(this);
            sp.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    100));
            container.addView(sp);
            TextView exampleLabel = new TextView(this);
            exampleLabel.setText("Example");
            exampleLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 24);
            container.addView(exampleLabel);

            TextView example = new TextView(this);
            example.setText(item.example);
            example.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
            container.addView(example);
        }
    }

    private void setIdioms() {
        LinearLayout idiomListItem;
        Idioms idioms = new Idioms(this);
        idioms.find(item.label);
        if (idioms.size() > 0) {
            Space sp = new Space(this);
            sp.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    100));
            container.addView(sp);
            TextView idiomLabel = new TextView(this);
            idiomLabel.setText("Idiom");
            idiomLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 24);
            container.addView(idiomLabel);
        }
        for (int i = 0; i < idioms.size(); i++) {
            String idiom = idioms.get(i).label;
            String translation = idioms.get(i).translation;
            idiomListItem = (LinearLayout) getLayoutInflater().inflate(R.layout.idiom_list_item, null);
            ((TextView) idiomListItem.findViewById(R.id.idiom_textview)).setText(idiom);
            ((TextView) idiomListItem.findViewById(R.id.idiom_translation_textview)).setText(translation);
            container.addView(idiomListItem);
        }

    }

    private void setFab() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
