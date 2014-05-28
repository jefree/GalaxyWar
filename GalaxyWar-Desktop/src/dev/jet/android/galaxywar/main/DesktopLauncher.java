package dev.jet.android.galaxywar.main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopLauncher {
        public static void main (String[] args) {
                new LwjglApplication(new GalaxyWar(), "Galaxy War", 788, 480);
        }
}
