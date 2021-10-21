//Project
package repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class VirtualKey {
	File repo = new File("/Users/Shared/repo"); //Root directory to be used in the program
		
	public static void main(String[] args) throws CusExcep {
		VirtualKey VK = new VirtualKey();
		Scanner input = new Scanner(System.in);
		char ch;
		String fileName =null;
		try {
		//Main Program 
        do{
        	System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        	System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^ LockedMe.com ^^^^^^^^^^^^^^^^^^^^^^^^^");
        	System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            System.out.println("^^^^^^^^ REPO :: MAIN MENU ^^^^^^^^");
            System.out.println("1. Display Files in Ascending Order.");
            System.out.println("2. Business Level Operation");
            System.out.println("3. Close Application");
            System.out.println("Provide your input");
            int choice = input.nextInt();
            switch (choice)
            {
            case 1 : 
                System.out.println("1. Display Files in Ascending Order."); //Listing of file
                try
                {
                	VK.filelisting();
                }
                catch(Exception e)
                {
                    System.out.println("Error : " +e.getMessage());
                }                         
                break;                         
            case 2 : // Getting into Business Level operation for the repository 
                try
                {
                    do{
                    	
                        System.out.println("^^^^^^^^ REPO :: Business Level Operations ^^^^^^^^");
                        System.out.println("1. Add File");
                        System.out.println("2. Delete File");
                        System.out.println("3. Search File");
                        System.out.println("Provide your input");
                        choice = input.nextInt();
                        Scanner file = new Scanner(System.in);
                        switch (choice)
                        {
                        case 1 : 
                            System.out.println("Adding a File"); //Add a new file
                            try
                            {
                            System.out.println("Provide the File Name");
                            fileName = file.nextLine();
                      		VK.addFile(fileName);      	
                            }
                            catch(Exception e)
                            {
                                System.out.println("Error : " +e.getMessage());
                            }                         
                            break;                         
                        case 2 : 
            			 System.out.println("Deleting a File"); //Delete a file
                            try
                            {
                            System.out.println("Provide the File Name");
                            fileName = file.nextLine();
            				VK.deleteFile(fileName);

                            }
                            catch(Exception e)
                            {
                                System.out.println("Error : " +e.getMessage());
                            }
                            break;                         
                        case 3 : 
                           System.out.println("Searching for the File"); //Search a file
            			try
                            {
                            System.out.println("Provide the File Name");
                            fileName = file.nextLine();   
            				VK.searchFile(fileName);
                            }
                            catch(Exception e)
                            {
                                System.out.println("Error : "+e.getMessage());
                            }
                            break;                                             
                        default : System.out.println("Invalid option. Please provide a valid option. \n "); //Default option 
                            break;
                        }    
                        System.out.println("\nDo you want continue with Business Level Menu ? \n[Press: y or Y to continue. Press anyother key to quit!!!] \n");
                        ch = input.next().charAt(0);
                    } while (ch == 'Y'|| ch == 'y');  
                }
                catch(Exception e)
                {
                    System.out.println("Error : " +e.getMessage());
                }
                break;                         
            case 3 : 
                try
                {
                    System.out.println("Closing the Application!!! Thank you for Visting!!!"); //Exit from the application(code execution)
                    System.exit(0);
                }
                catch(Exception e)
                {
                    System.out.println("Error : "+e.getMessage());
                }
                break;                                             
            default : System.out.println("Invalid option. Please provide a valid option. \n ");
                break;
            }    
            System.out.println("\nDo you want to continue with Main Menu ? \n[Press: y or Y to continue. Press anyother key to quit!!!] \n");
            ch = input.next().charAt(0);
        } while (ch == 'Y'|| ch == 'y');    
        System.out.println("Thank you for Visting!!!");
		}
		catch(Exception e) {
			System.out.println("Exception caught" +e);
		}
	}

	
	//Method to list the files in the ascending order
	private void filelisting() throws CusExcep {
		//Get the files & folders in an array
		String filesList[] = repo.list();
	    
		if(repo.exists())
		{		
		System.out.println("-------Listing Files of '" + repo + "' as below : ------");
	      Arrays.sort(filesList); //Sorting the files & folders in ascending order
	      for(int i=0; i<filesList.length; i++) {
	         System.out.println(filesList[i]); //Printing the files & folders name
	      }
		}
		else {
			throw new CusExcep("Path does not exists!!!");
		}
	}
	
	//Method to search the file provided by the user
	private void searchFile(String fileName) throws CusExcep {
		File file1 = new File(repo,fileName);
		if(!file1.exists()) {
			throw new CusExcep("Search Failed!!! \n File Not Found! You can search for different file !!!!!");
		}
		else 
		{
		System.out.println("Search Successful!!!");	
		System.out.println("File " +fileName+ "is available in the Repo");
		}
	}

	//Method to delete the file provided by the user
	private void deleteFile(String fileName) throws CusExcep {
		File file1 = new File(repo,fileName);
		
		if(file1.exists()) {
			System.out.println("File exists");
			boolean b = file1.delete();
			if(b==true) 
				{
			System.out.println("File " +fileName+ " deleted successfully from" +repo+ "!");
				}
		}
		else 
		{
			throw new CusExcep("File Not Found in the Repo!!! Please provide a existing file.");
		}
	}

	//Method to add the file provided by the user
	private void addFile(String fileName) {
		File file1 = new File(repo,fileName);
		try {
			if(file1.createNewFile()) {
				String data = "This is the data I am writing into the output file.";
				FileWriter output = new FileWriter(file1);		
				output.write(data);
				System.out.println("Data Writtern into file");
				System.out.println("File" +fileName+ " created successfully in" +repo+ "!");
			}
			else {
				if(file1.exists()) {
					System.out.println("File already exists");
				}
				else {
					System.out.println("File doesnot exists");
				}
			}
		} catch (IOException e) {	
			e.printStackTrace();
		}
	}
	
	//Custom Exception
	class CusExcep extends Exception{
		CusExcep(String message) {
			super(message);
		}
	}
}