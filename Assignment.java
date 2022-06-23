package com.company;
import java.io.*;
import java.util.*;
class Slot{
    private int day;
    private int quantity;
    private Vaccine vaccine;
    public Slot(int day,int quantity,Vaccine vaccine){
        this.day=day;
        this.quantity=quantity;
        this.vaccine=vaccine;
    }
    //class Slot getters
    public int getDay() {
        return day;
    }

    public int getQuantity() {
        return quantity;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    //To reduce quantity
    public void QuantityDecrement() {
        this.quantity--;
    }
}
class Vaccine{
    private String name;
    private int doses;
    private int gap;
    public Vaccine(String name,int doses,int gap){
        this.name = name;
        this.doses = doses;
        this.gap = gap;
    }

    //class Vaccine getters
    public String getName() {
        return name;
    }

    public int getDoses() {
        return doses;
    }

    public int getGap() {
        return gap;
    }

    public String toString()
    {
        return("Vaccine Name:"+ this.name+
                ", Number of Doses: " +
                this.doses+"," +" Gap Between Doses: "+this.gap);
    }
}
class Hospital{
    private String name;
    private int pincode;
    private int id;
    private ArrayList <Slot> slots=new ArrayList<>();
    public Hospital(String name,int pincode,int id){
        this.name = name;
        this.pincode = pincode;
        this.id=id;
    }
    //class Hospital getters
    public String getName() {
        return name;
    }

    public ArrayList<Slot> getSlots() {
        return slots;
    }

    public int getPincode() {
        return pincode;
    }

    public int getId() {
        return id;
    }

    public String toString()
    {
        return("Hospital Name:"+ this.name+
                ", Pincode: " + this.pincode+
                ", Unique ID: " + this.id);
    }
    //to add a slot in the hospital
    public void AddSlot(int day,int quantity,Vaccine vaccine){
        Slot x=new Slot(day, quantity, vaccine);
        slots.add(x);
        System.out.println("Slot added by Hospital "+id+" for Day:"+day+",Available Quantity:"+quantity+" of Vaccine "+vaccine.getName());
    }

    //Show available slot for the citizen
    public boolean ShowSlot(Citizen x){
        int i=0;
        for(Slot y:slots){
            if(x.getDoses_taken()<y.getVaccine().getDoses() && y.getDay()>=x.getNextDue() &&(x.getVaccine()==null||x.getVaccine()== y.getVaccine()) && y.getQuantity()>0) {   //either x.vaccine should be null or same as slot
                System.out.println(i + "-> Day:" + y.getDay() + " Available Qty:" + y.getQuantity() + " Vaccine:" + y.getVaccine().getName());
                i++;
            }
        }
        if(i==0){
            System.out.println("No slots available");
            return false;
        }
        return true;
    }

    //Show available slots in hospital(for argument 6)
    public void ShowSlotNo_index(){
        for(Slot y:slots){
            if(y.getQuantity()>0) {
                System.out.println("Day:" + y.getDay() + " Vaccine:" + y.getVaccine().getName() + " Available Qty:" + y.getQuantity());
            }
        }
    }

    //Show available slots by vaccine
    public boolean ShowByVaccine(String vaccine,Citizen x){
        int flag=0;
        for(Slot y:slots){
            if(x.getDoses_taken()<y.getVaccine().getDoses() && y.getVaccine().getName().equals(vaccine) && y.getDay()>=x.getNextDue() && (x.getVaccine()==null||x.getVaccine()== y.getVaccine()) && y.getQuantity()>0){
                flag=1;
                System.out.println(slots.indexOf(y)+"->Day: "+ y.getDay()+" Available Qty:"+y.getQuantity()+" Vaccine:"+y.getVaccine().getName());
            }
        }
        if(flag==0){
            System.out.println("No slots available");
            return false;
        }
        return true;
    }
}
class Citizen{
    private String name;
    private int age;
    private long id;
    private int nextDue=0;
    private Vaccine vaccine=null;
    private int doses_taken=0;

