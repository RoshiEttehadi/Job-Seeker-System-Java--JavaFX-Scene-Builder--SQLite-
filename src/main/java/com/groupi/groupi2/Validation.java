package com.groupi.groupi2;

/**
 * File Name: Validation.java
 * @author Team I (For Job Seeker System project, ITO5136 Software Engineering TP6-2022, Monash University)
 * Last Modified On: 2022-NOV-26
 * Description: Class containing methods used to validate user's input.
 */
public class Validation
{

    public Validation()
    {
    }

    /**
     * Method to check if a string is blank.
     * @return boolean true if string is blank, false if string is not blank.
     *
     */
    public boolean stringIsBlank(String value)
    {
        boolean blank = false;
        if (value.trim().length() == 0)
            blank = true;
        return blank;
    }

    /**
     * Method to check if a string character count is within a specified range.
     * @return boolean true if the string is within the specified range, false if the string is not within the specified range.
     *
     */
    public boolean stringLengthWithinRange(String value, int min, int max)
    {
        boolean withinRange = false;
        if ((value.trim().length() >= min) && (value.trim().length() <= max))
            withinRange = true;
        return withinRange;
    }

    /**
     * Method to check if a string is structured as an email address.
     * @return boolean true if the string is structured as an email address, false if the string is not structured as an email address.
     *
     */
    public boolean checkEmailIsValid(String value)
    {
        boolean validEmail = false;
        String email_regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        if (value.matches(email_regex))
            validEmail = true;
        return validEmail;
    }

    /**
     * Method to check if a integer input can be parsed as an integer and whether the number of its digits match what is intended.
     * @return boolean true if a integer input can be parsed as an integer an the nunber of its digits is equal to the required number set each time, otherwise return false.
     *
     */
    public boolean numberDigits(int value, int numberDigits)
    {
        String stringValue;
        boolean digits = false;
        try
        {
            //intValue = Integer.parseInt(value);
            //int length = String.valueOf(intValue).length();
            stringValue = Integer.toString(value);
            int length = String.valueOf(stringValue).length();
            if (numberDigits == length)
            {
                digits = true;
            }
        } catch (NumberFormatException e) {
            //System.out.println("Input value cannot be parsed to integer value.");
        }
        return digits;
    }



}


