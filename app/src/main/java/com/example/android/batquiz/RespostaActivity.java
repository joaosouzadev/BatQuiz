package com.example.android.batquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RespostaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resposta);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.icon2);

        ImageView imgResposta = (ImageView) findViewById(R.id.imgResposta);
        TextView resposta = (TextView) findViewById(R.id.resposta);
        Button btnJogarNovamente = (Button) findViewById(R.id.btnJogarNovamente);

        Intent intent = getIntent();
        int pontos = intent.getIntExtra("pontos", 0);

        // right answer
        if (intent.hasExtra("acertou")) {
            btnJogarNovamente.setVisibility(View.INVISIBLE);
            boolean acertou = intent.getBooleanExtra("acertou", false);
            if (acertou) {
                imgResposta.setImageResource(R.drawable.acertou1);
                resposta.setText("Correto! \nTotal de pontos: " + pontos);
            } else { // wrong answer
                imgResposta.setImageResource(R.drawable.errou1);
                resposta.setText("Errado! \nTotal de pontos: " + pontos);
            }

            // set the time to load another question
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            });
            thread.start();
        }
        else { // end of questions, game is finished
            btnJogarNovamente.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Você fez " + pontos + " pontos!", Toast.LENGTH_SHORT).show();

            if (pontos >= 12) {
                imgResposta.setImageResource(R.drawable.verynice);
                resposta.setText("Você acertou " + pontos + " de 16. \nVocê é um Bat-nerd! Parabéns!!!");
                resposta.setTextSize(20);
            } if (pontos < 12){
                imgResposta.setImageResource(R.drawable.nice);
                resposta.setText("Você acertou " + pontos + " de 16. \nVocê está por dentro do mundo do Batman, bom trabalho!");
                resposta.setTextSize(20);
            } if (pontos < 8) {
                imgResposta.setImageResource(R.drawable.medium);
                resposta.setText("Você acertou " + pontos + " de 16. \nUm bom resultado se você apenas viu os filmes! \nBoa tentativa!");
                resposta.setTextSize(20);
            } if (pontos < 5) {
                imgResposta.setImageResource(R.drawable.bad);
                resposta.setText("Você acertou " + pontos + " de 16. \nNem um pouco fã do Batman, né? \nNão cometa crimes a noite! ;)");
                resposta.setTextSize(20);
            }}
    }

    // method to start to play again
    public void btnJogarNovamenteOnClick(View view){
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
        finish();
    }

}
