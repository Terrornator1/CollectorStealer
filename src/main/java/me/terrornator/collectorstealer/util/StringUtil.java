package me.terrornator.collectorstealer.util;

import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtil {
    private StringUtil() {}


    public static String replace(String input, String... replacements) {
        if (replacements.length % 2 != 0) {
            throw new IllegalArgumentException("replacements must be even");
        }

        for (int i = 0; i < replacements.length; i += 2) {
            input = input.replaceAll(replacements[i], replacements[i + 1]);
        }

        return input;
    }

    public static String replace(String input, Object... replacements) {
        if (replacements.length % 2 != 0) {
            throw new IllegalArgumentException("replacements must be even");
        }

        for (int i = 0; i < replacements.length; i += 2) {
            input = input.replaceAll(replacements[i].toString(), replacements[i + 1].toString());
        }

        return input;
    }

    public static String colorize (String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }


    public static String format(String input, String... replacements) throws IllegalArgumentException {
        return colorize(replace(input, replacements));
    }

    public static String format(String[] input, String... replacements) throws IllegalArgumentException {
        StringBuilder output = new StringBuilder();
        for (String line : input) {
            output.append(format(line, replacements)).append("\n");
        }
        return output.toString();
    }

    public static String format(List<String> input, String... replacements) throws IllegalArgumentException {
        StringBuilder output = new StringBuilder();
        for (String line : input) {
            output.append(format(line, replacements)).append("\n");

        }
        return output.toString();
    }

    public static String[] formatToArray(String[] input, String... replacements) throws IllegalArgumentException {
        return Arrays.stream(input).map(line -> format(line, replacements)).toArray(String[]::new);
    }

    public static List<String> formatToList(List<String> input, String... replacements) throws IllegalArgumentException {
        List<String> output = new ArrayList<>();
        for (String line : input) {
            output.add(format(line, replacements));
        }
        return output;
    }

}
