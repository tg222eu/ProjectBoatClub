package view;

import java.util.Scanner;

/**
 * Created by Jakob on 2015-09-28.
 */
public class View
{
    public char showStartMenu()
    {
        String message = "The happy pirate yacht club, what would you like to do? (Press 'q' to quit)" +
                "\n1. view.view.View information about the club " +
                "\n2. Add/delete/edit information about a member" +
                "\n3. Register/delete/edit info for a boat in the system";
        char[] startMenuOptions = {'1', '2', '3', 'q'};
        return showMenuAndReturnChosenOption(message, startMenuOptions);
    }

    public char showMemberInfoOptions()
    {
        String message = "view.view.View members information of the boat club (Enter 'q' to quit and 'h' for homescreen)"
                + "\n1. view.view.View a compact list of all members"
                + "\n2. view.view.View a verbose list of all members";
        char[] showMemberInfoOptions = {'1', '2', 'h', 'q'};
        return showMenuAndReturnChosenOption(message, showMemberInfoOptions);
    }

    public char showAddEditDeleteMemberOptions()
    {
        String message = "Edit information about a member (Enter 'q' to quit and 'h' for homescreen)"
                + "\n1. Create a new member"
                + "\n2. Delete a member"
                + "\n3. Edit info about a member";
        char[] showAddEditDeleteMemberOptions = {'1', '2', '3', 'q', 'h' };
        return showMenuAndReturnChosenOption(message, showAddEditDeleteMemberOptions);
    }

    public char showRegisterEditDeleteBoatOptions()
    {
        String message = "Edit information about a boat (Enter 'q' to quit and 'h' for homescreen)"
                + "\n1. Create a new boat"
                + "\n2. Delete a boat"
                + "\n3. Edit info about a boat";
        char[] showAddEditDeleteMemberOptions = {'1', '2', '3', 'q', 'h' };
        return showMenuAndReturnChosenOption(message, showAddEditDeleteMemberOptions);
    }

    public char showDoYouWantToSaveChanges()
    {
        String message = "Do you want to changes made to the system? (Y/N)";
        char[] yesOrNo = {'y', 'n'};
        return showMenuAndReturnChosenOption(message, yesOrNo);
    }

    public char showCompactList()
    {
        String message = "*Displaying member list in compact mode* ('q' = quit, 'h' = home)";
        char[] quitOrHome = {'q', 'h'};
        return showMenuAndReturnChosenOption(message, quitOrHome);
    }

    public char showVerboseList()
    {
        String message = "*Displaying member list in verbose view* ('q' = quit, 'h' = home)";
        char[] quitOrHome = {'q', 'h'};
        return showMenuAndReturnChosenOption(message, quitOrHome);
    }

    public void showRegisterNewBoatInstructions()
    {
        String message = "Register a new boat ('c' to cancel)" +
                "\nEnter what type of boat you want to register: " +
                "('s' = sail boat, 'm' = motorsailer, 'k' = kayak/canoe, 'o' = other, 'c' to cancel)";
        char[] options = {'s','m','k','o','c'};
        char boatType = showMenuAndReturnChosenOption(message, options);
        if (boatType != 'c')
        {
            //Continue with creating the new boat
            message = "Enter the length of the boat: (length in feet - 'c' to cancel)";
            double boatLength = showInstructionsAndReturnInputDouble(message);
            if (Math.abs(boatLength + 1) < 0.00000001)
            {
                //User chose to cancel, the returned value was -1
                System.out.println("Anv�ndaren avbr�t");
            }
            else
            {
                System.out.println("Anv�ndaren angav l�ngden: " + boatLength);
            }
        }
        else
        {
            //return a null boat
        }
        //REMEMBER IF
        //REMEMBER THIS METHOD SHOULD RETURN A BOAT
    }

    //L�gg till tv� restriction doubles f�r lower och upper bound dessutom
    private double showInstructionsAndReturnInputDouble(String instructions)
    {
        System.out.println(instructions);
        double out = -1;
        boolean done = false;
        while (!done)
        {
            String input = readStringInput();
            if (input.equalsIgnoreCase("c")) { return -1;}      //User cancelled
            if (stringCanBeConvertedToDouble(input)){ done = true; out = Double.parseDouble(input);}
            else {System.err.println("Invalid input, please try again");}
        }

        return out;
    }

    public void showCreateNewMemberInstructions()
    {
        String message = "Create a new member ('c' to cancel)"
                + "\nPlease enter first name of the member: ";
        String firstName = showOptionsAndReturnInput(message);
        String lastName = "";
        String socSecNr = "";
        if (!firstName.equalsIgnoreCase("cancel"))
        {
            //Continue with user info gathering
            message = "Enter last name of the member: - ('c' to cancel)";
            lastName = showOptionsAndReturnInput(message);
            if (!lastName.equalsIgnoreCase("cancel"))
            {
                //Get social security nr
                message = "Enter the social security nr of the member: (xxxxxx-xxxx) - ('c' to cancel)";
                socSecNr = showOptionsAndReturnInput(message);
                if (!socSecNr.equalsIgnoreCase("cancel"))
                {
                    //create the member
                    System.out.println("Medlem skapad, namn: " + firstName +", efternamn: " + lastName
                        + ", personnummer: " + socSecNr);
                }
            }
        }
        if (firstName.equalsIgnoreCase("cancel") || lastName.equalsIgnoreCase("cancel")
                || socSecNr.equalsIgnoreCase("cancel"))
        {
            System.out.println("Anv�ndaren valde att avbryta");
        }
    }

