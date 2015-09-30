package model;

import java.util.ArrayList;

public class UserRegistry {
	
	static ArrayList <Member> members = new ArrayList<Member>();

	public UserRegistry (){
	}

	public void addMember (String newName, String newSurname, String newSwedishNumber){

		if (SwedishId.isCorrect(newSwedishNumber)){

			if (!isRegistered(newSwedishNumber)){
				members.add(new Member(newName, newSurname, newSwedishNumber));
				System.out.println ("\nSUCCESS!: " + newName + " " + newSurname + " SSN (" + newSwedishNumber + ") was successfully added to the members registry.");
			} else {
				System.out.println("\nERROR!: There are already a registered member with the Swedish Number " + newSwedishNumber);
			}
		} else {
			System.out.println("\nERROR!: Can not add member since " + newSwedishNumber + " is not a valid Swedish Number!.");
		}
	}

	public void deleteMember (String memberId){

		Boolean deleted = false;

		for (int i = 0; i < members.size(); i++){
			if (members.get(i).getMemberId().equals(memberId)){

				System.out.println ("\nmodel.Member Id " + memberId + " corresponding to " + members.get(i).getName() + " " + members.get(i).getSurname() + " (SSN: " + members.get(i).getSwedishNumber() + ")" +  " was successfully deleted from the model.Member's Registry!.");
				members.remove(i);
				i = members.size() + 1;
				deleted = true;
			}
		}

		if (!deleted){
			System.out.println ("\nERROR!: model.Member Id " + memberId + " could not be found in the registry.");
		}
	}

	public void editMember (String memberId, String newName, String newSurname, String newSwedishId){

		if (SwedishId.isCorrect(newSwedishId)){ // check that the swedish id number provided is authentic

			Boolean edited = false;

			for (int i = 0; i < members.size(); i++){

				if (members.get(i).getMemberId().equals(memberId)){

					System.out.println ("\nmodel.Member's information for " + memberId + " corresponding to " + members.get(i).getName() + " " + members.get(i).getSurname() + "(" + members.get(i).getSwedishNumber() + ")" +  " was successfully updated!");

					members.get(i).setName(newName);
					members.get(i).setSurname(newSurname);
					members.get(i).setSwedishId(newSwedishId);
					members.get(i).generateMemberId();

					System.out.println("\nmodel.Member's Updated Information:\nUpdated Name: " + members.get(i).getName() + " " + members.get(i).getSurname() + "\nUpdated Swedish Id Number: " + members.get(i).getSwedishNumber() + "\nNew model.Member Id Number: " + members.get(i).getMemberId()  );

					edited = true;
					i = members.size() + 1;
				}

			}

			if (!edited){
				System.out.println ("\nERROR!: model.Member Id " + memberId + " could not be found in the registry.");
			}
		} else {

			System.out.println("\nERROR!: Information could not be updated for member id " + memberId + " since " + newSwedishId + " is not a valid Swedish Number!");
		}

	}

	public void viewMember (String memberId){

		Boolean viewed = false;

		for (int i = 0; i < members.size(); i++){

			if (members.get(i).getMemberId().equals(memberId)){

				System.out.println("\nview.View information for model.Member Id " + memberId + ":\nName: " + members.get(i).getName() + "\tSurname: " + members.get(i).getSurname() + "\nSwedish Id Number: " + members.get(i).getSwedishNumber());

				if (!members.get(i).boats.isEmpty()){
					System.out.println ("This model.Member has ("+members.get(i).boats.size()+") Registered Boats:");
					for (int j = 0; j < members.get(i).boats.size() ; j++){
						int bullet = j + 1;
						System.out.println ("("+ bullet +") " + members.get(i).boats.get(j).toString()); //model.Boat description print out method goes here
					}
				} else {
					System.out.println ("This club member has not yet registered any boats in the System!.\n");
				}

				viewed = true;
				i = members.size() + 1;
			}
		}

		if (!viewed){
			System.out.println ("\nERROR!: model.Member Id" + memberId + " could not be found in the registry!.");
		}
	}

	public void lookUpMember (String swedishIdNumber) {
		if (SwedishId.isCorrect(swedishIdNumber)){
			Boolean found = false;

			for (int i = 0; i < members.size(); i++){

				if (members.get(i).getSwedishNumber().equals(swedishIdNumber)){

					System.out.println ("FOUND!\t" + members.get(i).toString());
					found = true;
					i = members.size() + 1;
				}
			}
			if (!found){
				System.out.println ("NOT FOUND! " + swedishIdNumber + " is not a registered member");
			}
		} else {
			System.out.println("\nERROR!: " + swedishIdNumber + " is not a valid Swedish Number!.");
		}
	}

	public Boolean isRegistered (String swedishIdNumber) { //returns True if the Swedish Id Number provided is of a registered member

		if (SwedishId.isCorrect(swedishIdNumber)){
			Boolean found = false;

			for (int i = 0; i < members.size(); i++){

				if (members.get(i).getSwedishNumber().equals(swedishIdNumber)){
					found = true;
					i = members.size() + 1;
				}
			}
		return found;

		} else {
			System.out.println("\nERROR!: " + swedishIdNumber + " is not a valid Swedish Number!.");
			return false;
		}
	}

	public void printCompact() { // "Compact List" print out of all members

		if (!members.isEmpty()){
			System.out.println ("\n== Compact List of All Members ==");
			for (int i = 0; i < members.size(); i++){
				System.out.println("Name: " + members.get(i).getName() + " " + members.get(i).getSurname() + "\tmodel.Member Id Number: " + members.get(i).getMemberId() + "\tNumber of boats registered: " + members.get(i).boats.size());
			}
		} else {
			System.out.println ("\nRegister is Empty!.\nThere are currently no members registered in the Yatch Club!.");
		}
	}

	public void printVerbose (){ // "Verbose List" print out

		if (!members.isEmpty()){

			System.out.println ("\n== Verbose List of All Members ==");

			for (int i = 0; i < members.size(); i++){
				System.out.println("\nName: " + members.get(i).getName() + " " + members.get(i).getSurname() + "\tSwedish Personal Number: " + members.get(i).getSwedishNumber() + "\tmodel.Member Id Number: " + members.get(i).getMemberId());

				if (!members.get(i).boats.isEmpty()){
					for (int j = 0; j < members.get(i).boats.size() ; j++){
						int bullet = j + 1;
						System.out.println ("("+ bullet +") " + members.get(i).boats.get(j).toString()); //model.Boat description print out method goes here
					}
				} else {
					System.out.println("This club member has not yet registered any boats in the System!.");
				}
			}

		} else {
			System.out.println ("\nRegister is Empty!.\nThere are currently no members registered in the Yatch Club!.");
		}
	}

	}