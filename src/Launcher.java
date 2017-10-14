/*Hi this basic media player software is coded by assim A.K.A wiZard,
 * this software is used to play mp3 fies only..
 * keep coding..:)
 */

import javax.swing.SwingUtilities;
import java.awt.*;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.UIManager.*;
import javax.swing.plaf.nimbus.*;


public class Launcher {
	public static void main(String args[])
	{
		Launcher launcher =new Launcher();
		launcher.look();
		
		
	 SwingUtilities.invokeLater(new Runnable()
		{
		 
			public void run()
			{
				
				new Graphics();
				
			}
		});
	}
	
	void look()
	
	{
		try
		{
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		}
		catch(Exception e)
		{
			
		}
	}

}
