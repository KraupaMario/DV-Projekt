import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class SimpleTesting extends JPanel implements MouseListener {
	private Vector v;
	public SimpleTesting() {
		v = new Vector();
		setBackground(Color.white);
		addMouseListener(this);
	}
	public static void main(String args[]) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new SimpleTesting());
		frame.setTitle("Repaint Method");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setSize(375, 250);
		frame.setVisible(true);
	}
	public void paint(Graphics g) { // paint() method
		super.paint(g);
		g.setColor(Color.black);
		Enumeration enumeration = v.elements();
		while(enumeration.hasMoreElements()) {
			Point p = (Point)(enumeration.nextElement());
			g.drawOval(p.x-20, p.y-20, 40, 40);
		}
	}
	public void mousePressed(MouseEvent me) {
		v.add(me.getPoint());
		repaint(); // call repaint() method
	}
	// MouseListener method blank implementation
	public void mouseClicked(MouseEvent me) {}
	public void mouseEntered(MouseEvent me) {}
	public void mouseExited(MouseEvent me) {}
	public void mouseReleased(MouseEvent me) {}
}