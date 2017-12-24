package game;
import javax.swing.*;
import javax.sound.sampled.*;
import java.io.*;
import java.util.ArrayList;

public class MasterMind 
{
	//so we can pass in a mock object to test the arrays
	private IRandomValueGenerator rand;
	private int[] answer;
	private ArrayList<StringBuilder> guesses;
	
	//constructor; initialize the ArrayList to hold Strings of previous guesses
	public MasterMind(IRandomValueGenerator rand)
	{
		this.rand=rand;
		guesses = new ArrayList<StringBuilder>();
	}

	//***//***//The methods are presented below in the order that they are used://***//***//
	
		
	//play method that calls all methods in order so main does not have to
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
	
	//to let the user choose a game level
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
	
	//call generateRandomArray() with length based on level
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
	
	//gets the user input and converts the String returned to an array
	protected int[] getUserInput(String level)
	{
		int[] guess=null;
		String guessString=null;
		boolean validGuess=false;
		
		do{		
			try {
				guessString=createTextFieldAndGetUserGuess(level); //calls the text-field method that is the right size
													//for that level, returns user's guess
				guess=stringToIntArr(guessString);	//copy the users' guess into an array to be able to
													//compare it to the answer
				validGuess=true;
			}
			catch(InvalidEntryException i)
			{
				JOptionPane.showMessageDialog(null, "Invalid entry. Guess must be an integer from 1-9");
				validGuess=false;
			}
			
		}while(!validGuess);
		
		guesses.add(new StringBuilder(guessString));
		return guess;
	}
	
	//call the text-field method that is the right size for that level, returns user's guess
	protected String createTextFieldAndGetUserGuess(String level)
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
	
	//create a JTextField[] with three spaces for user entry
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
				new JLabel("Previous Guesses:"),
				new JLabel(guesses.toString()),
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
	
	//create a JTextField[] with four spaces for user entry
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
	
	//create a JTextField[] with five spaces for user entry
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
	
	//convert the JTextField to a String in order to be able to use it and test it
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
	
	//converts the String to an array to be able to compare it to the answer
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
	
	//check if the guess is 100% correct
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
	
	//call displayWin() if user won and displayLoss() if didn't get it all right yet
	protected void result(boolean win, int[] guessInt) throws IOException, LineUnavailableException
	{				
		if(win)
		{
			displayWin();
		}
		else
		{
			displayLoss(checkHowManyRightPlace(guessInt), checkHowManyWrongPlace(guessInt));
			
		}
	}

	//call playAudio() and display "You Win!" message
	private void displayWin() throws IOException, LineUnavailableException
	{
		playAudio("correct-answer.wav");
		JOptionPane.showConfirmDialog(null, "You Win!", "MasterMind", JOptionPane.PLAIN_MESSAGE);		
	}
	
	//display how many were Right-num-Right-place and how many were Right-num-Wrong-place, call playAudio()
	private void displayLoss(int correct, int wrongPlace) throws IOException, LineUnavailableException
	{		
		StringBuilder msg=new StringBuilder("Right Number Right Place: "+correct
				+"\nRight Number Wrong Place: "+wrongPlace);
		guesses.get(guesses.size()-1).append(msg.toString());
		playAudio("pling.wav");
		JOptionPane.showMessageDialog(null, msg, "MasterMind", JOptionPane.PLAIN_MESSAGE);
		
	}
	
	//to play the audio for a win or a loss
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
		
//		File file=new File(filename);
//		AudioInputStream stream;
//		AudioFormat format;
//		DataLine.Info info;
//		Clip clip;
//		try {
//			    stream = AudioSystem.getAudioInputStream(file);
//			    format = stream.getFormat();
//			    info = new DataLine.Info(Clip.class, format);
//			    clip = (Clip) AudioSystem.getLine(info);
//			    clip.open(stream);
//			    clip.start();
//		}
	}
		
	//check how many are: Right number, Right place
	protected int checkHowManyRightPlace(int[] guessInt)
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
	
	//check how many are: Right number, Wrong place
	protected int checkHowManyWrongPlace(int[] guessInt)
    {
		int[] copyAns = new int[answer.length];
		int[] copyGuess = new int[guessInt.length];
		
		for (int r=0; r<answer.length; r++)
		{
			copyAns[r] = answer[r];
			
			copyGuess[r] = guessInt[r];
			
			if(copyGuess[r]==copyAns[r])
			{
				copyGuess[r] = -1; //change the one in the guess array to -1
				copyAns[r] = 0; //change the one in the answer array to 0
			}
		}
				
		int wrongPlace=0;
        for(int x=0; x<copyAns.length; x++)
        {
            for(int y=0; y<copyGuess.length; y++)
            {
                if(copyAns[x]==copyGuess[y])
                {
                	wrongPlace++;
                	copyAns[x] = 0; //change the one in the answer array to 0
                	copyGuess[y] = -1; //change the one in the guess array to -1
                }
            }
        }
        return wrongPlace;    
    }
	
	//main, creates a new MasterMind game and calls the play() method, the only public method 	
	public static void main(String[] args) throws IOException, LineUnavailableException
	{
		MasterMind game=new MasterMind(new RegRandom());
		game.play();
		
		System.exit(0);
	}
}
