package fr.m2ihm.a1819.shi_fu_me.p2p.listeners;

import android.support.annotation.NonNull;

import fr.m2ihm.a1819.shi_fu_me.models.Choice;

public interface ClientCallBack {
    public void onReceiveOpponentChoice(@NonNull Choice choice);
}
