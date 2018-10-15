import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Competition {
	
	String Gndtype;  //String variable for store the gender type of the competition
	String Stktype;  //String variable for store the stroke type of the competition
	int Numswimmers; //String variable for store the number of swimmers in the competition
	int Numjugdes;   //String variable for store the number of judges in the competition
	int Numstaff;    //String variable for store the number of staff members in the competition
	

	public static void main(String[] args) {
	  //to catch if there is any invalid input
      try{
		 ///creating a pool object with 5 lanes and 50m depth and 50m long 
		 @SuppressWarnings("unused")
		pool pl=new pool(5,50,50);
		 
		 ///creating the score board object
		 Score_board scbd =new Score_board();
		 
		 //block names of the pavilion
		 String[] a={"A","B"};
		 //creating the pavilion object with 75 seats and block A and B
		 @SuppressWarnings("unused")
		pavilion pv=new pavilion(75,a);
		 
		 //general instructions array of the game for user
		 String[] Generalframe={"Enter Game Type(male or female)","Select stroke type(forward,backward,breast)","Select number of swimmers(2 to 5)","Select number of judges(1 to 3)","Select number of Supporting staff(3 to 10)","Number of spectators\n(1 for (1-25),2 for (25-50),3 for (50-75) " };
		
		 //details array of a swimmer which should be input by the user
		 String[] Swimmerframe={"Enter the name","Enter the age" };
		 
		 //details array of judge which should be input by the user
		 String[] Jugdeframe={"Enter the name of judge","Enter the gender(m/f)","Enter the age" };
		 String[] Staffframe={"Enter the name (staf)","Enter gender(m/f)","Enter age","Enter the duty" };
		 
		// List<String> InputStrings=new ArrayList<String>();
		 
		 //list containing general details of the game
		 List<String> Generallist=new ArrayList<String>();
		 
		 ///instantiate Scanner object
		 @SuppressWarnings("resource")
		 Scanner sc= new Scanner(System.in);
		
		 //adding items  to Generallist
		 for(String i:Generalframe){
			System.out.print(i);
			Generallist.add(sc.next());
		 }
		 
		 // assigning values for variables 
		 String Gndtype =Generallist.get(0);
		 String Stktype =Generallist.get(1);
		 int Numswimmers=Integer.parseInt(Generallist.get(2));
		 int Numjugdes  =Integer.parseInt(Generallist.get(3));
		 int Numstaff   =Integer.parseInt(Generallist.get(4));
		 
		 //instantiate the Competition object
		 Competition cmp=new Competition(Gndtype,Stktype,Numswimmers,Numjugdes,Numstaff);
		
		 //Instantiate linked class which has a method to hold swimmer objects 
		 linked<Swimmer> Objswim=new linked<Swimmer>(); 
		 
		//Instantiate linked class which has a method to hold touchpad objects 
		 linked<touch_pad> Objtouch=new linked<touch_pad>();
		 
		 //creating swimmer objects (Numswimmers times swimmers0
		 for(int i=1;i<=Numswimmers;i++){
			 
			System.out.println(" ");
			System.out.printf("Enter details of swimmer %s",i);
			System.out.println(" ");
			
			//Instantiate a new linked list for store swimmer details
			List<String> SDetails=new ArrayList<String>();
			
            //adding input details to Sdetails list
			for(String j:Swimmerframe){
			     System.out.println(j);
			     SDetails.add(sc.next());
		    }
			//instantiate a new swimmer class and add above input details and instantiate a touch pad object per swimmer 
			Swimmer s=new Swimmer(SDetails.get(0),Stktype ,Integer.parseInt(SDetails.get(1)));
			touch_pad t= new touch_pad();
			
			//storing swimmer object in a linkedlist
			Objswim.li.add(s);
			
			//storing touchpad object in a linkedlist
			Objtouch.li.add(t);
		
			
		 }
		 
		 
		 //creating judge objects ( same procedure as for swimmer objects)
		 linked<judge> Objjudge=new linked<judge>(); 
		 for(int i=1;i<=Numjugdes;i++){
			 
			System.out.println(" ");
			System.out.printf("Enter details of judge %s",i);
			System.out.println(" ");
			
			List<String> jdetails=new ArrayList<String>();
			
			for(String j:Jugdeframe){
			     System.out.println(j);
			     jdetails.add(sc.next());
		    }
			judge J=new judge(jdetails.get(0),jdetails.get(1),Integer.parseInt(jdetails.get(2)));
			Objjudge.li.add(J);
			
		 }
	
		//creating staff objects ( same procedure as for swimmer objects)
		 linked<staff> Objstaff=new linked<staff>(); 
		 for(int i=1;i<=Numstaff;i++){
			 
			System.out.println(" ");
			System.out.printf("Enter details of supporting staff member %s",i);
			System.out.println(" ");
			
			List<String> stfdetails=new ArrayList<String>();
			
			for(String j:Staffframe){
			     System.out.println(j);
			     stfdetails.add(sc.next());
		    }
			
			staff st=new staff(stfdetails.get(0),stfdetails.get(1),Integer.parseInt(stfdetails.get(2)),stfdetails.get(3));
		    Objstaff.li.add(st);
			
		 }
		 
		 //for spectator objects
		 //     the code gets too bulky if spectator objects are created as we are using command user interface
		 //     they will be created when we use GUI.
		 
		 
		 //Checking minimum condition for a competition
		if(Numswimmers>=2 &&Numjugdes>=1 && Numstaff>=3){
		    
			 System.out.println("The game is just about to start");
			 ((judge) Objjudge.li.get(0)).whistle();      //judge1 blows whistle to start the game
			
			 linked<Integer> rank=new linked<Integer>();  //an integer array for storing ranks of each swimmer
			 System.out.println("The game is  on,competitors are swimmimg fastly");
			 for(int i=0;i<Numswimmers;i++){
				 //getting the ranks of each
				 int j= ((touch_pad) Objtouch.li.get(i)).starts(Numswimmers);
				 //add ranks to a linkedlist
			     rank.li.add(j);
			    
			 }
			 
			 //getting the results
			 System.out.println("\nHere is the results");
			 cmp.fnl_rst(Objswim, rank);
			  
		   }
		 
			
		//no match will be held if minimum requirements are filled
		else{
				scbd.nomatch();
		 
		}
	  //if an error pops up
	  }catch(Exception e){
	  	System.out.print("There is something wrong with inputs");
	    	 
	    }
    }

	
	
	Competition(String Gndtype,String Stktype,int Numswimmers,int Numjugdes,int Numstaff ) {
	    this.Gndtype=Gndtype;
	    this.Stktype=Stktype;
	    this.Numswimmers=Numswimmers;
		this.Numjugdes=Numjugdes;
		this.Numstaff=Numstaff;
   }
	// a method to choose the winner
	public void fnl_rst(linked<Swimmer> objswim,linked<Integer> rank){
		
		int a=rank.li.size();
		//iterate through objects in rank list
		for (int i=0;i<a-1;i++){
			//iterate through objects in above index i rank list
			int max=(int) rank.li.get(i); //assume that (int) rank.li.get(i) is the max value
			int maxj=i; //assumes the index of the maximum value is i
			//loop through the array  ahead i th element
		    for (int j=i+1;j<a;j++){
		    	//Check whether there are elements which are grater than the element in i th index
			      if ((int) rank.li.get(j)> max){
			    	  //change max,maxj values if there is such  a value
			    	  max=(int) rank.li.get(j);
			    	  maxj=j;


			      }
		    	
		    }
		   
		    //swapping the maximum value of the ahead list if there is such a value
		    if (max >(int) rank.li.get(i))  {
		    	  
		    	 //swapping according descending order
		    	  int temp=(int) rank.li.get(i); //this variable will hold the i th element temporary
		    	  rank.li.add(i, max);
		    	  rank.li.add(maxj, temp);
		    	  
		    	 //swapping swimmer objects according there rank values
		    	  Swimmer tempobj= (Swimmer) objswim.li.get(i); //this object will hold the i th object temporary
		    	  objswim.li.add(i, (Swimmer) objswim.li.get(maxj));
		    	  objswim.li.add(maxj, tempobj);
		    	  
		   }
		    
		
		}  
		
		//declaration of a new swimmer object
		Score_board scbd=new Score_board();
		//score board displays the ranks
		
		
		for(int k=0;k<rank.li.size();k++){
			scbd.results(((Swimmer) objswim.li.get(k)).name, k+1);	
		}
		
		
		
	
	  }
}

