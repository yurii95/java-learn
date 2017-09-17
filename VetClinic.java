import java.util.*;
public class VetClinic{
	LinkedList<Client> clients=new LinkedList<Client>();
	/**
         * Method create new client in list of clients
         * @param name name of client 
         */
        public void addNewClient(String name){
            boolean flag=false;
            Client possibleClient=new Client(name);
            for(Client c:clients)
                if(c.equals(possibleClient))
                    flag=true;
            if(!flag)
		clients.add(possibleClient);
            else 
                System.out.println("This client already exist!");
	}

	/**
	* Method removes client from list of clients 
	* @return true if remove success, otherwise return false;
	*/
	public boolean removeClient(Client client){
		boolean result=false;
		for(Client c: clients){
			if(c.equals(client)){
				clients.remove(c);
				result=true;
				break;
			}
		}
		return result;
	}

	/**
	* Method search client by his name
	* @return Searched client or client with name "Null" if client doesn`t exist
	*/
	public Client searchClientByName(String clientName){
		Client result=new Client("Null");
		for(Client client:clients){
			if(client.getName() == clientName){
				result=client;
				break;
			}
		}
		return result;	
	}
        /**
	* Method search client by his pet name
	* @return Searched client or client with name "Null" if client doesn`t exist
	*/
        public Client seachClientByPetName(String petName){
            Client result=new Client("Null");
            for(Client c:clients){
                Pet temp=c.searchByPetName(petName);
                if(temp.getName()!="Null"){
                    result=c;
                    break;
                }       
            }
            return result;
        }
        
	public static void main(String[] arg){
            
	}		
}

class Client{
	//name - client name
	private String name;
	LinkedList<Pet> petsList;
	private int summaryCost=0;
	public Client(String name){
		this.name=name;
		petsList=new LinkedList<Pet>();
	}
	public Client(String name, LinkedList<Pet> petList){
		this.name=name;
		this.petsList=petList;
		for(Pet p: petsList){
			summaryCost+=p.getPetPrice();
		}	
	}
	public void addNewPet(String name,String type){
		petsList.add(new Pet(name,type));
	}
	public void addPet(Pet p){
            boolean flag=false;
            for(Pet pet:petsList){
                if(pet.equals(p)){
                    flag=true;
                    break;
                }       
            }
            if(!flag){
		petsList.add(p);
		summaryCost+=p.getPetPrice();
            }
            else
                System.out.println("This pet already exist on this client");
	}
	/**
	* Method removes pet from list of client pets
	* @return true if remove success, otherwise return false;
	*/
	public boolean removePet(Pet pet){
		boolean result=false;
		for(Pet p: petsList){
			if(p.equals(pet)){
				summaryCost-=p.getPetPrice();
				petsList.remove(p);
				result=true;
				break;
			}			
		}
		return result;
	}
	/**
	* Method search client pet by name
	* @return Pet or Pet with name "Null" if pets doesn`t exist
	*/
	public Pet searchByPetName(String petName){
		Pet result=new Pet("Null","Null");
		for(Pet p: petsList)
			if(p.getName()== petName){
				result=p;
				break;
			}
		return result;
	}
	public void SetName(String name){
		this.name=name;
	}
	public String getName(){
		return this.name;
	}
        public boolean equals(Client arg){
            boolean result=false;
            if(this.name==arg.name &&
               this.petsList.equals(arg.petsList)&&
               this.summaryCost==arg.summaryCost)
                    result=true;
            return result;
        }
	
}

class Pet{
	private String name;
        //type of pet(cat,dog,e.g)
        private String type;
	// it`s list of procedures with this pet
	private LinkedList<Procedure> proceduresList;
	//summaryPrice it`s cost of all procedures with this pet
	private int summaryPrice=0;
	
	public Pet(String name,String type){
		this.name=name;
                this.type=type;
		proceduresList=new LinkedList<Procedure>();
	}
	public Pet(String name,String type, LinkedList<Procedure> procList){
		this.name=name;
                this.type=type;
		this.proceduresList=procList;
		for(Procedure p: proceduresList){
			summaryPrice+=p.getProcedurePrice();
		}
		
	}
	public void addProcedure(String name, int price){
		proceduresList.add(new Procedure(name,price));
		summaryPrice+=price;
	}
	public void addProcedure(Procedure procedure){
		proceduresList.add(procedure);
		summaryPrice+=procedure.getProcedurePrice();
	}
	/**
	* Method removes procedure from list of procedures 
	* @returns true if remove success, otherwise return false;
	*/
	public boolean removeProcedureByName(String name){
		boolean result=false;
		for(Procedure p: proceduresList){
			if(p.getProcedureName() == name){
				summaryPrice-=p.getProcedurePrice();
				proceduresList.remove(p);
				result=true;
				break;
			}		
		}
		return result;
	}
	public int getPetPrice(){
		return summaryPrice;
	}
	public void SetName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
        public String getType(){
            return type;
        }
        public LinkedList<Procedure> getProcedures(){
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
            if(this.getName()==arg.getName() &&
               this.getType()==arg.getType() &&
               this.getPetPrice()==arg.getPetPrice() &&
               this.getProcedures()==arg.getProcedures())
                    result=true;
            return result;
                
        }
	
}

class Procedure{
	private String name;
	private int price;
        private Date date;
	public Procedure(String name, int price){
		this.name=name;
		this.price=price;
                date=new Date(System.currentTimeMillis());
	}
	public void set(String name, int price){
		this.name=name;
                this.price=price;
	}
	public String getProcedureName(){
                return name;
	}
	public int getProcedurePrice(){
		return price;
	}
        public Date getProcedureDate(){
                return date;
        }
	public String toString(){
		return name+": "+price+" "+date;
	}
}