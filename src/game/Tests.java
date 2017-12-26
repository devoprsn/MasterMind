package game;
import org.junit.*;
import static org.junit.Assert.*;

public class Tests {

	MockRandom rand=new MockRandom();
	MasterMind game=new MasterMind(rand);

	@Test
	public void GameLevelEasyCreatesArrayLengthThree()
	{
		rand.initializeArray(3);
		int[] nums=game.createArray("easy");
		assertEquals(3, nums.length);
	}
	
	@Test
	public void GameLevelMediumCreatesArrayLengthFour()
	{
		rand.initializeArray(4);
		int[] nums=game.createArray("medium");
		assertEquals(4, nums.length);
	}
	
	@Test
	public void GameLevelHardCreatesArrayLengthFive()
	{
		rand.initializeArray(5);
		int[] nums=game.createArray("hard");
		assertEquals(5, nums.length);
	}
	
	@Test (expected=InvalidEntryException.class)
	public void InvalidGuessCharacterThrowsException()
	{
		game.stringToIntArr("abcd");
	}
	
	@Test
	public void correctGuessReturnsTrue()
	{ 
		int[] nums={1,2,3};
		Integer[] guess={1,2,3};
		rand.setNums(nums);
		game.createArray("easy");
		assertTrue(game.checkGuess(guess));
	}
	
	@Test
	public void IncorrectGuessReturnsFalse()
	{
		int[] nums={1,2,3};
		Integer[] guess={3,2,1};
		rand.setNums(nums);
		game.createArray("easy");
		assertFalse(game.checkGuess(guess));
	}	
	
	@Test
	public void ValidGuessStringReturnsIntArrayRepresentation()
	{
		Integer[] nums={1, 2, 3, 4};
		Integer[] returnedArray=game.stringToIntArr("1234");
		for(int i=0; i<nums.length; i++)
		{
			assertEquals(nums[i], returnedArray[i]);
		}
	}

	@Test
	public void checkGuessReturnsTrueIfGuessEqualsAnswer()
	{
		rand.initializeArray(4);		
		int[] nums={1, 2, 3, 4};
		Integer[] guess={1,2,3};
		rand.setNums(nums);
		game.createArray("medium");
		assertTrue(game.checkGuess(guess));		
	}
	
	@Test
	public void checkGuessReturnsFalseIfGuessDoesNotEqualsAnswer()
	{
		rand.initializeArray(4);		
		int[] nums={1, 2, 3, 4};
		rand.setNums(nums);
		game.createArray("medium");
		Integer[] guess={6, 7, 8, 9};
		assertFalse(game.checkGuess(guess));
	}
	
	@Test
	public void checkHowManyCorrectReturnsCorrectAnswer()
	{
		rand.initializeArray(4);		
		int[] nums={1, 2, 3, 4};
		rand.setNums(nums);
		game.createArray("medium");
		Integer[] guess={1, 7, 3, 9};
		assertEquals(2, game.checkHowManyRightPlace(guess));
	}
	
	@Test
	public void HowManyWrongPlaceReturnsCorrectAnswer()
	{
		rand.initializeArray(4);		
		int[] nums={1, 2, 3, 4};
		rand.setNums(nums);
		game.createArray("medium");
		Integer[] guess={7, 4, 3, 1};
		assertEquals(2, game.checkHowManyWrongPlace(guess));
		assertEquals(1, game.checkHowManyRightPlace(guess));
	}
	
	@Test
	public void HowManyWrongPlaceWithMultiplesInAnswerReturnsCorrectAnswer()
	{
		rand.initializeArray(4);		
		int[] nums={3, 2, 3, 4};
		rand.setNums(nums);
		game.createArray("medium");
		Integer[] guess={7, 4, 8, 3};
		assertEquals(2, game.checkHowManyWrongPlace(guess));
		assertEquals(0, game.checkHowManyRightPlace(guess));
	}
	
	@Test
	public void HowManyWrongPlaceWithMultiplesInGuessReturnsCorrectAnswer()
	{
		rand.initializeArray(4);		
		int[] nums={1, 2, 3, 4};
		rand.setNums(nums);
		game.createArray("medium");
		Integer[] guess={7, 1, 3, 1};
		assertEquals(1, game.checkHowManyWrongPlace(guess));
		assertEquals(1, game.checkHowManyRightPlace(guess));
	}
	
	@Test
	public void HowManyWrongPlaceWithSomeRightNumRightPlaceAndSomeWrongPlaceReturnsCorrectAnswer()
	{
		rand.initializeArray(4);		
		int[] nums={1, 2, 3, 4};
		rand.setNums(nums);
		game.createArray("medium");
		Integer[] guess={1, 3, 5, 1};
		assertEquals(1, game.checkHowManyWrongPlace(guess));
		assertEquals(1, game.checkHowManyRightPlace(guess));
	}
	
	@Test
	public void HowManyWrongPlaceWithSameNumInRightPlaceAndWrongPlace()
	{
		rand.initializeArray(4);		
		int[] nums={3, 2, 3, 4};
		rand.setNums(nums);
		game.createArray("medium");
		Integer[] guess={7, 6, 3, 3}; //add comment
		assertEquals(1, game.checkHowManyWrongPlace(guess));
		assertEquals(1, game.checkHowManyRightPlace(guess));
	}
	
	@Test
	public void HowManyWrongPlace()
	{
		rand.initializeArray(3);
		int[] nums={1, 7, 7};
		rand.setNums(nums);
		game.createArray("easy");
		Integer[] guess={1, 7, 9};
		assertEquals(2, game.checkHowManyRightPlace(guess));
		assertEquals(0, game.checkHowManyWrongPlace(guess));	
	}
}
	
