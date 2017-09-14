package studentAssociation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class view_addStu extends JFrame {

	private JPanel contentPane;
	private JTextField stuNoInput;
	private JTextField nameInput;
	private JTextField majorInput;
	private static view_Stu superFrame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view_addStu frame = new view_addStu(superFrame);
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
	public view_addStu(view_Stu superFrame) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 332, 272);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u5B66\u53F7\uFF1A");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(33, 28, 54, 15);
		contentPane.add(label);
		
		stuNoInput = new JTextField();
		stuNoInput.setBounds(97, 25, 147, 18);
		contentPane.add(stuNoInput);
		stuNoInput.setColumns(10);
		
		JLabel label_1 = new JLabel("\u59D3\u540D\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(33, 71, 54, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u6027\u522B\uFF1A");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(33, 114, 54, 15);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u4E13\u4E1A\uFF1A");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(33, 157, 54, 15);
		contentPane.add(label_3);
		
		nameInput = new JTextField();
		nameInput.setColumns(10);
		nameInput.setBounds(97, 68, 147, 18);
		contentPane.add(nameInput);
		
		majorInput = new JTextField();
		majorInput.setColumns(10);
		majorInput.setBounds(97, 159, 147, 18);
		contentPane.add(majorInput);
		
		JRadioButton maleRadio = new JRadioButton("\u7537");
		maleRadio.setSelected(true);
		maleRadio.setBounds(97, 111, 60, 23);
		contentPane.add(maleRadio);
		
		JRadioButton femaleRadio = new JRadioButton("\u5973");
		femaleRadio.setBounds(153, 111, 60, 23);
		contentPane.add(femaleRadio);
		
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(femaleRadio);
		btnGroup.add(maleRadio);
		
		
		JButton button = new JButton("\u786E\u5B9A");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller controller = new Controller();
				
				String stuId = stuNoInput.getText().trim();
				String stuName = nameInput.getText().trim();
				String stuMajor = majorInput.getText().trim();
				

				Pattern pattern = Pattern.compile("[0-9]*");
				Matcher isNum = pattern.matcher(stuId);
				
				if(stuId.equals("")){
					JOptionPane.showMessageDialog(rootPane, "学号不能为空！");
				}else if(!isNum.matches()){
					JOptionPane.showMessageDialog(rootPane, "学号非法，添加失败！");
				}else if(stuId.length() != 12){
					JOptionPane.showMessageDialog(rootPane, "学号长度有误，添加失败！");
				}else if(controller.stuTab.getDataPos(stuId) != -1){
					JOptionPane.showMessageDialog(rootPane, "学号已存在，添加失败！");
				}else if(stuName.equals("")){
					JOptionPane.showMessageDialog(rootPane, "姓名不能为空！");
				}else if(stuMajor.equals("")){
					JOptionPane.showMessageDialog(rootPane, "专业不能为空！");
				}
				else{
					String recornd = stuId;
					recornd += " ";
					recornd += stuName;
					recornd += " ";
					recornd += maleRadio.isSelected()?maleRadio.getText():femaleRadio.getText();
					recornd += " ";
					recornd += stuMajor;
					controller.addStudent(recornd);
					controller.saveData();
					superFrame.updataTable();
					JOptionPane.showMessageDialog(rootPane, "添加成功！");
				}
			}
		});
		button.setBounds(120, 196, 93, 23);
		contentPane.add(button);
	}

}
