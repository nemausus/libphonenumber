import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.io.FileOutputStream;
import java.io.IOException;

public class Boiler
{
    public static void main(String[] args) throws IOException, NumberParseException
    {
        Reader reader = new Reader("large_input");
        Writer writer = new Writer(new FileOutputStream("large_output"), true);

        PhoneNumberUtil util = PhoneNumberUtil.getInstance();

        String number = reader.nextLine();
        long start = System.nanoTime();
        while (number != null)
        {
            Phonenumber.PhoneNumber phoneNumber = util.parse(number, "IN");
            writer.append("+" + phoneNumber.getCountryCode()).append("-" + phoneNumber.getNationalNumber()).append("\n");
            number = reader.nextLine();
        }

        System.out.println("time " + (System.nanoTime() - start)/1000000);

        writer.finish();
    }
}
