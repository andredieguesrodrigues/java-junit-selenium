package utils;

import java.util.ArrayList;

public class Support {

    public static boolean checkRepeatedValue(ArrayList input) {
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.size(); j++) {
                if (input.get(i).equals(input.get(j)) && i != j) {
                    return true;
                }
            }
        }
        return false;
    }
}
