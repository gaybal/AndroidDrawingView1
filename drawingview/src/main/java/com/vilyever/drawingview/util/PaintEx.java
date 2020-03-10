package com.vilyever.drawingview.util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public final class PaintEx {

	public static Paint clone(Paint paint) {
		Paint newPaint = new Paint();

		newPaint.setColor(paint.getColor());
		newPaint.setStrokeWidth(paint.getStrokeWidth());
		newPaint.setStyle(paint.getStyle());
		newPaint.setTextSize(paint.getTextSize());
		newPaint.setTypeface(paint.getTypeface());

		return newPaint;
	}

	public static double pointToAngle(Point p1, Point p2) {
		double distance;
		distance = Math.sqrt(Math.pow((p2.x - p1.x), 2)
				+ Math.pow((p2.y - p1.y), 2));

		if (distance == 0)
			return 0;

		double s伪 = (p2.y - p1.y) / distance;
		double c伪 = (p2.x - p1.x) / distance;
		double 伪 = 0.0;

		if (c伪 < 0) {
			伪 = Math.PI - Math.asin(s伪);
		} else {
			伪 = Math.asin(s伪);
		}
		return 伪 * 180 / Math.PI;
	}

//	public static Rect calculateSrcRect(int srcWidth, int srcHeight,
//                                        int dstWidth, int dstHeight, ScalingLogic scalingLogic) {
//		if (scalingLogic == ScalingLogic.CROP) {
//			final float srcAspect = (float) srcWidth / (float) srcHeight;
//			final float dstAspect = (float) dstWidth / (float) dstHeight;
//			if (srcAspect > dstAspect) {
//				final int srcRectWidth = (int) (srcHeight * dstAspect);
//				final int srcRectLeft = (srcWidth - srcRectWidth) / 2;
//				return new Rect(srcRectLeft, 0, srcRectLeft + srcRectWidth,
//						srcHeight);
//			} else {
//				final int srcRectHeight = (int) (srcWidth / dstAspect);
//				final int scrRectTop = (int) (srcHeight - srcRectHeight) / 2;
//				return new Rect(0, scrRectTop, srcWidth, scrRectTop
//						+ srcRectHeight);
//			}
//		} else {
//			return new Rect(0, 0, srcWidth, srcHeight);
//		}
//	}

//	public static Rect calculateDstRect(int srcWidth, int srcHeight,
//                                        int dstWidth, int dstHeight, ScalingLogic scalingLogic) {
//		if (scalingLogic == ScalingLogic.FIT) {
//			final float srcAspect = (float) srcWidth / (float) srcHeight;
//			final float dstAspect = (float) dstWidth / (float) dstHeight;
//			if (srcAspect > dstAspect) {
//				return new Rect(0, 0, dstWidth, (int) (dstWidth / srcAspect));
//			} else {
//				return new Rect(0, 0, (int) (dstHeight * srcAspect), dstHeight);
//			}
//		} else {
//			return new Rect(0, 0, dstWidth, dstHeight);
//		}
//	}
//
//	public static Bitmap createScaledBitmap(Bitmap unscaledBitmap,
//                                            int dstWidth, int dstHeight, ScalingLogic scalingLogic) {
//		Rect srcRect = calculateSrcRect(unscaledBitmap.getWidth(),
//				unscaledBitmap.getHeight(), dstWidth, dstHeight, scalingLogic);
//		Rect dstRect = calculateDstRect(unscaledBitmap.getWidth(),
//				unscaledBitmap.getHeight(), dstWidth, dstHeight, scalingLogic);
//		Bitmap scaledBitmap = Bitmap.createBitmap(dstRect.width(),
//				dstRect.height(), Config.ARGB_8888);
//		Canvas canvas = new Canvas(scaledBitmap);
//		canvas.drawBitmap(unscaledBitmap, srcRect, dstRect, new Paint(
//				Paint.FILTER_BITMAP_FLAG));
//		return scaledBitmap;
//	}
//
//	public static Bitmap decodeFile(String pathName, int dstWidth,
//                                    int dstHeight, ScalingLogic scalingLogic) {
//		Options options = new Options();
//		options.inJustDecodeBounds = true;
//		BitmapFactory.decodeFile(pathName, options);
//		options.inJustDecodeBounds = false;
//		options.inSampleSize = calculateSampleSize(options.outWidth,
//				options.outHeight, dstWidth, dstHeight, scalingLogic);
//		Bitmap unscaledBitmap = BitmapFactory.decodeFile(pathName, options);
//		return unscaledBitmap;
//	}
//
//	public static int calculateSampleSize(int srcWidth, int srcHeight,
//			int dstWidth, int dstHeight, ScalingLogic scalingLogic) {
//		if (scalingLogic == ScalingLogic.FIT) {
//			final float srcAspect = (float) srcWidth / (float) srcHeight;
//			final float dstAspect = (float) dstWidth / (float) dstHeight;
//			if (srcAspect > dstAspect) {
//				return srcWidth / dstWidth;
//			} else {
//				return srcHeight / dstHeight;
//			}
//		} else {
//			final float srcAspect = (float) srcWidth / (float) srcHeight;
//			final float dstAspect = (float) dstWidth / (float) dstHeight;
//			if (srcAspect > dstAspect) {
//				return srcHeight / dstHeight;
//			} else {
//				return srcWidth / dstWidth;
//			}
//		}
//	}
}
