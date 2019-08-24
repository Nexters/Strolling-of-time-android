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

import androidx.core.content.ContextCompat;

import com.nexters.wiw.strolling_of_time.R;

public class ArcTimerView extends View {
  private final String TAG = getClass().getSimpleName();
  private float time = 0;

  private Paint CircleTimer;
  private Paint gaugeMarginCircle;
  private Paint gauge;

  private Point viewCenter;
  private int circleRadius;

  private int startX;
  private int startY;
  private int endX;
  private int endY;

  private int gaugeColor;
  private int marginColor;

  // View의 기본 attribute를 사용하기 위한 생성자
  // LayoutInflater에서 사용하는 생성자
  public ArcTimerView(Context context, AttributeSet attrs) {
    super(context, attrs);
    gaugeColor = ContextCompat.getColor(getContext(), R.color.cereal_timer_time_gauge_color);
    marginColor = ContextCompat.getColor(getContext(), R.color.cereal_timer_ready_color_background);

  }

  @SuppressLint({"DrawAllocation", "ResourceAsColor"})
  @Override
  protected void onDraw(final Canvas canvas) {
    super.onDraw(canvas);
    RectF outline = new RectF();

    CircleTimer = new Paint();
    gaugeMarginCircle = new Paint();
    gauge = new Paint();

    gauge.setColor(gaugeColor);
    gaugeMarginCircle.setColor(marginColor);
    CircleTimer.setColor(Color.WHITE);
    viewCenter = new Point(getWidth() / 2, getHeight() / 2);

    circleRadius = (getWidth() / 2) - 10;

    startX = viewCenter.x - circleRadius;
    startY = viewCenter.y - circleRadius;
    endX = viewCenter.x + circleRadius;
    endY = viewCenter.y + circleRadius;

    Rect viewRect = new Rect();
    getLocalVisibleRect(viewRect);

    // arc를 그릴 영역 지정
    outline.set(startX, startY, endX, endY);

    canvas.drawCircle(viewCenter.x, viewCenter.y, circleRadius, CircleTimer); // 원의중심 x,y, 반지름,paint
    canvas.drawArc(outline, 270, time, true, gauge);
    canvas.drawCircle(viewCenter.x, viewCenter.y, circleRadius-10, gaugeMarginCircle);
  }

  public void setTime(float time){
    this.time = time;
  }

  public void setMarginColor(int color){
    marginColor = color;
  }

  public void initialize(){
    this.time = 0;
  }

}