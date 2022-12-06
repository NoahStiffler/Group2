package grp2;
import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.*;

public class Graph extends JPanel{

	
	// Fill x and y coordinate arrays with random values between 0 and 250
	public static int[] fillCord() {
		Random rand = new Random();
		int upperbound = 250;
		int xcord[] = new int[100];

		
		int random = rand.nextInt(upperbound);
		
		for(int i = 0; i < 100; i++) {
			xcord[i] = random;
			random = rand.nextInt(upperbound);
		}
		
		return xcord;
	}
	
	// This method takes the arrays of X and Y coordinates, runs the distance formula, and determines if there is a cluster.
	public String findCluster(int[] xcord, int[] cord) {
		double dist = 0;
		int total = 0;
		int clusterCount = 0;
		for (int i = 1; i < xcord.length; i++) {
			for (int j = 1; j < xcord.length; j++) {
				dist = Math.sqrt(Math.pow((xcord[i]-xcord[i-1]), 2) + Math.pow((cord[j]-cord[j-1]), 2));
				if (dist < 10) {
					clusterCount++;
				}
					
			}
			
			if (clusterCount > 5) {
				total++;
				
			}
			clusterCount = 0;
		}
		System.out.println("Total amount of clusters: " + total);
	//	System.out.println(clusterCount);
		String countStr = Integer.toString(total);
		return countStr;

	}
	
	
	
	static int[] xcord = fillCord();
	static int[] cord = fillCord();
	int marg = 80;
	

	// Code of generating the graph and plotting the points
	protected void paintComponent(Graphics grf) {
		super.paintComponent(grf);
		Graphics2D graph = (Graphics2D)grf;
		
		
		graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		
		int width = getWidth();
		int height = getHeight();
		
		graph.drawString(findCluster(xcord, cord), 10, 10);
		
		graph.draw(new Line2D.Double(marg, marg, marg, height-marg));
		graph.draw(new Line2D.Double(marg, height-marg, width-marg, height-marg));
		
		double x = (double)(width-2*marg)/(cord.length-1);
		double scale = (double)(height-2*marg)/getMax();
		double xscale = (double)(width-2*marg)/getMaxx();
		
		graph.setPaint(Color.RED);
		
		for(int i = 0; i < cord.length; i++) {
			double x1 = width-marg-xscale*xcord[i];
			double y1 = height-marg-scale*cord[i];
			graph.fill(new Ellipse2D.Double(x1-2, y1-2, 8, 8));
		}
		
	
	}
	
	// We use this method to get the scale of the graph.
	private int getMax() {
		
		int max = -Integer.MAX_VALUE;
		for(int i = 0; i < cord.length; i++) {
			if(cord[i]>max) {
				max = cord[i];
			}
			
		}
		return max;
	}
	
	private int getMaxx() {
		
		int max = -Integer.MAX_VALUE;
		for(int i = 0; i < xcord.length; i++) {
			if(xcord[i]>max) {
				max = xcord[i];
			}
			
		}
		return max;
	}

	
	public static void main(String args[]) {
		

		System.out.println("A 'cluster' is defined by any one point that has more than 5 instances of another point being within 10 units");
		
		
		JFrame frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new Graph());
		frame.setSize(1200, 1200);
		frame.setLocation(1500, 540);
		frame.setVisible(true);
		
		

	}
	

}
