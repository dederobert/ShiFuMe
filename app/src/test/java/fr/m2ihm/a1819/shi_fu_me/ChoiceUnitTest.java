package fr.m2ihm.a1819.shi_fu_me;

import org.junit.Assert;
import org.junit.Test;

import fr.m2ihm.a1819.shi_fu_me.models.Choice;

public class ChoiceUnitTest {
    @Test
    public void stoneBeatScissor() {
        Assert.assertTrue(Choice.PIERRE.win(Choice.CISEAUX));
        Assert.assertFalse(Choice.CISEAUX.win(Choice.PIERRE));
    }

    @Test
    public void paperBeatStone() {
        Assert.assertTrue(Choice.FEUILLE.win(Choice.PIERRE));
        Assert.assertFalse(Choice.PIERRE.win(Choice.FEUILLE));
    }

    @Test
    public void scissorBeatPaper() {
        Assert.assertTrue(Choice.CISEAUX.win(Choice.FEUILLE));
        Assert.assertFalse(Choice.FEUILLE.win(Choice.CISEAUX));
    }

    @Test
    public void paperNotBeatPaper() {
        Assert.assertFalse(Choice.FEUILLE.win(Choice.FEUILLE));
    }

    @Test
    public void stoneNotBeatStone() {
        Assert.assertFalse(Choice.PIERRE.win(Choice.PIERRE));
    }

    @Test
    public void scissorNotBeatScissor() {
        Assert.assertFalse(Choice.CISEAUX.win(Choice.CISEAUX));
    }

}
