package com.nexters.wiw.strolling_of_time.views.timer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.nexters.wiw.strolling_of_time.R;

public class ArcTimerView extends View {
  private final String TAG = getClass().getSimpleName();
  private float time = 0;

  // View의 기본 attribute를 사용하기 위한 생성자
  // LayoutInflater에서 사용하는 생성자
  public ArcTimerView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @SuppressLint({"DrawAllocation", "ResourceAsColor"})
  @Override
  protected void onDraw(Canvas canvas) { // 나라의 문양 설정
    setBackgroundColor(R.color.cereal_timer_ready_color_background); // 배경색을 지정

    RectF outline = new RectF();

    Paint redCircle = new Paint();
    Paint blueCircle = new Paint();
    Paint arc= new Paint();

    redCircle.setARGB(255, 192,16,40);
    arc.setColor(Color.WHITE);
    blueCircle.setARGB(255, 12,55,120);

    Point viewCenter = new Point(getWidth() / 2, getHeight() / 2);
    int circleRadius = (getWidth() / 2) - 10;


    int startX = viewCenter.x - circleRadius;
    int startY = viewCenter.y - circleRadius;
    int endX = viewCenter.x + circleRadius;
    int endY = viewCenter.y + circleRadius;

    Point leftCirclePoint = new Point(viewCenter.x - circleRadius/2, viewCenter.y);
    Point rightCirclePoint = new Point(viewCenter.x + circleRadius/2, viewCenter.y);

    Paint leftCircle = new Paint();
    Paint rightCircle = new Paint();

    leftCircle.setARGB(255, 192,16,40);
    rightCircle.setARGB(255, 12,55,120);

    Rect viewRect = new Rect();
    getLocalVisibleRect(viewRect);

    // arc를 그릴 영역 지정
    outline.set(startX, startY, endX, endY);

    canvas.drawCircle(viewCenter.x, viewCenter.y, circleRadius, redCircle); // 원의중심 x,y, 반지름,paint
    canvas.drawArc(outline, 0, 180, true, blueCircle);
    canvas.drawCircle(leftCirclePoint.x, leftCirclePoint.y, circleRadius/2, leftCircle);
    canvas.drawCircle(rightCirclePoint.x, rightCirclePoint.y, circleRadius/2, rightCircle);

    canvas.drawArc(outline, 270, time, true, arc);
  }

  public void setTime(float time){
    this.time = time;
  }

  public void initialize(){
    this.time = 0;
  }
}