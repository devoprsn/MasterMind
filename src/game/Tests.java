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
		
	}
	
	@Test
	public void GameLevelMediumCreatesArrayLengthFour()
	{
		
	}
	
	@Test
	public void GameLevelHardCreatesArrayLengthFive()
	{
		
	}
}
