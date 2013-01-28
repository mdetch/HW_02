import java.awt.*;

public class MyAvatarClockDB extends MyAvatarClock{
	public void paint(Graphics g){
		Image image = createImage(800,800); 
		Graphics offscreen = image.getGraphics();
		paint(offscreen);
		g.drawImage(image,0,0,this);
	}
	public void update(Graphics g){
		   super.paint(g);
		}
}
