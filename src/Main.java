import Utils.RomanConverter;
public class Main
{
    enum Operation { multiply, divide, plus, minus }
    public static void main(String[] args)
    {
        System.out.println(calc("44+11+22"));
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
        boolean isFirstNumberNegative = false;
        Operation operation = Operation.plus;

        if(input.startsWith("-"))
        {
            isFirstNumberNegative = true;
        }
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
        if (isFirstNumberNegative)
        {
            if (data[1].contains("*"))
            {
                String[] data1 = data[1].split("\\*");
                data[0] = "-"+data1[0];
                data[1] = data1[1];
                operation = Operation.multiply;
            }
            else if(data[1].contains("/"))
            {
                String[] data2 = data[1].split("/");
                data[0] = "-"+data2[0];
                data[1] = data2[1];
                operation = Operation.divide;
            }else if (operatorChars[operatorIndex].equals("-"))
            {
                data[0] = "-"+data[1];
                if (data.length == 2)
                {
                    throw new ArrayIndexOutOfBoundsException("Выражение неверно");
                }
                data[1] = data[2];
                data[2] = null;
                operation = Operation.minus;
            }
        }
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
                if(isFirstNumberNegative)
                {
                    result = switch (operation)
                      {
                        case multiply -> a * b;
                        case divide -> a / b;
                        case plus -> a + b;
                        default -> a - b;
                      };
                    return String.valueOf(result);
                }
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