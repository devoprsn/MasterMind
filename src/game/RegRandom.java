package game;
import java.util.Random;

public class RegRandom implements IRandomValueGenerator{
	
	private Random rand;
	
	public RegRandom()
	{
		rand=new Random();
	}
		
	public int[] generateRandomArray(int length)
	{
		int[] nums=new int[length];
		for(int i=0; i<length; i++)
		{
			nums[i]=rand.nextInt(9)+1;
		}
		
		return nums;
	}
}
