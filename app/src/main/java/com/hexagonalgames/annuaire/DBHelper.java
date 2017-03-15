package com.hexagonalgames.annuaire;

import android.content.Context;
import android.database.Cursor;
import android.util.StringBuilderPrinter;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Gestion de la base de données
 *
 * Cette classe hérite de SQLiteAssetHelper, qui n'est pas une classe de la bibliothèque standart.
 * Pour l'ajouter, il a fallu indiquer dans build.gradle du module où la trouver grace aux lignes:
 * suivantes :
 *
 * dependencies {
 *     compile 'com.readystatesoftware.sqliteasset:sqliteassethelper:2.0.1'
 * }
 *
 * Pour éviter de charger plusieurs fois la base dans chaque activité de l'application, on utilise
 * un singleton (db), auquel on accède par getDBHelper() au lieu du constructeur
 *
 *
 * Created by dubois on 22/01/17.
 */
public class DBHelper extends SQLiteAssetHelper {

    // nom de la base (dans assets/databases )
    private static final String DATABASE_NAME = "annuaire.sqlite";
    private static final int DATABASE_VERSION = 1;

    // Requètes SQL. Les paramètres sont à remplacer par ?
    private static final String GET_ALL_QUERY = "SELECT _id, nom, prenom, mail FROM personne;";
    private static final String GET_PERSONNE_BY_ID = "SELECT _id, nom, prenom, mail FROM personne WHERE _id=?;";
    private static final String GET_PERSONNE_BY_FAVORITE = "SELECT _id,nom , prenom FROM personne WHERE favoris = 1;";
    private static final String GET_WEBSITE_BY_TOPIC = "SELECT web.titre,web.url,matiere.nom from web INNER JOIN correspond ON web._id= correspond._idWeb INNER JOIN matiere ON correspond._idMatiere=matiere._id WHERE matiere._id = ?;";
    private static final String AJOUT_PERSONNE_FAVORIS = " UPDATE personne SET favoris = 1 where _id=?;";
    private static final String SUPPR_PERSONNE_FAVORIS = " UPDATE personne SET favoris = null where _id=?;";

    //private static final String GET_ALL_BY_ID = "SELECT personne.nom,prenom, mail,matiere.nom FROM personne INNER JOIN enseigne ON personne._id = enseigne._idPersonne INNER JOIN matiere ON enseigne._idMatiere = matiere._id WHERE personne._id = ?;";




    // Utilisation d'un singleton pour éviter d'avoir plusieurs instances de cette classe.
    private static DBHelper db = null;

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Obtenir le singleton
    public static synchronized DBHelper getDBHelper(Context context){
        if (db == null) { // Si c'est le premier appel,
            db = new DBHelper(context); // alors on crée la base
        }
        return db;
    }

    public Cursor getAll(){
        return getReadableDatabase().rawQuery(GET_ALL_QUERY,null);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////
// MES REQUÊTES

// Requête qui retourne les profs en favoris pour JD & Clement <3

    public Cursor getFavorite(){ return getReadableDatabase().rawQuery(GET_PERSONNE_BY_FAVORITE,null);}

    // Requête qui ajoute des profs en favoris

    public void AjoutPersonneFavoris(long id){
        Cursor c= getWritableDatabase().rawQuery(AJOUT_PERSONNE_FAVORIS,null);
        c.moveToFirst();
        c.close();
    }
    // Requête qui retire les profs des favoris

    public void SupprPersonneFavoris(long id){
        Cursor c= getWritableDatabase().rawQuery(SUPPR_PERSONNE_FAVORIS,null);
        c.moveToFirst();
        c.close();
    }

    // Requête qui retourne les sites en fonction des matières et vice versa pour Flo <3

    public Cursor getWebsiteTopic(String matiere){
        return getReadableDatabase().rawQuery(GET_WEBSITE_BY_TOPIC,
                new String[]{matiere} // Les paramètres de la requète sont passés comme un
                // tableau de String. Il faut donc convertir l'entier long, d'où le Long.toString()
        );
    }
////////////////////////////////////////////////////////////////////////////////////////////////////


    public Cursor getPersonneByID(long id){
        return getReadableDatabase().rawQuery(GET_PERSONNE_BY_ID,
                new String[]{Long.toString(id)} // Les paramètres de la requète sont passés comme un
                // tableau de String. Il faut donc convertir l'entier long, d'où le Long.toString()
        );
    }
}
