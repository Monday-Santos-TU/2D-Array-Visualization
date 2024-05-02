import javax.swing.*;
import java.awt.*;

public class Board extends JPanel implements Runnable {
	private Thread thread;
	private Map mp;
	private int twoDee[][];
	private KeyHandler kh;

	public Board() {
		kh = new KeyHandler();
		thread = new Thread(this);
		setPreferredSize(new Dimension(480, 480));
		setVisible(true);
		setFocusable(true);
		setBackground(new Color(211, 211, 211));
		addKeyListener(kh);
		
		mp = new Map();
		resetArray();

		init();
	}

	private synchronized void init() {
		thread.start();
	}

	public synchronized void stop() {
		try {
			thread.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		mp.draw(g2d, twoDee);

		g2d.dispose();
	}

	public void draw() {
		repaint();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {}
	}

	public void resetArray() {
		twoDee = mp.createMap("array.txt", 5, 5);
	}

	public void reverseRow(int[][] arr) {
		for(int row = 0; row < arr.length; row++) {
			int startCol = 0;
			int endCol = arr[0].length - 1;

			while(startCol < endCol) {
				int temp = arr[row][startCol];
				arr[row][startCol] = arr[row][endCol];
				arr[row][endCol] = temp;
				draw();
				startCol++;
				endCol--;
			}
		}
	}

	public void reverseAll(int[][] arr) {
		int[][] temp = new int[arr.length][arr[0].length];

		for(int i = 0; i < temp.length; i++) {
			for(int j = 0; j < temp[0].length; j++) {
				temp[i][j] = arr[i][j];
			}
		}

		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				arr[i][j] = temp[arr.length - i - 1][arr[0].length - j - 1];
				draw();
			}
		}
	}

	public void reverseCol(int[][] arr) {
		for(int col = 0; col < arr.length; col++) {
			int startRow = 0;
			int endRow = arr.length - 1;

			while(startRow < endRow) {
				int temp = arr[startRow][col];
				arr[startRow][col] = arr[endRow][col];
				arr[endRow][col] = temp;
				draw();
				startRow++;
				endRow--;
			}
		}
	}

	@Override
	public void run() {
		double drawInterval = 1000000000/60;
		double nextDrawTime = System.nanoTime() + drawInterval;

		repaint();
		while(thread != null) {
			if(kh.one) {
				resetArray();
				reverseRow(twoDee);
				kh.one = false;
			}
			if(kh.two) {
				resetArray();
				reverseCol(twoDee);
				kh.two = false;
			}
			if(kh.three) {
				resetArray();
				reverseAll(twoDee);
				kh.three = false;
			}

			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime /= 1000000;
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}

				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String args[]) {
		JFrame jf = new JFrame();

		jf.add(new Board());

        jf.setResizable(false);
        jf.pack();

        jf.setTitle("2D Array");
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
	}
}
