package org.example;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.example.view.Home.home;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

        home();
    }
}
