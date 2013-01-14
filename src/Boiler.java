import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Boiler
{
    public static void main(String[] args) throws IOException, NumberParseException
    {
        Reader reader = new Reader("large_input");
        Writer writer = new Writer(new FileOutputStream("output"), true);

        PhoneNumberUtil util = PhoneNumberUtil.getInstance();

        List<String> numbers = new ArrayList<String>();
        List<Phonenumber.PhoneNumber> boiledNumbers = new ArrayList<Phonenumber.PhoneNumber>();

        String number = reader.nextLine();
        while (number != null)
        {
            numbers.add(number);
            number = reader.nextLine();
        }


        long start = System.nanoTime();
        for (String raw_number : numbers)
        {
            Phonenumber.PhoneNumber in = util.parse(raw_number, "IN");
            boiledNumbers.add(in);
        }
        System.out.println("time " + (System.nanoTime() - start) / 1000000);


        for (Phonenumber.PhoneNumber phoneNumber : boiledNumbers)
        {
            writer.append("+" + phoneNumber.getCountryCode()).append("-" + phoneNumber.getNationalNumber()).append("\n");
        }

        writer.finish();
    }
}
