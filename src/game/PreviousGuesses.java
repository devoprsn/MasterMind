package game;

import java.awt.*;
import javax.swing.*;

public class PreviousGuesses extends JFrame{

	public PreviousGuesses(){
		
		super("Previous Guesses\n (RcRp = Right color Right place; RcWp = Right color Wrong place):");

	}
	
	public JPanel addRow(Integer[] guessInt, JPanel mainPanel, String[] thisGuessReply){
		Color[] colors = new Color[guessInt.length];
		JPanel buttonPanel = new JPanel(new GridLayout(1, colors.length+2, 2, 2));
    	JButton button = new JButton();
    	    	
    	for(int i = 0; i<guessInt.length; i++){
			if (guessInt[i] == 1){
				colors[i] = new Color(235, 21, 21); //red
			} 
			else if (guessInt[i] == 2){
				colors[i] = new Color(240, 128, 16); //orange
			} 
			else if (guessInt[i] == 3){
				colors[i] = new Color(255, 255, 0); //yellow
			} 
			else if (guessInt[i] == 4){
				colors[i] = new Color(34, 255, 0); //green
			} 
			else if (guessInt[i] == 5){
				colors[i] = new Color(21, 56, 235); //blue
			} 
			else if (guessInt[i] == 6){
				colors[i] = new Color(142, 18, 236); //purple
			} 
			else if (guessInt[i] == 7){
				colors[i] = new Color(255, 0, 196); //pink
			}
			button = new JButton(guessInt[i].toString());
			button.setBackground(colors[i]);
			buttonPanel.add(button);
		}

		buttonPanel.add(new JButton(thisGuessReply[0]));
		buttonPanel.add(new JButton(thisGuessReply[1]));

		mainPanel.add(buttonPanel);
		
		
		getContentPane().add(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(colors.length*100+350, 700);
        setVisible(true);
        
        return mainPanel;
	}
	
}
