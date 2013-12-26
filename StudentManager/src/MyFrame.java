import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class MyFrame extends JFrame implements ActionListener
{
	JPanel pCenter;
	CardLayout card=null;
	Logon logon;
	AdmPanel ap;
	TeaPanel tp;
	StuPanel sp;
	MyFrame(String s)
	{
		super(s);
		card=new CardLayout();
		pCenter=new JPanel();
		logon=new Logon();
		ap=new AdmPanel();
		tp=new TeaPanel(this);
		sp=new StuPanel(this);
		
		logon.button1.addActionListener(this);
		logon.button2.addActionListener(this);
		
		pCenter.setLayout(card);
		pCenter.add("登录",logon);
		pCenter.add("管理员",ap);
		pCenter.add("学生",sp);
		pCenter.add("教师",tp);
		
		add(pCenter,BorderLayout.CENTER);
		setBounds(100,100,900,600);
		setVisible(true);
		validate();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==logon.button1)
		{
			char role='0';
			try{
				role=logon.logon();
				
			}catch(SQLException ex)
			{		
				ex.printStackTrace();
			}	
			if(role=='S')
			{
				card.show(pCenter, "学生");
			}
			else if(role=='T')
			{
				card.show(pCenter, "教师");
			}	
			else if(role=='A')
			{
				
				card.show(pCenter, "管理员");
			}
		}
		else if(e.getSource()==logon.button2)
		{
			logon.reset();
		}		
	}
}
