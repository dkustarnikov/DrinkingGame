package learning.udemy.drinkinggame.Model;

import android.view.View;

public class Tile {
    private String nameOfAction;
    private boolean shortcutAvailable;

    public Tile() {};

    public Tile(String nameOfAction) {
        this.nameOfAction = nameOfAction;
        this.shortcutAvailable = false;
    }

    public Tile(String nameOfAction, boolean shortcutAvailable) {
        this.nameOfAction = nameOfAction;
        this.shortcutAvailable = shortcutAvailable;
    }

    public String getNameOfAction() {
        return nameOfAction;
    }

    public void setNameOfAction(String nameOfAction) {
        this.nameOfAction = nameOfAction;
    }

    public boolean isShortcutAvailable() {
        return shortcutAvailable;
    }

    public void setShortcutAvailable(boolean shortcutAvailable) {
        this.shortcutAvailable = shortcutAvailable;
    }


}
