package fixbugs;

public class Main {

    public static void main(String[] args)
    {
        /*System.out.println("Hello World!");*/
    	Menu menu = new Menu();
        System.out.println("\n**************************************\n");
        System.out.println("\tWelcome to TheDesk \n");
        System.out.println("**************************************");
        do
        {
        	menu.optionsSelection();
        }while(true);
    }
}
	