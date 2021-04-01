package com.example.api.utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorsUtil {

    public static Boolean validateEmail(String email) {
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    public static Boolean validateCEP(String cep) {
        String formatCEP = cep.replace("/", "").replace(".", "").replace("-", "").trim();

        return formatCEP.length() == 9;
    }

    public static Boolean validateCEP(ArrayList<String> cep) {

        for (String c : cep) {
            String formatCEP = c.replace("/", "").replace(".", "").replace("-", "").trim();
            if (formatCEP.length() != 8) return false;
        }
        return true;

    }
}
