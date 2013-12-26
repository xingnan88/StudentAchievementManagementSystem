import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class StuInfo extends JPanel implements ActionListener
{
	static JLabel jid,jname,jsex,jbirth,jclass;
	JButton b;
	ModifyPassword modify;
	StuInfo(MyFrame f)
	{
		setLayout(new BorderLayout());
		modify=new ModifyPassword(f,"修改密码",true);
		
		jid=new JLabel();
		jname=new JLabel();
		jsex=new JLabel();
		jbirth=new JLabel();
		jclass=new JLabel();
		
		JPanel p=new JPanel();
		b=new JButton("修改密码");
		b.addActionListener(this);
		p.add(b);
		add(p,BorderLayout.SOUTH);
		initInstance(this);
	}
	private void initInstance(Object obj)
	{
		Box base=Box.createHorizontalBox();
		Box left=Box.createVerticalBox();
		Box right=Box.createVerticalBox();
		JPanel p11=new JPanel(),
		       p12=new JPanel(),
		       p13=new JPanel(),
		       p14=new JPanel(),
		       p15=new JPanel();
		       
		JPanel p21=new JPanel(),
	           p22=new JPanel(),
	           p23=new JPanel(),
	           p24=new JPanel(),
	           p25=new JPanel();
	    
		p11.add(new JLabel("学生编号："));
		p21.add(jid);
		p12.add(new JLabel("学生姓名："));
		p22.add(jname);
		p13.add(new JLabel("学生性别："));
		p23.add(jsex);
		p14.add(new JLabel("学生生日："));
		p24.add(jbirth);
		p15.add(new JLabel("学生班级："));
		p25.add(jclass);
		
		left.add(Box.createVerticalStrut(30));
		left.add(p11);
		left.add(Box.createVerticalStrut(10));
		left.add(p12);
		left.add(Box.createVerticalStrut(10));
		left.add(p13);
		left.add(Box.createVerticalStrut(10));
		left.add(p14);
		left.add(Box.createVerticalStrut(10));
		left.add(p15);
		left.add(Box.createVerticalStrut(10));
		
		right.add(Box.createVerticalStrut(30));
		right.add(p21);
		right.add(Box.createVerticalStrut(10));
		right.add(p22);
		right.add(Box.createVerticalStrut(10));
		right.add(p23);
		right.add(Box.createVerticalStrut(10));
		right.add(p24);
		right.add(Box.createVerticalStrut(10));
		right.add(p25);
		right.add(Box.createVerticalStrut(10));
		
		base.add(new JPanel());
		base.add(new JPanel());
		base.add(new JPanel());
		base.add(new JPanel());
		base.add(new JPanel());
		base.add(left);
		base.add(right);
		base.add(new JPanel());
		base.add(new JPanel());
		base.add(new JPanel());
		base.add(new JPanel());
		base.add(new JPanel());
		this.add(base,BorderLayout.CENTER);
	}
	public void actionPerformed(ActionEvent e)
	{
		modify.setVisible(true);
	}
	public static void saveStu(String id)
	{
		SqlManager DBm=SqlManager.createInstance();
		DBm.connectDB();
		String sql="exec ProcStudent'"+id+"'";
		ResultSet rs=DBm.executeQuery(sql);
		System.out.println(sql);
		try{
			rs.next();
			jid.setText(rs.getString(1));
			jname.setText(rs.getString(2));
			jsex.setText(rs.getString(3));
			jbirth.setText(rs.getString(4));
			jclass.setText(rs.getString(5));
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
