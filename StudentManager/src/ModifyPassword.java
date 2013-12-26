import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.*;


public class ModifyPassword extends JDialog implements ActionListener
{
	JPasswordField t1,t2,t3;
	JButton b1,b2;
	static String userid,password;
	ModifyPassword(MyFrame f,String s,boolean b)
	{
		super(f,s,b);
		t1=new JPasswordField(20);
		t2=new JPasswordField(20);
		t3=new JPasswordField(20);
		
		b1=new JButton("确定");
		b2=new JButton("取消");
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		Box vbox=Box.createVerticalBox();
		JPanel p1=new JPanel(),
		       p2=new JPanel(),
		       p3=new JPanel(),
		       p4=new JPanel();
		
		p1.add(new JLabel("原始密码:"));
		p1.add(t1);
		p2.add(new JLabel(" 新 密 码 :"));
		p2.add(t2);
		p3.add(new JLabel("确认密码:"));
		p3.add(t3);
		p4.add(b1);
		p4.add(b2);
		
		vbox.add(Box.createVerticalStrut(20));
		vbox.add(p1);
		vbox.add(Box.createVerticalStrut(20));
		vbox.add(p2);
		vbox.add(Box.createVerticalStrut(20));
		vbox.add(p3);
		vbox.add(Box.createVerticalStrut(20));
		vbox.add(p4);
		add(vbox);
		setBounds(200,200,400,300);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)
		{
			String s1=new String(t1.getPassword()),
		           s2=new String(t2.getPassword()),
		           s3=new String(t3.getPassword());
			userid=Logon.userid;
            password=Logon.password;
			 System.out.println(password+"passwordx");
		    if(s1.equals(password.trim()))
		    {
			    System.out.println(password+"passwordok");
			    if(s2.equals(s3))
			    {	
			    	SqlManager DBm=SqlManager.createInstance();
			    	DBm.connectDB();
			    	String sql="exec ProcModify'"+userid+"','"+s2+"'";
			    	System.out.println(sql);
			    	if(DBm.executeUpdate(sql)==1)
			    	{
			    		JOptionPane.showMessageDialog(this, "密码修改成功");
			    		password=s2;
			    		Logon.password=s2;
			    		setVisible(false);
			    	}
			    	else{
			    		JOptionPane.showMessageDialog(this, "密码修改失败!!!\n请重试");
			    		t1.setText(null);
					    t2.setText(null);
					    t3.setText(null);
					    t1.requestFocusInWindow();	
			    	}
			    }
		    }
		    else{
			    JOptionPane.showMessageDialog(this, "原始密码输入错误!!!\n请重新输入");
			    t1.setText(null);
			    t2.setText(null);
			    t3.setText(null);
			    t1.requestFocusInWindow();	
		    }        
		}
		else if(e.getSource()==b2)
		{
			t1.setText(null);
			t2.setText(null);
			t3.setText(null);
			t1.requestFocusInWindow();
			setVisible(false);
		}
		
	}
}
