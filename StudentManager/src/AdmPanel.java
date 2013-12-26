import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;
public class AdmPanel extends JPanel implements ActionListener,ItemListener,MouseListener
{
	JButton b1,b2,b3,b4,b5,b6;
	JPanel p1,p2,pCenter;
	CardLayout card=null;
	JTextField t1=new JTextField(10),
               t2=new JTextField(10),
               t3=new JTextField(10),
               tt1=new JTextField(10),
               tt2=new JTextField(10),
               tt3=new JTextField(10);
	ButtonGroup sex1=new ButtonGroup(),
	            sex2=new ButtonGroup();
	JRadioButton radio1=new JRadioButton("男",true),
	             radio2=new JRadioButton("女");
	JRadioButton r1=new JRadioButton("男",true),
                 r2=new JRadioButton("女");
	JComboBox year,month,date,yy,mm,dd,post;

	
	JTable table1,table2;
	Vector vectorColName1=new Vector(),
	       vectorColName2=new Vector();
	Vector vector1=new Vector(),
	       vector2=new Vector();
	DefaultTableModel model1=new DefaultTableModel(vectorColName1,0){
		public boolean isCellEditable(int row, int column)
        {
            return false;
        }
	};
	DefaultTableModel model2=new DefaultTableModel(vectorColName2,0){
		 public boolean isCellEditable(int row, int column)
         {
             return false;
         }

	};
	
