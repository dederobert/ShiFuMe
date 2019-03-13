package fr.m2ihm.a1819.shi_fu_me;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.m2ihm.a1819.shi_fu_me.models.Choice;
import fr.m2ihm.a1819.shi_fu_me.models.Game;

public class GameUnitTest {

    private Game game;

    @Before
    public void init() {
        game = new Game();
    }

    @Test
    public void resetScorePlayerScore0() {
        game.setPlayerChoice(Choice.randomChoice());
        game.setAdvChoice(Choice.randomChoice());
        game.updateScore();
        game.resetScore();
        Assert.assertEquals(game.getPlayerScore(), 0);
    }

    @Test
    public void resetScoreAdversaireScore0() {
        game.setPlayerChoice(Choice.randomChoice());
        game.setAdvChoice(Choice.randomChoice());
        game.updateScore();
        game.resetScore();
        Assert.assertEquals(game.getAdvScore(), 0);
    }

    @Test
    public void updateScorePlayerWin() {
        game.resetScore();
        game.setPlayerChoice(Choice.CISEAUX);
        game.setAdvChoice(Choice.FEUILLE);
        game.updateScore();
        Assert.assertEquals(game.getPlayerScore(), 1);
    }

    @Test
    public void updateScoreAdvWin() {
        game.resetScore();
        game.setAdvChoice(Choice.FEUILLE);
        game.setPlayerChoice(Choice.PIERRE);
        game.updateScore();
        Assert.assertEquals(game.getAdvScore(), 1);
    }

    @Test
    public void updateScorePlayerEqual() {
        game.resetScore();
        game.setPlayerChoice(Choice.FEUILLE);
        game.setAdvChoice(Choice.FEUILLE);
        game.updateScore();
        Assert.assertEquals(game.getPlayerScore(), 0);
    }

    @Test
    public void updateScoreAdvEqual() {
        game.resetScore();
        game.setPlayerChoice(Choice.FEUILLE);
        game.setAdvChoice(Choice.FEUILLE);
        game.updateScore();
        Assert.assertEquals(game.getAdvScore(), 0);
    }
}
