package com.google.i18n.phonenumbers;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
        while (number != null)
        {
            assertEquals(boil(number), fastBoil(number));
            number = bufferedReader.readLine();
        }
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
            return null;
        }
        return null;
    }

    public String fastBoil(String number)
    {
        try
        {
            Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.fastParse(number, "IN");
            if (phoneNumber != null)
            {
                return "+" + phoneNumber.getCountryCode() + "-" + phoneNumber.getNationalNumber();
            }
        }
        catch (NumberParseException e)
        {
            return null;
        }
        return null;
    }
}
