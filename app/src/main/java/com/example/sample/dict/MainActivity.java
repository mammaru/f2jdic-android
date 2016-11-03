package com.example.sample.dict;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.app.Activity;
import android.text.TextWatcher;
import android.text.Editable;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.content.Intent;
import android.util.Log;
import java.util.ArrayList;
import com.example.sample.dict.models.*;
import com.example.sample.dict.views.WordAdapter;

public class MainActivity extends AppCompatActivity implements TextWatcher {
    private ListView lv;
    //private EditText et;
    private Words words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.v(this.getLocalClassName(), String.valueOf(R.id.list_item_textview));
        lv = (ListView) findViewById(R.id.item_listview);
        Words words = new Words(this);
        words.find("avoir");
        setItemList(words);
        EditText et = (EditText)this.findViewById(R.id.search_input_edittext);
        et.addTextChangedListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setItemList(Words words) {
        WordAdapter adapter = new WordAdapter(this, R.layout.list_item, words);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listview = (ListView) parent;
                Item item = (Item) listview.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                //intent.putStringArrayListExtra("item", item.toArrayList());
                intent.putExtra("item", item);
                startActivity(intent);
            }
        });
        //lv.setAdapter(adapter);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        Log.v(TextWatcher.class.getSimpleName(), "beforeTextChanged s: " + s.toString());
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.v(TextWatcher.class.getSimpleName(), "onTextChanged s: " + s.toString());
        //Words words = new Words(MainActivity.this);
        //words.find(s.toString());
        //setItemList(words);
    }

    @Override
    public void afterTextChanged(Editable s) {
        Log.v(TextWatcher.class.getSimpleName(), "afterTextChanged s: " + s.toString());
        Words words = new Words(this);
        words.find(s.toString());
        setItemList(words);
    }
}
