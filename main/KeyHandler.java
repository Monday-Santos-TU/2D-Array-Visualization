import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean one, two, three;

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        
        if(k == KeyEvent.VK_1) {
            one = true;
            if(two) {
                two = false;
            }
            if(three) {
                three = false;
            }
        }
        if(k == KeyEvent.VK_2) {
            two = true;
            if(one) {
                one = false;
            }
            if(three) {
                three = false;
            }
        }
        if(k == KeyEvent.VK_3) {
            three = true;
            if(one) {
                one = false;
            }
            if(two) {
                two = false;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
