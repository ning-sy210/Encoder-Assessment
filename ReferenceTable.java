import java.util.Arrays;
import java.util.HashMap;

public class ReferenceTable {
    private char[] table;
    private HashMap<Character, Character> shiftTable;
    private char offsetChar;

    public ReferenceTable(char[] table, char offsetChar) {
        this.table = table;
        this.offsetChar = offsetChar;
        generateShiftTable();
    }

    private void generateShiftTable() {
        int offset = -1;

        for (int i = 0; i < table.length; i++) {
            if (table[i] == offsetChar) {
                offset = i;
                break;
            }
        }

        if (offset == 0) {
            shiftTable = null;
            return;
        }
        HashMap<Character, Character> result = new HashMap<>();

        for (int i = 0; i < table.length; i++) {
            char mappedChar = table[(i - offset) % table.length];
            result.put(table[i], mappedChar);
        }

        shiftTable = result;
    }

    public char getOffsetChar() {
        return offsetChar;
    }

    public char lookup(char c) {
        if (shiftTable == null || !shiftTable.containsKey(c))
            return c;

        return shiftTable.get(c);
    }

    public void setTable(char[] table) {
        if (Arrays.equals(this.table, table))
            return;

        this.table = table;
        generateShiftTable();
    }

    public void setOffsetChar(char offsetChar) {
        if (offsetChar == this.offsetChar)
            return;

        this.offsetChar = offsetChar;
        generateShiftTable();
    }
}
