package calculator;


// Calculator performs basic arithmetic operations (addition, subtraction, multiplication, and division)
public class Calculator
{
	public Calculator()
	{
		System.out.println("Calculator running...");
	}
	
	private double firstOperand = 0, secondOperand = 0;
	private String operator = "";
	
	public void setFirstOperand(double num)
	{
		this.firstOperand = num;
	}
	
	public void setSecondOperand(double num)
	{
		this.secondOperand = num;
	}
	
	public void setOeprator(String input)
	{
		this.operator = input;
	}
	
	public double getFirstOperand()
	{
		return firstOperand;
	}
	
	public double getSecondOperand()
	{
		return secondOperand;
	}
	
	public String getOperator()
	{
		return operator;
	}
	
	public double operation(String operator)
	{
		switch(operator)
		{
			case "+":
				return addition();
			case "-":
				return subtraction();
			case "*":
				return multiplication();
			case "/":
			{		
				return division();
			}
		}
		return 0;
	}
	
	private double addition()
	{
		return firstOperand + secondOperand;
	}
	
	private double subtraction()
	{
		return firstOperand - secondOperand;
	}
	
	private double multiplication()
	{
		return firstOperand * secondOperand;
	}
	
	private double division()
	{
			return firstOperand / secondOperand;
	}
}