//declaration of other classes

//declaration of Swimmer class,it inherits from people class
class Swimmer extends people {
	String cloth_colour; //cloth colour
    Swimmer(String name,String gender,int age){
    	super(name,gender,age);
    	//male swimmers ware red and female swimmers ware blue in this competition
    	if (gender=="male"){
    		this.cloth_colour="red";
    	}
    	else{
    		this.cloth_colour="blue";
    	}
    }
    }
//declaration of pavilion class
class pavilion{
    int num_of_seats; // for number of seats in the pavilion
    String[] block_names;
    pavilion(int num_of_seats,String[] a){
        this.num_of_seats=num_of_seats;
        this.block_names=a;
    }
}

//declaration of people class
 class staff extends people{
	String duty; //duty of the staff member
    staff(String name,String gender,int age,String duty){
        super(name,gender,age); 
        this.duty=duty;
    }
}
//declaration of judge class ,it inherits from people class
class judge extends people{
    judge(String name,String gender,int age){
        super(name,gender,age); 
        
    }
    public boolean whistle(){ //method for blowing the whistle
    	@SuppressWarnings("unused")
		whistle wh= new whistle();
    	return(whistle.blow());
    }
            
}
//declaration of Score_board class
class Score_board{
	public void results(String name,int rank){ //for display the ranks
		
        System.out.printf("\n%S %s",name,rank);
    }
    public void nomatch(){ //the competition will be cancelled
    	System.out.print("The compitition is cancelled");
    }
}
//making people class as abstract as it is not needed to instantiate it.
abstract class people{
    String name;    //name of the person
    int age;        //age of the person
    String gender;  //gender of the person
    people(String name,String gender,int age){
        this.age=age;
        this.name=name;
        this.gender=gender;
    }
}
//declaration of whistle class
class whistle {
    public static boolean  blow(){ //a method to blow the whistle
        return true;
    }
}
//declaration of pool class
class pool{
    public  int lanes; //number of lanes
    public int depth;  //depth in meters
    public int length; //length in meters
    pool(int lanes,int depth,int length){
    	this.lanes=lanes;
    	this.depth=depth;
    	this.length=length;
    }
}

// declaration of spectator class
class spectator{
    public  int tiktnum;  //for ticket number of the spectator
    public String block;  //for block name of the spectator
    spectator(int tiktnum,String block){ 
         this.tiktnum=tiktnum;
         this.block=block;
      }
      
}

//declaration of touch_pad class
class touch_pad{
	//a method to take a random number between 1-5
	public int starts(int i){	
			
			 //initiating a random class for get the rank randomly in between 1-5
			 Random rank=new Random();
			 //set j as a integer between 0 & i-1
			 int j=rank.nextInt(i);
			
             return(j);

	}
}

//a class to create a linkedlist
class linked<E>{
	  //Instantiating Linkedlist li
	  List<E> li=new LinkedList<E>();
}