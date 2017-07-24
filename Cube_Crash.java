package Cube_Crash;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Cube_Crash extends Frame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int Length;
	public static void main(String args[]){
		Length=3;
		JFrame frame=new JFrame("My blockie");
		frame.setBounds(100,100,355,400);
		
		Cube cube=new Cube(3);
		JMenu menu_d=new JMenu("difficulty");
		JMenuItem menuItem_1=new JMenuItem("Easily!");
		menuItem_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cube.actionEaysily();
			}
		});
		JMenuItem menuItem_2=new JMenuItem("Normally!");
		menuItem_2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cube.actionNormally();
			}
		});
		JMenuItem menuItem_3=new JMenuItem("Difficulty");
		menuItem_3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cube.actiondifficultly();
			}
		});
		cube.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				if(e.getButton()==MouseEvent.BUTTON1){
					int x=e.getX()/20;
					int y=e.getY()/20;
					cube.cubeAction(x, y);
					frame.repaint();
					if(cube.gameOver())
						frame.repaint();;
				}
			}
		});
		frame.add(cube);
		JMenuBar menuBar=new JMenuBar();
		JMenu menu=new JMenu("Help");
		JMenuItem item_1=new JMenuItem("About the game");
		item_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Please read the text of help","information", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		menuBar.add(menu_d);
		menu_d.add(menuItem_1);
		menu_d.add(menuItem_2);
		menu_d.add(menuItem_3);
		frame.setJMenuBar(menuBar);
		menuBar.add(menu);
		menu.add(item_1);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
