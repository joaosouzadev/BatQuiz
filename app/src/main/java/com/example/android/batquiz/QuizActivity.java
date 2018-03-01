package com.example.android.batquiz;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    TextView pergunta;
    RadioGroup rgRespostas;
    RadioButton rbResposta1, rbResposta2, rbResposta3, rbResposta4;
    int respostaCerta = R.id.rbResposta1;
    int pontos;

    // array of questions
    List<Questao> questoes = new ArrayList<Questao>() {
        {
            add(new Questao("Batman é o alter-ego de qual playboy milionário?", R.id.rbResposta4, "Thomas Wayne", "Bill Gates", "Tony Stark", "Bruce Wayne"));
            add(new Questao("Quem é o criador oficial do Batman?", R.id.rbResposta2, "Tim Burton", "Bob Kane", "Frank Miller", "Christopher Nolan"));
            add(new Questao("Qual vilão recebe a fama de ter quebrado as costas do Batman e o deixado paraplégico?", R.id.rbResposta3, "Duas-Caras", "Espantalho", "Bane", "Hugo Strange"));
            add(new Questao("Qual desses super-heróis co-existem no mesmo mundo de Batman?", R.id.rbResposta4, "Hulk", "Wolverine", "Homem-Aranha", "Superman"));
            add(new Questao("Quem é o autor do famoso arco \"O Cavaleiro das Trevas Ressurge\"?", R.id.rbResposta2, "Bob Kane", "Frank Miller", "Stan Lee", "Jeph Loeb"));
            add(new Questao("Qual vilão se refere à Batman como \"Detetive\"?", R.id.rbResposta1, "Ra's Ah Ghul", "Coringa", "Charada", "Máscara-Negra"));
            add(new Questao("Quem é o pai da Batgirl?", R.id.rbResposta1, "Jim Gordon", "Lucius Fox", "Alfred", "Batman"));
            add(new Questao("O Batman tem muitos meios de transporte. Em qual filme ele usa apenas o Batmóvel para se locomover?", R.id.rbResposta4, "Batman Forever", "Batman and Robin", "Batman Returns", "Batman Begins"));
            add(new Questao("Quem é considerado pelo Batman como 'o homem mais perigoso do mundo'?", R.id.rbResposta1, "Hugo Strange", "Coringa", "Ra's Ah Ghul", "Espantalho"));
            add(new Questao("Quando é o Aniversário de Bruce Wayne?", R.id.rbResposta3, "1 de Março", "20 de Maio", "19 de Fevereiro", "Nunca foi revelado"));
            add(new Questao("Qual vilão se originou das Animações e não das HQ's?", R.id.rbResposta2, "Coringa", "Arlequina", "Chapeleiro Louco", "Exterminador"));
            add(new Questao("Qual foi o primeiro ator a interpretar o Coringa nos cinemas?", R.id.rbResposta3, "Christian Bale", "Jared Leto", "Jack Nicholson", "Heath Ledger"));
            add(new Questao("O que faz o dispositivo que equipa as botas do Batman no filme 'Batman Begins'?", R.id.rbResposta4, "Chama o Batmóvel", "Chama policias para ajudá-lo", "Ensurdece seus inimigos", "Convoca uma multidão de morcegos"));
            add(new Questao("Em que ano foi lançado o primeiro 'Batman' no cinema?", R.id.rbResposta3, "1990", "1989", "1966", "1985"));
            add(new Questao("Qual dos apelidos abaixo não pertence ao Batman?", R.id.rbResposta4, "O Cruzado Encapuzado", "Morcego", "O Cavaleiro das Trevas", "A Sombra"));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        pergunta = (TextView) findViewById(R.id.pergunta);
        rbResposta1 = (RadioButton) findViewById(R.id.rbResposta1);
        rbResposta2 = (RadioButton) findViewById(R.id.rbResposta2);
        rbResposta3 = (RadioButton) findViewById(R.id.rbResposta3);
        rbResposta4 = (RadioButton) findViewById(R.id.rbResposta4);
        rgRespostas = (RadioGroup) findViewById(R.id.rgRespostas);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.icon2);
    }

    // method to refresh the screen and show new question when the previous got answered
    @Override
    protected void onRestart() {
        super.onRestart();
        carregarQuestao();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.icon);
    }

    // method to check the radiogroup for the right answer
    public void btnResponderOnClick(View view) {
        RadioButton rb = (RadioButton) findViewById(rgRespostas.getCheckedRadioButtonId());
        Intent intent = new Intent(this, RespostaActivity.class);
        if (rgRespostas.getCheckedRadioButtonId() == respostaCerta) {
            intent.putExtra("acertou", true);
            pontos++;
        } else intent.putExtra("acertou", false);
        intent.putExtra("pontos", pontos);
        startActivity(intent);
        rb.setChecked(false);
        rgRespostas.clearCheck(); // unchecking the radiobutton to start the new question with no buttons checked
    }

    // method to store questions in the app memory
    public class Questao {

        private String pergunta;
        private List<String> respostas = new ArrayList<>();
        private int respostaCerta;

        public Questao(String pergunta, int respostaCerta, String... respostas) {
            this.pergunta = pergunta;
            this.respostas.add(respostas[0]);
            this.respostas.add(respostas[1]);
            this.respostas.add(respostas[2]);
            this.respostas.add(respostas[3]);
            this.respostaCerta = respostaCerta;
        }

        public String getPergunta() {
            return this.pergunta;
        }

        public List<String> getRespostas() {
            return this.respostas;
        }

        public int getRespostaCerta() {
            return this.respostaCerta;
        }
    }

    // method to load a new question
    private void carregarQuestao() {
        if (questoes.size() > 0) {

            Questao q = questoes.remove(0);
            pergunta.setText(q.getPergunta());
            List<String> resposta = q.getRespostas();
            rbResposta1.setText(resposta.get(0));
            rbResposta2.setText(resposta.get(1));
            rbResposta3.setText(resposta.get(2));
            rbResposta4.setText(resposta.get(3));
            respostaCerta = q.getRespostaCerta();
            rgRespostas.setSelected(false);
        } else { // no more questions
            Intent intent = new Intent(this, RespostaActivity.class);
            intent.putExtra("pontos", pontos);
            startActivity(intent);
            finish();
        }
    }
}
