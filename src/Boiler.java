import com.google.i18n.phonenumbers.NumberParseException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Boiler
{
    public static void main(String[] args) throws IOException, NumberParseException
    {
        Reader reader = new Reader("numbers.txt");
        Writer writer = new Writer(new FileOutputStream("output.txt"), true);

        PhoneNumberBoiler phoneNumberBoiler = new PhoneNumberBoiler();
        List<String> numbers = new ArrayList<String>();
        List<String> boiledNumbers = new ArrayList<String>();

        String number = reader.nextLine();
        while (number != null)
        {
            numbers.add(number);
            number = reader.nextLine();
        }


        long start = System.nanoTime();
        for (String raw_number : numbers)
        {
            String in = phoneNumberBoiler.getBoiledNumber(raw_number);
            boiledNumbers.add(in);
        }
        System.out.println("time " + (System.nanoTime() - start) / 1000000);


        for (String phoneNumber : boiledNumbers)
        {
            writer.append(phoneNumber).append("\n");
        }

        writer.finish();
    }

}
