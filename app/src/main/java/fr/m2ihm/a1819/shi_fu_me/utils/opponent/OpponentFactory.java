package fr.m2ihm.a1819.shi_fu_me.utils.opponent;

import android.support.annotation.NonNull;

import fr.m2ihm.a1819.shi_fu_me.models.Game;
import fr.m2ihm.a1819.shi_fu_me.utils.opponent.ComputerOpponent;
import fr.m2ihm.a1819.shi_fu_me.utils.opponent.Opponent;
import fr.m2ihm.a1819.shi_fu_me.utils.opponent.PlayerOpponent;

public final class OpponentFactory {

    public static @NonNull
    Opponent getOpponent(final Game.GameType gameType) {
        switch (gameType) {
            case MULTI_PLAYER: return new PlayerOpponent();
            case SINGLE_PLAYER: default: return new ComputerOpponent();
        }
    }
}
