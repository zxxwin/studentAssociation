package studentAssociation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class home extends JFrame {

	private JPanel contentPane;
	private Controller controler = new Controller();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					home frame = new home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	

//	table = new JTable();
//	String[][] stuData = controler.stuTab.getData();
//	int colNum = stuData[0].length;
//	int rowNum = controler.stuTab.getRecordNum();
//	Object[][] tableData = new Object[rowNum][colNum];
//	
//	
//	for(int row = 0; row < rowNum; row++){
//		for(int col = 0; col < colNum; col++){
//			tableData[row][col] = stuData[row][col];
//			System.out.println(stuData[row][col]);
//		}
//	}
//	
//	
//	//tableData ， stuData 
//	table.setModel(new DefaultTableModel(
//			tableData,
//		new String[] {
//			"学号", "姓名", "性别", "专业"
//		}
//	));
	public home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 457, 209);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u6B22\u8FCE\u4F7F\u7528\u5B66\u751F\u793E\u56E2\u7BA1\u7406\u7CFB\u7EDF");
		label.setBounds(87, 44, 264, 26);
		label.setForeground(new Color(30, 144, 255));
		label.setFont(new Font("宋体", Font.PLAIN, 22));
		contentPane.add(label);
		
		JButton stuManageBtn = new JButton("\u5B66\u751F\u7BA1\u7406");
		stuManageBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				view_Stu view_stu = new view_Stu();
				view_stu.setVisible(true);
			}
		});
		stuManageBtn.setBounds(75, 87, 133, 40);
		contentPane.add(stuManageBtn);
		
		JButton assManageBtn = new JButton("\u793E\u56E2\u7BA1\u7406");
		assManageBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				view_Ass assFrame = new view_Ass();
				assFrame.setVisible(true);
			}
		});
		assManageBtn.setBounds(235, 87, 133, 40);
		contentPane.add(assManageBtn);
		String[][] stuData = controler.stuTab.getData();
		int colNum = stuData[0].length;
		int rowNum = controler.stuTab.getRecordNum();
		Object[][] tableData = new Object[rowNum][colNum];
		
		
		for(int row = 0; row < rowNum; row++){
			for(int col = 0; col < colNum; col++){
				tableData[row][col] = stuData[row][col];
			}
		}
		
	}

}
