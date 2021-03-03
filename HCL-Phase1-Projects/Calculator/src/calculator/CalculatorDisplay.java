package calculator;

public class CalculatorDisplay extends Calculator{

	public static void display(Calculator calc, double result)
	{	
		if(calc.getOperator().equals("/") && calc.getSecondOperand() == 0)
			System.out.println("ERROR! Divide by 0!");
		else if(result % 1 == 0)
			System.out.println((int) calc.getFirstOperand() + " " + calc.getOperator() + " " + (int) calc.getSecondOperand() + " = " + (int) result);
		else if(result % 1 != 0&& calc.getFirstOperand() % 1 == 0 && calc.getSecondOperand() % 1 == 0)
			System.out.println((int) calc.getFirstOperand() + " " + calc.getOperator() + " " + (int) calc.getSecondOperand() + " = " + result);
		else
			System.out.println(calc.getFirstOperand() + " " + calc.getOperator() + " " + calc.getSecondOperand() + " = " + result);
	}
}
