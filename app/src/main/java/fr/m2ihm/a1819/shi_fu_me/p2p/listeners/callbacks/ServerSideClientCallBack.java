package fr.m2ihm.a1819.shi_fu_me.p2p.listeners.callbacks;

import fr.m2ihm.a1819.shi_fu_me.models.Choice;

public interface ServerSideClientCallBack {
    void onReceivePlayerChoice(Choice choice, int clientIndex);
}
