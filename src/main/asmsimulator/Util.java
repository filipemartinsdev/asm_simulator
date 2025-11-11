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
}
