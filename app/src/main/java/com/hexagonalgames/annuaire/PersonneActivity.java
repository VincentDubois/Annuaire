package com.hexagonalgames.annuaire;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PersonneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personne);

        // On récupère l'attribut id passé lors du lancement de l'activité
        long id = getIntent().getLongExtra("id",-1);

        // On la lance requète sur la base pour cet id
        Cursor cursor = DBHelper.getDBHelper(this).getPersonneByID(id);

        // On se place sur la première (et normalement unique) ligne du résultat
        cursor.moveToFirst();

        //On récupère (si ils existent) les champs voulus
        String result;
        if (!cursor.isAfterLast()) {
            String nom = cursor.getString(cursor.getColumnIndex("nom"));
            String prenom = cursor.getString(cursor.getColumnIndex("prenom"));
            String mail = cursor.getString(cursor.getColumnIndex("mail"));

            result = nom + " " + prenom + " : " + mail;
        } else result = "Error : empty result !!";

        // On récupère le textview utilisé pour afficher le résultat
        TextView textView = (TextView) findViewById(R.id.textViewResultat);
        // On affiche le résultat de la requète.
        textView.setText(result);
    }
}
