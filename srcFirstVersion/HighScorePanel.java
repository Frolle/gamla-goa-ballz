import java.util.LinkedList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

public class HighScorePanel extends JPanel {
	
	private LinkedList<String[]> highScoreList = new LinkedList<String[]>();
	private JTable highScoreTable;
	public Player players;
	private HighScoreHandler high;
	

	public HighScorePanel(Player[] playerArray)
	{
		high = new HighScoreHandler();
		high.updateScore(playerArray);
		highScoreList = high.getHighScoreList();
		
		this.setLayout(new BorderLayout());
		this.setBackground(Color.BLACK);
		makeTable();
	}
	
	private void makeTable()
	{
		// Initiate arrays and table header
		String[][] row = new String[10][3];
		String[] col = {"Placering", "Spelare", "Poäng"};
		
		// Create the arrays that will feed the table
		for(int i = 0; i < 10; i++)
		{ 
			Integer tempFlytt = i+1;
			row[i][0] = tempFlytt.toString();
			row[i][1] = highScoreList.get(i)[0];
			row[i][2] = highScoreList.get(i)[1];			
		}		
		
		// Create and style the table
		highScoreTable = new JTable(row,col);
		highScoreTable.setBackground(Color.BLACK);
		highScoreTable.setForeground(Color.WHITE);
		highScoreTable.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
		highScoreTable.setRowHeight(30);
		highScoreTable.setEnabled(false);
		
		
		// Get the header and style it
		JTableHeader header = highScoreTable.getTableHeader();	
		header.setEnabled(false);
		header.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		header.setBackground(new Color(0).DARK_GRAY);
		header.setForeground(new Color(0).WHITE);
		header.setPreferredSize(new Dimension(this.WIDTH, 40));
		
		// Add the header and the table
		this.add(header, BorderLayout.NORTH);
		this.add(highScoreTable, BorderLayout.CENTER);
	
	
	}
	
	
}
