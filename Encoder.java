public class Encoder implements EncoderInterface {
    private ReferenceTable refTable;

    public Encoder(ReferenceTable refTable) {
        this.refTable = refTable;
    }

    public void setRefTable(ReferenceTable refTable) {
        this.refTable = refTable;
    }

    @Override
    public String encode(String plainText) {
        String res = "";
        res += refTable.getOffsetChar();

        for (int i = 0; i < plainText.length(); i++) {
            res += refTable.getEncryptionOf(plainText.charAt(i));
        }

        return res;
    }

    @Override
    public String decode(String encodedText) {
        char offsetChar = encodedText.charAt(0);
        String encodedStr = encodedText.substring(1);
        String res = "";

        refTable.setOffsetChar(offsetChar);

        for (int i = 0; i < encodedStr.length(); i++) {
            res += refTable.getDecryptionOf(encodedStr.charAt(i));
        }
        return res;
    }
}
