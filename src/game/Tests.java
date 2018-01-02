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
		Integer[] nums=game.createArray(3);
		assertEquals(3, nums.length);
	}
	
	@Test
	public void GameLevelMediumCreatesArrayLengthFour()
	{
		rand.initializeArray(4);
		Integer[] nums=game.createArray(4);
		assertEquals(4, nums.length);
	}
	
	@Test
	public void GameLevelHardCreatesArrayLengthFive()
	{
		rand.initializeArray(5);
		Integer[] nums=game.createArray(5);
		assertEquals(5, nums.length);
	}
	
	@Test
    public void getLengthOfLevelReturnsCorrectAnswer()
    {
        assertEquals(3, game.getLengthOfLevel("easy"));
        assertEquals(4, game.getLengthOfLevel("medium"));
        assertEquals(5, game.getLengthOfLevel("hard"));   
    }
	
	@Test (expected=InvalidEntryException.class)
	public void InvalidGuessCharacterThrowsException()
	{
		game.stringToIntArr("abcd");
	}
	
	@Test
	public void correctGuessReturnsTrue()
	{ 
		Integer[] nums={1,2,3};
		Integer[] guess={1,2,3};
		rand.setNums(nums);
		game.createArray(3);
		assertTrue(game.checkGuess(guess));
	}
	
	@Test
	public void IncorrectGuessReturnsFalse()
	{
		Integer[] nums={1,2,3};
		Integer[] guess={3,2,1};
		rand.setNums(nums);
		game.createArray(3);
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
		Integer[] nums={1, 2, 3, 4};
		Integer[] guess={1,2,3};
		rand.setNums(nums);
		game.createArray(4);
		assertTrue(game.checkGuess(guess));		
	}
	
	@Test
	public void checkGuessReturnsFalseIfGuessDoesNotEqualsAnswer()
	{
		rand.initializeArray(4);		
		Integer[] nums={1, 2, 3, 4};
		rand.setNums(nums);
		game.createArray(4);
		Integer[] guess={6, 7, 8, 9};
		assertFalse(game.checkGuess(guess));
	}
	
	@Test
	public void checkHowManyCorrectReturnsCorrectAnswer()
	{
		rand.initializeArray(4);		
		Integer[] nums={1, 2, 3, 4};
		rand.setNums(nums);
		game.createArray(4);
		Integer[] guess={1, 7, 3, 9};
		assertEquals(2, game.checkHowManyRightPlace(guess));
	}
	
	@Test
	public void HowManyWrongPlaceReturnsCorrectAnswer()
	{
		rand.initializeArray(4);		
		Integer[] nums={1, 2, 3, 4};
		rand.setNums(nums);
		game.createArray(4);
		Integer[] guess={7, 4, 3, 1};
		assertEquals(2, game.checkHowManyWrongPlace(guess));
		assertEquals(1, game.checkHowManyRightPlace(guess));
	}
	
	@Test
	public void HowManyWrongPlaceWithMultiplesInAnswerReturnsCorrectAnswer()
	{
		rand.initializeArray(4);		
		Integer[] nums={3, 2, 3, 4};
		rand.setNums(nums);
		game.createArray(4);
		Integer[] guess={7, 4, 8, 3};
		assertEquals(2, game.checkHowManyWrongPlace(guess));
		assertEquals(0, game.checkHowManyRightPlace(guess));
	}
	
	@Test
	public void HowManyWrongPlaceWithMultiplesInGuessReturnsCorrectAnswer()
	{
		rand.initializeArray(4);		
		Integer[] nums={1, 2, 3, 4};
		rand.setNums(nums);
		game.createArray(4);
		Integer[] guess={7, 1, 3, 1};
		assertEquals(1, game.checkHowManyWrongPlace(guess));
		assertEquals(1, game.checkHowManyRightPlace(guess));
	}
	
	@Test
	public void HowManyWrongPlaceWithSomeRightNumRightPlaceAndSomeWrongPlaceReturnsCorrectAnswer()
	{
		rand.initializeArray(4);		
		Integer[] nums={1, 2, 3, 4};
		rand.setNums(nums);
		game.createArray(4);
		Integer[] guess={1, 3, 5, 1};
		assertEquals(1, game.checkHowManyWrongPlace(guess));
		assertEquals(1, game.checkHowManyRightPlace(guess));
	}
	
	@Test
	public void HowManyWrongPlaceWithDuplicateNumberBeforeRightNumRightPlace()
	{
		rand.initializeArray(4);		
		Integer[] nums={3, 2, 3, 4};
		rand.setNums(nums);
		game.createArray(4);
		Integer[] guess={7, 6, 3, 3}; //add comment
		assertEquals(1, game.checkHowManyWrongPlace(guess));
		assertEquals(1, game.checkHowManyRightPlace(guess));
	}
	
	@Test
	public void HowManyWrongPlaceWithDuplicateNumberAfterRightNumRightPlace()
	{
		rand.initializeArray(3);
		Integer[] nums={1, 7, 7};
		rand.setNums(nums);
		game.createArray(3);
		Integer[] guess={1, 7, 9};
		assertEquals(2, game.checkHowManyRightPlace(guess));
		assertEquals(0, game.checkHowManyWrongPlace(guess));	
	}	
	 
    @Test
    public void HowManyRightPlaceWithAllSameNumInGuessReturnsCorrectAnswer()
    {
        rand.initializeArray(4);        
        Integer[] nums={3, 2, 3, 4};
        rand.setNums(nums);
        game.createArray(4);
        Integer[] guess={3, 3, 3, 3};
        assertEquals(2, game.checkHowManyRightPlace(guess));
    }
}
	
