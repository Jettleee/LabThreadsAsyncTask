package com.labthreadsasynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Déclaration des variables avec le suffixe 'Charaf'
    private TextView labelInfoCharaf;
    private ProgressBar indicateurProgresCharaf;
    private ImageView vueImageCharaf;

    // Handler pour les mises à jour UI depuis un thread
    private Handler handlerPrincipalCharaf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Ajustement des Insets pour le conteneur principal modifié
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_container_charaf), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Liaison des composants avec les nouveaux IDs
        labelInfoCharaf = findViewById(R.id.charaf_label_status);
        indicateurProgresCharaf = findViewById(R.id.charaf_progress_indicator);
        vueImageCharaf = findViewById(R.id.charaf_display_image);

        Button btnThreadCharaf = findViewById(R.id.charaf_btn_load_thread);
        Button btnAsyncCharaf = findViewById(R.id.charaf_btn_compute_async);
        Button btnToastCharaf = findViewById(R.id.charaf_btn_show_toast);

        // Initialisation du Handler Charaf
        handlerPrincipalCharaf = new Handler(Looper.getMainLooper());

        // Bouton Toast : vérifie que l'UI reste fluide
        btnToastCharaf.setOnClickListener(v ->
                Toast.makeText(getApplicationContext(), "L'interface est réactive ! (Charaf)", Toast.LENGTH_SHORT).show()
        );

        // Chargement d'image par un Thread séparé
        btnThreadCharaf.setOnClickListener(v -> demarrerChargementThreadCharaf());

        // Calcul lourd via une AsyncTask personnalisée
        btnAsyncCharaf.setOnClickListener(v -> new CharafCalculTask().execute());
    }

    // -----------------------------------------
    // PARTIE 1 : UTILISATION DE THREAD
    // -----------------------------------------
    private void demarrerChargementThreadCharaf() {
        indicateurProgresCharaf.setVisibility(View.VISIBLE);
        indicateurProgresCharaf.setProgress(0);
        labelInfoCharaf.setText("Action : Chargement via Thread Charaf...");

        new Thread(() -> {
            try {
                // Pause simulée
                Thread.sleep(1500);
            } catch (InterruptedException error) {
                error.printStackTrace();
            }

            // Récupération de l'image bitmap
            Bitmap imageGenereeCharaf = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

            // Mise à jour de l'UI en utilisant le Handler
            handlerPrincipalCharaf.post(() -> {
                vueImageCharaf.setImageBitmap(imageGenereeCharaf);
                indicateurProgresCharaf.setVisibility(View.INVISIBLE);
                labelInfoCharaf.setText("Résultat : Image chargée par le Thread");
            });
        }).start();
    }

    // -----------------------------------------
    // PARTIE 2 : UTILISATION DE L'ASYNCTASK
    // -----------------------------------------
    private class CharafCalculTask extends AsyncTask<Void, Integer, Long> {

        @Override
        protected void onPreExecute() {
            indicateurProgresCharaf.setVisibility(View.VISIBLE);
            indicateurProgresCharaf.setProgress(0);
            labelInfoCharaf.setText("Action : Calcul lourd Charaf en cours...");
        }

        @Override
        protected Long doInBackground(Void... voids) {
            long totalCharaf = 0;

            for (int indexCharaf = 1; indexCharaf <= 100; indexCharaf++) {
                // Algorithme de simulation de charge
                for (int boucleCharaf = 0; boucleCharaf < 250000; boucleCharaf++) {
                    totalCharaf += (indexCharaf * boucleCharaf) % 6;
                }

                // Envoi de l'avancement
                publishProgress(indexCharaf);
            }
            return totalCharaf;
        }

        @Override
        protected void onProgressUpdate(Integer... progres) {
            indicateurProgresCharaf.setProgress(progres[0]);
        }

        @Override
        protected void onPostExecute(Long resultatFinalCharaf) {
            indicateurProgresCharaf.setVisibility(View.INVISIBLE);
            labelInfoCharaf.setText("Action : Calcul terminé. Total = " + resultatFinalCharaf);
        }
    }
}
