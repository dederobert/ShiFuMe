package fr.m2ihm.a1819.shi_fu_me;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import fr.m2ihm.a1819.shi_fu_me.models.Choice;
import fr.m2ihm.a1819.shi_fu_me.p2p.Client;
import fr.m2ihm.a1819.shi_fu_me.p2p.Server;

public class ClientServerUnitTest {
    Server server;
    Client player;
    Client opponent;

    @Before
    public void before() {
        server = new Server(null);
        server.start();
        try {
            player = new Client(null, Inet4Address.getLocalHost(), true);
            player.run();
            opponent = new Client(null, Inet4Address.getLocalHost(), false);
            opponent.run();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void playerSendChoiceUnitTest() {
        player.setOwnChoice(Choice.CISEAUX);
        player.sendChoice();
        Assert.assertTrue(server.getChoicePlayer1() != null || server.getChoicePlayer2() != null);
    }
}
