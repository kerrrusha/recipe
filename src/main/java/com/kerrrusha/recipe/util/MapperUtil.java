package com.kerrrusha.recipe.util;

public class MapperUtil {

    public static Byte[] toByteObjects(byte[] bytePrimitives) {
        Byte[] result = new Byte[bytePrimitives.length];
        int i = 0;
        for (byte b : bytePrimitives) {
            result[i++] = b;
        }
        return result;
    }

    public static byte[] toBytePrimitives(Byte[] byteObjects) {
        byte[] result = new byte[byteObjects.length];
        int i = 0;
        for (Byte b : byteObjects) {
            result[i++] = b;
        }
        return result;
    }

}
