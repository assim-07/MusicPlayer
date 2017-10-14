/*In this program we use Java Media Framework Library(JMF).It is a external
 
 * libary. so we need to download and import libary to the project manually
 * 
 */
import java.net.URL;
import java.awt.Component;
import java.io.*;
import javax.media.*;

import java.util.ArrayList;
import javax.sound.sampled.*;
import javax.sound.sampled.Control;
import javax.sound.sampled.FloatControl.Type;
import javax.media.bean.playerbean.*;

import com.sun.media.MediaPlayer;
public class player {
	
	public Player song;
	
	

	void play(URL s)
	
	{
			
		try
		{
		
			
			
		song=Manager.createPlayer(s);//creating the player 
		//FloatControl gainControl =(FloatControl)song.getControl(FloatControl.Type.MASTER_GAIN);
		
		//control = song.getControlPanelComponent();
		;
		//FloatControl gainControl =song.getControl(FloatControl.Type.MASTER_GAIN);
		
		System.out.println();
		
	
		
		
	

		song.start();//start playing;
		
	
	
	}catch(Exception e)
	{
		System.out.println("error Unable to get a song"+e.getMessage());
	}

}


	
	
}

