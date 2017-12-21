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
		rand.setNums(nums);
		game.createArray("easy");
		assertTrue(game.checkGuess(nums));
	}
	
	@Test
	public void IncorrectGuessReturnsFalse()
	{
		int[] nums={1,2,3};
		int[] guess={3,2,1};
		rand.setNums(nums);
		game.createArray("easy");
		assertFalse(game.checkGuess(guess));
	}	
	
	@Test
	public void ValidGuessStringReturnsIntArrayRepresentation()
	{
		int[] nums={1, 2, 3, 4};
		int[] returnedArray=game.stringToIntArr("1234");
		for(int i=0; i<nums.length; i++)
		{
			assertEquals(nums[i], returnedArray[i]);
		}
	}

	@Test
	public void checkGuessReturnsTrueIfGuessEqualsAnswer()
	{
		rand.initializeArray(4);		
		int[] guess={1, 2, 3, 4};
		rand.setNums(guess);
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
		int[] guess={6, 7, 8, 9};
		assertFalse(game.checkGuess(guess));
	}
	
	@Test
	public void checkHowManyCorrectReturnsCorrectAnswer()
	{
		rand.initializeArray(4);		
		int[] nums={1, 2, 3, 4};
		rand.setNums(nums);
		game.createArray("medium");
		int[] guess={1, 7, 3, 9};
		assertEquals(2, game.checkHowManyCorrect(guess));
	}
	
	@Test
	public void HowManyWrongPlaceReturnsCorrectAnswer()
	{
		rand.initializeArray(4);		
		int[] nums={1, 2, 3, 4};
		rand.setNums(nums);
		game.createArray("medium");
		int[] guess={7, 4, 3, 1};
		assertEquals(2, game.howManyWrongPlace(guess));
	}
	
	@Test
	public void HowManyWrongPlaceWithMultiplesInGuessReturnsCorrectAnswer()
	{
		rand.initializeArray(4);		
		int[] nums={3, 2, 3, 4};
		rand.setNums(nums);
		game.createArray("medium");
		int[] guess={7, 4, 3, 1};
		assertEquals(2, game.howManyWrongPlace(guess));
	}
	
	@Test
	public void HowManyWrongPlaceWithMultiplesInAnswerReturnsCorrectAnswer()
	{
		rand.initializeArray(4);		
		int[] nums={1, 2, 3, 4};
		rand.setNums(nums);
		game.createArray("medium");
		int[] guess={7, 1, 3, 1};
		assertEquals(2, game.howManyWrongPlace(guess));
	}
}
	
