import Utils.RomanConverter;

import java.util.Scanner;

public class Main
{
    enum Operation { multiply, divide, plus, minus }
    public static void main(String[] args)
    {
        calc();
    }
    public static void calc()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите значения:");
        String input = scanner.nextLine();

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
                operation = Operation.minus;
                data[0] = "-"+data[1];
                data[1] = data[2];
                data[2] = null;
            }
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
                if(isFirstNumberNegative)
                {
                    a = Integer.parseInt(data[0]);
                    b = Integer.parseInt(data[1]);
                    if(operation == Operation.multiply)
                    {
                        result = a*b;
                    }
                    else if(operation == Operation.divide)
                    {
                        result = a/b;
                    } else if (operation == Operation.plus)
                    {
                        result = a + b;
                    }else if (operation == Operation.minus)
                    {
                        result = a - b;
                    }
                    System.out.println(result);
                    return;
                }
                else
                {
                    a = Integer.parseInt(data[0]);
                    b = Integer.parseInt(data[1]);
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
                    System.out.println(RomanConverter.intToRoman(result));
                    return;
                }
            }
        }
        else
        {
            throw new ArrayIndexOutOfBoundsException("Числа должны быть в одном формате");
        }
        System.out.println(result);
    }
}