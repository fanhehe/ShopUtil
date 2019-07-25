package com.fanhehe.util.type;

import java.util.regex.Pattern;
import com.fanhehe.util.regexp.Uid;
import com.fanhehe.util.regexp.Email;
import com.fanhehe.util.regexp.Phone;

public final class Type {

    public static boolean isUid(String uid) {
        return Pattern.compile(Uid.BASE.getRegexp()).matcher(uid).matches();
    }

    public static boolean isEmail(String email) {
        return Pattern.compile(Email.BASE.getRegexp()).matcher(email).matches();
    }

    public static boolean isPhone(String phone) {
        for (Phone exp: Phone.values()) {
            Pattern pattern = Pattern.compile(exp.getRegexp());

            if (pattern.matcher(phone).matches()) {
                return true;
            }
        }

        return false;
    }
}
