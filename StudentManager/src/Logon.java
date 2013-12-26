import java.awt.Font;
import java.awt.Label;

import java.sql.*;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class Logon extends JPanel 
{
	JTextField t1;
	JPasswordField t2;
	JButton button1,button2;
	static String info[];
	static String userid,password;
	Logon()
	{
		JLabel label=new JLabel("登陆");
		label.setFont(new Font("TimesRoman",Font.BOLD,24));
		t1=new JTextField(20);
		t2=new JPasswordField(20);
		button1=new JButton("登陆");
		button2=new JButton("取消");
		JPanel p1,p2,p3,p4;
		Box box=Box.createVerticalBox();
		p1=new JPanel();//p1.setBackground(Color.PINK);
		p2=new JPanel();
		p3=new JPanel();
		p4=new JPanel();
		p1.add(label);
		p2.add(new Label("用户名:"));
		p2.add(t1);
		p3.add(new Label("口    令:"));
		p3.add(t2);
		p4.add(button1);
		p4.add(button2);	
		box.add(p1);
		box.add(p2);
		box.add(p3);
		box.add(p4);
		add(box);
	}
	public char logon() throws SQLException
	{
		String tuserid=t1.getText();
		String tpassword=new String(t2.getPassword());
		String sql1=null,sql2=null;
		ResultSet rs=null;
		SqlManager DBm=SqlManager.createInstance();//单态模式获取实例
		DBm.connectDB();
		sql1="exec ProcLogon'"+tuserid+"','"+tpassword+"'";
		rs=DBm.executeQuery(sql1);
		
		if(rs.next())//如果存储过程有结果返回，则将用户信息读入并保存
		{
			System.out.println(rs.getString(1)+"如果存储过程有结果返回，则将用户信息读入并保存");
			userid=rs.getString(1);
			password=rs.getString(2);
			if(userid.startsWith("T"))
			{
				TeaInfo.saveTea(userid);
				
			}
			else if(userid.startsWith("S"))
			{
				StuInfo.saveStu(userid);
			}
			else if(userid.startsWith("A"))
			{
				sql2=sql1;
				DBm.connectDB();
				rs=DBm.executeQuery(sql2);
			}
			rs.close();
			DBm.closeDB();
			return userid.substring(0, 1).toCharArray()[0];
		}
		else{
			userid=null;
			password=null;
			JOptionPane.showMessageDialog(this,"用户名或密码错误,请重新填写",
					"警告",JOptionPane.WARNING_MESSAGE);
			rs.close();
			DBm.closeDB();
			return '0';
		}
	}
	private static void dispResultSet(ResultSet rs) throws SQLException
	{
		ResultSetMetaData rsmd=rs.getMetaData();
		int numCols=rsmd.getColumnCount();
		info=new String[numCols];
		while(rs.next())
		{
			for(int i=1;i<=numCols;i++)
			{				
				info[i-1]=rs.getString(i);
				if(i>1)System.out.print(",");
				System.out.print(rs.getString(i));
			}
			System.out.println("");
		}
	}
	public void reset()
	{
		t1.setText(null);
		t2.setText(null);
		t1.requestFocusInWindow();
	}
}