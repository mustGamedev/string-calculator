package Utils;

import org.jetbrains.annotations.NotNull;
import java.util.TreeMap;

public abstract class RomanConverter
{
    private static final TreeMap<Character, Integer> map = new TreeMap<>();
    static
    {
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
    }

    public static int romanToInt(String romanString)
    {
        if(romanString == null || romanString.startsWith(" "))
        {
            throw new IllegalArgumentException("Пустая строка");
        }
        int result = 0;
        int length = romanString.length() - 1;
        for (int i = 0; i < length; i++)
        {
            if (map.get(romanString.charAt(i)) < map.get(romanString.charAt(i + 1)))
            {
                throw new IllegalArgumentException("Неправильный порядок");
            } else
            {
                result += map.get(romanString.charAt(i));
            }
        }
        result += map.get(romanString.charAt(length));
        return result;
    }

    public static String intToRoman(Integer intString)
    {
       final Integer[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
       final String[] romanLetters = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder result = new StringBuilder();
        for(int i=0; i<values.length; i++)
        {
            while(intString >= values[i])
            {
                intString -= values[i];
                result.append(romanLetters[i]);
            }
        }
        return result.toString();
    }

    public static boolean isRoman(@NotNull String romanNumber)
    {
        if(romanNumber.isEmpty()) { return false; }
        return map.containsKey(romanNumber.charAt(0));
    }

}