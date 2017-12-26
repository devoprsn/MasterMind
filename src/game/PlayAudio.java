package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.sound.sampled.*;


public final class PlayAudio {

	public static void play(String filename) throws IOException, LineUnavailableException
	{		
		File file=new File(filename);
		AudioInputStream stream;
		AudioFormat format;
		DataLine.Info info;
		Clip clip;
		try {
			    stream = AudioSystem.getAudioInputStream(file);
			    format = stream.getFormat();
			    info = new DataLine.Info(Clip.class, format);
			    clip = (Clip) AudioSystem.getLine(info);
			    clip.open(stream);
			    clip.start();
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
	
//	public static void play(String filename) throws IOException, LineUnavailableException
//	{
	//	File soundFile;
	//	AudioInputStream stream;
	//	SourceDataLine soundLine = null;
	//	int BUFFER_SIZE = 64*1024; 
	//	try {
	//		soundFile = new File(filename);	
	//		stream = AudioSystem.getAudioInputStream(soundFile);
	//		AudioFormat audioFormat = stream.getFormat();
	//        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
	//        soundLine = (SourceDataLine) AudioSystem.getLine(info);
	//        soundLine.open(audioFormat);
	//        soundLine.start();
	//        int nBytesRead = 0;
	//        byte[] sampledData = new byte[BUFFER_SIZE];
	//        while (nBytesRead != -1) 
	//        {
	//           nBytesRead = stream.read(sampledData, 0, sampledData.length);
	//           if (nBytesRead >= 0) 
	//           {
	//              soundLine.write(sampledData, 0, nBytesRead);
	//           }
	//        }
	//	}
	//	catch(UnsupportedAudioFileException e)
	//	{
	//		e.printStackTrace();
	//		return;
	//	}
	//	catch(FileNotFoundException f)
	//	{
	//		System.err.println("File not found");
	//	}
//	}
}
