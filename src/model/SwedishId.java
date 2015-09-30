package model;

public class SwedishId { // format used is a String "YYMMDD-NNNN" , Note 11 digits with '-' in the 6th digit!.

    String firstPart = "";
    String secondPart = "";

    public SwedishId (String newSwedishId){ //Constructor
        if (SwedishId.isCorrect(newSwedishId)){
            firstPart = getFirstPart(newSwedishId);
            secondPart = getSecondPart(newSwedishId);
        } else {
            System.out.println("ERROR!: " + newSwedishId + " is not a valid Swedish Number!"); //Error handling goes here
        }
    }

    public static String getFirstPart(String swedishID) // First Part
    {
        String firstPart = swedishID.substring(0, 6);
        return firstPart;
    }

    public static String getSecondPart(String swedishID) // Second Part
    {
        String secondPart = swedishID.substring(7, 11);
        return secondPart;
    }

    public static boolean checkGender (String swedishID) // Check if the person is Female (true) or Male (false)
    {
        char check = swedishID.charAt(9);
        int aux = Character.getNumericValue(check);
        boolean checkGender = aux % 2 == 0;
        return checkGender;
    }

  /*
     public static boolean isEqual(String swedishID, String swedishID2) { // Check if both Swedish ID numbers match
        return swedishID.equals(swedishID2);
    }

    */ //this could be usefull later on

    @Override
    public String toString(){
        return firstPart + "-" + secondPart;
    }

    public static boolean isCorrect (String swedishID) { //We check if the swedishNumber is Valid
        boolean valid = true;

        if (swedishID.length() != 11) { //check if the String has 11 digits
            valid = false;
            return valid;
        }

        int aux;
        char aux2;

        aux2 = swedishID.charAt(6); // this could be '+' if the person is older than 100 years old
        if (aux2 != '-' && aux2 != '+'){
            valid = false;
            return valid;
        }

        int month;  //Check if the month is valid
        aux2 = swedishID.charAt(2);
        aux = Character.getNumericValue(aux2);
        aux2 = swedishID.charAt(3);
        aux = aux * 10 + Character.getNumericValue(aux2);
        if (aux < 1 || aux > 12){
            valid = false;
            return valid;
        } else {
            month = aux;
        }

        aux2 = swedishID.charAt(4); //Check is the day is valid
        aux = Character.getNumericValue(aux2);
        aux2 = swedishID.charAt(5);
        aux = aux * 10 + Character.getNumericValue(aux2);

        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12 ){	//months with up to 31 days
            if (aux < 0 || aux > 31){
                valid = false;
                return valid;
            }
        }

        if (month == 4 || month == 6 || month == 9 || month == 11){	//months with up to 30 days
            if (aux < 0 || aux > 30){
                valid = false;
                return valid;
            }
        }

        if (month == 2){	//February is the only month with 28 or 29 days.
            if (aux < 0 || aux > 29){
                valid = false;
                return valid;
            }
        }

        int checksum = 0;  //Check if the checksum digit is valid

        aux2 = swedishID.charAt(0);
        aux = Character.getNumericValue(aux2) * 2; // first digit
        if (aux > 9){
            checksum = (aux/10)+ aux % 10;
        } else {
            checksum = aux;
        }

        aux2 = swedishID.charAt(1);
        aux = Character.getNumericValue(aux2) * 1; // second digit
        if (aux > 9){
            checksum = checksum + (aux/10) + aux % 10;
        } else {
            checksum = checksum + aux;
        }

        aux2 = swedishID.charAt(2);
        aux = Character.getNumericValue(aux2) * 2; // third digit
        if (aux > 9){
            checksum = checksum + (aux/10) + aux % 10;
        } else {
            checksum = checksum + aux;
        }

        aux2 = swedishID.charAt(3);
        aux = Character.getNumericValue(aux2) * 1; // fourth digit
        if (aux > 9){
            checksum = checksum + (aux/10) + aux % 10;
        } else {
            checksum = checksum + aux;
        }

        aux2 = swedishID.charAt(4);
        aux = Character.getNumericValue(aux2) * 2; // fifth digit
        if (aux > 9){
            checksum = checksum + (aux/10) + aux % 10;
        } else {
            checksum = checksum + aux;
        }

        aux2 = swedishID.charAt(5);
        aux = Character.getNumericValue(aux2) * 1; // sixth digit
        if (aux > 9){
            checksum = checksum + (aux/10) + aux % 10;
        } else {
            checksum = checksum + aux;
        }

        // NB: from now on digits match together, since 6th of the String is '-'.

        aux2 = swedishID.charAt(7);
        aux = Character.getNumericValue(aux2) * 2; // seventh digit
        if (aux > 9){
            checksum = checksum + (aux/10) + aux % 10;
        } else {
            checksum = checksum + aux;
        }

        aux2 = swedishID.charAt(8);
        aux = Character.getNumericValue(aux2) * 1; // eighth digit
        if (aux > 9){
            checksum = checksum + (aux/10) + aux % 10;
        } else {
            checksum = checksum + aux;
        }

        aux2 = swedishID.charAt(9);
        aux = Character.getNumericValue(aux2) * 2; // ninth digit
        if (aux > 9){
            checksum = checksum + (aux/10) + aux % 10;
        } else {
            checksum = checksum + aux;
        }

        checksum = 10 - (checksum % 10); // tenth digit
        checksum = checksum % 10;

        aux2 = swedishID.charAt(10);
        aux = Character.getNumericValue(aux2);

        if (aux != checksum){ // compare 10th digit with checksum to see if they match
            valid = false;
            return valid;
        }
        return valid;
    }
}
