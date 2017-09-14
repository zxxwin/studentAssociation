package studentAssociation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class view_assMember extends JFrame {

	private JPanel contentPane;
	private Controller controller = new Controller();
	private JTable sectary;
	private JTable masters;
	private JTable all;
	static String assName;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view_assMember frame = new view_assMember(assName);
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
	public view_assMember(String assName) {
//		String assName = "领航工作室";
		String assId = controller.assTab.getPkByName(assName);
		String[] allMember = controller.stu_assTab.getMembers(assId);
		String[] master = controller.stu_assTab.getMasters(assId);
		String[] secretary = controller.stu_assTab.getSecreataries(assId);
		String[][] allMemberData = new String[50][];
		int allMemberDataNum = 0;
		for(String stuId:allMember){
			if(stuId != null){
				String[] record = controller.stuTab.getRecordByPk(stuId);
				allMemberData[allMemberDataNum++] = record;
			}else{
				break;
			}
		}
		String[][] masterData = new String[10][];
		int masterDataNum = 0;
		for(String stuId:master){
			if(stuId != null){
				String[] record = controller.stuTab.getRecordByPk(stuId);
				masterData[masterDataNum++] = record;
			}else{
				break;
			}
		}
		String[][] secretaryData = new String[20][];
		int secretaryDataNum = 0;
		for(String stuId:secretary){
			if(stuId != null){
				String[] record = controller.stuTab.getRecordByPk(stuId);
				secretaryData[secretaryDataNum++] = record;
			}else{
				break;
			}
		}
		
		Object[] tableHeader = new String[] {
				"学号", "姓名", "性别", "专业"
			};
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("\u5168\u90E8\u6210\u5458", null, scrollPane, null);
		
		all = new JTable();
		all.setModel(new DefaultTableModel(
			allMemberData,tableHeader
		));
		scrollPane.setViewportView(all);
		

		
		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("\u4F1A\u957F", null, scrollPane_1, null);
		
		masters = new JTable();
		masters.setModel(new DefaultTableModel(
				masterData,tableHeader
			));
		scrollPane_1.setViewportView(masters);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		tabbedPane.addTab("\u79D8\u4E66", null, scrollPane_2, null);
		
		sectary = new JTable();
		sectary.setModel(new DefaultTableModel(
				secretaryData,tableHeader
			));
		scrollPane_2.setViewportView(sectary);
	}

}
