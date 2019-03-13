package fr.m2ihm.a1819.shi_fu_me;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.m2ihm.a1819.shi_fu_me.models.Game;
import fr.m2ihm.a1819.shi_fu_me.utils.Opponent;
import fr.m2ihm.a1819.shi_fu_me.utils.OpponentFactory;

public class OpponentUnitTest {
    private Opponent playerOpponent;
    private Opponent computerOpponent;


    @Before
    public void init() {
        playerOpponent = OpponentFactory.getOpponent(Game.GameType.MULTI_PLAYER);
        computerOpponent = OpponentFactory.getOpponent(Game.GameType.SINGLE_PLAYER);
    }

    @Test
    public void playerOpponentNotNull(){
        Assert.assertNotNull(playerOpponent);
    }

    @Test
    public void computerOpponentNotNull(){
        Assert.assertNotNull(computerOpponent);
    }

    @Test
    public void playerOpponentGetChoiceNotNull() {
        Assert.assertNotNull(playerOpponent.getChoice());
    }

    @Test
    public void computerOpponentGetChoiceNotNull() {
        Assert.assertNotNull(computerOpponent.getChoice());
    }
}