class Cube extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Image background=null;
	public Image color_0=null;
	public Image color_1=null;
	public Image color_2=null;
	public Image color_3=null;
	public Image color_4=null;
	public Image color_5=null;
	public void readImg(){
		if(!ifImg)
		try {
			switch(Length){
			case 3:
				background = ImageIO.read(new File("img/background.jpg"));
				color_0 = ImageIO.read(new File("img/color_0.jpg"));
				color_1 = ImageIO.read(new File("img/color_4.jpg"));
				color_2 = ImageIO.read(new File("img/color_5.jpg"));
				color_3 = ImageIO.read(new File("img/color_8.jpg"));
				color_4 = ImageIO.read(new File("img/color_9.jpg"));
				color_5 = ImageIO.read(new File("img/color_16.jpg"));
				break;
			case 2:
				background = ImageIO.read(new File("img/background_2.jpg"));
				color_0 = ImageIO.read(new File("img/color_0.jpg"));
				color_1 = ImageIO.read(new File("img/color_3.jpg"));
				color_2 = ImageIO.read(new File("img/color_11.jpg"));
				color_3 = ImageIO.read(new File("img/color_13.jpg"));
				color_4 = ImageIO.read(new File("img/color_17.jpg"));
				color_5 = ImageIO.read(new File("img/color_18.jpg"));
				break;
			case 1:
				background = ImageIO.read(new File("img/background_3.jpg"));
				color_0 = ImageIO.read(new File("img/color_0.jpg"));
				color_1 = ImageIO.read(new File("img/color_7.jpg"));
				color_2 = ImageIO.read(new File("img/color_9.jpg"));
				color_3 = ImageIO.read(new File("img/color_12.jpg"));
				color_4 = ImageIO.read(new File("img/color_14.jpg"));
				color_5 = ImageIO.read(new File("img/color_15.jpg"));
				break;
			default:
				break;
			}
		}catch(IOException e){
			JOptionPane.showMessageDialog(null,"Please put the img in the D:","Alert", JOptionPane.ERROR_MESSAGE);
		}
	}
	public boolean ifImg=false;
	Cube(int Length){
		this.Length=Length;
		readImg();
		newMap();
	}
	public void actionEaysily() {
		Length=3;
		newMap();
		Score=0;
		readImg();
		repaint();
		
	}
	public void actionNormally() {
		// TODO Auto-generated method stub
		Length=2;
		newMap();
		Score=0;
		readImg();
		repaint();
	}
	public void actiondifficultly() {
		// TODO Auto-generated method stub
		Length=1;
		newMap();
		Score=0;
		readImg();
		repaint();
	}
	JPanel Game=new JPanel();
	int orx[]={0,1,0,-1};
	int ory[]={-1,0,1,0};
	int map[][]=new int[17][17];
	int Score=0;
	int Length;
	public int Count(int x,int y){
		if(x<=0||x>=16||y<=0||y>=16||map[x][y]==6)
			return 0;
		boolean Ift[][]=new boolean[17][17];
		Queue<Integer>allx=new LinkedList<Integer>();
		Queue<Integer>ally=new LinkedList<Integer>();
		Queue<Integer>qx=new LinkedList<Integer>();
		Queue<Integer>qy=new LinkedList<Integer>();
		int theColor=map[x][y];
		int nx,ny;
		int count=1;
		qx.add(x);
		qy.add(y);
		allx.add(x);
		ally.add(y);
		Ift[x][y]=true;
		while(!qx.isEmpty()){
			int thisx= qx.poll();
			int thisy= qy.poll();
			for(int i=0;i<4;i++){
				nx=thisx+orx[i];
				ny=thisy+ory[i];
				if(map[nx][ny]==theColor&&!Ift[nx][ny]){
					qx.add(nx);
					qy.add(ny);
					count++;
					Ift[nx][ny]=true;
					allx.add(nx);
					ally.add(ny);
				}
			}
		}
		return count;
	}
	public void Clear(int x,int y){
		Queue<Integer>qx=new LinkedList<Integer>();
		Queue<Integer>qy=new LinkedList<Integer>();
		int theColor=map[x][y];
		int nx,ny;
		qx.add(x);
		qy.add(y);
		while(!qx.isEmpty()){
			int thisx= qx.poll();
			int thisy= qy.poll();
			map[thisx][thisy]=6;
			for(int i=0;i<4;i++){
				nx=thisx+orx[i];
				ny=thisy+ory[i];
				if(map[nx][ny]==theColor){
					qx.add(nx);
					qy.add(ny);
				}
			}
		}
	}
	public void newMap() {
		Random random = new Random();
		for(int i=1;i<16;i++){
			int dx=1;
			while(dx<16){
				int colorLength=random.nextInt(Length)+1;
				int color=random.nextInt(5)+1;
				colorLength = dx + colorLength > 16 ? 15-dx:colorLength;
				for(int j=0;j<colorLength;j++){
					map[dx+j][i]=color;
				}
				dx+=colorLength;
			}
		}
		for (int i=0; i<17; i++){
			map[0][i] = 0;
			map[16][i] = 0;
			map[i][0] = 0;
			map[i][16] = 0;
		}
	}
	public void moveByX(){
		for(int i=0;i<14;i++){
			for(int j=2;j<16;j++){
				boolean flag=false;
				for(int k=1;k<16;k++){
					if(map[j-1][k]!=6)
					{
						flag=true;
						break;
					}
				}
				if(!flag){
					for(int k=1;k<16;k++){
						map[j-1][k]=map[j][k];
						map[j][k]=6;
					}
				}
			}
		}
	}
	public void moveByY(){
		for(int k=0;k<14;k++){
			for(int i=1;i<16;i++){
				for(int j=14;j>0;j--)
				{
					if(map[i][j+1]==6){
						map[i][j+1]=map[i][j];
						map[i][j]=6;
					}
				}
			}
		}
	}
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		Image color=null;
		g.drawImage(background, 0, 0, 350, 380, 0, 0, 350, 380, null);
		for(int i=0;i<17;i++)
		{
			for(int j=0;j<17;j++){
				switch(map[i][j]){
				case 0:
					g.setColor(Color.white);
					color=color_0;
					break;
				case 1:
					g.setColor(Color.blue);
					color=color_1;
					break;
				case 2:
					g.setColor(Color.red);
					color=color_2;
					break;
				case 3:
					g.setColor(Color.GRAY);
					color=color_3;
					break;
				case 4:
					g.setColor(Color.green);
					color=color_4;
					break;
				case 5:
					g.setColor(Color.PINK);
					color=color_5;
					break;
				}
				if(map[i][j]!=6)
					g.drawImage(color, 20*i, 20*j, 20+20*i, 20*j+20, 0, 0, 70, 70, null);
			}
			g.setColor(new Color(255, 255, 0));
			g.setFont(new Font("Courier",Font.BOLD,12));
			g.drawString("Score:"+Score, 0, 330);
		}
	}
	public boolean gameOver() {
		for (int i=1; i<16; i++){
			for (int j=1; j<16; j++){
				if (Count(i,j) >2) 
					return false;
			}
		}
		JOptionPane.showMessageDialog(null,"Sorry!Game over!Your score is "+Score,"information", JOptionPane.INFORMATION_MESSAGE);
		newMap();
		Score=0;
		return true;
	}
	public int getScore(int degree, int starNum) {
		int score = 0;
		if(starNum<3)
			return 0;
		switch (degree)
		{
		case 3:
			score = (starNum-2)*(starNum-2);
			break;
		case 2:
			score = 2*((starNum-2)*(starNum-2));
			break;
		case 1:
			score = 3*((starNum-2)*(starNum-2));
			break;
			
		}
		return score;
	}
	public void cubeAction(int x,int y){
		int count=Count(x,y);
		Score+=getScore(Length,count);
		if(count>2)
			Clear(x,y);
		moveByY();
		moveByX();
	}
}