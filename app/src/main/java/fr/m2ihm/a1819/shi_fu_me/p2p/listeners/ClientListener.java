package fr.m2ihm.a1819.shi_fu_me.p2p.listeners;

import android.support.annotation.NonNull;

import fr.m2ihm.a1819.shi_fu_me.models.Choice;
import fr.m2ihm.a1819.shi_fu_me.models.Game;

public class ClientListener implements ClientCallBack {

    private Game game;

    public ClientListener(Game game) {
        this.game = game;
    }

    @Override
    public void onReceiveOpponentChoice(@NonNull Choice choice) {
            game.setAdvChoice(choice);
    }
}
