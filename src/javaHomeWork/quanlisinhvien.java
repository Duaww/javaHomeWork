package javaHomeWork;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class quanlisinhvien implements ActionListener
{
	JFrame f;
	JTextField masvText;
	JTextField hotenText;
	JTextField lopText;
	JTextField khoaText;
	JTextField diemText;
	JTextField ngaysinhText;
	JTextField searchT;
	DefaultTableModel model;
	JTable table;
	public quanlisinhvien() throws SQLException 
	{
		f = new JFrame("QUAN LI SINH VIEN!!");
		f.setSize(700,500);
		f.setDefaultCloseOperation(3);
		f.getContentPane().setLayout(new BorderLayout());
		JPanel pf = new JPanel();
		pf.setLayout(new BorderLayout(10,20));
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(2,6,5,10));
		JLabel masv = new JLabel("MSSV");
		JLabel hoten = new JLabel("Name");
		JLabel lop = new JLabel("Class");
		JLabel khoa = new JLabel("Faculty");
		JLabel diem = new JLabel("Scores");
		JLabel ngaysinh = new JLabel("Date");
		masvText = new JTextField("");
	    hotenText = new JTextField("");
		lopText = new JTextField("");
		khoaText = new JTextField("");
		diemText = new JTextField("");
		ngaysinhText = new JTextField("");
		p1.add(masv);
		p1.add(masvText);
		p1.add(hoten);
		p1.add(hotenText);
		p1.add(ngaysinh);
		p1.add(ngaysinhText);
		p1.add(lop);
		p1.add(lopText);
		p1.add(khoa);
		p1.add(khoaText);
		p1.add(diem);
		p1.add(diemText);
		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(1,6,5,10));
		JButton addButton = new JButton("Add");
		addButton.addActionListener(this);
		JButton updateButton = new JButton("Update");
		updateButton.addActionListener(this);
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(this);
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(this);
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(this);
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(this);
		p2.add(addButton);
		p2.add(updateButton);
		p2.add(deleteButton);
		p2.add(searchButton);
		p2.add(resetButton);
		p2.add(exitButton);
		pf.add(BorderLayout.NORTH,p1);
		pf.add(BorderLayout.CENTER,p2);
		f.add(BorderLayout.NORTH,pf);
		JPanel pf2 = new JPanel();
		f.add(BorderLayout.CENTER,pf2);
		createTable();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
	private void createTable()
	{
		model = new DefaultTableModel();
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},new String [] {
		                "MSSV", "HoTen", "NgaySinh", "LOP", "KHOA","DIEM"
		            }));
		model = (DefaultTableModel) table.getModel();
		
		JScrollPane sc = new JScrollPane();
		sc.setViewportView(table);
		f.add(sc, BorderLayout.CENTER);
		show();
		this.table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				int hang = table.getSelectedRow();
				masvText.setEnabled(false);
				if (hang >= 0)
				{
					masvText.setText(model.getValueAt(hang, 0).toString());
					hotenText.setText(model.getValueAt(hang, 1).toString());
					ngaysinhText.setText(model.getValueAt(hang, 2).toString());
					lopText.setText(model.getValueAt(hang, 3).toString());
					khoaText.setText(model.getValueAt(hang, 4).toString());
					diemText.setText(model.getValueAt(hang, 5).toString());
				}
				
			}
		});
	}
	List<sinhvien> svList = new ArrayList<sinhvien>();
	private void show()
	{
		List<sinhvien> svList = sinhvienModify.findAll();
		model.setRowCount(0);
		for(sinhvien sv :svList)
		{
			model.addRow(new Object[] {sv.getMssv(),sv.getHoten(),sv.getNgaysinh(),sv.getLop(),sv.getKhoa(),sv.getDiem()});
		}
	}
	private void reset() 
	{
		this.masvText.setEnabled(true);
		this.masvText.setText("");
		this.hotenText.setText("");
		this.ngaysinhText.setText("");
		this.lopText.setText("");
		this.khoaText.setText("");
		this.diemText.setText("");
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Add"))
		{
			try {
				String mssv = this.masvText.getText();
				String hoten = this.hotenText.getText();
				String ngaysinh = this.ngaysinhText.getText();
				String lop = this.lopText.getText();
				String khoa = this.khoaText.getText();
				String diem = this.diemText.getText();
				sinhvien sv = new sinhvien(mssv, hoten, ngaysinh, lop, khoa, diem);
				sinhvienModify.Insert(sv);
				show();
				reset();
				
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		else if(e.getActionCommand().equals("Update"))
		{
			try {
				int hang = this.table.getSelectedRow();
				String mssv = this.masvText.getText();
				String hoten = this.hotenText.getText();
				String ngaysinh = this.ngaysinhText.getText();
				String lop = this.lopText.getText();
				String khoa = this.khoaText.getText();
				String diem = this.diemText.getText();
				sinhvien sv = new sinhvien(mssv, hoten, ngaysinh, lop, khoa, diem);
				int choice =JOptionPane.showConfirmDialog(this.f,"Do you want to update student ? ");
				if(choice == 0)
				{
					sinhvienModify.Update(sv);
					show();
					reset();
					this.masvText.setEnabled(true);
				}	
				
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		else if(e.getActionCommand().equals("Delete"))
		{
			try {
				int hang= this.table.getSelectedRow();
				if(hang >=0 )
				{
					
					int choice = JOptionPane.showConfirmDialog(this.f, "Do you want to delete student ?");
					if(choice == 0)
					{
						sinhvienModify.Delete(this.masvText.getText());
						show();
						reset();
					}
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		else if(e.getActionCommand().equals("Search"))
		{
			try 
			{
				String ms_input = JOptionPane.showInputDialog(this.f,"Enter MSSV");
				if(ms_input!=null && ms_input.length()>0)
				{
					this.svList = sinhvienModify.searchByMSSV(ms_input);
					this.model.setRowCount(0);
					for(sinhvien sv : this.svList)
					{
						this.model.addRow(new Object[] {sv.getMssv(),sv.getHoten(),sv.getNgaysinh(),sv.getLop(),sv.getKhoa(),sv.getDiem()});
					}
				}
			} 
			catch (Exception e2) 
			{
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		else if (e.getActionCommand().equals("Reset"))
		{
			try {
				this.show();
				this.reset();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		else if(e.getActionCommand().equals("Exit"))
		{
			int choice = JOptionPane.showConfirmDialog(this.f, "Are you sure want to exit ?");
			if(choice == 0)
			{
				System.exit(0);
			}
		}
		
	}
	public static void main(String[] args) throws SQLException 
	{
		quanlisinhvien ql = new quanlisinhvien();
		
	}
	
}

