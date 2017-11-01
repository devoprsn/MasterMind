package game;
import java.util.*;

public class MasterMind {

	Random rand;
	
	public MasterMind()
	{
		rand=new Random();
	}
	
	public int[] createArray(int length, int max)
	{
		int[] nums=new int[length];
		for(int i=0; i<nums.length; i++)
		{
			nums[i]=rand.nextInt(max)+1;
		}
		
		return nums;
	}
}
