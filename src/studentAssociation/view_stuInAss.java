package studentAssociation;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class view_stuInAss extends JFrame {

	private JPanel contentPane;
	private Controller controller = new Controller();
	public JTable table;
	static String stuId;
	static view_stuInAss stuInAss;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					stuInAss = new view_stuInAss(stuId);
					stuInAss.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
//	public void t(){
//		System.out.println("ass");
//	}
	public boolean isCellEditable(int row,int column){  
	    if(column == 0){  
	       return true;  
	    }else{  
	       return false;  
	    }  
	}  
	public boolean updataTable(){
		System.out.println("updataTable" + stuId);
		controller.assTab.readData();
		String[][] assData = controller.assTab.getData();
		if(controller.getStuAss(stuId) == null){
			return false;
		}
		String[][] ass = controller.getStuAss(stuId);
		int colNum = ass[0].length + 1;
		int rowNum = 0;
		for(int i = 0; i < ass.length; i++){
			if(ass[i][0] != null){
				rowNum++;
			}else {
				break;
			}
		}
		System.out.println("rowNum" + rowNum);
		Object[][] tableData = new Object[rowNum][colNum];
		
		Object[] tableHeader = new String[] {
			"社团编号", "社团名字", "职位信息"
		};
		
		for(int row = 0; row < rowNum && ass[row][0] != null; row++){
			tableData[row][0] = ass[row][0];
			tableData[row][1] = controller.assTab.getNameByPk(ass[row][0]);
			tableData[row][2] = ass[row][1];
		}
		
		DefaultTableModel newTableModel = new DefaultTableModel(tableData,tableHeader){  
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override  
            public boolean isCellEditable(int row,int column){    
      	       return false; 
            }  
        };  
        table.setModel(newTableModel);
		return true;
	}
	/**
	 * Create the frame.
	 */
	public view_stuInAss(String stuId) {
		this.stuId = stuId;
		stuInAss = this;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 762, 461);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		
		table = new JTable();
		this.updataTable();
		
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JButton quitAssBtn = new JButton("\u9000\u51FA\u793E\u56E2");
		quitAssBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();//获取选中的行号
				if(row == -1){
					JOptionPane.showMessageDialog(rootPane, "请先选择一条记录");					
				}else{
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					String primaryKeyValue = (String) table.getValueAt(row, 0);
					
					System.out.println(stuId + " " +primaryKeyValue);
//					controller.ass_indexTab.readData();
					controller.studentQuitAssociation(stuId,primaryKeyValue);
					model.removeRow(row);//删除某行
//					controller.deleteAssociation(primaryKeyValue);
					
					controller.saveData();
					updataTable();
				}
				
			}
		});
		
		JButton addAssBtn = new JButton("\u6DFB\u52A0\u793E\u56E2");
		addAssBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				view_addAss addAss = new view_addAss(assFrame);
//				addAss.setVisible(true);
			}
		});
		addAssBtn.setVerticalAlignment(SwingConstants.TOP);
		addAssBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				String[][] ass = controller.assTab.getData();
//				int recordNum = controller.assTab.getRecordNum();
//				String[] assNames = new String[recordNum];
//				System.out.println(recordNum);
//				int rows = 0;
//				for(int row = 0; row < recordNum; row++){
//					assNames[rows++] =  ass[row][1];
//				}
//				for(String names:assNames){
//					System.out.println(names);
//				}
				view_joinAss joinAss = new view_joinAss(stuInAss,stuId);
				joinAss.setVisible(true);
			}
		});
		
		JButton setJobBtn = new JButton("\u4FEE\u6539\u89D2\u8272");
		setJobBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();//获取选中的行号
				if(row == -1){
					JOptionPane.showMessageDialog(rootPane, "请先选择一条记录");					
				}else {
//					String[] record = controller.
					view_editJob editJob = new view_editJob(stuInAss,stuId);
//					view_editAss editAss = new view_editAss(assFrame,record);
					editJob.setVisible(true);
				}
			}
		});
		setJobBtn.setVerticalAlignment(SwingConstants.TOP);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(addAssBtn, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(setJobBtn, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(quitAssBtn)
					.addGap(482))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(addAssBtn, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
						.addComponent(setJobBtn)
						.addComponent(quitAssBtn, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}
}
