package model;

import java.util.ArrayList;

public class Member {

	String name;
	String surname;
	SwedishId swedishNumber;
	String memberId;
	ArrayList<Boat> boats = new ArrayList<Boat>();

	public Member(String newName, String newSurname, String newSwedishNumber) { // constructor

		//super ("user","password",Role.memberId); //fields for User
		setName(newName);
		setSurname(newSurname);
		setSwedishId(newSwedishNumber);
		generateMemberId();
		//System.out.println("SUCCESS! New member Created! \nName: " + this.getName() + " " + this.getSurname() + "\tId number: " + swedishNumber.toString() + "\tMember Number: " + memberId.toString());
	}

	public String getName() { // getters and setters
		return name;
	}

	public void setName(String newName) {
		name = newName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String newSurname) {
		surname = newSurname;
	}

	public String getSwedishNumber() {
		return swedishNumber.toString();
	}

	public void setSwedishId(String newSwedishNumber) {
		swedishNumber = new SwedishId(newSwedishNumber);
	}

	public String getMemberId() {
		return memberId;
	}

	public void generateMemberId() { //NOTE: Members' Id are generated as follows: II YY G NNNN (II: Name initials, YY: Date of Birth, G: Gender, NNNN: Last four digits of Swedish Id number)

		char gender;
		if (SwedishId.checkGender(this.swedishNumber.toString())){
			gender = 'X'; //female
		} else {
			gender = 'Y'; //male
		}

		memberId = this.name.substring(0,1) + this.surname.substring(0,1) + this.swedishNumber.firstPart.substring(0,2)+ gender + this.swedishNumber.secondPart;
	}

	@Override
	public String toString(){
	 return memberId + " " + name + " " + surname + " (SSN: "  + swedishNumber + ")";
	}
}
