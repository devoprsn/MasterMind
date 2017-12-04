package game;

public class MockRandom implements IRandomValueGenerator{
	
	private int[] nums;

	
	public MockRandom()
	{
	}

	public void initializeArray(int length)
	{
		nums=new int[length];
	}
	
	public int[] generateRandomArray(int length)
	{		
		return nums;
	}
	
	public void setNums(int[] nums)
	{
		this.nums=nums;
	}
}
