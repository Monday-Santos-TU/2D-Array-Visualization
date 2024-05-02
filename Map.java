import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class Map extends JPanel {
    private Color purp = new Color(128, 0, 128);

    public Map() {
        setSize(120, 120);
		setVisible(true);
    }

    public int[][] createMap(String location, int row, int column) {
        int temp[][] = new int[row][column];
        try {
            Scanner frank = new Scanner(new File(location));
            
            while(frank.hasNextLine()) {
                for(int i = 0; i < temp.length; i++) {
                    String[] line = frank.nextLine().trim().split(" ");
                    for(int j = 0; j < line.length; j++) {
                        temp[i][j] = Integer.parseInt(line[j]);
                    }
                }
            }
            frank.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public void draw(Graphics2D g2d, int[][] arr) {
        int tileHeight = 480/(arr.length + 2);
        int tileWidth = 480/(2 + arr[0].length);
        int screenX = tileWidth - 8;
        int screenY = tileHeight - 8;

        for(int x = 0; x < arr.length; x++) {
            for(int y = 0; y < arr[x].length; y++) {
                int pos = arr[x][y];
                if(pos == 0) {
                    draw(g2d, screenX, screenY, tileWidth, tileHeight, Color.white);
                } else if(pos == 1) {
                    draw(g2d, screenX, screenY, tileWidth, tileHeight, Color.red);
                } else if(pos == 2) {
                    draw(g2d, screenX, screenY, tileWidth, tileHeight, Color.yellow);
                } else if(pos == 3) {
                    draw(g2d, screenX, screenY, tileWidth, tileHeight, Color.green);
                } else if(pos == 4) {
                    draw(g2d, screenX, screenY, tileWidth, tileHeight, Color.blue);
                } else if(pos == 5) {
                    draw(g2d, screenX, screenY, tileWidth, tileHeight, purp);
                }
                screenX += tileWidth + 4;
            }
            screenX = tileWidth - 8;
            screenY += tileHeight + 4;
        }
    }

    public void draw(Graphics g2d, int screenX, int screenY, int width, int height, Color color) {
		g2d.setColor(color);
		g2d.fillRect(screenX, screenY, width, height);
	}
}