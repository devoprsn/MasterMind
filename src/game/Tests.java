package game;
import org.junit.*;
import static org.junit.Assert.*;

public class Tests {

	MasterMind game=new MasterMind();
	
	@Test
	public void createArray()
	{
		game.createArray(3, 5);
		assertEquals(3, game.nums.length);
	}

	@Test
	public void GameLevelEasyCreatesArrayLengthThree()
	{
		game.setLevel("easy");
		assertEquals(3, game.getArrayLength());
	}
	
	@Test
	public void GameLevelMediumCreatesArrayLengthFour()
	{
		game.setLevel("medium");
		assertEquals(4, game.getArrayLength());
	}
	
	@Test
	public void GameLevelHardCreatesArrayLengthFive()
	{
		game.setLevel("hard");
		assertEquals(5, game.getArrayLength());
	}
	
	
}
