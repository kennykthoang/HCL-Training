package longestincreasingsubsequence;

import java.util.Scanner;
import java.util.ArrayList;

public class LongestIncreasingSubsequence 
{
	private Sequence sequence;
	
	
	public Sequence getSequence() 
	{
		return sequence;
	}
	
	public void getInput() 
	{
		Scanner scan = new Scanner(System.in);
		int s = 0;
		boolean validInput;
		do{
			System.out.println("Please input the ArrayList Size (Input 0 for a random size)");
			System.out.println("Size: ");
			String str = scan.nextLine();
			try 
			{
				s = Integer.parseInt(str);
				validInput = true;
			}
			catch(NumberFormatException e)
			{
				System.out.printf("%s is not valid input. Please enter an integer.\n", str);
				validInput = false;
			}
		}while(!validInput);
		
		sequence = new Sequence(s);
		sequence.buildSequence();
		
		scan.close();
	}
	
	public void setSeq(Sequence s) 
	{
			sequence = s;
	}
	
	public void search()
	{
		sequence.printSeq();
		int start = 0;
		int end = 0;
		int temp = 0;
		int tempLongestSeq = 0;
		int longestSeq = 0;
		ArrayList<Integer> seq = sequence.getSequence();
		
		for(int i = 1; i< sequence.getSize(); i++) {
			
			if(seq.get(i) > seq.get(i-1)) 
			{
				if(tempLongestSeq == 0) 
				{
					temp = i;
				}
				tempLongestSeq++;				
			}
			else if(tempLongestSeq > longestSeq) 
			{
				start = temp;
				longestSeq = tempLongestSeq;
				tempLongestSeq = 0;
				end=i;
			} 
			else 
			{
				tempLongestSeq = 0;
			}
		}
		System.out.println("Finding Longest Subsequence...");
		System.out.println("Starts at index: " + (start-1) +  "\nEnds at index: " + end);
		System.out.println("Longest Subsequence: " + seq.subList(start-1, end));
	}
}
