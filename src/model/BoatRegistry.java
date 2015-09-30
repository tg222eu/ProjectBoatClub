package model;

import java.util.ArrayList;

public class BoatRegistry {

    static ArrayList<Boat> boats = new ArrayList<Boat>();

    public BoatRegistry (){
    }

    public void registerBoat (String memberId, double newSize, Type newType, String newDescription){

        Boolean registered = false;

        for (int i = 0; i < UserRegistry.members.size(); i++){

            if (UserRegistry.members.get(i).getMemberId().equals(memberId)){
                UserRegistry.members.get(i).boats.add(new Boat(newSize, newType, newDescription));
                registered = true;
                i = UserRegistry.members.size() + 1;
            }
        }

        if (registered){
            System.out.println ("\nSUCCESS!: The model.Boat has been successfully registered in " + memberId + "'s registry!.");
        } else {
            System.out.println ("\nERROR!: model.Member Id " + memberId + " could not be found in the registry.");
        }
    }

    public void deleteBoat (String memberId, String boatId){
        Boolean memberFound = false;
        Boolean BoatDeleted = false;

        for (int i = 0; i < UserRegistry.members.size(); i++){ // find user

            if (UserRegistry.members.get(i).getMemberId().equals(memberId)){

                for (int j = 0; j < UserRegistry.members.get(i).boats.size(); j++){ // find boat
                    if (UserRegistry.members.get(i).boats.get(j).getBoatId().equals(boatId)){
                        UserRegistry.members.get(i).boats.remove(j);
                        BoatDeleted = true;
                        j = UserRegistry.members.get(i).boats.size() + 1;
                    }
                }

                memberFound = true;
                i = UserRegistry.members.size() + 1;
            }
        }
        if (!memberFound){
            System.out.println ("\nERROR!: model.Member Id " + memberId + " could not be found in the registry!.");
        } else {
            if (!BoatDeleted){
                System.out.println ("\nERROR!: model.Boat Id " + boatId + " could not be found in the model.Member Id " + memberId  + " 's boat registry!.");
            } else {
                System.out.println("\nSUCCESS!: The model.Boat with id " + boatId + " has been removed from model.Member Id " + memberId  +" 's boat registry!.");
            }
        }
    }

    public void editBoat (String memberId, String boatId, double newSize, Type newType, String newDescription){
        Boolean memberFound = false;
        Boolean BoatEdited = false;

        for (int i = 0; i < UserRegistry.members.size(); i++){ // find member

            if (UserRegistry.members.get(i).getMemberId().equals(memberId)){

                for (int j = 0; j < UserRegistry.members.get(i).boats.size(); j++){ // find boat
                    if (UserRegistry.members.get(i).boats.get(j).getBoatId().equals(boatId)){

                        UserRegistry.members.get(i).boats.get(j).setSize(newSize);
                        UserRegistry.members.get(i).boats.get(j).setType(newType);
                        UserRegistry.members.get(i).boats.get(j).setDescription(newDescription);
                        UserRegistry.members.get(i).boats.get(j).generateBoatId(); // changes to a new boat ID only if the boat type has changed

                        BoatEdited = true;
                        j = UserRegistry.members.get(i).boats.size() + 1;
                    }
                }
                memberFound = true;
                i = UserRegistry.members.size() + 1;
            }
        }
        if (!memberFound){
            System.out.println ("\nERROR!: model.Member Id " + memberId + " could not be found in the registry!.");
        } else {
            if (!BoatEdited){
                System.out.println ("\nERROR!: model.Boat Id " + boatId + " could not be found in the model.Member's Id " + memberId  + " boat registry!.");
            } else {
                System.out.println("\nSUCCESS!: The model.Boat with id " + boatId + " information has been updated successfully in members id " + memberId + " boat registry!");
            }
        }
    }
}
