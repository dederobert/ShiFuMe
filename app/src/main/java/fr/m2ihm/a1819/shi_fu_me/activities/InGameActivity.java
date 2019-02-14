package fr.m2ihm.a1819.shi_fu_me.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.m2ihm.a1819.shi_fu_me.R;
import fr.m2ihm.a1819.shi_fu_me.utils.Choice;

public class InGameActivity extends AppCompatActivity {

    private Choice playerChoice;
    private Choice computerChoice;
    private int yourScore = 0;
    private int advScore = 0;

    @BindView(R.id.img_you) ImageView img_you;
    @BindView(R.id.img_adv) ImageView img_adv;
    @BindView(R.id.txt_your_score) TextView txt_your_score;
    @BindView(R.id.txt_adv_score) TextView txt_adv_score;

    @OnClick(R.id.btn_ciseaux) public void clickBtnCiseaux() {
        playerChoice = Choice.CISEAUX;
        computerChoice = Choice.randomChoice();
        setImages();
        score();
    }

    @OnClick(R.id.btn_feuille) public void clickBtnFeuille() {
        playerChoice = Choice.FEUILLE;
        computerChoice = Choice.randomChoice();
        setImages();
        score();
    }

    @OnClick(R.id.btn_pierre) public void clickBtnPierre() {
        playerChoice = Choice.PIERRE;
        computerChoice = Choice.randomChoice();
        setImages();
        score();
    }

    private void setImages() {
        img_you.setImageResource(playerChoice.getDrawable());
        img_adv.setImageResource(computerChoice.getDrawable());
    }

    private void score() {
        if (this.playerChoice.win(this.computerChoice))
            this.yourScore++;
        else if(this.computerChoice.win(this.playerChoice))
            this.advScore++;

        StringBuilder strB_adv = new StringBuilder();
        StringBuilder strB_you = new StringBuilder();

        strB_adv.append("Score adversaire ");
        strB_adv.append(this.advScore);
        this.txt_adv_score.setText(strB_adv);

        strB_you.append("Votre score ");
        strB_you.append(this.yourScore);
        this.txt_your_score.setText(strB_you);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);
        ButterKnife.bind(this);
        setTitle(R.string.app_name);

        img_adv.setRotation(180);
        this.txt_your_score.setText("Votre score  ");
         this.txt_adv_score.setText("Score adversaire  ");
    }
}
