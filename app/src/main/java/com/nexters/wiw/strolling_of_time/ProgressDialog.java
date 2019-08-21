package com.nexters.wiw.strolling_of_time;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ProgressDialog {
  private Dialog dialog;

  public ProgressDialog() {
    throw new UnsupportedOperationException("기본 생성자는 사용할 수 없습니다.");
  }

  public ProgressDialog(@NotNull Context context) {
    dialog = new Dialog(context);
    dialog.setContentView(R.layout.progress_dialog);
    Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    dialog.setCanceledOnTouchOutside(false);
  }
  public void show(){
    dialog.show();
  }
  public void dismiss(){
    dialog.dismiss();
  }
}
