package com.example.androidhouseandcar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.util.Timer;
import java.util.TimerTask;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class HouseAndCarView extends View {
	
private Bitmap carright, carleft;
private final int DELAY1 = 250;    // Delay between waves
private final int DELAY2 = 40;     // Delay in car movements
private final int XMID = 240;
private final int YTOP = 200;
private Paint paint = new Paint();
private RectF oval = new RectF();
private Timer timer1;              // Timer for wave animation
private Timer timer2;              // Timer for car animation

private int waveCnt = 0;           // Counts wave positions
private int x1 = XMID-100, y1 = YTOP+60;
private int x2 = XMID+115, y2 = YTOP+100;

private int carX = 0, carY = 450;  // Car X and Y coordinates
private int carXOff = 10;          // Car horizontal offset
private int carYOff = 50;          // Car vertical offset
private boolean carGoRight = true; // Car direction flag

private int carWidth;              // Width of car
private int scrWidth, scrHeight;   // Width and Height of screen

private Bitmap carRight, carLeft;  // Right and left car images

public HouseAndCarView(Context context) {
  super(context);
  
  carright = BitmapFactory.decodeResource(getResources(), R.drawable.pcarright);
  carleft = BitmapFactory.decodeResource(getResources(), R.drawable.pcarleft);

  Resources res = context.getResources();
  carRight = BitmapFactory.decodeResource(res, R.drawable.pcarright);
  carLeft = BitmapFactory.decodeResource(res, R.drawable.pcarleft);
  carWidth = carLeft.getWidth();     // Get the car width

  timer1 = new Timer();    // Instiantiate the wave timer
  timer1.schedule(new TimerTask() {          
     @Override
     public void run() {
        TimerMethod1();
     }
  }, 0, DELAY1);

  timer2 = new Timer();    // Instiantiate the car timer
  timer2.schedule(new TimerTask() {          
     @Override
     public void run() {
        TimerMethod2();
     }
  }, 0, DELAY2);
}

private void TimerMethod1()
{
  // We call the method that will work with the UI
  // through the post method.
  this.post(Timer1_Tick);
}

private Runnable Timer1_Tick = new Runnable() {
  @Override
  public void run() {
     if (++waveCnt > 3)  // After 4 start back at 0
        waveCnt = 0;
     switch(waveCnt) {   // Set new arm pos for each count
     case 0:
        x1 = XMID-100; y1 = YTOP+60;
        x2 = XMID+115; y2 = YTOP+100;
        break;
     case 1:
        x1 = XMID-107; y1 = YTOP+80;
        x2 = XMID+107; y2 = YTOP+80;
        break;
     case 2:
        x1 = XMID-115; y1 = YTOP+100;
        x2 = XMID+100; y2 = YTOP+60;
        break;
     case 3:
        x1 = XMID-107; y1 = YTOP+80;
        x2 = XMID+107; y2 = YTOP+80;
        break;
     }
     invalidate();
  }
};

private void TimerMethod2()
{
  // We call the method that will work with the UI
  // through the post method.
  this.post(Timer2_Tick);
}

private Runnable Timer2_Tick = new Runnable() {
  @Override
  public void run() {
     if (carGoRight) {  // if go right, add to car pos
        carX += carXOff;
        // check for boundary
        if (carX > scrWidth && scrWidth > carWidth) {
           carY -= carYOff;      // lift car up slightly 
           carGoRight = false;   // turn car around
        } 
     } else {  // if not go right, subtract from car pos
        carX -= carXOff;
        if (carX < -carWidth) {  // check for boundary
           carY += carYOff;      // drop car down slightly
           carGoRight = true;    // turn car around
        }
     }
     invalidate();
  }
};

// Get's the screen size
@Override
protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld) {
  super.onSizeChanged(xNew, yNew, xOld, yOld);
  scrWidth = xNew;
  scrHeight = yNew;
}

@Override
protected void onDraw(Canvas canvas) {
  super.onDraw(canvas);

 
  int xOffset = 170;
  int yOffset = 240;

  canvas.drawColor(Color.CYAN);   // background color
  paint.setStrokeWidth(0);
  paint.setStyle(Style.FILL);

  paint.setColor(Color.GREEN);
  canvas.drawRect(0, 460, 550, 550, paint);   // ground

  paint.setColor(Color.YELLOW);
  oval.set(-80, -80, 80, 80);
  canvas.drawOval(oval, paint);   // sun

  if (!carGoRight)
     canvas.drawBitmap(carLeft, carX, carY, paint);

  //insert house code below
  // Roof Polygon
  
  
  //Roof Outline
  paint.setColor(Color.BLACK);
  canvas.drawLine(xOffset, yOffset+40, xOffset+100, yOffset, paint);
  canvas.drawLine(xOffset+100, yOffset, xOffset+200, yOffset+40, paint);
  canvas.drawLine(xOffset+200, yOffset+40,xOffset, yOffset+40, paint);
  
  //House Base Outline
  paint.setColor(Color.BLACK);
  canvas.drawRect(xOffset+1, yOffset+40+1, 370, 475, paint);
  
  //House Base
  paint.setColor(Color.GREEN);
  canvas.drawRect(xOffset+2, yOffset+40+2, 369, 474, paint);
  
  //Window/Shutter1 outline
  paint.setColor(Color.BLACK);
  canvas.drawRect(xOffset+15, yOffset+40+15, 265, 355, paint);
  
  //Windowpane1
  paint.setColor(Color.YELLOW);
  canvas.drawRect(xOffset+30, yOffset+40+18, 221, 320, paint);
  
  //Windowpane2
  paint.setColor(Color.YELLOW);
  canvas.drawRect(xOffset+59, yOffset+40+18, 250, 320, paint);
  
  //Windowpane3
  paint.setColor(Color.YELLOW);
  canvas.drawRect(xOffset+30, yOffset+88, 221, 350, paint);
  
  //Windowpane4
  paint.setColor(Color.YELLOW);
  canvas.drawRect(xOffset+59, yOffset+88, 250, 350, paint);
  
//Window/Shutter2 outline
  paint.setColor(Color.BLACK);
  canvas.drawRect(xOffset+110, yOffset+40+15, 359, 355, paint);
  
  //Windowpane2.1
  paint.setColor(Color.YELLOW);
  canvas.drawRect(xOffset+30+95, yOffset+40+18, 221+95, 320, paint);
  
  //Windowpane2.2
  paint.setColor(Color.YELLOW);
  canvas.drawRect(xOffset+59+95, yOffset+40+18, 250+95, 320, paint);
  
  //Windowpane2.3
  paint.setColor(Color.YELLOW);
  canvas.drawRect(xOffset+30+95, yOffset+88, 221+95, 350, paint);
  
  //Windowpane2.4
  paint.setColor(Color.YELLOW);
  canvas.drawRect(xOffset+59+95, yOffset+88, 250+95, 350, paint);
  
  //Door
  paint.setColor(Color.RED);
  canvas.drawRect(xOffset+59+50, yOffset+88+70, 250+90, 474, paint);
  
  //Doorknob 170,240
  paint.setColor(Color.BLUE);
  oval.set(290, 450, 295, 455);
  canvas.drawOval(oval, paint);
  	  

  if (carGoRight)
     canvas.drawBitmap(carRight, carX, carY, paint);
}

public void start() {
	// TODO Auto-generated method stub
	
}
}
