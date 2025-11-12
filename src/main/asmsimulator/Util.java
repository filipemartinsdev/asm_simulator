package main.asmsimulator;

public final class Util {
    public static String removeBrackets(String str){
        String newStr = "";
        char[] charArr = str.toCharArray();
        for(int i= 1; i<str.length()-1;i++){
            newStr+=charArr[i];
        }
        return newStr;
    }

    public static char[] parseBytesToChars(byte[] bytes){
        var charArr = new char[bytes.length/2];

        for(int i=0; i<bytes.length; i+=2){
            charArr[i/2] = (char)(((bytes[i] & 0xFF ) << 8) | (bytes[i+1] & 0xFF));
        }

        return charArr;
    }

    public static byte[] parseCharsToBytes(char[] chars){
        var byteArr = new byte[chars.length * 2];
        int i = 0;

        for (char c : chars) {
            byteArr[i+1] = (byte) (c & 0xFF);
            byteArr[i] = (byte) ((c >> 8) & 0xFF);
            i+=2;
        }

        return byteArr;
    }
}
