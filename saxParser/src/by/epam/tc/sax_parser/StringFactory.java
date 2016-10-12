package by.epam.tc.sax_parser;

public class StringFactory {
    private final static char startSymbol = '|';

    public static String getString(char symbol, int symbolCount, boolean isAppendStartSymbol) {
        StringBuilder stringBuilder = new StringBuilder();
        if (isAppendStartSymbol)
            stringBuilder.append(startSymbol);
        for (int i = 0; i < symbolCount; i++) {
            stringBuilder.append(symbol);
        }
        return stringBuilder.toString();
    }
}
