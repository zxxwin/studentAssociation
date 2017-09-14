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

public class view_Ass extends JFrame {

	private JPanel contentPane;
	private JTextField keyWordInput;
	private Controller controller = new Controller();
	private JTable table;
	static view_Ass assFrame;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					assFrame = new view_Ass();
					assFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public boolean isCellEditable(int row,int column){  
	    if(column == 0){  
	       return true;  
	    }else{  
	       return false;  
	    }  
	}  
	public void updataTable(){
//		System.out.println("updataTable");
		controller.assTab.readData();
		String[][] assData = controller.assTab.getData();
		int colNum = assData[0].length;
		int rowNum = controller.assTab.getRecordNum();
//		System.out.println(rowNum);
		Object[][] tableData = new Object[rowNum][colNum];

		Object[] tableHeader = new String[] {
			"社团编号", "社团名字", "创建日期"
		};
		
		for(int row = 0; row < rowNum; row++){
			for(int col = 0; col < colNum; col++){
				isCellEditable(row,col);
				tableData[row][col] = assData[row][col];
//				System.out.println(stuData[row][col]);
			}
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
	}
	/**
	 * Create the frame.
	 */
	public view_Ass() {
		assFrame = this;
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
		
		JButton delRecordBtn = new JButton("\u5220\u9664");
		delRecordBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();//获取选中的行号
				if(row == -1){
					JOptionPane.showMessageDialog(rootPane, "请先选择一条记录");					
				}else{
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					String primaryKeyValue = (String) table.getValueAt(row, 0);
					model.removeRow(row);//删除某行
					controller.deleteAssociation(primaryKeyValue);
					controller.saveData();
					updataTable();
				}
				
			}
		});
		
		keyWordInput = new JTextField();
		keyWordInput.setColumns(10);
		
		JButton searchBtn = new JButton("\u641C\u7D22");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyword = keyWordInput.getText().trim();
				int resultPos = controller.assTab.searchData(keyword);
				table.getSelectionModel().setSelectionInterval(resultPos, resultPos);
			}
		});
		
		JButton addRecordBtn = new JButton("\u6DFB\u52A0");
		addRecordBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				view_addAss addAss = new view_addAss(assFrame);
				addAss.setVisible(true);
			}
		});
		addRecordBtn.setVerticalAlignment(SwingConstants.TOP);
		addRecordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton showMemberBtn = new JButton("\u67E5\u770B\u6210\u5458");
		showMemberBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row = table.getSelectedRow();//获取选中的行号
				String assId = (String) table.getValueAt(row, 1);
				view_assMember assMember = new view_assMember(assId);
				assMember.setVisible(true);
			}
		});
		
		JButton setRecord = new JButton("\u4FEE\u6539");
		setRecord.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();//获取选中的行号
				if(row == -1){
					JOptionPane.showMessageDialog(rootPane, "请先选择一条记录");					
				}else {
					String[] record = controller.assTab.getRecordByPpos(row);
					view_editAss editAss = new view_editAss(assFrame,record);
					editAss.setVisible(true);					
				}
			}
		});
		setRecord.setVerticalAlignment(SwingConstants.TOP);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(addRecordBtn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(setRecord, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(delRecordBtn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(showMemberBtn, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addGap(155)
					.addComponent(keyWordInput, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(searchBtn, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addGap(13))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(delRecordBtn, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
							.addComponent(addRecordBtn, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
							.addComponent(setRecord)
							.addComponent(showMemberBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(keyWordInput, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addComponent(searchBtn, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}
	
}
