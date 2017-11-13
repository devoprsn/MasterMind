package game;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class MasterMind {

	private Random rand;
	private int[] nums;
	private JTextField[] guessString;
	private int[] guessInt;
	
	public MasterMind()
	{
		rand=new Random();
	}
	
	public void createArray(int length, int max)
	{
		nums=new int[length];
		for(int i=0; i<nums.length; i++)
		{
			nums[i]=rand.nextInt(max)+1;
		}
	}
	
	public int getArrayLength()
	{
		return nums.length;
	}
	
	public boolean CheckGuess()
	{
		for(int i=0; i<nums.length; i++)
		{
			if(nums[i]!=guessInt[i])
			{
				return false;
			}
		}
		return true;
	}
	
	public void gameLevel()
	{
		JRadioButton easy=new JRadioButton("Easy");
			easy.setActionCommand("easy");
			easy.setSelected(true);
		JRadioButton medium=new JRadioButton("Medium");
			medium.setActionCommand("medium");
		JRadioButton hard=new JRadioButton("Hard");
			hard.setActionCommand("hard");
		
		final ButtonGroup group=new ButtonGroup();
			group.add(easy);
			group.add(medium);
			group.add(hard);

		final JPanel buttons=new JPanel();
		buttons.add(new JLabel("Pick Level: "));
			buttons.add(easy);
			buttons.add(medium);
			buttons.add(hard);
			
		JOptionPane.showConfirmDialog(null, buttons, "MasterMind", JOptionPane.PLAIN_MESSAGE);
			
		if(easy.isSelected())
		{
			createArray(3, 5);
		}
		else if(medium.isSelected())
		{
			createArray(4, 5);
		}
		else
		{
			createArray(5, 5);
		}
	}
	
	//input validation- input verifier?
	public void guessArray(int length)
	{
		guessString=new JTextField[length];
		
		for (int t = 0; t<guessString.length; t++) 
		{
		 	guessString[t] = new JTextField();
		 	guessString[t].setToolTipText("Enter guess here");
		}
		
		switch (length){
		case 3:
			threeSpaces();
			guessInt=stringToIntArr();
			break;
		case 4:
			fourSpaces();
			guessInt=stringToIntArr();
			break;
		case 5:
			fiveSpaces();
			guessInt=stringToIntArr();
			break;
		}
	}
	
	public int[] stringToIntArr()
	{
		guessInt=new int[guessString.length];
		
		for (int y = 0; y<guessInt.length; y++)
		{
			guessInt[y]=Integer.parseInt(guessString[y].getText());
		}
		
		return guessInt;
	}
	
	private void threeSpaces()
	{
		final JComponent[] inputs = new JComponent[] { 
				new JLabel("Place #1:"), guessString[0],
				new JLabel("Place #2:"), guessString[1],
				new JLabel("Place #3:"), guessString[2],
		};
		JOptionPane.showConfirmDialog(null, inputs, "MasterMind", JOptionPane.PLAIN_MESSAGE);
	}
	
	private void fourSpaces()
	{
		final JComponent[] inputs = new JComponent[] 
				{
				new JLabel ("Place #1:"), guessString[0],
				new JLabel ("Place #2:"), guessString[1],
				new JLabel ("Place #3:"), guessString[2],
				new JLabel ("Place #4:"), guessString[3],
		};
		//int result =
		JOptionPane.showConfirmDialog(null, inputs, "MasterMind", JOptionPane.PLAIN_MESSAGE);
	}
	
	private void fiveSpaces()
	{
		final JComponent[] inputs = new JComponent[] {
				new JLabel ("Place #1:"), guessString[0],
				new JLabel ("Place #2:"), guessString[1],
				new JLabel ("Place #3:"), guessString[2],
				new JLabel ("Place #4:"), guessString[3],
				new JLabel ("Place #5:"), guessString[4],
		};
		JOptionPane.showConfirmDialog(null, inputs, "MasterMind", JOptionPane.PLAIN_MESSAGE);
	}
	
	//play method that calls all methods so main dus not have to
	public void play()
	{
		gameLevel();
	}
	
	public static void main(String[] args)
	{
		MasterMind game=new MasterMind();
		game.play();
		game.guessArray(game.getArrayLength());
		
		System.exit(0);
	}
}
