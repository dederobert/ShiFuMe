package fr.m2ihm.a1819.shi_fu_me.activities;

import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.widget.TextView;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.m2ihm.a1819.shi_fu_me.R;
import fr.m2ihm.a1819.shi_fu_me.models.Game;
import fr.m2ihm.a1819.shi_fu_me.p2p.Client;
import fr.m2ihm.a1819.shi_fu_me.p2p.Common;
import fr.m2ihm.a1819.shi_fu_me.p2p.Server;
import fr.m2ihm.a1819.shi_fu_me.p2p.WiFiDirectBroadcastReceiver;
import fr.m2ihm.a1819.shi_fu_me.models.Choice;
import fr.m2ihm.a1819.shi_fu_me.utils.AndroidDrawableResource;
import fr.m2ihm.a1819.shi_fu_me.utils.AndroidImageViewDrawer;
import fr.m2ihm.a1819.shi_fu_me.utils.Opponent;
import fr.m2ihm.a1819.shi_fu_me.utils.OpponentFactory;

/**
 * Activité affichée pendant le jeu
 * @version 1.0.0
 */
public class InGameActivity extends AppCompatActivity {

    final Game.GameType GAME_TYPE = Game.GameType.SINGLEPLAYER;

    Game game = new Game();
    Opponent opponent = OpponentFactory.getOpponent(GAME_TYPE);

    AndroidImageViewDrawer imgDrawerPlayer;
    AndroidImageViewDrawer imgDrawerOpponent;

    @BindView(R.id.img_you) AppCompatImageView img_you;
    @BindView(R.id.img_adv) AppCompatImageView img_adv;

    @BindView(R.id.txt_your_score) TextView txt_your_score;
    @BindView(R.id.txt_adv_score) TextView txt_adv_score;

    private WiFiDirectBroadcastReceiver receiver;
    private IntentFilter intentFilter;

    private Server server;
    private Client client;

    /**
     * Callback du clique sur le bouton ciseaux
     */
    @OnClick(R.id.btn_ciseaux) public void clickBtnCiseaux() {
        game.setPlayerChoice(Choice.CISEAUX);
        game.setAdvChoice(opponent.getChoice());
        game.updateScore();
        updateUi();
    }

    /**
     * Callback du clique sur le bouton feuille
     */
    @OnClick(R.id.btn_feuille) public void clickBtnFeuille() {
        game.setPlayerChoice(Choice.FEUILLE);
        game.setAdvChoice(opponent.getChoice());
        game.updateScore();
        updateUi();
    }

    /**
     * Callback du clique sur le boutton pierre
     */
    @OnClick(R.id.btn_pierre) public void clickBtnPierre() {
        game.setPlayerChoice(Choice.PIERRE);
        game.setAdvChoice(opponent.getChoice());
        game.updateScore();
        updateUi();
    }

    /**
     * Met à jours l'affichage en fonction des scores et des choix des joueurs
     */
    private void updateUi() {
        imgDrawerPlayer.drawResource((AndroidDrawableResource) game.getPlayerChoice().getRessource());
        imgDrawerOpponent.drawResource((AndroidDrawableResource) game.getAdvChoice().getRessource());
        StringBuilder strB_adv = new StringBuilder();
        StringBuilder strB_you = new StringBuilder();

        strB_adv.append("Score adversaire ");
        strB_adv.append(this.game.getAdvScore());
        this.txt_adv_score.setText(strB_adv);

        strB_you.append("Votre score ");
        strB_you.append(this.game.getPlayerScore());
        this.txt_your_score.setText(strB_you);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);
        ButterKnife.bind(this);
        setTitle(R.string.app_name);

        imgDrawerPlayer = new AndroidImageViewDrawer(img_you);
        imgDrawerOpponent = new AndroidImageViewDrawer(img_adv);

        WifiP2pManager manager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        assert manager != null;
        WifiP2pManager.Channel channel = manager.initialize(this, getMainLooper(), null);
        receiver = new WiFiDirectBroadcastReceiver(manager, channel, this);

        intentFilter = new IntentFilter();
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

        updateUi();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }


    public AppCompatImageView getImg_you() {
        return img_you;
    }

    public AppCompatImageView getImg_adv() {
        return img_adv;
    }

    public TextView getTxt_your_score() {
        return txt_your_score;
    }

    public TextView getTxt_adv_score() {
        return txt_adv_score;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
