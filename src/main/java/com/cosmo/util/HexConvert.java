package com.cosmo.util;

public class HexConvert {

    public static String convertStringToHex(String str) {
        char[] chars = str.toCharArray();
        StringBuffer hex = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            hex.append(Integer.toHexString((int) chars[i]));
        }
        return hex.toString();
    }

    public static String convertHexToString(String hex) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < hex.length() - 1; i += 2) {
            String s = hex.substring(i, (i + 2));
            int decimal = Integer.parseInt(s, 16);
            sb.append((char) decimal);
            sb2.append(decimal);
        }
        return sb.toString();
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        // toUpperCase將字串中的所有字轉為大寫
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        // toCharArray將字串轉換為陣列
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static short bytes2Short2(byte[] b) {
        short i = (short) (((b[1] & 0xff) << 8) | b[0] & 0xff);
        return i;
    }

    // 將byte轉換為16進制
    public static String BinaryToHexString(byte[] bytes) {
        String hexStr = "0123456789ABCDEF";
        String result = "";
        String hex = "";
        for (byte b : bytes) {
            hex = String.valueOf(hexStr.charAt((b & 0xF0) >> 4));
            hex += String.valueOf(hexStr.charAt(b & 0x0F));
            result += hex + " ";
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("======ASCII碼轉換為16進位======");
        String str = "*00007VERSION\\n1$";
        System.out.println("字串: " + str);
        String hex = HexConvert.convertStringToHex(str);
        System.out.println("====轉換為16進位=====" + hex);
        System.out.println("======16進位轉換為ASCII======");
        System.out.println("Hex : " + hex);
        System.out.println("ASCII : " + HexConvert.convertHexToString(hex));
        byte[] bytes = HexConvert.hexStringToBytes(hex);
        System.out.println(HexConvert.BinaryToHexString(bytes));
    }
}
