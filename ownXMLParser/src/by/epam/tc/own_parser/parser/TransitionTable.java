package by.epam.tc.own_parser.parser;

public class TransitionTable {

    public static final int ELEMENT_NAME_STATE = 8;
    public static final int ATTRIBUTE_NAME_STATE = 13;
    public static final int ATTRIBUTE_VALUE_STATE = 15;
    public static final int ELEMENT_DATA_STATE = 17;

    public static final int END_WRITING_ELEMENT = 12;
    public static final int CLOSE_TAG = 21;

    public static int UNCONDITIONAL_STATE = 10;

    private static int[] unconditionalStates = {7, 10, 12, 16, 21};

    private static byte[][] transitionTable = {
            {17, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},    // 0
            {8, -1, -1, -1, 2, -1, -1, -1, -1, 19, -1, 22},     // 1
            {-1, -1, -1, 3, -1, -1, -1, -1, -1, -1, -1, -1},    // 2
            {-1, -1, -1, 4, -1, -1, -1, -1, -1, -1, -1, -1},    // 3
            {4, -1, -1, 5, -1, -1, -1, -1, -1, -1, -1, -1},     // 4
            {4, -1, -1, 6, -1, -1, -1, -1, -1, -1, -1, -1},     // 5
            {4, -1, 7, -1, -1, -1, -1, -1, -1, -1, -1, -1},     // 6
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1},    // 7
            {8, -1, 10, -1, -1, 9, -1, -1, -1, 11, -1, -1},     // 8
            {13, -1, 10, -1, -1, 9, -1, -1, -1, 11, -1, 10},    // 9
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1},    // 10
            {-1, -1, 12, -1, -1, 11, -1, -1, -1, -1, -1, -1},   // 11
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1},    // 12
            {13, -1, -1, -1, -1, -1, 14, -1, -1, -1, -1, -1},   // 13
            {-1, -1, -1, -1, -1, -1, -1, 18, 18, -1, -1, -1},   // 14
            {15, -1, -1, -1, -1, -1, -1, 16, 16, -1, -1, -1},   // 15
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 9, -1},    // 16
            {17, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},    // 17
            {15, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},   // 18
            {20, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},   // 19
            {20, -1, 21, -1, -1, -1, -1, -1, -1, -1, -1, -1},   // 20
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1},     // 21
            {22, -1, -1, -1, -1, 9, -1, -1, -1, -1, -1, -1}     // 22
    };

    public static int getLiteralNumber(int character) {
        switch((char)character) {
            case '<': return 1;
            case '>': return 2;
            case '-': return 3;
            case '!': return 4;
            case ' ': return 5;
            case '=': return 6;
            case '\'': return 7;
            case '\"': return 8;
            case '/': return 9;
            case '?': return 11;
            default: return 0;
        }
    }

    public static int getNextState(int currentState, int literalNumber) {
        return transitionTable[currentState][literalNumber];
    }

    public static boolean isUnconditional(int currentState) {
        for(int temp : unconditionalStates) {
            if (temp == currentState) {
                return true;
            }
        }
        return false;
    }
}
