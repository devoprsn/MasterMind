package game;
import javax.swing.*;
import javax.sound.sampled.*;
import java.io.*;

public class MasterMind 
{
	//so we can pass in a mock object to test the arrays
	private IRandomValueGenerator rand;
	private int[] answer;
	private String guesses;
	
	public MasterMind(IRandomValueGenerator rand)
	{
		this.rand=rand;
		guesses="Previous guesses:\n";
	}
	
	protected int[] createArray(String level)
	{
		int length;
		if(level=="easy")
		{
			length=3;
		}
		else if(level=="medium")
		{
			length=4;
		}
		else
		{
			length=5;
		}
		
		answer=rand.generateRandomArray(length);
		return answer;
	}
	
	protected boolean checkGuess(int[] guessInt)
	{

		for(int i=0; i<guessInt.length; i++)
		{
			if(guessInt[i]!=answer[i])
			{
				return false;
			}
		}
		
		return true;
	}
	
	
	protected int checkHowManyCorrect(int[] guessInt)
	{
		int total=0;	
		for(int i=0; i<guessInt.length; i++)
		{
			if(guessInt[i]==answer[i])
			{
				total++;
			}
		}
		
		return total;
	}
	
	
	protected String checkHowMany(int[] guessInt)
	{		
		int[] copyNums=new int[guessInt.length];
		int rightNumRightPlace=0, rightNumWrongPlace=0;

		for(int i=0; i<answer.length; i++)
		{
			copyNums[i]=answer[i];
		}		

		for(int i=0; i<copyNums.length; i++)
		{
			if(copyNums[i]==guessInt[i])
			{
				rightNumRightPlace++;
				copyNums[i]=0;
			}
		}
		
		for(int i=0; i<guessInt.length; i++)
		{
			for(int j=0; j<copyNums.length; j++)
			{
				if(guessInt[i]==copyNums[j])
				{
					rightNumWrongPlace++;
					copyNums[j]=0;
				}
			}
		}
		
		StringBuilder s=new StringBuilder("Right Number Right Place: "+rightNumRightPlace
				+"\nRight Number Wrong Place: "+rightNumWrongPlace);
		return s.toString();	
	}

	protected void result(boolean win, int[] guessInt) throws IOException, LineUnavailableException
	{				
		if(win)
		{
			displayWin();
		}
		else
		{
			displayLoss(checkHowManyCorrect(guessInt));
		}
	}
	
	private void displayLoss(int correct) throws IOException, LineUnavailableException
	{		
		playAudio("missed.wav");
		JOptionPane.showConfirmDialog(null, "Sorry, wrong answer.\nYou got "+correct+" correct numbers.", 
				"MasterMind", JOptionPane.PLAIN_MESSAGE);
		
	}
	
	private void displayWin() throws IOException, LineUnavailableException
	{
		playAudio("applause.wav");
		JOptionPane.showConfirmDialog(null, "You Win!", "MasterMind", JOptionPane.PLAIN_MESSAGE);		
	}
	
