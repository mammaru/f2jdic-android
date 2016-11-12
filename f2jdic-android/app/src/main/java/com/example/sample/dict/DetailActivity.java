package com.example.sample.dict;

import java.util.Locale;

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
import android.util.Log;
import android.speech.tts.TextToSpeech;
import android.widget.Button;


import com.example.sample.dict.models.*;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener {

    private Item item;
    private LinearLayout container;
    private TextToSpeech tts; // TODO: Setting speech speed and pitch
    final private Float SPEECH_SLOW = 0.5f;
    final private Float SPEECH_NORMAL = 1.0f;
    final private Float SPEECH_FAST = 1.5f;
    final private Float PITCH_LOW = 0.5f;
    final private Float PITCH_NORMAL = 1.0f;
    final private Float PITCH_HIGH = 1.5f;
    private Button buttonSpeech;

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

        tts = new TextToSpeech(this, this);
        buttonSpeech = (Button)findViewById(R.id.ButtonToSpeech);
        buttonSpeech.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != tts) {
            tts.shutdown();
        }
    }

    @Override
    public void onInit(int status) {
        if (TextToSpeech.SUCCESS == status) {
            Locale locale = Locale.FRENCH;
            if (tts.isLanguageAvailable(locale) >= TextToSpeech.LANG_AVAILABLE) {
                tts.setLanguage(locale);
            } else {
                Log.d("", "Error SetLocale");
            }
        } else {
            Log.d("", "Error Init");
        }
    }

    private void speechText() {
        if (0 < item.label.length()) {
            if (tts.isSpeaking()) {
                // 読み上げ中なら止める
                tts.stop();
            }

            // 読み上げ開始
            tts.speak(item.label, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    private void setWordDetail() {
        ((TextView) findViewById(R.id.word_textview)).setText(item.label);
        ((TextView) findViewById(R.id.translation_textview)).setText(item.translation);

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

    private void setFab() { // TODO: Implement edit mode!
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

    @Override
    public void onClick(View v) {
        if (buttonSpeech == v) {
            speechText();
        }
    }
}
