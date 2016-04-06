package io.sharif.prj1.st91105506.st91100905;


import android.content.Context;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;

public class RainbowSpan extends CharacterStyle implements UpdateAppearance{
    private final int[]colors;
    public RainbowSpan(Context context){
        colors=context.getResources().getIntArray(R.array.rainbow);
    }
    @Override
    public void updateDrawState(TextPaint paint){
        paint.setStyle(Paint.Style.FILL);
        Shader shader=new LinearGradient(0,0,0,
                paint.getTextSize()*colors.length,colors,
                null,Shader.TileMode.MIRROR);
        Matrix matrix=new Matrix();
        matrix.setRotate(90);
        shader.setLocalMatrix(matrix);
        paint.setShader(shader);
    }
}
