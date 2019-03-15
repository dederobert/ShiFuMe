package fr.m2ihm.a1819.shi_fu_me;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import fr.m2ihm.a1819.shi_fu_me.models.Choice;
import fr.m2ihm.a1819.shi_fu_me.p2p.Common;

public class MessageHeaderUnitTest {

    @Test
    public void checkSndPlayerChocie() {
        String message = Common.MessageHeader.SND_PLAYER_CHOICE + ":" + Choice.CISEAUX;

        Assert.assertTrue(Common.MessageHeader.SND_PLAYER_CHOICE.checkResponse(message));

        Assert.assertFalse(Common.MessageHeader.RCV_PLAYER_CHOICE.checkResponse(message));
        Assert.assertFalse(Common.MessageHeader.END.checkResponse(message));
        Assert.assertFalse(Common.MessageHeader.RESET.checkResponse(message));
    }


    @Test
    public void checkRcvPlayerChocie() {
        String message = Common.MessageHeader.RCV_PLAYER_CHOICE + ":" + Choice.CISEAUX;

        Assert.assertTrue(Common.MessageHeader.RCV_PLAYER_CHOICE.checkResponse(message));

        Assert.assertFalse(Common.MessageHeader.SND_PLAYER_CHOICE.checkResponse(message));
        Assert.assertFalse(Common.MessageHeader.END.checkResponse(message));
        Assert.assertFalse(Common.MessageHeader.RESET.checkResponse(message));
    }

    @Test
    public void checkEnd() {
        String message = Common.MessageHeader.END + ":" + new RandomString(8, ThreadLocalRandom.current()).nextString();

        Assert.assertTrue(Common.MessageHeader.END.checkResponse(message));

        Assert.assertFalse(Common.MessageHeader.SND_PLAYER_CHOICE.checkResponse(message));
        Assert.assertFalse(Common.MessageHeader.RCV_PLAYER_CHOICE.checkResponse(message));
        Assert.assertFalse(Common.MessageHeader.RESET.checkResponse(message));
    }

    @Test
    public void testExtractInfo() {
        String message = Common.MessageHeader.RCV_PLAYER_CHOICE + ":" + Choice.CISEAUX;

        Assert.assertNotNull(Common.MessageHeader.RCV_PLAYER_CHOICE.extractInfo(message));
        Assert.assertEquals(Choice.valueOf(Common.MessageHeader.RCV_PLAYER_CHOICE.extractInfo(message)), Choice.CISEAUX);
    }
}
