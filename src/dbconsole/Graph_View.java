package dbconsole;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Graph_View extends Frame implements ActionListener,WindowListener {
	private int sellsyear[][] = new int[4][15];
	private TextField text1 = new TextField("", 4);
	private TextField text2 = new TextField("", 10);
	private TextField text3 = new TextField("", 10);
	private Button button1 = new Button("検索");
	public Graph_View(){
		
		addWindowListener(this);
		setTitle("年間CD総出荷枚数");
		
		int year,sells,i;
		ResultSet rs;
		
		
		
		MySQL1 mysql1 = new MySQL1();
		rs = mysql1.selectAll();
		i=0;
		try{
			while(rs.next()){
				year =rs.getInt("year");
				sells = rs.getInt("sells");
				sellsyear[0][i]=year;
				sellsyear[1][i]=sells;
				System.out.println("年度:"+year);
				System.out.println("売上枚数(千枚):"+sells);
				i++;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		MySQL mysql = new MySQL();
		rs = mysql.selectAll();
		i=0;
		try{
			while(rs.next()){
				year =rs.getInt("year");
				sells = rs.getInt("sells");
				sellsyear[2][i]=year;
				sellsyear[3][i]=sells;
				System.out.println("年度:"+year);
				System.out.println("売上枚数(千枚):"+sells);
				i++;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
		DefaultCategoryDataset data = new DefaultCategoryDataset();
			for(int j=0;j<i;j++){
				String s1=String.valueOf(sellsyear[0][j]);
				data.addValue(sellsyear[1][j], "album", s1);
				String s2=String.valueOf(sellsyear[2][j]);
				data.addValue(sellsyear[3][j], "single", s2);
			}
		  JFreeChart chart = 
	      ChartFactory.createLineChart("A year total sells",
	                                   "year",
	                                   "The number(x1000)",
	                                   data,
	                                   PlotOrientation.VERTICAL,
	                                   true,
	                                   false,
	                                   false);
		ChartPanel cpanel = new ChartPanel(chart);
	    add(cpanel, BorderLayout.CENTER);
	    setLayout(new FlowLayout(FlowLayout.CENTER));
	    add(text1);
	    add(new Label("年の売上枚数"));
	    add(button1);
	    add(new Label("シングル売上枚数(千枚)"));
		add(text2);
		add(new Label("アルバム売上枚数(千枚)"));
		add(text3);
		button1.addActionListener(this);
	 }
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==button1){
			int val1=Integer.parseInt(text1.getText());
			if(val1>=1999 && val1<=2013){
				int i = val1 - 1999;
				text2.setText(String.valueOf(sellsyear[3][i]));
				text3.setText(String.valueOf(sellsyear[1][i]));
			}else{
				text2.setText("");
				text3.setText("");
			}
		}	
	}

}
