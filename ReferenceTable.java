import java.util.Arrays;
import java.util.HashMap;

public class ReferenceTable {
    private char[] table;
    private char offsetChar;
    private HashMap<Character, Character> shiftTable;
    private HashMap<Character, Character> reverseShiftTable;

    public ReferenceTable(char[] table, char offsetChar) {
        this.table = table;
        this.offsetChar = offsetChar;
        generateShiftTables();
    }

    private void generateShiftTables() {
        int offset = -1;

        for (int i = 0; i < table.length; i++) {
            if (table[i] == offsetChar) {
                offset = i;
                break;
            }
        }

        if (offset <= 0) {
            shiftTable = null;
            reverseShiftTable = null;
            return;
        }

        HashMap<Character, Character> newShiftTable = new HashMap<>();
        HashMap<Character, Character> newReverseShiftTable = new HashMap<>();

        for (int i = 0; i < table.length; i++) {
            char mappedChar = table[Math.floorMod((i - offset), table.length)];
            newShiftTable.put(table[i], mappedChar);
            newReverseShiftTable.put(mappedChar, table[i]);
        }

        shiftTable = newShiftTable;
        reverseShiftTable = newReverseShiftTable;
    }

    public char getOffsetChar() {
        return offsetChar;
    }

    public char getEncryptionOf(char plainTextChar) {
        if (shiftTable == null || !shiftTable.containsKey(plainTextChar))
            return plainTextChar;

        return shiftTable.get(plainTextChar);
    }

    public char getDecryptionOf(char encodedChar) {
        if (reverseShiftTable == null || !reverseShiftTable.containsKey(encodedChar))
            return encodedChar;

        return reverseShiftTable.get(encodedChar);
    }

    public void setTable(char[] table) {
        if (Arrays.equals(this.table, table))
            return;

        this.table = table;
        generateShiftTables();
    }

    public void setOffsetChar(char offsetChar) {
        if (offsetChar == this.offsetChar)
            return;

        this.offsetChar = offsetChar;
        generateShiftTables();
    }
}
