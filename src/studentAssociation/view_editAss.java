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
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class view_editAss extends JFrame {

	private JPanel contentPane;
	private JTextField assNoInput;
	private JTextField nameInput;
	private static view_Ass superFrame;
	private static String[] record;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view_editAss frame = new view_editAss(superFrame,record);
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
	public view_editAss(view_Ass superFrame,String[] record) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 309, 190);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u793E\u56E2\u7F16\u53F7\uFF1A");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(10, 28, 77, 15);
		contentPane.add(label);
		
		assNoInput = new JTextField();
		assNoInput.setBounds(97, 25, 147, 18);
		assNoInput.setText(record[0]);
		assNoInput.setEditable(false);
		contentPane.add(assNoInput);
		assNoInput.setColumns(10);
		
		JLabel label_1 = new JLabel("\u793E\u56E2\u540D\u5B57\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(10, 71, 77, 15);
		contentPane.add(label_1);
		
		nameInput = new JTextField();
		nameInput.setColumns(10);
		nameInput.setText(record[1]);
		nameInput.setBounds(97, 68, 147, 18);
		contentPane.add(nameInput);
		
		
		
		JButton button = new JButton("\u786E\u5B9A");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller controller = new Controller();
				
				String assNo = assNoInput.getText().trim();
				String assName = nameInput.getText().trim();
				
				if(assName.equals("")){
					JOptionPane.showMessageDialog(rootPane, "社团名字不能为空！");
				}else{
					Calendar date = Calendar.getInstance();
					int y = date.get(Calendar.YEAR);
					int m = date.get(Calendar.MONTH);
					int d = date.get(Calendar.DATE);
					String recornd = assNo;
					recornd += " ";
					recornd += assName;
					recornd += " ";
					recornd += record[2];
					controller.setAssociationInfo(recornd);
					controller.saveData();
					superFrame.updataTable();
//					superFrame.t();
					JOptionPane.showMessageDialog(rootPane, "修改成功！");
				}
			}
		});
		button.setBounds(103, 108, 93, 23);
		contentPane.add(button);
	}

}
