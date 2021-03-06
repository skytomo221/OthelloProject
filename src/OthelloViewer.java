import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class OthelloViewer extends JFrame {

	BoardDrawer boardDrawer;

	public OthelloViewer(Othello othello) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Othello");
		setBounds(100, 100, 450, 300);
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout(0, 0));
		setContentPane(panel);
		JPanel southPanel = new JPanel();
		JLabel label = new JLabel("コマンドを入力して戦闘開始．23を入力すると(x = 2, y = 3)のところにコマを置く．");
		JTextField textField = new JTextField();
		textField.setText("");
		JButton button = new JButton("送信");
		//textField.setColumns(30);
		southPanel.setLayout(new BorderLayout());
		southPanel.add(label, BorderLayout.NORTH);
		southPanel.add(textField, BorderLayout.CENTER);
		southPanel.add(button, BorderLayout.EAST);
		boardDrawer = new BoardDrawer(othello);
		panel.add(boardDrawer, BorderLayout.CENTER);
		panel.add(southPanel, BorderLayout.SOUTH);
	}

	public void addActionListener(OthelloController othelloController) {
	}

}
