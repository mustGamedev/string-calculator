import Utils.RomanConverter;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println(calc("I+V"));
    }
    public static String calc(String input)
    {
        String[] operatorChars = {"+","-","*","/"};
        String[] regexChars = {"\\+", "-","\\*", "/"};
        int operatorIndex = -1;

        for (int i = 0; i < operatorChars.length; i++)
        {
            if (input.contains(operatorChars[i]))
            {
                operatorIndex = i;
                break;
            }
        }
        if (operatorIndex == -1)
        {
            throw new ArrayIndexOutOfBoundsException("Выражение неверно");
        }
        String[] data = input.split(regexChars[operatorIndex]);
        if(data.length >= 3)
        {
            throw new IllegalArgumentException("Неверный формат математической операции");
        }
        int a,b,result = 0;
        if(RomanConverter.isRoman(data[0]) == RomanConverter.isRoman(data[1]))
        {
            boolean isRoman = RomanConverter.isRoman(data[0]);
            if (isRoman)
            {
                a = RomanConverter.romanToInt(data[0]);
                b = RomanConverter.romanToInt(data[1]);
            }
            else
            {
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }
            String operator = operatorChars[operatorIndex];
            switch (operator)
            {
                case "+" -> result = a + b;
                case "-" -> result = a - b;
                case "*" -> result = a * b;
                case "/" -> result = a / b;
            }
            if(isRoman)
            {
                if(result < 0)
                {
                    throw new IllegalArgumentException("В римской системе нет отрицательных чисел");
                }
                else
                {
                    return RomanConverter.intToRoman(result);
                }
            }
        }
        else
        {
            throw new ArrayIndexOutOfBoundsException("Числа должны быть в одном формате");
        }
        return Integer.toString(result);
    }
}