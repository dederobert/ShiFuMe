package fr.m2ihm.a1819.shi_fu_me.utils;

import fr.m2ihm.a1819.shi_fu_me.models.Choice;
import fr.m2ihm.a1819.shi_fu_me.models.Game;

public class ComputerOpponent implements Opponent {

    @Override
    public Choice getChoice() {
        return Choice.randomChoice();
    }
}
