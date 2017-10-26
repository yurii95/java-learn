package ua.lesson.lessons;

import java.util.ArrayList;


public class Pet{
	private String name;
	//type of pet(cat,dog,e.g)
	private String type;

	private static int count=0;
	private final int id;

	// it`s list of procedures with this pet
	private int clientId=-1;
	private int age=-1;

	private ArrayList<Procedure> proceduresList;

	public double getSummaryPrice() {
		return summaryPrice;
	}

	//summaryPrice it`s cost of all procedures with this pet
	private double summaryPrice=0;

	public Pet(int id,String name,String type){
		this.id=id;
		this.name=name;
		this.type=type;
		proceduresList=new ArrayList<Procedure>();
	}
	public Pet(int id,String name,String type, ArrayList<Procedure> procList){
		this.id=id;
		this.name=name;
		this.type=type;
		this.proceduresList=procList;
		for(Procedure p: proceduresList){
			summaryPrice+=p.getPrice();
		}
	}
	public Pet(int id,String name,String type, ArrayList<Procedure> procList, int clientId,int age){
		this.id=id;
		this.name=name;
		this.type=type;
		this.proceduresList=procList;
		for(Procedure p: proceduresList){
			summaryPrice+=p.getPrice();
		}
		this.clientId=clientId;
		this.age=age;
	}

	public void addProcedure(String name, double price){
		Procedure proc=new Procedure(Procedure.generateId(),name,price);
		proc.setPetId(this.getId());
		proceduresList.add(proc);
		summaryPrice+=price;
	}
	public void addProcedure(Procedure procedure){
		proceduresList.add(procedure);
		summaryPrice+=procedure.getPrice();

	}
	/**
	 * Method removes procedure from list of procedures
	 * @return true if remove success, otherwise return false;
	 */
	public boolean removeProcedureByName(String name){
		boolean result=false;
		for(Procedure p: proceduresList){
			if(p.getName().equals(name)){
				summaryPrice-=p.getPrice();
				proceduresList.remove(p);
				result=true;
				break;
			}
		}
		return result;
	}

	public int getId() {
		return id;
	}

	public double getPetPrice(){
		return summaryPrice;
	}
	public void SetName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}

	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId){this.clientId=clientId;}
	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}
	public int getAge(){return age;}
	public void setAge(int age){this.age=age;}
	public String getType(){
		return type;
	}
	public ArrayList<Procedure> getProcedures(){
		return proceduresList;
	}
	public String toShortString(){
		return name+": "+summaryPrice;
	}
	public String toString(){
		return name+": "+proceduresList+": "+ summaryPrice;
	}
	/**
	 * Method compare 2 pets
	 * @param arg pet to compare
	 * @return true if pets are equals or false otherwise
	 */
	public boolean equals(Pet arg){
		boolean result=false;
		if(this.getName().equals(arg.getName()) &&
				this.getType().equals(arg.getType()) &&
				this.getPetPrice()==arg.getPetPrice() &&
				this.compareProcedures(arg))
			result=true;
		return result;

	}

	private boolean compareProcedures(Pet arg){
		if(this.proceduresList.size()!= arg.getProcedures().size()) return false;

		boolean result=true;
		for(int i=0;i<proceduresList.size();i++){
			if(!proceduresList.get(i).equals(arg.getProcedures().get(i))){
				result=false;
				break;
			}
		}
		return result;
	}

	public static int generateId(){
		return count++;
	}
}