    //class Citizen getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public long getId() {
        return id;
    }

    public int getNextDue() {
        return nextDue;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public int getDoses_taken() {
        return doses_taken;
    }

    public Citizen(String name, int age, long id){
        this.name = name;
        this.age = age;
        this.id = id;
    }

    //To give vaccine to the citizen
    public void giveVaccine(Vaccine vaccine,int day){
        this.nextDue=day+vaccine.getGap();
        this.vaccine=vaccine;
        doses_taken++;
    }

    //prints Status of the citizen(for argument 7)
    public void Status(){
        if(doses_taken==0){
            System.out.println("Citizen REGISTERED");
        }
        else if(doses_taken== vaccine.getDoses()){
            System.out.println("FULLY VACCINATED");
            System.out.println("Vaccine Given:"+vaccine.getName());
            System.out.println("Number of Doses given:"+doses_taken);
        }
        else if(doses_taken< vaccine.getDoses()){
            System.out.println("PARTIALLY VACCINATED");
            System.out.println("Vaccine Given:"+vaccine.getName());
            System.out.println("Number of Doses given:"+doses_taken);
            System.out.println("Next Dose due date:"+nextDue);
        }
    }
}
public class AdvancedProgramming_1 {
    public static void printMenu(){
        System.out.println("---------------------------------");
        System.out.println("1. Add Vaccine\n" +
                "2. Register Hospital\n" +
                "3. Register Citizen\n" +
                "4. Add Slot for Vaccination\n" +
                "5. Book Slot for Vaccination\n" +
                "6. List all slots for a hospital\n" +
                "7. Check Vaccination Status\n" +
                "8. Exit");
        System.out.println("---------------------------------");
    }
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        ArrayList <Vaccine> vaccines=new ArrayList<>();
        ArrayList <Hospital> hospitals=new ArrayList<>();
        ArrayList <Citizen> citizens=new ArrayList<>();
        int hospitalID=100000;
        System.out.println("CoWin Portal initialized....");
        printMenu();
        int n = scn.nextInt();
        while(n!=8) {
            if (n == 1) {
                int gap;
                System.out.print("Vaccine Name:");
                String name = scn.next();
                System.out.print("Number of Doses:");
                int doses = scn.nextInt();
                if(doses>1) {
                    System.out.print("Gap between Doses:");
                    gap = scn.nextInt();
                }
                else{
                    gap=0;
                }
                Vaccine x = new Vaccine(name, doses, gap);
                vaccines.add(x);
                System.out.println(x);

            }
            if(n==2){
                System.out.print("Hospital Name:");
                String name = scn.next();
                System.out.print("Pincode: ");
                int doses = scn.nextInt();
                Hospital x = new Hospital(name, doses,hospitalID);
                hospitalID++;
                hospitals.add(x);
                System.out.println(x);
            }
            if(n==3){
                System.out.print("Citizen Name:");
                String name = scn.next();
                System.out.print("Age: ");
                int age = scn.nextInt();
                System.out.print("Unique ID: ");
                long ID = scn.nextLong();
                int flag=1;
                //we will register only if id is unique and age>18
                for(Citizen y:citizens){
                    if(y.getId()==ID){
                        flag=0;
                    }
                }
                if(flag==1) {  //Id is unique
                    System.out.println("Citizen Name:" + name +
                            ", Age: " + age +
                            ", Unique ID: " + ID);
                    if(age<18){
                        System.out.println("Only 18 and above are allowed");
                    }
                    else{
                        Citizen x=new Citizen(name,age,ID);
                        citizens.add(x);
                    }

                }
                else{
                    System.out.println("ID not unique");
                }

            }
            if(n==4){
                System.out.print("Enter Hospital ID:");
                int ID = scn.nextInt();
                int flag=0;
                Hospital current = null;
                for(Hospital y:hospitals){
                    if(y.getId()==ID){
                        flag=1;
                        current=y;

                    }
                }
                if(flag==0){
                    System.out.println("Please enter Valid Hospital ID.");
                    continue;
                }
                System.out.print("Enter number of Slots to be added:");
                int number = scn.nextInt();
                for(int i=0;i<number;i++){
                    int a=0;
                    System.out.print("Enter Day Number:");
                    int Day = scn.nextInt();
                    System.out.print("Enter Quantity:");
                    int quantity = scn.nextInt();
                    System.out.println("Select Vaccine");
                    for(Vaccine y:vaccines){
                        System.out.println(a+". "+y.getName());
                        a++;
                    }
                    int vaccine_i = scn.nextInt();
                    current.AddSlot(Day,quantity, vaccines.get(vaccine_i));

                }
            }
            if(n==5){
                System.out.print("Enter patient Unique ID:");
                long ID = scn.nextLong();
                int flag=0;
                Citizen current = null;
                int a=0;
                for(Citizen y:citizens){
                    if(y.getId()==ID){
                        flag=1;
                        current=y;
                    }
                }
                if(flag==0){
                    System.out.println("Please enter Valid Patient ID.");
                    continue;
                }
                System.out.println("1. Search by area\n" +
                        "2. Search by Vaccine\n" +
                        "3. Exit");
                System.out.print("Enter option:");
                int option = scn.nextInt();
                if(option==1){
                    System.out.print("Enter PinCode:");
                    int pincode = scn.nextInt();
                    for(Hospital y:hospitals) {
                        if (y.getPincode() == pincode) {
                            System.out.println(y.getId() + " " + y.getName());
                        }
                    }
                    System.out.print("Enter hospital id:");
                    int hosp_id = scn.nextInt();
                    for(Hospital y:hospitals) {
                        if(y.getPincode()==pincode && y.getId()==hosp_id){
                            boolean abc=y.ShowSlot(current);
                            if(abc==false){
                                continue;
                            }
                            else {
                                System.out.print("Choose Slot:");
                                int choseSlot = scn.nextInt();
                                Slot chosenSlot = y.getSlots().get(choseSlot);
                                chosenSlot.QuantityDecrement();
                                current.giveVaccine(chosenSlot.getVaccine(), chosenSlot.getDay());
                                System.out.println(current.getName() + " vaccinated with " + chosenSlot.getVaccine().getName());
                            }
                        }
                    }
                }
                if(option==2){
                    System.out.print("Enter Vaccine name:");
                    String v = scn.next();
                    for(Hospital y:hospitals){
                        for(Slot x:y.getSlots()){
                            if(v.equals(x.getVaccine().getName())){
                                System.out.println(y.getId() + " " + y.getName());
                                break;
                            }
                        }
                    }
                    System.out.print("Enter hospital id:");
                    int hosp_id = scn.nextInt();
                    for(Hospital y:hospitals) {
                        if(y.getId()==hosp_id){
                            Boolean abc=y.ShowByVaccine(v,current);
                            if(abc==false){
                                continue;
                            }
                            else {
                                System.out.print("Choose Slot:");
                                int choseSlot = scn.nextInt();
                                Slot chosenSlot = y.getSlots().get(choseSlot);
                                chosenSlot.QuantityDecrement();
                                current.giveVaccine(chosenSlot.getVaccine(), chosenSlot.getDay());
                                System.out.println(current.getName() + " vaccinated with " + chosenSlot.getVaccine().getName());
                            }
                        }
                    }

                }

            }
            if(n==6){
                System.out.print("Enter hospital id:");
                int hosp_id = scn.nextInt();
                for(Hospital y:hospitals) {
                    if (y.getId() == hosp_id) {
                        y.ShowSlotNo_index();
                    }
                }
            }
            if(n==7){
                System.out.print("Enter Patient ID:");
                long patient = scn.nextLong();
                for(Citizen y:citizens){
                    if(y.getId()==patient){
                        y.Status();
                    }
                }
            }
            printMenu();
            n=scn.nextInt();
        }


    }

}
