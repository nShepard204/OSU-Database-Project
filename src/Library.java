import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

public class Library{

    /* Data Structures */
    public static Map<Integer, MediaType> mediaList;

    public Library(){
        mediaList = new HashMap<>();
    }

    // Add new records - Returns id of new record
    public int addRecord(String selectionStr){
        //Input data
        /* MediaType newRec = new MediaType("", "", "", "", 0, 0);
        System.out.println("\nInput the name of the Record");
        newRec.name = input.nextLine();
        System.out.println("\nInput the type of the Record");
        newRec.type = input.nextLine();
        System.out.println("\nInput the creator of the Record");
        newRec.creator = input.nextLine();
        System.out.println("\nInput the year published");
        newRec.year_pub = input.nextInt();
        System.out.println("\nInput the length in seconds(Song), number of chapters(Book) or" +
                                        " number of songs contained(Album) in the Record");
        newRec.number = input.nextInt();
        System.out.println("\nInput the genre of the Record");
        newRec.genre = input.nextLine();
        System.out.println("\nAdding Record...");
        int id = mediaList.size() + 1;
        mediaList.put(id, newRec); 
        */

        
        String insertStr = "INSERT INTO " + selectionStr + " VALUES "

        return id;
    }

    // Edit records
    public void editRecord(int recordId, Scanner input){
        MediaType updatedRec = mediaList.get(recordId);
        System.out.println("\nWhat would you like to edit? \nPlease enter the number of your choice:" + 
                    " \n0:Name \n1:Creator \n2:Year Published \n3:Genre");
        switch(input.nextInt()){
            case 0:
                System.out.println("\nEnter the new name");
                updatedRec.name = input.nextLine();
                break;
            case 1:
                System.out.println("\nEnter the new Creator");
                updatedRec.creator = input.nextLine();
                break;
            case 2:
                System.out.println("\nEnter the new Year");
                updatedRec.year_pub = input.nextInt();
                break;
            case 3:
                System.out.println("\nEnter the new Genre");
                updatedRec.genre = input.nextLine();
                break;
                        
        }
        mediaList.replace(recordId, updatedRec);
    }

    // Search records
    // Return time will be changed to whatever data structure we decide to use.
    public int lookupRecord(String name){
        int id = 0;
        Set<Entry<Integer, MediaType>> medias = mediaList.entrySet();
        for(Entry<Integer, MediaType> x : medias){
            if(x.getValue().name.equals(name)){
                id = x.getKey();
            }
        }
        return id;
    }

    // Order items
    public void placeOrder(int recordId){
        boolean isHere = mediaList.containsKey(recordId);
        
        if(isHere){
            MediaType x = mediaList.remove(recordId);
            System.out.println("Thank you for your order! " + x.name + " will be added to your account.");
            // add to account
        }else{
            System.out.println("Item not avalible or invalid ID, Please search for a valid ID.");
        }
        
    }

    // Useful reports

    
}
