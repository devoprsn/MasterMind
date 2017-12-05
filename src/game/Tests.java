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
		
//	@Test
//	public void ValidGuessStringReturnsIntArray()
//	{
//		int[] nums={1, 2, 3, 4};
//		assertEquals(nums, game.stringToIntArr("1234"));
//	}

}
	
