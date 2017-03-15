package com.hexagonalgames.annuaire;

import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class LiensActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liens);

        // On exécute la requète sur la base
        Cursor c = DBHelper.getDBHelper(this).getAll();

        // On créer un adaptateur, chargé de convertir les lignes du résultats en vues
        String[] columnSite = {"nom","mail"}; // les noms des champs utilisés
        int[] columnId = {R.id.textViewTitre,R.id.textViewUrl};
        // les id des vues à utiliser, dans le même ordre que les champs
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.site_element, // La mise en page de chaque ligne
                c, // les données à afficher
                columnSite, // champs à afficher
                columnId, // dans quelles vues
                0); // options

        // On récupère le listView à configurer
        ListView listView = (ListView) findViewById(R.id.listView_web);
        listView.setAdapter(adapter); // on définit le contenu
       // listView.setOnItemClickListener(this); // on écoute les clics sur les lignes
    }
}
