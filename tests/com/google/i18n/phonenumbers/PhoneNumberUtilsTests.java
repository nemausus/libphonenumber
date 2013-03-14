package com.google.i18n.phonenumbers;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class PhoneNumberUtilsTests
{
    PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    @Test
    public void test() throws IOException
    {
        FileReader fileReader = new FileReader("numbers.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String number = bufferedReader.readLine();
        int diffCount = 0;
        while (number != null)
        {
            String expected = boil(number);
            String actual = fastBoil(number);
            if (expected != null && actual != null)
            {
                if (!expected.equals(actual))
                {
                    diffCount++;
                    System.out.println("input:" + number + "\t" + "expected:" + expected + "\t" + "actual:" + actual);
                }
            }
            else if (expected != null || actual != null)
            {
                diffCount++;
                System.out.println("input:" + number + "\t" + "expected:" + expected + "\t" + "actual:" + actual);
            }
            number = bufferedReader.readLine();
        }
        assertEquals(0, diffCount);
    }

    @Test
    public void performanceTest() throws IOException
    {
        FileReader fileReader = new FileReader("numbers.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String number = bufferedReader.readLine();

        List<String> numbers = new ArrayList<String>();
        while (number != null)
        {
            numbers.add(number);
            number = bufferedReader.readLine();
        }

        long start = System.nanoTime();
        for (String num : numbers)
        {
            fastBoil(num);
        }

        System.out.println("fast time : " + (System.nanoTime() - start) / 1000000);

        start = System.nanoTime();
        for (String num : numbers)
        {
            boil(num);
        }

        System.out.println("normal time : " + (System.nanoTime() - start) / 1000000);
    }

    public String boil(String number)
    {
        try
        {
            Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.parse(number, "IN");
            if (phoneNumber != null)
            {
                return "+" + phoneNumber.getCountryCode() + "-" + phoneNumber.getNationalNumber();
            }
        }
        catch (NumberParseException e)
        {
        }
        return null;
    }

    public String fastBoil(String number)
    {
        try
        {
            return phoneNumberUtil.fastParse(phoneNumberUtil.extractDigits(number), "IN");
        }
        catch (NumberParseException e)
        {
        }
        return null;
    }
}
