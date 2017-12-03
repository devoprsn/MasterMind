package game;
import org.junit.*;
import static org.junit.Assert.*;

public class Tests {

	MockRandom rand=new MockRandom();
	MasterMind game=new MasterMind(rand);
	Testing t=new Testing(rand);

	@Test
	public void GameLevelEasyCreatesArrayLengthThree()
	{
		int[] nums = game.createArray("easy");
		assertEquals(3, nums.length);
	}
	
	@Test
	public void GameLevelMediumCreatesArrayLengthFour()
	{
		int[] nums =game.createArray("medium");
		assertEquals(4, nums.length);
	}
	
	@Test
	public void GameLevelHardCreatesArrayLengthFive()
	{
		int[] nums=game.createArray("hard");
		assertEquals(5, nums.length);
	}
	
	@Test (expected=InvalidEntryException.class)
	public void InvalidGuessNumberThrowsException()
	{
		game.stringToIntArr("1942");
	}
	
	@Test (expected=InvalidEntryException.class)
	public void InvalidGuessCharacterThrowsException()
	{
		game.stringToIntArr("abcd");
	}
	
//	@Test
//	public void ValidGuessStringReturnsIntArray()
//	{
//		int[] nums={1, 2, 3, 4};
//		assertEquals(nums, game.stringToIntArr("1234"));
//	}
//	
//	@Test
//	public void OneDuplicateGuessReturns() throws IOException, LineUnavailableException
//	{
//		game.createArray("medium");
//		int[] nums={5, 5, 2, 3};
//		rand.setNums(nums);
//		int[] guess={4, 5, 5, 1};
//		
//		int[] r=game.checkGuess(guess);
//		assertEquals(1, r[1]);
//	}
//	
//	@Test
//	public void CorrectGuessGeneratesCorrectDialogBox()
//	{
//		int[] nums={1, 2, 3, 4};
//		rand.setNums(nums);
//		int[] guess={1, 2, 3, 4};
//		
//		int[] r=game.checkGuess(guess);
//		assertEquals(4, r[0]);
//	}
//	
//	@Test
//	public void CheckHowManyCorrect()
//	{
//		game.createArray("medium");
//		int[] nums={1, 2, 3, 4};
//		rand.setNums(nums);
//		int[] guess={4, 3, 2, 1};
//		
//		int[] r=game.checkGuess(guess);
//		assertEquals(4, r[1]);
//	}
}
	
