package org.zaccarias.fernandes.christian.aramis;

import org.zaccarias.fernandes.christian.aramis.cliloop.MainLoop;
import org.zaccarias.fernandes.christian.aramis.enums.CargosEnum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main{
    public static void main(String[] args) throws IOException {
        MainLoop mainLoop = new MainLoop();
        try {
            mainLoop.runMainLoop();
        } catch (Exception exception) {
            mainLoop.runMainLoop();
        }
    }
}