	private void playAudio(String filename) throws IOException, LineUnavailableException
	{
		File soundFile;
		AudioInputStream stream;
		SourceDataLine soundLine = null;
		int BUFFER_SIZE = 64*1024; 
		try {
			soundFile = new File(filename);	
			stream = AudioSystem.getAudioInputStream(soundFile);
			AudioFormat audioFormat = stream.getFormat();
	        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
	        soundLine = (SourceDataLine) AudioSystem.getLine(info);
	        soundLine.open(audioFormat);
	        soundLine.start();
	        int nBytesRead = 0;
	        byte[] sampledData = new byte[BUFFER_SIZE];
	        while (nBytesRead != -1) 
	        {
	           nBytesRead = stream.read(sampledData, 0, sampledData.length);
	           if (nBytesRead >= 0) 
	           {
	              soundLine.write(sampledData, 0, nBytesRead);
	           }
	        }
		}
		catch(UnsupportedAudioFileException e)
		{
			e.printStackTrace();
			return;
		}
		catch(FileNotFoundException f)
		{
			System.err.println("File not found");
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
	
	protected String createTextField(String level)
	{
		String guessString=null;
		
		try {
			switch(level)
			{
				case "easy":
					guessString=threeSpaces();	
					break;
				case "medium":
					guessString=fourSpaces();
					break;
				case "hard":
					guessString=fiveSpaces();
					break;
			}
		}
		catch(InvalidEntryException i)
		{
			throw new InvalidEntryException();
		}
		
		return guessString;
	}
	
	protected int[] getUserInput(String level)
	{
		int[] guess=null;
		String guessString=null;
		boolean validGuess=false;
		
		do{		
			try {
				guessString=createTextField(level);
				guess=stringToIntArr(guessString);
				validGuess=true;
			}
			catch(InvalidEntryException i)
			{
				JOptionPane.showMessageDialog(null, "Invalid entry. Guess must be an integer from 1-9");
				validGuess=false;
			}
			
		}while(!validGuess);
		
		guesses+=(guessString+"\n");
		return guess;
	}
	
	protected int[] stringToIntArr(String guessString)
	{
		if(guessString.length()==0)
		{
			throw new InvalidEntryException();
		}
		int[] guessInt=new int[guessString.length()];
		
		for (int y=0; y<guessInt.length; y++)
		{
			try {
				if(Character.isDigit(guessString.charAt(y)))
				{
					guessInt[y]=Character.getNumericValue(guessString.charAt(y));
				}
				else
				{
					throw new InvalidEntryException();
				}
			}
			catch(NumberFormatException f)
			{
				throw new InvalidEntryException();
			}

			if(guessInt[y]<1)
			{
				throw new InvalidEntryException();
			}
		}
		
		return guessInt;
	}
	
	private String threeSpaces() 
	{
		int len=3;
		JTextField[] guessField=new JTextField[len];
		
		for (int t = 0; t<guessField.length; t++) 
		{
		 	guessField[t] = new JTextField();
		 	guessField[t].setToolTipText("Enter guess here");
		}
		
		final JComponent[] inputs = new JComponent[] { 
				//previous guesses
				new JLabel("Enter a guess from 1-9 into each slot: \n"),
				new JLabel("#1:"), guessField[0],
				new JLabel("#2:"), guessField[1],
				new JLabel("#3:"), guessField[2],
		};
		
		JOptionPane.showConfirmDialog(null, inputs, "MasterMind", JOptionPane.PLAIN_MESSAGE);

		try {
			return textFieldToString(guessField, len);
		}
		catch(InvalidEntryException i)
		{
			throw new InvalidEntryException();
		}
	} 
	
	private String textFieldToString(JTextField[] field, int len) 
	{
		String s=field[0].getText();
		for(int i=1; i<field.length; i++)
		{		
			s+=field[i].getText();
		}
	
		if(s.length()!=len)
		{
			throw new InvalidEntryException();
		}
		return s;
	}
	
	private String fourSpaces()
	{
		int len=4;
		JTextField[] guessField=new JTextField[len];
		
		for (int t = 0; t<guessField.length; t++) 
		{
		 	guessField[t] = new JTextField();
		 	guessField[t].setToolTipText("Enter guess here");
		}
		
		final JComponent[] inputs = new JComponent[] {
				new JLabel("Enter a guess from 1-9 into each slot: \n"),
				new JLabel ("#1:"), guessField[0],
				new JLabel ("#2:"), guessField[1],
				new JLabel ("#3:"), guessField[2],
				new JLabel ("#4:"), guessField[3],
		};

		JOptionPane.showConfirmDialog(null, inputs, "MasterMind", JOptionPane.PLAIN_MESSAGE);
		try {
			return textFieldToString(guessField, len);
		}
		catch(InvalidEntryException i)
		{
			throw new InvalidEntryException();
		}
	}
	
	private String fiveSpaces()
	{
		int len=5;
		JTextField[] guessField=new JTextField[len];
		
		for (int t = 0; t<guessField.length; t++) 
		{
		 	guessField[t] = new JTextField();
		 	guessField[t].setToolTipText("Enter guess here");
		}
		final JComponent[] inputs = new JComponent[] {
				new JLabel("Enter a guess from 1-9 into each slot: \n"),
				new JLabel ("#1:"), guessField[0],
				new JLabel ("#2:"), guessField[1],
				new JLabel ("#3:"), guessField[2],
				new JLabel ("#4:"), guessField[3],
				new JLabel ("#5:"), guessField[4],
		};
		
		
		
		JOptionPane.showConfirmDialog(null, inputs, "MasterMind", JOptionPane.PLAIN_MESSAGE);
		try {
			return textFieldToString(guessField, len);
		}
		catch(InvalidEntryException i)
		{
			throw new InvalidEntryException();
		}
	}
	
	//play method that calls all methods so main dus not have to
	public void play() throws IOException, LineUnavailableException
	{
		int x;
		do
		{
			String level=gameLevel();
			createArray(level);
				boolean win=false;
				int tries=0;
			while(!win && tries<=10)
			{
				tries++;
				int[] guessInt=getUserInput(level);
				win=checkGuess(guessInt);
				result(win, guessInt);
			}
			
			if(tries==11)
			{
//				playAudio("game-over");
				JOptionPane.showMessageDialog(null, "Game over");
			}
			x=JOptionPane.showConfirmDialog(null, "Would you like to play again?", "MasterMind", JOptionPane.YES_OPTION);
		}while(x==0);
			
	}
	
	public static void main(String[] args) throws IOException, LineUnavailableException
	{
		MasterMind game=new MasterMind(new RegRandom());
		game.play();
		
		System.exit(0);
	}
}
