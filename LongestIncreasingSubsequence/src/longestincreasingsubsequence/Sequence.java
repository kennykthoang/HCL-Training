package longestincreasingsubsequence;

import java.util.ArrayList;
import java.lang.Math;

public class Sequence 
{
	private int arraySize;
	private int min = 1;
	private int max = 100;
	private ArrayList<Integer> sequence;
	
	// Constructor
	public Sequence(int len) 
	{
		if(len == 0) 
		{
			int range = (max - min) + 1;
			arraySize = (int)(Math.random() * range) + min;
			System.out.println("Generating random list size...");
			System.out.println("Random list size is: " + arraySize);
		}
		else 
		{
			arraySize = len;
		}
	}
	
	public void buildSequence()
	{
		sequence = new ArrayList<Integer>();
		
		for(int i = 0; i < arraySize; i++) 
		{
			int random = (int) (Math.random() * ((max - min) + 1)) + min;
			sequence.add(random);
		}
	}
	
	public ArrayList<Integer> getSequence()
	{
		return sequence;
	}
	
	public int getSize() 
	{
		return arraySize;
	}
	
	public void printSeq() 
	{
		// Print using the getSequence method
		System.out.println("Sequence: " + this.getSequence());
		
		// Print using the toString method
//		System.out.println("Sequence: " + sequence.toString());
	}
}
