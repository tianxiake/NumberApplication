package com.snailyy.numberapplication;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.text.style.ReplacementSpan;

/**
 * @author yongjie created on 2020/4/15.
 */
public class VerticalCenterSpan extends ReplacementSpan {
	/**
	 * 单位是sp
	 */
	private float fontSize;
	private int color;

	public VerticalCenterSpan(float fontSize, int color) {
		this.fontSize = fontSize;
		this.color = color;
	}

	@Override
	public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
		text = text.subSequence(start, end);
		Paint p = getCustomTextPaint(paint);
		return (int) p.measureText(text.toString());
	}

	@Override
	public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
		text = text.subSequence(start, end);
		Paint p = getCustomTextPaint(paint);
		Paint.FontMetricsInt fm = p.getFontMetricsInt();
		p.setColor(color);
		// 此处重新计算y坐标，使字体居中
		canvas.drawText(text.toString(), x, y - ((y + fm.descent + y + fm.ascent) / 2 - (bottom + top) / 2), p);
	}

	private TextPaint getCustomTextPaint(Paint srcPaint) {
		TextPaint paint = new TextPaint(srcPaint);
		paint.setTextSize(fontSize * paint.density);
		return paint;
	}
}
