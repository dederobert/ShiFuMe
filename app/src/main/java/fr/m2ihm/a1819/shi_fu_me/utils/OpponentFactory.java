package fr.m2ihm.a1819.shi_fu_me.utils;

import android.support.annotation.NonNull;

import fr.m2ihm.a1819.shi_fu_me.models.Game;

public final class OpponentFactory {
    public static @NonNull Opponent getOpponent(final Game.GameType gameType) {
        switch (gameType) {
            case MULTIPLAYER: return new PlayerOpponent();
            case SINGLEPLAYER: default: return new ComputerOpponent();
        }
    }
}
