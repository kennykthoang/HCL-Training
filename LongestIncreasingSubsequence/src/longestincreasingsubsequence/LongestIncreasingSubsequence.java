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
			System.out.print("Size: ");
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
		int current = 0;
		int currentLongestSeq = 0;
		int longestSeq = 0;
		ArrayList<Integer> seq = sequence.getSequence();
		for(int i = 1; i < sequence.getSize(); i++) 
		{
			
			if(seq.get(i) > seq.get(i-1) && currentLongestSeq == 0) 
			{
				current = i;
				currentLongestSeq++;				
			}
			else if(seq.get(i) > seq.get(i-1))
			{
				// Subsequence size increased, update length and current end of subsequence
				currentLongestSeq++;
			}
			else if(currentLongestSeq > longestSeq)  // Found new longest subsequence
			{
				// Update start of the longest subsequence and longest subsequence size
				start = current;
				longestSeq = currentLongestSeq;
				currentLongestSeq = 0;
				end=i;
			} 
			else
			{
				currentLongestSeq = 0;
			}
		}
		
		/*  Fixes bug if last element is supposed to be included in subsequence the program outputs no subsequence found
		 *  If the currentLongestSequence is longer than the longestSequence after exiting the for-loop then that means
		 *  that the last element makes the currentLongestSequence the new longest. This also fixes the edge case
		 *  that the randomly populated ArrayList is sorted in increasing order.
		 */
		if(currentLongestSeq > longestSeq)
		{
			start = current;
			longestSeq = currentLongestSeq;
			end = sequence.getSize();
		}
		
		System.out.println("Finding Longest Subsequence...");
		
		// Solves the problem if the first index is part of the subsequence
		// if not it would cause an IndexOutOfBoundsException
		if(start != 0)
		{
			System.out.println("Starts at index: " + (start-1) +  "\nEnds at index: " + (end-1));
			System.out.println("Longest Subsequence: " + seq.subList(start-1, end));
		}
		else
		{
			System.out.println("No longest increasing subsequence found!");
		}
	}
}
