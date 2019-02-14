package fr.m2ihm.a1819.shi_fu_me.utils;
import fr.m2ihm.a1819.shi_fu_me.R;
import java.util.Random;

public enum Choice {
    PIERRE(R.drawable.pierre),
    FEUILLE(R.drawable.feuille),
    CISEAUX(R.drawable.ciseaux);

    private static Random random = new Random();

    public static Choice randomChoice() {
        return Choice.values()[random.nextInt(Choice.values().length)];
    }

    private int drawable;

    Choice(int drawable) {
        this.drawable = drawable;
    }

    public boolean win(Choice adv) {
        switch (adv) {
            case PIERRE: return this.equals(FEUILLE);
            case CISEAUX: return this.equals(PIERRE);
            case FEUILLE: return this.equals(CISEAUX);
            default: return false;
        }
    }

    public int getDrawable() {
        return drawable;
    }
}
