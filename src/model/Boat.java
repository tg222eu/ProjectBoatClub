package model;

public class Boat {
	
	String boatId;
	double size;
	Type type;
	String description;

	static int M = 0; //This counters are used to generate a 6 digit boatId
	static int S = 0;
	static int K = 0;
	static int O = 0;
	
	public Boat (){ // Constructors
		
	}
	
	public Boat (double newSize, Type newType, String newDescription){
		setSize (newSize);
		setType (newType);
		setDescription (newDescription);

		generateBoatId ();
	}
	
	public String getBoatId (){ //Getters and Setters
		return boatId;
	}
	
	public void generateBoatId () {
		char letter = 'X';
		int number = 1000000;

		if (type == Type.Motorsailer){
			letter = 'M';
			M++;
			number = number + M;
		}

		if (type == Type.Kayak_canoe){
			letter = 'K';
			K++;
			number = number + K;
		}

		if (type == Type.Sailboat){
			letter = 'S';
			S++;
			number = number + S;
		}

		if (type == Type.Other){
			letter = 'O';
			O++;
			number = number + O;
		}

		boatId = letter +""+ number;
		boatId = boatId.substring(0,1) + boatId.substring(2,8);
	}
	
	public double getSize (){
		return size;
	}
	
	public void setSize (double newSize){
		size = newSize;
	}
	
	public Type getType (){
		return type;
	}
	
	public void setType (Type newType){
		type = newType;
	}
	
	public String getDescription (){
		return description;
	}
	
	public void setDescription (String newDescription){
		description = newDescription;
	}

	@Override
	public String toString (){
		return "model.Boat Id: " + boatId + "\tmodel.Boat Length: " + size + " feet" + "\tmodel.Type: " + type + "\tDescription: " + description;

	}

}
