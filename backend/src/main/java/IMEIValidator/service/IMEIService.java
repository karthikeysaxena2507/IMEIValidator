package IMEIValidator.service;

import org.springframework.stereotype.Service;

@Service
public class IMEIService {

    public static final String INVALID_LENGTH = "IMEI Code must have exactly 15 digits";
    public static final String INVALID_DIGIT = "IMEI Code must contain only digits (0 - 9)";
    public static final String VALID = "IMEI Code is valid";

    public boolean isDigit(char c) {
        if (c >= 48 && c <= 57) {
            return true;
        }
        return false;
    }

    public int getSum(int n) {
        int sum = 0;
        while (n != 0) {
            sum += (n % 10);
            n = (n / 10);
        }
        return sum;
    }

    public String getCorrectIMEICodeMessage(int remainder, int lastDigit) {
        int newDigit = (lastDigit - remainder);
        if (newDigit < 0) {
            newDigit += 10;
        }
        return "last digit must be replaced with " + newDigit + " to make the IMEI code valid";
    }

    public String checkIMEI(String imeiCode) {
        int len = imeiCode.length();
        int digSum = 0;
        if (len != 15) {
            return INVALID_LENGTH;
        }
        for (int i = len - 1; i >= 0; i--) {
            if (!isDigit(imeiCode.charAt(i))) {
                return INVALID_DIGIT;
            }
            int d = imeiCode.charAt(i) - 48;
            int value = d;
            if ((i % 2) != 0) {
                d = (d * 2);
                value = getSum(d);
            }
            digSum += value;
        }
        if ((digSum % 10) == 0) {
            return VALID;
        }
        return getCorrectIMEICodeMessage(digSum % 10, imeiCode.charAt(14) - 48);
    }
}
