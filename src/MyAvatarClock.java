import java.awt.*;
import java.applet.Applet;

public class MyAvatarClock extends MyAvatar implements Runnable{
	
	protected Thread mainThread;
	protected int delay;
	protected int i;
	
	public void init(){
		mainThread = null;
		delay = 1000;
		i=1;
	}
	public void start(){
		if(mainThread==null){
			mainThread = new Thread(this);
			mainThread.start();
		}
	}
	public void paint(Graphics g){
		Image image = createImage(900,900); 
		Graphics offscreen = image.getGraphics();
		g.drawImage(image,0,0,this);
		super.paint(g);
		g.drawOval(160,280,80,40);		//eye
		g.drawOval(290,280,80,40);
		g.setColor(Color.WHITE);
		g.fillOval(160,280,80,40);		//eye
		g.fillOval(290,280,80,40);
		
		if(i>60)	i=1;
		paintClock(g,i);
		paintEyes(g,i);
		i++;
		
	}
	public void update(Graphics g){
		   paint(g);
	}
	
	public void paintClock(Graphics g, int i){
		for(int j=1; j<=60; j++){
			int x = 270+(int)(Math.sin(Math.toRadians(6*j))*260);
			int y = 325-(int)(Math.cos(Math.toRadians(6*j))*260);
			g.setColor(Color.BLACK);
			if(j==i) g.setColor(Color.YELLOW);
			if(j%5==0){
				g.drawString(""+j,x,y);
			}else{
				g.drawString(".",x,y);
			}
		}
	}
	
	public void paintEyes(Graphics g, int i){
		int x = (int)(Math.sin(Math.toRadians(6*i))*12);
		int y = (int)(Math.cos(Math.toRadians(6*i))*12);
		g.setColor(Color.BLACK);
		g.fillOval(190+x,290-y,20,20);
		g.fillOval(320+x,290-y,20,20);
	}
	public void run(){
		while(Thread.currentThread()==mainThread){
			repaint();
			try{
				Thread.currentThread().sleep(delay);
			}catch(InterruptedException e){}
		}
	}
}
