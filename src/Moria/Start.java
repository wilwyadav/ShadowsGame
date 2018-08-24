package Moria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Start extends JPanel implements ActionListener {
	
	protected JTextField textField;
	protected JTextArea textArea;
	protected JTextField textConsole;
	private final static String newline = "\n";
	private Engine e;
	
	private int fontSize = 14;
	
	public Start()
	{
		super(new GridBagLayout());
		
		textField = new JTextField(20);
		textField.setVisible(true);
		textField.addActionListener(this);
		
		Font font = new Font("Lucida Console", Font.PLAIN, fontSize);
		
		textArea = new JTextArea(5,20);
		textArea.setEditable(false);
		textArea.setFont(font);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = GridBagConstraints.REMAINDER;
		
		c.fill = GridBagConstraints.HORIZONTAL;
		this.add(textField,c);
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;
		this.add(scrollPane, c);
		
		textConsole = new JTextField(5);
		textConsole.setEditable(false);
		textConsole.setText("Starting Moria in Java!!!");
		this.add(textConsole, c);
		
		e = new Engine(20,10);
		
		// CREATE A TEST MONSTER AND ADD TO GAME
		Goblin g = new Goblin();
		g.Hp = 100;
		g.Symbol = "G";
		g.x = 1;
		g.y = 1;
		
		e.addEntity(g);
		this.printMap();
	}
	
	public static void main(String[] args)
	{		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				createAndShowGUI();
			}
		});
	}

	@Override
    public void actionPerformed(ActionEvent evt) 
	{
		// GET USER INPUT AND EVALUATE BEFORE TAKING NEXT TURN
		String input = this.textField.getText();		
		this.nextTurn(input);	
    }
	
	private void nextTurn(String Input)
	{
		String result = "";
		
		// MOVE UP
		if(Input.equals("w"))
		{
			result = e.NextTurn("w");
		}
		//MOVE DOWN
		if(Input.equals("s"))
		{
			result = e.NextTurn("s");
		}
		//MOVE RIGHT
		if(Input.equals("d")) 
		{
			result = e.NextTurn("d");
		}
		//MOVE LEFT
		if(Input.equals("a"))
		{
			result = e.NextTurn("a");
		}
		
		
		this.textConsole.setText(result);		
		this.printMap();
	}
	
	private void printMap() 
	{
		textField.setText("");
        textArea.setText(e.getStringMap());
        //textArea.setCaretPosition(textArea.getDocument().getLength());
	}

	private static void createAndShowGUI()
	{
        //Create and set up the window.
        JFrame frame = new JFrame("Moria");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add contents to the window.
        frame.add(new Start());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
	}
	

}
