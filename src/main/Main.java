package main;

import java.util.regex.Pattern;

public class Main
{
    static class Password
    {
        int securityLevel = 0;
        String missingLength = null;
        String missingLowerCase = null;
        String missingUpperCase = null;
        String missingDigit = null;
        String missingSpecial = null;
        public Password()
        {
            securityLevel = 0;
            missingLength = "at least 8 characters";
            missingLowerCase = "at least one lower case character";
            missingUpperCase = "at least one upper case character";
            missingDigit = "at least one digit";
            missingSpecial = "at least one special character";
        }
        public String getMissing()
        {
            String result = "";
            if (missingLength != null) result += missingLength + ", ";
            if (missingDigit != null) result += missingDigit + ", ";
            if (missingSpecial != null) result += missingSpecial + ", ";
            if (missingLowerCase != null) result += missingLowerCase + ", ";
            if (missingUpperCase != null) result += missingUpperCase + ", ";
            if (result.length() == 0) return "nothing";
            else return result.substring(0, result.length()-2);
        }
    }
    public static Password getPasswordLevel(String password)
    {
        Password result = new Password();
        if (password.replaceAll("[ ]","").length() < 8) return result;
        if (password.replaceAll("[ ]","").length() >= 8) {result.missingLength = null; result.securityLevel++; }
        if (Pattern.compile("[a-z]").matcher(password).find()) {result.missingLowerCase = null; result.securityLevel++;}
        if (Pattern.compile("[A-Z]").matcher(password).find()) {result.missingUpperCase = null; result.securityLevel += 2;}
        if (Pattern.compile("[0-9]").matcher(password).find()) {result.missingDigit = null; result.securityLevel += 2;}
        if (Pattern.compile("[^A-Za-z0-9]").matcher(password).find()) {result.missingSpecial = null; result.securityLevel += 3;}
        return result;
    }
    public static String getPasswordFinalResult(Password password)
    {
        if (password.securityLevel == 0) return "Your password is invalid!\nIt is missing at least 8 characters";
        else if (password.securityLevel > 0 && password.securityLevel <= 2) return "Your password's strength is weak!\nIt is missing " + password.getMissing();
        else if (password.securityLevel > 2 && password.securityLevel <= 5) return  "Your password's strength is moderate.\nIt is missing " + password.getMissing();
        else if (password.securityLevel > 5) return "Your password's strength is strong.\nIt is missing " + password.getMissing();
        else return "Your password is invalid!";
    }
    public static void main(String[] args)
    {
        Password p = getPasswordLevel("P@swo0rd");
        System.out.print(getPasswordFinalResult(p));
    }
}
