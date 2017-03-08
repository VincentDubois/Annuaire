package com.hexagonalgames.annuaire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity implements TextView.OnEditorActionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        EditText searchView = (EditText) findViewById(R.id.search);
        searchView.setOnEditorActionListener(this);
/*

*/
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
        if ( actionId == EditorInfo.IME_ACTION_DONE ||
                ( event != null && event.getAction() == KeyEvent.ACTION_DOWN &&
                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) ) {

            // Traitement de l'évènement
            EditText txtname = (EditText)findViewById(R.id.search);
            String search      =  txtname.getText().toString();

            Intent intent = new Intent(this, PersonneActivity.class);
            startActivity(intent);

            return true;
        }
        return false;
    }
}
