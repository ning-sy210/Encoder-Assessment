public class Main {
    public static void main(String[] args) {
        ReferenceTable refTable = null;
        Encoder encoder = null;

        char[] table = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
                'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '(', ')',
                '*', '+', ',', '-', '.', '/' };
        char[] offsetChars = new char[] { 'B', 'F' };
        String input = "HELLO WORLD";

        for (char c : offsetChars) {
            if (refTable == null && encoder == null) {
                refTable = new ReferenceTable(table, c);
                encoder = new Encoder(refTable);
            } else {
                refTable.setOffsetChar(c);
            }

            String encodedString = encoder.encode(input);
            String decodedString = encoder.decode(encodedString);

            String output = String.format(
                    "Offset Character: %c%n- Encoded: %s%n- Decoded: %s%n",
                    c, encodedString, decodedString);
            System.out.println(output);
        }
    }
}