package fr.m2ihm.a1819.shi_fu_me.p2p.listeners;

import fr.m2ihm.a1819.shi_fu_me.models.Choice;

public interface ServerSideClientCallBack {
    public void onReceivePlayerChoice(Choice choice, boolean runningOnServerSide, int clientIndex);
}
