import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class ButtonPanel extends JPanel {

	private JButton gameMenuButton;
	private JButton rematchButton;
	private JButton exitButton;
	private GameView gameView;
	
	
	public ButtonPanel(GameView gameView)
	{
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.gameView = gameView;
		setLayout(new GridLayout(3,1));
		makeButtons();
	}
	
	
	private void makeButtons()
	{
		gameMenuButton = new JButton("Menu");
		gameMenuButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				gameView.endGameToMenu();
			}
		});
			
		rematchButton = new JButton("Rematch");
		rematchButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				gameView.rematch();
			}
		});
		
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
		add(gameMenuButton);
		add(rematchButton);
		add(exitButton);
		
	}
	

	
}