	AdmPanel()
	{
		setLayout(new BorderLayout());
		
		t1=new JTextField(10);
		t2=new JTextField(10);
		t3=new JTextField(10);
		tt1=new JTextField(10);
		tt2=new JTextField(10);
		tt3=new JTextField(10);
		
		sex1.add(radio1);
		sex1.add(radio2);
		sex2.add(r1);
		sex2.add(r2);
		
		year=new JComboBox();
		month=new JComboBox();
		date=new JComboBox();
		yy=new JComboBox();
		mm=new JComboBox();
		dd=new JComboBox();
		post=new JComboBox();
		
		post.addItem("助教");
		post.addItem("讲师");
		post.addItem("副教授");
		post.addItem("教授");
		for(int i=1980;i<1995;i++)
			year.addItem(i);
		for(int i=1;i<=12;i++)
			month.addItem(i);
		for(int i=1;i<=31;i++)
			date.addItem(i);
		for(int i=1950;i<1995;i++)
			yy.addItem(i);
		for(int i=1;i<=12;i++)
			mm.addItem(i);
		for(int i=1;i<=31;i++)
			dd.addItem(i);
		
		year.addItemListener(this);
		month.addItemListener(this);
		yy.addItemListener(this);
		mm.addItemListener(this);
		post.addItemListener(this);
		
		b1=new JButton("学生信息管理");
		b2=new JButton("教师信息管理");
		b3=new JButton("输入");
		b4=new JButton("删除");
		b5=new JButton("输入");
		b6=new JButton("删除");
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		
		JPanel p0=new JPanel();
		p0.add(b1);
		p0.add(b2);
		
		pCenter=new JPanel();
		card=new CardLayout();
		pCenter.setLayout(card);
		p1=createStuPanel();
		p2=createTeaPanel();
		
		pCenter.add("学生信息管理",p1);
		pCenter.add("教师信息管理",p2);
		
		add(p0,BorderLayout.NORTH);
		add(pCenter,BorderLayout.CENTER);
	}
	private JPanel createStuPanel()
	{
		JPanel p=new JPanel();
		JScrollPane p1;
		JPanel p2=new JPanel();
		p.setLayout(new GridLayout(2,1));
				
		SqlManager DBm=SqlManager.createInstance();//单态模式获取实例
		DBm.connectDB();
		String sql="exec ProcAllStu";
		ResultSet rs=DBm.executeQuery(sql);
		vectorColName1.addElement("学号");   
		vectorColName1.addElement("姓名");   
		vectorColName1.addElement("性别");   
		vectorColName1.addElement("生日");   
		vectorColName1.addElement("班级");
		model1.setDataVector(vector1,vectorColName1);
		table1=new JTable(model1);
		table1.addMouseListener(this);
		p1=new JScrollPane(table1);	
		p.add(p1);
		try{
			while(rs.next()){   
		          Vector   rec_vector=new   Vector();//从结果集中取数据放入向量rec_vector中   
		          rec_vector.addElement(rs.getString(1));   
		          rec_vector.addElement(rs.getString(2));   
		          rec_vector.addElement(rs.getString(3));   
		          rec_vector.addElement(rs.getString(4));   
		          rec_vector.addElement(rs.getString(5));   
		          vector1.addElement(rec_vector);//向量rec_vector加入向量vect中   
		                          }   
		          //tm.fireTableStructureChanged();//更新表格，显示向量vect的内容  
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		DBm.closeDB();	
		//构造p2
		Box base=Box.createHorizontalBox(),
		    boxleft=Box.createVerticalBox(),
		    boxright=Box.createVerticalBox();
		JPanel pp1=new JPanel(),
		       pp2=new JPanel(),
		       pp3=new JPanel(),
		       pp4=new JPanel(),
		       pp5=new JPanel(),
		       pp6=new JPanel(),
		       psex=new JPanel(),
		       pbirth=new JPanel();
		
		
		psex.add(radio1);
		psex.add(radio2);
		
		pbirth.add(year);
		pbirth.add(month);
		pbirth.add(date);
		
		pp1.add(new JLabel("学生编号："));
		pp1.add(t1);
		pp2.add(new JLabel("学生姓名："));
		pp2.add(t2);
		pp3.add(new JLabel("学生性别："));
		pp3.add(psex);
		pp4.add(new JLabel("学生生日："));
		pp4.add(pbirth);
		pp5.add(new JLabel("所在班级："));
		pp5.add(t3);
		pp6.add(b3);
		pp6.add(b4);
		
		boxleft.add(Box.createVerticalStrut(30));
	    boxleft.add(pp1);
	    boxleft.add(Box.createVerticalStrut(20));
	    boxleft.add(pp2);
	    boxleft.add(Box.createVerticalStrut(20));
	    boxleft.add(pp3);
	    
	    boxright.add(Box.createVerticalStrut(20));
	    boxright.add(pp4);
	    boxright.add(Box.createVerticalStrut(20));
	    boxright.add(pp5);
	    boxright.add(Box.createVerticalStrut(20));
	    boxright.add(pp6);
	     
		base.add(boxleft);
		base.add(Box.createHorizontalStrut(80));
		base.add(boxright);
		p2.add(base);
		p.add(p2);
		return p;
	}
	private JPanel createTeaPanel()
	{
		JPanel p=new JPanel();
		JScrollPane p1;
		JPanel p2=new JPanel();
		p.setLayout(new GridLayout(2,1));
				
		SqlManager DBm=SqlManager.createInstance();//单态模式获取实例
		DBm.connectDB();
		String sql="exec  ProcAllTea";
		ResultSet rs=DBm.executeQuery(sql);
		vectorColName2.addElement("教师编号");   
		vectorColName2.addElement("教师姓名");   
		vectorColName2.addElement("教师性别");   
		vectorColName2.addElement("生日");   
		vectorColName2.addElement("职称");
		vectorColName2.addElement("所在院系");
		model2.setDataVector(vector2,vectorColName2);
		table2=new JTable(model2);
		table2.addMouseListener(this);
		
		p1=new JScrollPane(table2);	
		p.add(p1);
		try{
			while(rs.next()){   
		          Vector   rec_vector=new   Vector();//从结果集中取数据放入向量rec_vector中   
		          rec_vector.addElement(rs.getString(1));   
		          rec_vector.addElement(rs.getString(2));   
		          rec_vector.addElement(rs.getString(3));   
		          rec_vector.addElement(rs.getString(4));   
		          rec_vector.addElement(rs.getString(5)); 
		          rec_vector.addElement(rs.getString(6)); 
		          vector2.addElement(rec_vector);//向量rec_vector加入向量vect中   
		    }   
		          //tm.fireTableStructureChanged();//更新表格，显示向量vect的内容  
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		DBm.closeDB();		
		//构造p2
		Box base=Box.createHorizontalBox(),
	    boxleft=Box.createVerticalBox(),
	    boxright=Box.createVerticalBox();
	    JPanel pp1=new JPanel(),
	           pp2=new JPanel(),
	           pp3=new JPanel(),
	           pp4=new JPanel(),
	           pp5=new JPanel(),
	           pp6=new JPanel(),
	           pp7=new JPanel(),
	           psex=new JPanel(),
	           pbirth=new JPanel();
	    
	    psex.add(r1);
	    psex.add(r2);
	    pbirth.add(yy);
	    pbirth.add(mm);
	    pbirth.add(dd);
	    
	    pp1.add(new JLabel("教职工号："));
	    pp1.add(tt1);
	    pp2.add(new JLabel("教师姓名："));
	    pp2.add(tt2);
	    pp3.add(new JLabel("教师性别："));
	    pp3.add(psex);
	    pp4.add(new JLabel("教师生日："));
	    pp4.add(pbirth);
	    pp5.add(new JLabel("教师职称："));
	    pp5.add(post);
	    pp6.add(new JLabel("所在院系："));
	    pp6.add(tt3);
	    pp7.add(b5);
	    pp7.add(b6);
	    
	    boxleft.add(Box.createVerticalStrut(40));
	    boxleft.add(pp1);
	    boxleft.add(Box.createVerticalStrut(20));
	    boxleft.add(pp2);
	    boxleft.add(Box.createVerticalStrut(20));
	    boxleft.add(pp3);
	    
	    boxright.add(Box.createVerticalStrut(30));
	    boxright.add(pp4);
	    boxright.add(Box.createVerticalStrut(10));
	    boxright.add(pp5);
	    boxright.add(Box.createVerticalStrut(10));
	    boxright.add(pp6);

	    boxright.add(pp7);
	    
	    base.add(boxleft);
	    base.add(Box.createHorizontalStrut(80));
	    base.add(boxright);
		p2.add(base);
		p.add(p2);
		return p;
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)
		{
			card.show(pCenter, "学生信息管理");
		}
		else if(e.getSource()==b2)
		{
			card.show(pCenter, "教师信息管理");
		}
		if(e.getSource()==b3)
		{			
			String userid=t1.getText(),
			       username=t2.getText(),
			       classs=t3.getText();
			String sex="男";
			if(radio2.isSelected())
			{
				sex="女";
			}
			String birth=String.valueOf((Integer)year.getSelectedItem())+"-"
			               +String.valueOf((Integer)month.getSelectedItem())
			               +"-"+String.valueOf((Integer)date.getSelectedItem());	
			String sql="exec InsertStudent'"+userid+"','"+username+"','"+sex+"','"+birth+"','"+classs+"'";
			String s="exec IsExistsStu'"+userid+"'";
			String inserlog="exec InsertLogon'"+userid+"'";
			System.out.println(sql);
			SqlManager DBm=SqlManager.createInstance();//单态模式获取实例
			DBm.connectDB();
	        
			
			ResultSet rset=DBm.executeQuery(s);
			System.out.println(s);
			
			try{
				if (rset.next())
				{
					JOptionPane.showMessageDialog(this,"学生信息插入失败，该学生ID号已存在",
							"警告",JOptionPane.WARNING_MESSAGE);
					rset.close();
				}
				else{ 
					ResultSet rs=DBm.executeQuery(sql);
					
					if(rs.next())
					{
						JOptionPane.showMessageDialog(this,"学生信息插入成功",
								"成功",JOptionPane.WARNING_MESSAGE);
						DBm.connectDB();
						DBm.executeUpdate(inserlog);
						t1.setText("S");
						t2.setText(null);
						t3.setText(null);
						radio1.setSelected(true);
						//this.post.setSelectedIndex(0);
						year.setSelectedIndex(0);
						month.setSelectedIndex(0);
						date.setSelectedIndex(0);
						//sql="select * from Inserted";
						//ResultSet rs=DBm.executeQuery(sql);
						Vector   rec_vector=new   Vector();//从结果集中取数据放入向量rec_vector中   
				        rec_vector.addElement(rs.getString(1));   
				        rec_vector.addElement(rs.getString(2));   
				        rec_vector.addElement(rs.getString(3));   
				        rec_vector.addElement(rs.getString(4));   
				        rec_vector.addElement(rs.getString(5));   
				        vector1.addElement(rec_vector);//向量rec_vector加入向量vect中       
				        model1.fireTableStructureChanged();//更新表格，显示向量vect的内容   
				        rs.close();    
					}
					else{
						JOptionPane.showMessageDialog(this,"学生信息插入失败",
								"警告",JOptionPane.WARNING_MESSAGE);
						rs.close();
					}
				}
			}catch(SQLException ex){
				ex.printStackTrace();
			}
			DBm.closeDB();
		}
		if(e.getSource()==b4)
		{
			//同步删除数据库中的数据
			int selectrow = 0;
	        selectrow =table1.getSelectedRow();
			String id =table1.getValueAt(selectrow,0).toString();
			String sql="exec DeleteStudent'"+id+"'";
			String deletelog="exec DeleteLogon'"+id+"'";
			SqlManager DBm=SqlManager.createInstance();//单态模式获取实例
			DBm.connectDB();
			
			if(DBm.executeUpdate(sql)==1)
			{
				JOptionPane.showMessageDialog(this,"学生信息已删除",
						"删除成功",JOptionPane.INFORMATION_MESSAGE);
				DBm.connectDB();
				DBm.executeUpdate(deletelog);
				model1.removeRow(selectrow); 
				t1.setText("S");
				t2.setText(null);
				t3.setText(null);
				radio1.setSelected(true);
				year.setSelectedIndex(0);
				month.setSelectedIndex(0);
				date.setSelectedIndex(0);				
			}
			DBm.closeDB();
		}
		if(e.getSource()==b5)
		{
			String userid=tt1.getText(),
			       username=tt2.getText(),
			       depart=tt3.getText(),
			       post=(String)this.post.getSelectedItem();
			String sex="男";
			if(r2.isSelected())
			{
				sex="女";
			}
			String birth=String.valueOf((Integer)yy.getSelectedItem())+"-"+String.valueOf((Integer)mm.getSelectedItem())
                          +"-"+String.valueOf((Integer)dd.getSelectedItem());	
			String sql="exec InsertTeacher'"+userid+"','"+username+"','"+sex+"','"+birth+"','"+post+"','"+depart+"'";
			String s="exec IsExistsTea'"+userid+"'";
			String insertlog="exec InsertLogon'"+userid+"'";
			System.out.println(sql);
			SqlManager DBm=SqlManager.createInstance();//单态模式获取实例
			DBm.connectDB();
			
			ResultSet rset=DBm.executeQuery(s);
			System.out.println(s);
			try{
				if (rset.next())
				{
					JOptionPane.showMessageDialog(this,"教师信息插入失败，该教师ID号已存在",
							"警告",JOptionPane.WARNING_MESSAGE);
					rset.close();
				}
				else{ 
					ResultSet rs=DBm.executeQuery(sql);
					if(rs.next())
					{
						JOptionPane.showMessageDialog(this,"教师信息插入成功",
								"成功",JOptionPane.WARNING_MESSAGE);
						DBm.connectDB();
						DBm.executeUpdate(insertlog);
						tt1.setText("T");
						tt2.setText(null);
						tt3.setText(null);
						r1.setSelected(true);
						this.post.setSelectedIndex(0);
						yy.setSelectedIndex(0);
						mm.setSelectedIndex(0);
						dd.setSelectedItem(0);

						Vector   rec_vector=new   Vector();//从结果集中取数据放入向量rec_vector中   
				        rec_vector.addElement(rs.getString(1));   
				        rec_vector.addElement(rs.getString(2));   
				        rec_vector.addElement(rs.getString(3));   
				        rec_vector.addElement(rs.getString(4));   
				        rec_vector.addElement(rs.getString(5)); 
				        rec_vector.addElement(rs.getString(6)); 
				        vector2.addElement(rec_vector);//向量rec_vector加入向量vect中       
				        model2.fireTableStructureChanged();//更新表格，显示向量vect的内容   
				        rs.close(); 					          
					}
					else{
						JOptionPane.showMessageDialog(this,"教师信息插入失败",
								"警告",JOptionPane.WARNING_MESSAGE);
						rs.close();
					}
				}
			}catch(SQLException ex){
				ex.printStackTrace();
			}
			DBm.closeDB();
		}
		if(e.getSource()==b6)
		{
			//同步删除数据库中的数据
			int selectrow = 0;
	        selectrow =table2.getSelectedRow();
			String id =table2.getValueAt(selectrow,0).toString();
			String sql="exec DeleteTeacher'"+id+"'";
			String deletelog="exec DeleteLogon'"+id+"'";
			SqlManager DBm=SqlManager.createInstance();//单态模式获取实例
			DBm.connectDB();
			
			if(DBm.executeUpdate(sql)==1)
			{
				JOptionPane.showMessageDialog(this,"教师信息信息已删除",
						"删除成功",JOptionPane.INFORMATION_MESSAGE);
				DBm.connectDB();
				DBm.executeUpdate(deletelog);
				model2.removeRow(selectrow); 
				tt1.setText("T");
				tt2.setText(null);
				tt3.setText(null);
				r1.setSelected(true);
				yy.setSelectedIndex(0);
				mm.setSelectedIndex(0);
				dd.setSelectedIndex(0);
			}
			DBm.closeDB();
		}
	}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseMoved(MouseEvent e){}
	public void mouseDraged(MouseEvent e){}
	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource()==table1)
		{
			int selectrow = 0;
	        selectrow =table1.getSelectedRow();
			String id =table1.getValueAt(selectrow,0).toString();
			String name=table1.getValueAt(selectrow,1).toString();
			String sex=table1.getValueAt(selectrow,2).toString();
			String birth=table1.getValueAt(selectrow,3).toString().substring(0,10);
			String classs=table1.getValueAt(selectrow,4).toString();
			t1.setText(id);
			t2.setText(name);
			t3.setText(classs);
			if(sex.trim().equals("男"))
				radio1.setSelected(true);
			else if(sex.equals("女"))
				radio2.setSelected(true);
			int y=Integer.parseInt(birth.substring(0, 4));
			int m=Integer.parseInt(birth.substring(5, 7));
			int d=Integer.parseInt(birth.substring(8));
			year.setSelectedIndex(y-1980);
			month.setSelectedIndex(m-1);
			date.setSelectedIndex(d-1);
		}
		else if(e.getSource()==table2)
		{
			int selectrow = 0;
	        selectrow =table2.getSelectedRow();
			String id =table2.getValueAt(selectrow,0).toString();
			String name=table2.getValueAt(selectrow,1).toString();
			String sex=table2.getValueAt(selectrow,2).toString();
			String birth=table2.getValueAt(selectrow,3).toString().substring(0,10);
			String position=table2.getValueAt(selectrow,4).toString();
			String depart=table2.getValueAt(selectrow,5).toString();
			tt1.setText(id);
			tt2.setText(name);
			tt3.setText(depart);
			if(sex.trim().equals("男"))
				r1.setSelected(true);
			else if(sex.equals("女"))
				r2.setSelected(true);
			int y=Integer.parseInt(birth.substring(0, 4));
			int m=Integer.parseInt(birth.substring(5, 7));
			int d=Integer.parseInt(birth.substring(8));
			yy.setSelectedIndex(y-1950);
			mm.setSelectedIndex(m-1);
			dd.setSelectedIndex(d-1);
			if(position.equals("助教"))
				post.setSelectedIndex(0);
			else if(position.equals("讲师"))
				post.setSelectedIndex(1);
			else if(position.equals("副教授"))
				post.setSelectedIndex(2);
			else if(position.equals("教授"))
				post.setSelectedIndex(3);
		}
	}
	public void itemStateChanged(ItemEvent e)
	{
		if(e.getSource()==year)
		{
			int y=(Integer)year.getSelectedItem();
			int m=(Integer)month.getSelectedItem();
			
			if(((y%4==0)&&(y%100!=0))||(y%400==0))
			{
				if(m==2)
				{
					date.removeAll();
					for(int i=1;i<=29;i++)
					{
						date.addItem(i);
					}
				}
				else if(m==1||m==3||m==5||m==7||m==8||m==10||m==12)
				{
					date.removeAll();
					for(int i=1;i<=31;i++)
					{
						date.addItem(i);
					}
				}
				else if(m==4||m==6||m==9||m==11)
				{
					date.removeAll();
					for(int i=1;i<=30;i++)
					{
						date.addItem(i);
					}
				}
			}
			else{
				if(m==2)
				{
					date.removeAll();
					for(int i=1;i<=28;i++)
					{
						date.addItem(i);
					}
				}
				else if(m==1||m==3||m==5||m==7||m==8||m==10||m==12)
				{
					date.removeAll();
					for(int i=1;i<=31;i++)
					{
						date.addItem(i);
					}
				}
				else if(m==4||m==6||m==9||m==11)
				{
					date.removeAll();
					for(int i=1;i<=30;i++)
					{
						date.addItem(i);
					}
				}
			}
		}
		else if(e.getSource()==month)
		{
			int y=(Integer)year.getSelectedItem();
			int m=(Integer)month.getSelectedItem();
			
			if(((y%4==0)&&(y%100!=0))||(y%400==0))
			{
				if(m==2)
				{
					date.removeAll();
					for(int i=1;i<=29;i++)
					{
						date.addItem(i);
					}
				}
				else if(m==1||m==3||m==5||m==7||m==8||m==10||m==12)
				{
					date.removeAll();
					for(int i=1;i<=31;i++)
					{
						date.addItem(i);
					}
				}
				else if(m==4||m==6||m==9||m==11)
				{
					date.removeAll();
					for(int i=1;i<=30;i++)
					{
						date.addItem(i);
					}
				}
			}
			else{
				if(m==2)
				{
					date.removeAll();
					for(int i=1;i<=28;i++)
					{
						date.addItem(i);
					}
				}
				else if(m==1||m==3||m==5||m==7||m==8||m==10||m==12)
				{
					date.removeAll();
					for(int i=1;i<=31;i++)
					{
						date.addItem(i);
					}
				}
				else if(m==4||m==6||m==9||m==11)
				{
					date.removeAll();
					for(int i=1;i<=30;i++)
					{
						date.addItem(i);
					}
				}
			}
		}		
		else if(e.getSource()==yy)
		{
			int y=(Integer)yy.getSelectedItem();
			int m=(Integer)mm.getSelectedItem();
			if(((y%4==0)&&(y%100!=0))||(y%400==0))
			{
				if(m==2)
				{
					dd.removeAll();
					for(int i=1;i<=29;i++)
					{
						dd.addItem(i);
					}
				}
				else if(m==1||m==3||m==5||m==7||m==8||m==10||m==12)
				{
					dd.removeAll();
					for(int i=1;i<=31;i++)
					{
						dd.addItem(i);
					}
				}
				else if(m==4||m==6||m==9||m==11)
				{
					dd.removeAll();
					for(int i=1;i<=30;i++)
					{
						dd.addItem(i);
					}
				}
			}
			else{
				if(m==2)
				{
					dd.removeAll();
					for(int i=1;i<=28;i++)
					{
						dd.addItem(i);
					}
				}
				else if(m==1||m==3||m==5||m==7||m==8||m==10||m==12)
				{
					date.removeAll();
					for(int i=1;i<=31;i++)
					{
						date.addItem(i);
					}
				}
				else if(m==4||m==6||m==9||m==11)
				{
					date.removeAll();
					for(int i=1;i<=30;i++)
					{
						date.addItem(i);
					}
				}
			}
	    }
		else if(e.getSource()==mm)
		{
			int y=(Integer)yy.getSelectedItem();
			int m=(Integer)mm.getSelectedItem();
			
			if(((y%4==0)&&(y%100!=0))||(y%400==0))
			{
				if(m==2)
				{
					dd.removeAll();
					for(int i=1;i<=29;i++)
					{
						dd.addItem(i);
					}
				}
				else if(m==1||m==3||m==5||m==7||m==8||m==10||m==12)
				{
					dd.removeAll();
					for(int i=1;i<=31;i++)
					{
						dd.addItem(i);
					}
				}
				else if(m==4||m==6||m==9||m==11)
				{
					dd.removeAll();
					for(int i=1;i<=30;i++)
					{
						dd.addItem(i);
					}
				}
			}
			else{
				if(m==2)
				{
					dd.removeAll();
					for(int i=1;i<=28;i++)
					{
						dd.addItem(i);
					}
				}
				else if(m==1||m==3||m==5||m==7||m==8||m==10||m==12)
				{
					dd.removeAll();
					for(int i=1;i<=31;i++)
					{
						dd.addItem(i);
					}
				}
				else if(m==4||m==6||m==9||m==11)
				{
					dd.removeAll();
					for(int i=1;i<=30;i++)
					{
						dd.addItem(i);
					}
				}
			}
		}
	}
}

