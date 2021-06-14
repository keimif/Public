import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.io.*;

public class Main{
    
    // The main ArrayList
    private static ArrayList<Animal> animalList = new ArrayList<Animal>();
    
    // The main method starting the program and launching the menu
    public static void main(String[] args){
        System.out.println("Welcome");
        
        printMenu();
       }
    
    // This method prints out the main menu and allows you to store, edit , delete animals and finally save/read file
    private static void printMenu(){
        Scanner sc = new Scanner(System.in);
        boolean isOpen = true;
        while(isOpen){
            System.out.println("1) Add an animal");
            System.out.println("2) Edit an animal");
            System.out.println("3) Delete an animal");
            System.out.println("4) View all live stock");
            System.out.println("5) Save to file");
            System.out.println("6) Read from file");
            System.out.println("7) Exit");
            int input = sc.nextInt();
            switch(input){
                case(1) : addAnimal();
                    break;
                case(2): editAnimal();
                    break;
                case(3): deleteAnimal();
                    break;
                case(4): viewStock();
                    break;
                case(5): saveFile();
                    break;
                case(6): readFile();
                    break;
                case(7): exitApplication();
                default: System.out.println("Please enter a valid number.");
                    printMenu();
                        break;
            }
        }
    }
    
    // This method adds an animal to the ArrayList
    private static void addAnimal(){
        Scanner sc = new Scanner(System.in);
        System.out.println("How many animals do you want to input?");
        int noOfAnimals = Integer.parseInt(sc.nextLine());
        for(int i = 0; i < noOfAnimals; i++){
            Animal animal = inputAnimal();
            animalList.add(animal);
            System.out.println();
        }
    }

    // This is the mehod edits the animals in the ArrayList
    private static void editAnimal(){
        viewStock();
        int animalIndex = returnAnimal();
        Animal animal = inputAnimal();
        animalList.get(animalIndex).setId(animal.getId());
        animalList.get(animalIndex).setType(animal.getType());
        animalList.get(animalIndex).setWeight(animal.getWeight());
        animalList.get(animalIndex).setDate(animal.getDate());
        animalList.get(animalIndex).setLocation(animal.getLocationRoom(), animal.getLocationSection());
        System.out.println();
    }
    
    // This method stores the animal
    private static Animal inputAnimal(){
        Scanner sc = new Scanner(System.in); 
        System.out.println("Please enter the class of the animal from the following: Amphibians, Birds, Fish, Invertebrates, Mammals, Reptiles, Fish ");
        Animal animal = animalType();
        System.out.println("When was the animal obtained?: ");
        Date dateObtained = animalDate();
        System.out.println("Please enter the animal Id: ");
        String animalId = sc.nextLine();
        System.out.println("What is the type of the animal (example: Dog, Cat etc)?: ");
        String animalType = sc.nextLine();
        System.out.println("How much does the animal weigh (in kg)?: ");
        int animalWeight = Integer.parseInt(sc.nextLine());
        System.out.println("In what room will it be stored?: ");
        String animalRoom = sc.nextLine();
        System.out.println("In which section?: ");
        String animalSection = sc.nextLine();
        animal.setDate(dateObtained);
        animal.setId(animalId);
        animal.setType(animalType);
        animal.setWeight(animalWeight);
        animal.setLocation(animalRoom, animalSection);
        return animal;
    }
    
    //  This method returns a child object of the animal class
    private static Animal animalType(){
        Scanner sc = new Scanner(System.in);
        String animalType = sc.nextLine();
        switch(animalType){
            case "Amphibians": Amphibians amphibians = new Amphibians();
                return amphibians;
            case "Birds": Birds birds = new Birds();
                return birds;
            case "Fish": Fish fish = new Fish();
                return fish;
            case "Invertebrates": Invertebrates invertebrates = new Invertebrates();
                return invertebrates;
            case "Mammals": Mammals mammals = new Mammals();
                return mammals;
            case "Reptiles": Reptiles reptiles = new Reptiles();
                return reptiles;
        }
        Animal animal = new Animal();
        return animal;
    }
    
    //  This method returns a Date object
    private static Date animalDate(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Day: ");
        int day = Integer.parseInt(sc.nextLine());
        System.out.println("Month: ");
        int month = Integer.parseInt(sc.nextLine());
        System.out.println("Year: ");
        int year = Integer.parseInt(sc.nextLine());
        int yearDifference = 1900;
        int correctYear = year-yearDifference;
        Date dateObtained = new Date(correctYear,month,day);
        return dateObtained;
    }
    
    // This method returns the index of an animal
    private static int returnAnimal(){
        System.out.println("Please enter the Id of the animal you want to change: ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        for(Animal animal : animalList){
            if((animal.getId()).equals(input)){
                return animalList.indexOf(animal);
            }
        }return -1;
    }
    
    // This method deletes the animal found
    private static void deleteAnimal(){
        viewStock();
        int animalIndex = returnAnimal();
        animalList.remove(animalIndex);
        System.out.println("The animal was removed successfully.");
    }
    
    //  This method prints the toString method of every object in ArrayList
    private static void viewStock(){
        System.out.println("The following animals are stored inside the park: ");
        for(Animal animal : animalList){
            String toString = animal.toString();
            System.out.println(toString);
        }
    }
    
    // Serialize the objects and saves to file
    private static void saveFile(){
        try{
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("animalList"));
            os.writeObject(animalList);
            os.close();
            System.out.println("File Saved Successfully.");
        }
        catch(IOException ioe){
            System.out.println("IO Exception found.");
        }
    }  
    
    // Reads the file and deserialize the objects
    private static void readFile(){
        try{
            FileInputStream fis = new FileInputStream("animalList");
            ObjectInputStream ois = new ObjectInputStream(fis);
            animalList = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
            System.out.println("File Read Successfully.");
        }
        catch(IOException ioe){
            System.out.println("IO Exception found.");
        }
        catch(ClassNotFoundException cnfe){
            System.out.println("Class Not Found Exception found.");
        }
    }
    
    // Closes the application
    private static void exitApplication(){
        System.out.println("Hope to see you again.");
        System.exit(0);
    }
}