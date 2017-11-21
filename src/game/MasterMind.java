package game;
import java.util.*;
import javax.swing.*;
import javax.swing.text.JTextComponent;

public class MasterMind 
{
	protected int[] nums;
	private int[] guessInt;
	private String guesses;
	
	public MasterMind() {}
	
	protected void createArray(int length, int max)
	{
		Random rand=new Random();
		nums=new int[length];
		for(int i=0; i<nums.length; i++)
		{
			nums[i]=rand.nextInt(max)+1;
		}	
	}
	
	protected int getArrayLength()
	{
		return nums.length;
	}
	
	protected boolean CheckGuess(int[] arr)
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
	
	protected void result(boolean result)
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
	
	protected String gameLevel()
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
					
		return group.getSelection().getActionCommand();
	}
	
	protected void setLevel(String lev)
	{
		if(lev=="easy")
		{
			createArray(3, 5);
		}
		else if(lev=="medium")
		{
			createArray(4, 5);		
		}
		else
		{
			createArray(5, 5);
		}
	}
	
	protected void createTextField(String level)
	{
		boolean validGuess;
		switch(level)
		{
			case "easy":
				do {
					JTextField[] guessString=threeSpaces();
					validGuess=stringToIntArr(guessString);
				}while(!validGuess);
				break;
			case "medium":
				do {
					JTextField[] guessString=fourSpaces();
					validGuess=stringToIntArr(guessString);
				}while(!validGuess);
				break;
			case "hard":
				do {
					JTextField[] guessString=fiveSpaces();
					validGuess=stringToIntArr(guessString);	
				}while(!validGuess);
				break;
		}
	}
	
	protected boolean stringToIntArr(JTextField[] guessString)
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
	
	private JTextField[] threeSpaces() 
	{
		JTextField[] guessString=new JTextField[3];
		
		for (int t = 0; t<guessString.length; t++) 
		{
		 	guessString[t] = new JTextField();
		 	guessString[t].setToolTipText("Enter guess here");
		}
		
		final JComponent[] inputs = new JComponent[] { 
//				new JText ("appendedString"),
				new JLabel("Enter a guess from 1-5 into each slot: \n"),
				new JLabel("#1:"), guessString[0],
				new JLabel("#2:"), guessString[1],
				new JLabel("#3:"), guessString[2],
		};
		
		JOptionPane.showConfirmDialog(null, inputs, "MasterMind", JOptionPane.PLAIN_MESSAGE);
		return guessString;
	} 
	
	private JTextField[] fourSpaces()
	{
		JTextField[] guessString=new JTextField[4];
		
		for (int t = 0; t<guessString.length; t++) 
		{
		 	guessString[t] = new JTextField();
		 	guessString[t].setToolTipText("Enter guess here");
		}
		
		final JComponent[] inputs = new JComponent[] 
				{
				new JLabel ("#1:"), guessString[0],
				new JLabel ("#2:"), guessString[1],
				new JLabel ("#3:"), guessString[2],
				new JLabel ("#4:"), guessString[3],
		};

		JOptionPane.showConfirmDialog(null, inputs, "MasterMind", JOptionPane.PLAIN_MESSAGE);
		return guessString;
	}
	
	private JTextField[] fiveSpaces()
	{
		JTextField[] guessString=new JTextField[5];
		
		for (int t = 0; t<guessString.length; t++) 
		{
		 	guessString[t] = new JTextField();
		 	guessString[t].setToolTipText("Enter guess here");
		}
		final JComponent[] inputs = new JComponent[] {
				new JLabel ("#1:"), guessString[0],
				new JLabel ("#2:"), guessString[1],
				new JLabel ("#3:"), guessString[2],
				new JLabel ("#4:"), guessString[3],
				new JLabel ("#5:"), guessString[4],
		};
		
		JOptionPane.showConfirmDialog(null, inputs, "MasterMind", JOptionPane.PLAIN_MESSAGE);
		return guessString;
	}
	
	//play method that calls all methods so main dus not have to
	public void play()
	{
		String level=gameLevel();
		setLevel(level);
		createTextField(level);
	}
	
	public static void main(String[] args)
	{
		MasterMind game=new MasterMind();
		game.play();
		
		System.exit(0);
	}
}
