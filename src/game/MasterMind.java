package game;
import java.util.*;
import javax.swing.*;
import javax.sound.sampled.*;
import java.io.*;

public class MasterMind 
{
	private IRandomValueGenerator rand;
	private int[] answer;
	private int[] guessInt;
	private String guesses;
	
	public MasterMind(IRandomValueGenerator rand)
	{
		this.rand=rand;
		guesses="Previous Guesses:";
	}
	
	protected int[] createArray(String lev)
	{
		int length;
		if(lev=="easy")
		{
			length=3;
		}
		else if(lev=="medium")
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
	
//	protected int[] checkGuess(int[] guessInt)
//	{
//		int[] copyNums=new int[guessInt.length];
//		int[] results = {0, 0}; //results[0] holds number of right number in the right place and
//								//results[1] holds number of right numbers in the wrong place
//		for(int i=0; i<answer.length; i++)
//		{
//			copyNums[i]=answer[i];
//		}
//		
//		for(int i=0; i<copyNums.length; i++)
//		{
//			if(copyNums[i]==guessInt[i])
//			{
//				results[0]++;
//				copyNums[i]=0;
//			}
//		}
//		
//		for(int i=0; i<guessInt.length; i++)
//		{
//			for(int j=0; j<copyNums.length; j++)
//			{
//				if(guessInt[i]==copyNums[j])
//				{
//					results[1]++;
//					copyNums[j]=0;
//					break;
//				}
//			}
//		}
//		
//		return results;
//	}
	
	protected boolean result(int[] results) throws IOException, LineUnavailableException
	{
		int length = guessInt.length;
		
		if(results[0] == length)
		{
			JOptionPane.showConfirmDialog(null, "You Win!", "MasterMind", JOptionPane.PLAIN_MESSAGE);
			playAudio();
			return true;
		}
		else
		{
			JOptionPane.showConfirmDialog(null, 
					"Right number, right place: " + results[0] +
					"\nRight number, wrong place: "  + results[1], 
					"MasterMind", JOptionPane.PLAIN_MESSAGE);
			return false;
		}
	}
	
	private void playAudio() throws IOException, LineUnavailableException
	{
		File soundFile;
		AudioInputStream audioIn;
		SourceDataLine soundLine = null;
		int BUFFER_SIZE = 64*1024; 
		try {
			soundFile = new File("applause.wav");	
			audioIn = AudioSystem.getAudioInputStream(soundFile);
			AudioFormat audioFormat = audioIn.getFormat();
	        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
	        soundLine = (SourceDataLine) AudioSystem.getLine(info);
	        soundLine.open(audioFormat);
	        soundLine.start();
	        int nBytesRead = 0;
	        byte[] sampledData = new byte[BUFFER_SIZE];
	        while (nBytesRead != -1) 
	        {
	           nBytesRead = audioIn.read(sampledData, 0, sampledData.length);
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
		
		return guessString;
	}
	
	protected int[] getUserInput(String level)
	{
		int[] guess=createGuessArray(level);
		String guessString=null;
		boolean validGuess=false;
		
		do{
			guessString=createTextField(level);
			try {
				guess=stringToIntArr(guessString);
				validGuess=true;
			}
			catch(InvalidEntryException i)
			{
				validGuess=false;
			}
			
		}while(!validGuess);
		
		return guess;
	}
	
	private int[] createGuessArray(String level)
	{
		switch(level)
		{
		case "easy":
			return new int[3];
		case "medium":
				return new int[4];
		case "hard":
			return new int[5];
		default:
			return null;
		}		
	}
	
	protected int[] stringToIntArr(String guessString)
	{
		int[] guessInt=new int[guessString.length()];
		
		for (int y=0; y<guessInt.length; y++)
		{
			try {
				guessInt[y]=Character.getNumericValue(guessString.charAt(y));
			}
			catch(NumberFormatException f)
			{
				JOptionPane.showMessageDialog(null, "Invalid entry. Guess must be an integer from 1-8");
				throw new InvalidEntryException();
			}
			
			if(guessInt[y]<1 || guessInt[y]>8)
			{
				JOptionPane.showMessageDialog(null, "Invalid entry. Guess must be an integer from 1-8");
				throw new InvalidEntryException();
			}
		}
		
		return guessInt;
	}
	
	private String threeSpaces() 
	{
		JTextField[] guessField=new JTextField[3];
		
		for (int t = 0; t<guessField.length; t++) 
		{
		 	guessField[t] = new JTextField();
		 	guessField[t].setToolTipText("Enter guess here");
		}
		
		final JComponent[] inputs = new JComponent[] { 
//				new JLabel(guesses),
				new JLabel("Enter a guess from 1-8 into each slot: \n"),
				new JLabel("#1:"), guessField[0],
				new JLabel("#2:"), guessField[1],
				new JLabel("#3:"), guessField[2],
		};
		
		JOptionPane.showConfirmDialog(null, inputs, "MasterMind", JOptionPane.PLAIN_MESSAGE);
		return textFieldToString(guessField); 
	} 
	
	private String textFieldToString(JTextField[] field)
	{
		String s=field[0].getText();
		for(int i=1; i<field.length; i++)
		{
			s+=field[i].getText();
		}
		
		return s;
	}
	
	private String fourSpaces()
	{
		JTextField[] guessField=new JTextField[4];
		
		for (int t = 0; t<guessField.length; t++) 
		{
		 	guessField[t] = new JTextField();
		 	guessField[t].setToolTipText("Enter guess here");
		}
		
		final JComponent[] inputs = new JComponent[] {
				new JLabel("Enter a guess from 1-8 into each slot: \n"),
				new JLabel ("#1:"), guessField[0],
				new JLabel ("#2:"), guessField[1],
				new JLabel ("#3:"), guessField[2],
				new JLabel ("#4:"), guessField[3],
		};

		JOptionPane.showConfirmDialog(null, inputs, "MasterMind", JOptionPane.PLAIN_MESSAGE);
		return textFieldToString(guessField);
	}
	
	private String fiveSpaces()
	{
		JTextField[] guessField=new JTextField[5];
		
		for (int t = 0; t<guessField.length; t++) 
		{
		 	guessField[t] = new JTextField();
		 	guessField[t].setToolTipText("Enter guess here");
		}
		final JComponent[] inputs = new JComponent[] {
				new JLabel("Enter a guess from 1-8 into each slot: \n"),
				new JLabel ("#1:"), guessField[0],
				new JLabel ("#2:"), guessField[1],
				new JLabel ("#3:"), guessField[2],
				new JLabel ("#4:"), guessField[3],
				new JLabel ("#5:"), guessField[4],
		};
		
		
		
		JOptionPane.showConfirmDialog(null, inputs, "MasterMind", JOptionPane.PLAIN_MESSAGE);
		return textFieldToString(guessField);
	}
	
	//play method that calls all methods so main dus not have to
	public void play() throws IOException, LineUnavailableException
	{
		int[] nums;
		String level=gameLevel();
		createArray(level);
			boolean win=false;
			int tries=0;
		while(!win && tries<6)
		{
			tries++;
			guessInt=getUserInput(level);
//			int[] results=checkGuess(guessInt);
//			win=result(results);		
		}
	}
	
	public static void main(String[] args) throws IOException, LineUnavailableException
	{
		MasterMind game=new MasterMind(new RegRandom());
		game.play();
		
		System.exit(0);
	}
}
