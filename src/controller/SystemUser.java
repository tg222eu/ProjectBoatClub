package controller;

import view.View;

/**
 * Created by Jakob on 2015-09-28.
 */
public class SystemUser
{
    public void startProgram(View a_view)
    {
        //printout tottes start greeting
        boolean doneUsingSystem = false;
        while (!doneUsingSystem)
        {
            char chosenOption = a_view.showStartMenu();
            if (chosenOption == 'q') {doneUsingSystem = true;}
            else if (chosenOption == '1')
            {
                /* view.view.View information list on members in two different modes*/
                chosenOption = a_view.showMemberInfoOptions();
                //q == quit, h == home, 1 == view.view.View compact list of all members, 2 == view.view.View verbose list of members
                if (chosenOption == 'q') {doneUsingSystem = true;}
                else if (chosenOption == '1')
                {
                    //view.view.View compact list of all members, option 'h' for home and 'q' for quit
                    //Get a list of all members on compact mode for argument to view later
                    if (a_view.showCompactList() == 'q') { doneUsingSystem = true;}
                }
                else if (chosenOption == '2')
                {
                    //view.view.View verbose list of all members, option 'h' and 'q' for quit
                    //Get a list of all members in verbose view for argument to view
                    if (a_view.showVerboseList() == 'q') { doneUsingSystem = true;}
                }
            }
            else if (chosenOption == '2') //Create, edit or delete member
            {
                chosenOption = a_view.showAddEditDeleteMemberOptions();
                if (chosenOption == 'q') {doneUsingSystem = true;}
                else if (chosenOption == '1') //Create new member, option 'h' for home and 'q' for quit
                {
                    a_view.showCreateNewMemberInstructions(); // Change it to return a new member
                    //Ask user if it wants to add the member to the boat club or discard
                }
                else if (chosenOption == '2')  //Delete a member, option 'h' and 'q' for quit
                {
                    //Only possible to delete by id number initially
                    int idOfUsToDelete = a_view.showAskForIdNr("delete");
                    //If the Id nr == -1 the user have cancelled
                    System.out.println("Id numret (delete, -1 == cancel): " + idOfUsToDelete);
                }
                else if (chosenOption == '3')
                {
                    //The user wants to edit the information about a member, option 'h' and 'q' for quit
                    int idOfUsToDelete = a_view.showAskForIdNr("edit");
                    //If the Id nr == -1 the user have cancelled
                    System.out.println("Id numret (edit, -1 == cancel): " + idOfUsToDelete);
                }
            }
            else if (chosenOption == '3')
            {
                chosenOption = a_view.showRegisterEditDeleteBoatOptions();
                //Register (create), delete or edit boat
                if (chosenOption == 'q') {doneUsingSystem = true;}
                else if (chosenOption == '1')
                {
                    a_view.showRegisterNewBoatInstructions(); // Change it to return a new boat
                    //Ask user which user it wants to register the boat to
                    //Ask if it wants to add the boat to the user or discard the new boat
                }
                else if (chosenOption == '2')
                {
                    //The user wants to delete a boat, option 'h' and 'q' for quit
                    a_view.showDeleteABoatInstructions();
                }
                else if (chosenOption == '3')
                {
                    //The user wants to edit info about a boat, option 'h' and 'q' for quit
                    a_view.showEditABoatInstructions();
                    System.out.println("Edit info on boat");
                }
            }
        }

        if (a_view.showDoYouWantToSaveChanges() == 'y')
        {
            //Save changes
            System.out.println("User wants to save changes");
        }
        else
        {
            System.out.println("User don't want to save changes");
        }
    }
}
