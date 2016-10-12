package by.epam.tc.dom_parse;

public class StringFactory {
    private static char startSymbol = ' ';

    public static String getString(char symbol, int symbolCount, boolean isAppendStartSymbol) {
        StringBuilder stringBuilder = new StringBuilder();
        if (isAppendStartSymbol)
            stringBuilder.append(startSymbol);
        for (int i = 0; i < symbolCount; i++) {
            stringBuilder.append(symbol);
        }
        return stringBuilder.toString();
    }

    public static void setStartSymbol(char symbol) {
        startSymbol = symbol;
    }
}
