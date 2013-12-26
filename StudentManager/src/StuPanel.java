import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StuPanel extends JPanel implements ActionListener
{
	JButton b1,b2,b3;
	StuInfo stuinfo;
	CourseList courselist;
	ChooseList chooselist;

	JPanel p1=new JPanel(),
           p2=new JPanel(),
           p3=new JPanel();
    static JPanel pCenter=new JPanel();
	static CardLayout card=new CardLayout();
	StuPanel(MyFrame f)
	{
		setLayout(new BorderLayout());
		p3.setLayout(new BorderLayout());
		pCenter.setLayout(card);
		
		JLabel label=new JLabel("学生选课系统");
		label.setFont(new Font("TimesRoman",Font.BOLD,24));
		p1.add(label);
		
		b1=new JButton("  个  人  信  息  ");
		b2=new JButton("  课  程  列  表  ");
		b3=new JButton("  已  选  课  程  ");
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		p2.add(b1);
		p2.add(b2);
		p2.add(b3);
		
		stuinfo=new StuInfo(f);
		courselist=new CourseList();
		chooselist=new ChooseList();
		pCenter.add("个人信息",stuinfo);
		pCenter.add("课程列表",courselist);
		pCenter.add("已选课程",chooselist);
		
		p3.add(p2,BorderLayout.NORTH);
		p3.add(pCenter,BorderLayout.CENTER);
	    
		add(p1,BorderLayout.NORTH);
		add(p3,BorderLayout.CENTER);
		
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)
			card.show(pCenter, "个人信息");
		if(e.getSource()==b2)
			card.show(pCenter, "课程列表");
		if(e.getSource()==b3)
		{
			ChooseList.updateTable();
			/*Vector   rec_vector=new   Vector();//从结果集中取数据放入向量rec_vector中   
	        rec_vector.addElement(rs.getString(1));   
	        rec_vector.addElement(rs.getString(2));   
	        rec_vector.addElement(rs.getString(3));   
	        rec_vector.addElement(rs.getString(4));   
	        rec_vector.addElement(rs.getString(5));   
	        Vector vector=new Vector();
	        vector.addElement(rec_vector);//向量rec_vector加入向量vect中       
	        ChooseList.dtm.fireTableStructureChanged();//更新表格，显示向量vect的内容 */
			card.show(pCenter, "已选课程");
		}			
	}
}
