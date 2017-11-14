package game;
import java.util.*;
import javax.swing.*;
import javax.swing.text.JTextComponent;

public class MasterMind 
{
	private int[] nums;
	private int[] guessInt;
	private String guesses;
	
	public MasterMind() {}
	
	public void createArray(int length, int max)
	{
		Random rand=new Random();
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
	
	public boolean CheckGuess(int[] arr)
	{
		for(int i=0; i<nums.length; i++)
		{
			if(nums[i]!=arr[i])
			{
				return false;
			}
		}
		return true;
	}
	
	public void result(boolean result)
	{
		if(result)
		{
			JOptionPane.showConfirmDialog(null, "You Win!", "MasterMind", JOptionPane.PLAIN_MESSAGE);
		}
		else
		{
			JOptionPane.showConfirmDialog(null, "Sorry, wrong answer", "MasterMind", JOptionPane.PLAIN_MESSAGE);
		}
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
	
	public void guessArray(int length)
	{
		JTextField[] guessString=new JTextField[length];
		
		for (int t = 0; t<guessString.length; t++) 
		{
		 	guessString[t] = new JTextField();
		 	guessString[t].setToolTipText("Enter guess here");
		}
		
		boolean validGuess;
		switch(length)
		{
			case 3:
				do {
					threeSpaces(guessString);
					validGuess=stringToIntArr(guessString);
				}while(!validGuess);
				break;
			case 4:
				do {
					fourSpaces(guessString);
					validGuess=stringToIntArr(guessString);
				}while(!validGuess);
				break;
			case 5:
				do {
					fiveSpaces(guessString);
					validGuess=stringToIntArr(guessString);	
				}while(!validGuess);
				break;
		}
	}
	
	public boolean stringToIntArr(JTextField[] guessString)
	{
		guessInt=new int[guessString.length];
		
		for (int y = 0; y<guessInt.length; y++)
		{
			try {
				guessInt[y]=Integer.parseInt(guessString[y].getText());
			}
			catch(NumberFormatException f)
			{
				JOptionPane.showMessageDialog(null, "Invalid entry. Guess must be an integer from 1-5");
				return false;
			}
			
			if(guessInt[y]<1 || guessInt[y]>5)
			{
				JOptionPane.showMessageDialog(null, "Invalid entry. Guess must be an integer from 1-5");
				return false;
			}
		}
		
		return true;
	}
	
	private void threeSpaces(JTextField[] guessString) 
	{
		final JComponent[] inputs = new JComponent[] { 
//				new JText ("appendedString"),
				new JLabel("Enter a guess from 1-5 into each slot: \n"),
				new JLabel("#1:"), guessString[0],
				new JLabel("#2:"), guessString[1],
				new JLabel("#3:"), guessString[2],
		};
		
		JOptionPane.showConfirmDialog(null, inputs, "MasterMind", JOptionPane.PLAIN_MESSAGE);
	} 
	
	private void fourSpaces(JTextField[] guessString)
	{
		final JComponent[] inputs = new JComponent[] 
				{
				new JLabel ("#1:"), guessString[0],
				new JLabel ("#2:"), guessString[1],
				new JLabel ("#3:"), guessString[2],
				new JLabel ("#4:"), guessString[3],
		};

		JOptionPane.showConfirmDialog(null, inputs, "MasterMind", JOptionPane.PLAIN_MESSAGE);
	}
	
	private void fiveSpaces(JTextField[] guessString)
	{
		final JComponent[] inputs = new JComponent[] {
				new JLabel ("#1:"), guessString[0],
				new JLabel ("#2:"), guessString[1],
				new JLabel ("#3:"), guessString[2],
				new JLabel ("#4:"), guessString[3],
				new JLabel ("#5:"), guessString[4],
		};
		
		JOptionPane.showConfirmDialog(null, inputs, "MasterMind", JOptionPane.PLAIN_MESSAGE);
	}
	
	//play method that calls all methods so main dus not have to
	public void play()
	{
		gameLevel();
		guessArray(getArrayLength());
	}
	
	public static void main(String[] args)
	{
		MasterMind game=new MasterMind();
		game.play();
		
		System.exit(0);
	}
}
