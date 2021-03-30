package org.djr.cdi.converter;

import java.util.Scanner;

public class LoadResourceUtil {
    private LoadResourceUtil() {

    }

    public static String getResourceFromFile(String filename) {
        return new Scanner(LoadResourceUtil.class.getResourceAsStream(filename), "UTF-8").useDelimiter("\\A").next();
    }
}
