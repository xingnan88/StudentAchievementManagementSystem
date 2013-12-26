import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.sql.*;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class StoreScore extends JPanel 
{
    private   MeTable   table   =   null;   
	private   JScrollPane   s_pan   =   null;   
	private   JButton   button_1   =   null; 
	
	StoreScore()
	{
		setLayout(new BorderLayout());
		table=new MeTable();
		button_1=new JButton("录入");
		button_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				inputPerform();
			}
		});
		JPanel p=new JPanel();
		p.add(button_1);
		s_pan=new JScrollPane(add(initTable(table)));
		add(s_pan,BorderLayout.CENTER);
		add(p,BorderLayout.SOUTH);
	}
    private JTable initTable(JTable table) {
    	DefaultTableModel dtm = new DefaultTableModel(   
                new Object [] {"学生编号","学生姓名","课程名称","成绩"},0);  
        //dtm.addRow(new Object[] {"12","050101","男","教授"});   
        //dtm.addRow(new Object[] {"21","053333","女","讲师"});
        /*SqlManager DBm=SqlManager.createInstance();
        DBm.connectDB();
        String sql="exec StoreScore'"+TeaInfo.jid.getText()+"'";
        System.out.println(sql);
        ResultSet rs=DBm.executeQuery(sql);
        try{
        	while(rs.next())
            {
            	dtm.addRow(new Object[]{rs.getString(1),rs.getString(2),
            			rs.getString(3),rs.getString(4)});
            }
        }catch(SQLException e){
        	e.printStackTrace();
        }*/
        
        
        
        
        
        table.setModel(dtm);   
        return table;   
	}
    public void updateTable()
    {
    	SqlManager DBm=SqlManager.createInstance();
        DBm.connectDB();
        String sql="exec StoreScore'"+TeaInfo.jid.getText()+"'";
        System.out.println(sql);
        ResultSet rs=DBm.executeQuery(sql);
        DefaultTableModel dtm = new DefaultTableModel(   
                new Object [] {"学生编号","学生姓名","课程名称","成绩"},0);
        try{
        	while(rs.next())
            {
            	dtm.addRow(new Object[]{rs.getString(1),rs.getString(2),
            			rs.getString(3),rs.getString(4)});
            }
        }catch(SQLException e){
        	e.printStackTrace();
        }
        table.setModel(dtm);
    }
	public void inputPerform()
	{
		int selectrow = 0;
        selectrow =table.getSelectedRow();
        String sid =table.getValueAt(selectrow,0).toString();
		String cname=table.getValueAt(selectrow,2).toString();
		String score=table.getValueAt(selectrow,3).toString();
		String sql="exec InputPerform '"+sid+"','"+cname+"','"+score+"'";
		System.out.println(sid+"...."+cname+"....");
		System.out.println(sql);
		SqlManager DBm=SqlManager.createInstance();
        DBm.connectDB();
        if(DBm.executeUpdate(sql)==1)
        {
        	JOptionPane.showMessageDialog(this,"成绩输入成功",
					"消息",JOptionPane.INFORMATION_MESSAGE);
        }
        DBm.closeDB();
	} 
}
class MeTable extends JTable
{
	public   boolean   isCellEditable(int   rowIndex,   int   columnIndex){
		if (columnIndex==3){
		  return true;   
		  } 
		else {
			return false;
		}
	}
	
}