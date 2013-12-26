import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TeaPanel extends JPanel 
{
	JButton b1,b2;
	TeaInfo teainfo;
	StoreScore ss;
	JPanel p1=new JPanel(),
           p2=new JPanel(),
           p3=new JPanel();
    static JPanel pCenter=new JPanel();
	static CardLayout card=new CardLayout();
	TeaPanel(MyFrame f)
	{
		setLayout(new BorderLayout());
		p3.setLayout(new BorderLayout());
		pCenter.setLayout(card);
		teainfo=new TeaInfo(f);
		ss=new StoreScore();
		pCenter.add("个人信息",teainfo);
		pCenter.add("录入成绩",ss);
		JLabel label=new JLabel("教师登录系统");
		label.setFont(new Font("TimesRoman",Font.BOLD,24));
		p1.add(label);
		
		b1=new JButton("  个  人  信  息  ");
		b2=new JButton("  录  入  成  绩  ");
		b1.addActionListener(new   ActionListener(){
			public void actionPerformed(ActionEvent e){
				card.show(pCenter,"个人信息");
			}
		});
		b2.addActionListener(new   ActionListener(){
			public void actionPerformed(ActionEvent e){
				ss.updateTable();
				card.show(pCenter,"录入成绩");
			}
		});
		p2.add(b1);
		p2.add(b2);
		
		p3.add(p2,BorderLayout.NORTH);
		p3.add(pCenter,BorderLayout.CENTER);
	    
		add(p1,BorderLayout.NORTH);
		add(p3,BorderLayout.CENTER);
		
	}
	public void actionPerformed(ActionEvent e)
	{
		
	}
}
