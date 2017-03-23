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
        //private static final String GET_WEBSITE_BY_TOPIC = "SELECT web.titre,web.url,matiere.nom from web INNER JOIN correspond ON web._id= correspond._idWeb INNER JOIN matiere ON correspond._idMatiere=matiere._id WHERE matiere._id = ?;";

        // On créer un adaptateur, chargé de convertir les lignes du résultats en vues
        String[] columnSite = {"titre","url"}; // les noms des champs utilisés
        int[] columnId = {R.id.textViewTitre,R.id.textViewUrl};
        // les id des vues à utiliser, dans le même ordre que les champs
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.site_element, // La mise en page de chaque ligne
                c, // les données à afficher
                columnSite, // champs à afficher
                columnId, // dans quelles vues
                0); // options

        // On récupère le listView à configurer
        ListView listViewAlgo = (ListView) findViewById(R.id.listViewAlgo);
        ListView listViewAndroid = (ListView) findViewById(R.id.listViewAndroid);
        ListView listViewBDD = (ListView) findViewById(R.id.listViewBDD);
        ListView listViewGitHub = (ListView) findViewById(R.id.listViewGitHub);
        ListView listViewHtmlCSS = (ListView) findViewById(R.id.listViewHtmlCSS);
        ListView listViewJava = (ListView) findViewById(R.id.listViewJava);
        ListView listViewJS = (ListView) findViewById(R.id.listViewJS);
        ListView listViewReseaux = (ListView) findViewById(R.id.listViewReseaux);
        ListView listViewDivers = (ListView) findViewById(R.id.listViewDivers);

        //ListView listView = (ListView) findViewById(R.id.listView_web);

        //listView.setAdapter(adapter); // on définit le contenu
       // listView.setOnItemClickListener(this); // on écoute les clics sur les lignes
        listViewAlgo.setAdapter(adapter); // on définit le contenu
        listViewAndroid.setAdapter(adapter); // on définit le contenu
        listViewBDD.setAdapter(adapter); // on définit le contenu
        listViewGitHub.setAdapter(adapter); // on définit le contenu
        listViewHtmlCSS.setAdapter(adapter); // on définit le contenu
        listViewJava.setAdapter(adapter); // on définit le contenu
        listViewJS.setAdapter(adapter); // on définit le contenu
        listViewReseaux.setAdapter(adapter); // on définit le contenu
        listViewDivers.setAdapter(adapter); // on définit le contenu
    }
}
