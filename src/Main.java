import Utils.RomanConverter;
public class Main
{
    public static void main(String[] args)
    {
        System.out.println(calc("10+11"));
    }

    public static String calc(String input)
    {
        if(input == null)
        {
            throw new NullPointerException("Input is null");
        }
        String[] operatorChars = {"+","-","*","/"};
        String[] regexChars = {"\\+", "-","\\*", "/"};
        int operatorIndex = -1;

        for (int i = 0; i < operatorChars.length; i++)
        {
            if(input.contains(operatorChars[i]))
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
        int a,b,result = 0;
        for (int i = 2; i < data.length; i++)
        {
            if(data[i] != null)
            {
                throw new ArrayIndexOutOfBoundsException("Введено более двух операндов");
            }
        }
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
            if (a > 10 || b > 10)
            {
                throw new IllegalArgumentException("Строка должна принимать на вход числа от 1 до 10");
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
        return String.valueOf(result);
    }
}