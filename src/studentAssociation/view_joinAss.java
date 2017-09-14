package studentAssociation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class view_joinAss extends JFrame {

	private JPanel contentPane;
	static view_stuInAss stuInAssFrame;
	private Controller controller = new Controller();
	static String stuId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view_joinAss frame = new view_joinAss(stuInAssFrame,stuId);
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
	public view_joinAss(view_stuInAss stuInAssFrame,String stuId) {
		setTitle("\u52A0\u5165\u793E\u56E2");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 372, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox assName = new JComboBox();
		
		
//		controller.assTab.searchData(keyWord)
		
		

		String[][] ass = controller.assTab.getData();
		int recordNum = controller.assTab.getRecordNum();
		String[] assNames = new String[recordNum];
//		System.out.println(recordNum);
		int rows = 0;
		for(int row = 0; row < recordNum; row++){
			assNames[rows++] =  ass[row][1];
		}
		assName.setModel(new DefaultComboBoxModel(assNames));
//		assName.setSelectedIndex(0);
		Object s = "会长";
		assName.setSelectedItem(s);
		assName.setBounds(37, 33, 124, 34);
		contentPane.add(assName);
		
		JComboBox job = new JComboBox();
		job.setModel(new DefaultComboBoxModel(new String[] {"\u4F1A\u5458", "\u79D8\u4E66", "\u4F1A\u957F"}));
		job.setSelectedIndex(0);
		job.setBounds(191, 33, 124, 34);
		contentPane.add(job);
		
		JButton button = new JButton("\u786E\u5B9A");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
//				System.out.println();
				String assNameValue = assName.getSelectedItem().toString();
				String jobValue = job.getSelectedItem().toString();
				String assId = controller.assTab.getPkByName(assNameValue);
//				System.out.println(assId+"??????");
				if(controller.stu_assTab.getDataPos(stuId, assId) != -1){
					JOptionPane.showMessageDialog(rootPane, "已是该社团成员，加入失败！");
				}else{
					controller.studentJoinAssociation(stuId + " " + assId + " "  + jobValue);
					controller.saveData();
					stuInAssFrame.updataTable();
					JOptionPane.showMessageDialog(rootPane, "加入成功！");	
				}
			}
		});
		button.setBounds(131, 85, 93, 34);
		contentPane.add(button);
	}
}
