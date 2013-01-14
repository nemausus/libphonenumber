
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.util.ArrayList;
import java.util.List;

public class PhoneNumberBoiler
{
    private PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
    private final List<PhoneNumberUtil.PhoneNumberType> mValidCellNumbers;

    public PhoneNumberBoiler()
    {
        mValidCellNumbers = new ArrayList<PhoneNumberUtil.PhoneNumberType>();
        mValidCellNumbers.add(PhoneNumberUtil.PhoneNumberType.FIXED_LINE_OR_MOBILE);
        mValidCellNumbers.add(PhoneNumberUtil.PhoneNumberType.MOBILE);
        mValidCellNumbers.add(PhoneNumberUtil.PhoneNumberType.PERSONAL_NUMBER);
    }

    public String getBoiledNumber(String number)
    {
        try
        {
            Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.TalkToParse(number, "IN");
            if (phoneNumberUtil.isValidNumber(phoneNumber) || phoneNumberUtil.truncateTooLongNumber(phoneNumber))
            {
                if (mValidCellNumbers.contains(phoneNumberUtil.getNumberType(phoneNumber)))
                {
                    return "+" + phoneNumber.getCountryCode() + "-" + phoneNumber.getNationalNumber();
                }
            }
        }
        catch (NumberParseException ignored)
        {
        }
        return null;
    }
}
