import javax.media.Clock;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.nimbus.*;
import javax.swing.plaf.metal.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;
import java.io.*;

public class Graphics implements ActionListener,Serializable {
	
	
	private static final long serialVersionUID = -8009428825440729323L;

	

	public Graphics()// constructor
	{   
		
		int result=JOptionPane.showConfirmDialog(null, "Do you want to load previous songs ?");
		if(result==0)
		{
			load();
		}
		if(result==2)
		{
			System.exit(0);
		}
		myPlayer =new player();//player object
		
		//making frames and panel
		 myFrame = new JFrame("Mp3 Player");
		
		myPanel = new JPanel();
		myPanel.setBackground(Color.darkGray);
		
		BoxLayout box =new BoxLayout(myPanel,BoxLayout.Y_AXIS);
		myPanel.setLayout(box);
		myFrame.getContentPane().add(myPanel,BorderLayout.EAST);
		
	
				
		JLabel op =new JLabel("OPTION");
		op.setFont(new Font("Comic Sans MS",Font.BOLD,13));
		op.setForeground(Color.BLUE);
		
		//image display While playing 
		imgLabel=new JLabel(new ImageIcon(this.getClass().getResource("144143145.png")));
		myFrame.getContentPane().add(imgLabel);
		
        playButton =new JButton("Play");
		stopButton =new JButton("Stop");
		browse =new JButton("open");
		nextSong = new JButton("Next");
		logs=new JButton("Logs");
		back =new JButton("Back");
		
		// Adding buttons to jpanel
		myPanel.add(op);
		myPanel.add(browse);
		myPanel.add(playButton);
		myPanel.add(stopButton);
		myPanel.add(nextSong);
		myPanel.add(back);
		myPanel.add(logs);
	    //creating menubar
		JMenuBar myBar =new JMenuBar();
		myFrame.setJMenuBar(myBar);
		
		//creating menu
		JMenu file =new JMenu("FIle");
		JMenu option=new JMenu("Option");
		JMenu help =new JMenu("Help");
	
		//creating menu Sub items
		JMenuItem open = new JMenuItem("open");
		JMenuItem save =new JMenuItem("Save");
		JMenuItem load =new JMenuItem("Load");
		 JMenuItem exit = new JMenuItem("Close");
		JMenuItem plays = new JMenuItem("Play");
		JMenuItem stops = new JMenuItem("Stop");
		JMenuItem nextSongs = new JMenuItem("NextSong");
		JMenuItem previous =new JMenuItem("Previous Song");
		JMenuItem abouts = new JMenuItem("About");
		JMenu lookAndFeel =new JMenu("Look And Feel");
		JMenuItem nimbus = new JMenuItem("Nimbus");
		JMenuItem metal =new JMenuItem("Metal");
		
		//adding sub menu item to menu 
		file.add(open);
		file.add(save);
		file.add(load);
		file.add(exit);
		lookAndFeel.add(nimbus);
		lookAndFeel.add(metal);
		option.add(plays);
		option.add(stops);
		option.add(nextSongs);
		option.add(previous);
		option.add(lookAndFeel);
		help.add(abouts);
		
		//adding menu to menu bar
		myBar.add(file);
		myBar.add(option);
		myBar.add(help);
		
		//action listener source
		open.addActionListener(this);
		save.addActionListener(this);
		load.addActionListener(this);
		exit.addActionListener(this);
		plays.addActionListener(this);
		stops.addActionListener(this);
		nextSongs.addActionListener(this);
		previous.addActionListener(this);
		nimbus.addActionListener(this);
		metal.addActionListener(this);
		abouts.addActionListener(this);
		
		//instance Action listener source
		playButton.addActionListener(this);
		stopButton.addActionListener(this);
		nextSong.addActionListener(this);
		browse.addActionListener(this);
		logs.addActionListener(this);
		back.addActionListener(this);
		
		//frame visibility and size
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);
		myFrame.setSize(400,350);
		myFrame.setResizable(false);
		
	}
	
	//instance variablee
	
	private JButton playButton;
	private JButton stopButton;
    private JButton nextSong;
	private JButton browse;
	private JFileChooser myChooser;
	private player myPlayer;
	private JLabel imgLabel;
	private JPanel myPanel;
	private JFrame myFrame;
   private int sng=0;
   private  JButton logs;
   private JButton back;
	public ArrayList <URL> songsList=new ArrayList<URL>();

	
	//action listener predefined abstract method
	public void actionPerformed(ActionEvent event)
	{
		
		String a= event.getActionCommand();
	if(a.equals("Nimbus"))//set look and feel
	{

		 try
			{
				UIManager.setLookAndFeel(new NimbusLookAndFeel());
				System.out.println("Nimbus started");
				
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
	}
	
	if(a.equals("Metal"))
	{
		try
		{
		UIManager.setLookAndFeel(new MetalLookAndFeel());
		myPanel.repaint();
		
		}
		catch(Exception es)
		{
			System.out.println(es.getMessage());
		}
	}
		
	if(a.equals("open"))//open file for playing
	{  
		loadFile();
		
			
	}
	
	if(a.equals("NextSong")||a.equals("Next"))
	{
       sng++;
		if(myPlayer.song!=null)
		{
			myPlayer.song.stop();
		}
		if(sng<songsList.size())
		{
			URL getsng=songsList.get(sng);
			myPlayer.play(getsng);
			
		}
		else
		{
			JOptionPane.showMessageDialog(null, "No More Songs Availabe");
	   }
		}
	
	if(a.equals("Back")||a.equals("Previous Song"))
	{
		sng--;
		if(myPlayer.song!=null)
		{
			myPlayer.song.stop();
		}
		URL getsng=songsList.get(sng);
		myPlayer.play(getsng);
	
	}
	if(a.equals("Play"))
	{
		if(myPlayer.song==null)
		{
			loadFile();
		}
	
		if((myPlayer.song!=null))
		{
			JOptionPane.showMessageDialog(null,"Already playing");
		}
		else
		{
		URL ns = songsList.get(sng);
		myPlayer.play(ns);
		imgLabel.setIcon(new ImageIcon(this.getClass().getResource("pause-button-outline_318-40569.jpg")));
		myPlayer.song.start();
		}
	
	}
	if(a.equals("Stop"))
	{
	myPlayer.song.stop();
	myPlayer.song=null;
	imgLabel.setIcon(new ImageIcon(this.getClass().getResource("play-button_318-42541kkk.png")));
	}
	if(a.equals("About"))
	{
		JOptionPane.showMessageDialog(null, "Programmed by Assim A.K.A wiZard");
			
	}
	if(a.equals("Logs"))
	{
		
		
		JFrame m =new JFrame();
		JTextArea nn =new JTextArea(5,5);
		nn.setBackground(Color.green);
		nn.setText("No Songs Available.( coded by assim)");
		nn.setEditable(false);
		m.getContentPane().add(nn);
		int b =songsList.size();
		if(songsList.size()>0)
		{
			nn.setBackground(Color.gray);
			nn.setText("");
		}
		for(int as=0;as<b;as++)
		{
			nn.append(songsList.get(as)+"\n");
		}
		m.setVisible(true);
		
	    m.pack();
	}
	
	if(a.equals("Save"))
	{
		save();
	}
	
	if(a.equals("Load"))
	{
		load();
	}
	
	if(a.equals("Exit")||a.equals("Close"))
	{
		System.exit(0);
		System.out.println("exiting the program");
		myPlayer.song.stop();
	}
	
	
	
	}	
	
	// method load
	void load()
	{
		try
		{
			songsList.clear();
			sng=0;
			System.out.println("array size "+songsList.size());
			FileInputStream myFile1 =new FileInputStream("D://Songstate.data");
			ObjectInputStream myObj1 =new ObjectInputStream(myFile1);
			
			songsList.addAll((ArrayList<URL>)myObj1.readObject());
			sng=myObj1.readInt();
			myObj1.close();
		
		}
		catch(Exception t)
		{
			System.err.print(t.getLocalizedMessage());
	        JOptionPane.showMessageDialog(null, "Unable to load");
		}
	}
	//method save
	void save()
	{
		try
		{
		
			
			FileOutputStream myFile =new FileOutputStream("D://Songstate.data");
			ObjectOutputStream myObj =new ObjectOutputStream(myFile);
			myObj.writeObject(songsList);
            myObj.writeInt(sng);
            myObj.close();
			
			
		}
		catch(Exception t)
		{
	         JOptionPane.showMessageDialog(null, "Unable to save");
	         
		}
	}
	
	
	/*void splashScreens()
	{
		JWindow myWindows =new JWindow();
		myWindows.setSize(300,300);
		myWindows.setVisible(true);
		try
		{
			Thread.sleep(5000);
		}
		catch(Exception s)
		{
			System.err.println(s.getLocalizedMessage());
		}
		
			//myWindows.setVisible(false);
			//myWindows.dispose();
		
	}*/
	
	private void loadFile()
	{
		myChooser =new JFileChooser();//file chooser
		//myChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		myChooser.setMultiSelectionEnabled(true);

		//seting file filter
		FileNameExtensionFilter filter =new FileNameExtensionFilter("MP3 File","mp3","wav","ogg");
		myChooser.setFileFilter(filter);
		
		int ret =myChooser.showOpenDialog(null);
		
		if(ret==JFileChooser.APPROVE_OPTION)
		{
			File name = myChooser.getSelectedFile();
		
				try
				{
			
				URL media = name.toURI().toURL();
				
				songsList.add(media);
				System.out.println("Song added succssfully");
				System.out.println("Song Entry no: "+songsList.size());
				}
				
				catch(Exception e)
				{
					System.out.println("Unable to add the song");
					
				}
			
			
		}
		
	}
	}
