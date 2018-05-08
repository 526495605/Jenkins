package com.ykyy.server.util;

/**
 * @author lenovo
 */
public class IDCardUtil
{
    public static Boolean isIDCard(String idCard)
    {
        String reg = "^\\d{15}$|^\\d{17}[0-9Xx]$";
        if (!idCard.matches(reg)) {
            return false;
        }
        return true;
    }

}
