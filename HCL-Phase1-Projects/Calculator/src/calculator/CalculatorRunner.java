package calculator;

// Imports
import java.util.Scanner;

public class CalculatorRunner {

	public static void main(String[] args) {
		Calculator calc = new Calculator();
		double operand1, operand2, result;
		String operator = null;
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to the Calculator app!\nThis calculator accepts the following operands: + - * /");
		boolean validInput = false;
		while(true)
		{
			do{
				System.out.println("Please input the first operand or \"~\" to exit.");
				String str = scan.nextLine();
				if(str.equals("~"))
				{
					System.out.println("Exiting...");
					scan.close();
					System.exit(0);
				}
				try 
				{
					operand1 = Double.parseDouble(str);
					calc.setFirstOperand(operand1);
					validInput = true;
				}
				catch(NumberFormatException e)
				{
					System.out.printf("%s is not valid input. Please enter valid a operand or \"~\".\n", str);
					validInput = false;
				}
			}while(!validInput);
			
			validInput = false;
			do{
				System.out.println("Please input the operator:");
				String str = scan.nextLine();
				if(!str.equals("+") && !str.equals("-") && !str.equals("*") && !str.equals("/"))
				{
					System.out.printf("%s is not valid input. Please enter a valid operator.\n", str);
				}
				else
				{
					operator = str;
					calc.setOeprator(operator);
					validInput = true;
				}
			}while(!validInput);
			
			validInput = false;
			do{
				System.out.println("Please input the second operand:");
				while(!scan.hasNextDouble()) {
					String str = scan.nextLine();
					System.out.printf("%s is not valid input. Please enter a valid number: ", str);
				}
				if(scan.hasNextDouble())
				{
					operand2 = scan.nextDouble();
					scan.nextLine();
					calc.setSecondOperand(operand2);
					validInput = true;
				}
			}while(!validInput);
			
			result = calc.operation(operator);
			CalculatorDisplay.display(calc, result);
		}
	}
}
