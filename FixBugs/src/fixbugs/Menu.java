package fixbugs;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu 
{
    // ArrayList<Integer> arrlist = new ArrayList<Integer>();
    ArrayList<Integer> expenses = new ArrayList<Integer>();
    
    public Menu()
    {
    	this.addExpenses();
    }
	
    public void optionsSelection() 
    {
        String[] arr = {"1. I wish to review my expenditure",
                "2. I wish to add my expenditure",
                "3. I wish to delete my expenditure",
                "4. I wish to sort the expenditures",
                "5. I wish to search for a particular expenditure",
                "6. Close the application"
        };
        
        // int[] arr1 = {1,2,3,4,5,6}; // This array is redundant
        int  slen = arr.length; // set slen to the String arr length instead
        
        for(int i=0; i<slen;i++){
            System.out.println(arr[i]);
            // display the all the Strings mentioned in the String array
        }
        
        System.out.println("\nEnter your choice:\t");
        Scanner sc = new Scanner(System.in);
        int  options =  sc.nextInt();
	        switch (options){
	            case 1:
	                System.out.println("Your saved expenses are listed below: \n");
	                System.out.println(expenses+"\n");
	                // optionsSelection(); Removed this call because the menu is continuously displayed from Main
	                break;
	            case 2:
	                System.out.println("Enter the value to add your Expense: \n");
	                int value = sc.nextInt();
	                expenses.add(value);
	                System.out.println("Your value is updated\n");
	                // expenses.addAll(arrlist); // not needed we can use expenses arraylist only arrlist is redundant
	                System.out.println(expenses+"\n");
	                // optionsSelection(); Removed this call because the menu is continuously displayed from Main
	
	                break;
	            case 3:
	                System.out.println("You are about the delete all your expenses! \nConfirm again by selecting the same option...\n");
	                int con_choice = sc.nextInt();
	                if(con_choice==options){
	                       expenses.clear();
	                    System.out.println(expenses+"\n");
	                    System.out.println("All your expenses are erased!\n");
	                } else {
	                    System.out.println("Oops... try again!");
	                }
	                // optionsSelection(); Removed this call because the menu is continuously displayed from Main
	                break;
	            case 4:
	                sortExpenses(expenses);
	                // optionsSelection(); Removed this call because the menu is continuously displayed from Main
	                break;
	            case 5:
	            	System.out.println("Enter the expense you need to search:\t");
	            	int expense = sc.nextInt();
	                searchExpenses(expenses, expense);
	                // optionsSelection(); Removed this call because the menu is continuously displayed from Main
	                break;
	            case 6:
	            	sc.close();
	                closeApp();
	                break;
	            default:
	                System.out.println("You have made an invalid choice!");
	                break;
	        }
    }
    
    private void addExpenses()
    {
    	expenses.add(1000);
        expenses.add(2300);
        expenses.add(45000);
        expenses.add(32000);
        expenses.add(110);
    }
    
    private static void closeApp() {
        System.out.println("Closing your application... \nThank you!");
        System.exit(0);
            }
    private static void searchExpenses(ArrayList<Integer> arrayList, int expense) {
        int leng = arrayList.size();
        //Complete the method
        // Search using linear search
        boolean found = false;
        for(int i = 0; i < leng; i++)
        {
        	int current = arrayList.get(i);
        	if(current == expense)
        	{
        		System.out.printf("Found expenditure at index %d.\n", i);
        		found = true;
        	}
        }
        
        if(!found)
        {
        	System.out.println("Did not find the expenditure!");
        }
        
    }
    private static void sortExpenses(ArrayList<Integer> arrayList) {
        int arrlength =  arrayList.size();
        QuickSort quickObject = new QuickSort();
        quickObject.sort(arrayList);
        
        System.out.println("Sorted Expenses: " + arrayList.toString());
       //Complete the method. The expenses should be sorted in ascending order.
    }
}