    public int showAskForIdNr(String editOrDelete)
    {
        int idNr = -1;
        String message = "";
        if (editOrDelete.equalsIgnoreCase("delete"))
        {
            message = "Enter the id of the member you want to delete ('c' to cancel)";
        }
        else
        {
            message = "Enter the id of the member you want to edit ('c' to cancel)";
        }
        idNr = getIntegerInputFromUser(message);
        return idNr;
    }

    public void showDeleteABoatInstructions()
    {
        String message = "Enter the id nr of the boat: ('c' to cancel)";
        int boatId = getIntegerInputFromUser(message);
        if (boatId != -1)
        {
            //Ask again the user with info about the boat if it want to delete the boat
            System.out.println("Anv�ndaren vill ta bort b�ten med id nr: " + boatId);
        }
        else
        {
            //User chose to cancel
            System.out.println("Anv�ndaren valde att avbryta");
        }
    }

    public void showEditABoatInstructions()
    {
        String message = "Enter the id nr of the boat: ('c' to cancel)";
        int boatId = getIntegerInputFromUser(message);
        if (boatId != -1)
        {
            //Ask again the user with info about the boat if it want to delete the boat
            System.out.println("Anv�ndaren vill modifiera b�ten med id nr: " + boatId);
        }
        else
        {
            //User chose to cancel
            System.out.println("Anv�ndaren valde att avbryta");
        }
    }

    private boolean stringCanBeConvertedToDouble(String input)
    {
        if (input.length() >= 1)
        {
            if (Character.isDigit(input.charAt(0)) || input.charAt(0) == '-')
            {
                int noPeriods = 0;
                int i = 1;
                boolean numericalOrPeriodInput = true;
                while (noPeriods <= 1 && i < input.length() && numericalOrPeriodInput)
                {
                    char c = input.charAt(i);
                    if (!Character.isDigit(c) && c != '.') {return false; }
                    else if (c == '.')
                    {
                        noPeriods++;
                        if (noPeriods >= 2) {return false; }
                    }
                    i++;
                }
                return true;
            }
            else if (input.charAt(0) == '.')
            {
                if (input.length() >= 2)
                {
                    for (int i = 1; i < input.length(); i++)
                    {
                        if (!Character.isDigit(input.charAt(i))) {return false;}
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private int getIntegerInputFromUser(String message)
    {
        System.out.println(message);
        int out = -1;
        String input = "";         //Dummy value
        boolean done = false;
        while (!done)
        {
            Scanner scan = new Scanner(System.in);
            input = scan.nextLine();
            //Check input if valid input
            input = input.trim();
            if (input.length() == 1 && input.equalsIgnoreCase("c"))
            {
                done = true;
                //cancel
            }
            else if (input.length() >= 1)
            {
                //Check if it's numerical input
                boolean isNumerical = true;
                for (int i = 0; i < input.length(); i++)
                {
                    if (!Character.isDigit(input.charAt(i)))
                    {
                        isNumerical = false;
                        break;
                    }
                }
                if (isNumerical)
                {
                    out = Integer.parseInt(input);
                    done = true;
                }
                else
                {
                    System.err.println("Invalid input, has to be a positive integer");
                }
            }
            else { System.err.println("Invalid input, has to be one or more digits");}
        }
        return out;
    }

    private String showOptionsAndReturnInput(String message)
    {
        System.out.println(message);
        return readStringInput();
    }

    private String readStringInput()
    {
        String out = "";         //Dummy value
        boolean done = false;
        while (!done)
        {
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            //Check input if valid input
            input = input.trim();
            if (input.length() == 1 && input.equalsIgnoreCase("c"))
            {
                done = true;
                out = "cancel";
                //cancel
            }
            else if (input.length() > 1) { out = input; done = true;}
            else { System.err.println("Invalid input, has to be more than one character");}
        }
        return out;
    }

    private char showMenuAndReturnChosenOption(String message, char[] validOptions)
    {
        char out = 'x';             //out dummy
        System.out.println(message);
        boolean done = false;
        while (!done)
        {
            char chosenOption = readOneCharInput();
            boolean validOptionChosen = false;
            for (int i = 0; i < validOptions.length; i++)
            {
                if (chosenOption == validOptions[i]) {out = chosenOption; done = true; validOptionChosen = true;}
            }
            if (validOptionChosen) { out = chosenOption; done = true; }
            else {System.err.println("Invalid input, please try again");}
        }
        return out;
    }

    private char readOneCharInput()
    {
        char out = 'x';         //Dummy value
        boolean done = false;
        while (!done)
        {
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            //Check input if valid input
            input = trimAndFixInput(input);
            if (input.length() == 1) { out = input.charAt(0); done = true;}
            else { System.err.println("Invalid input, please try again");}
        }
        return out;
    }

    private String trimAndFixInput(String input) {
        input = input.trim();
        input = input.toLowerCase();
        return input;
    }



}