package cn.yuan.demo.viewdemo.bezier;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

/**
 * 描述：采用贝塞尔曲线实现 自定义View
 *
 * @author yuanye
 * @date 2019/9/6 11:58
 */
public class WaveView extends View {

    /**
     * 画笔
     */
    private Paint paint;

    /**
     * 曲线路径
     */
    private Path path1;

    private Path path2;

    /**
     * 背景颜色
     */
    private static int backgroundColor = Color.parseColor("#B8EAFC");
    /**
     * 路径一颜色
     */
    private static int path1Color = Color.parseColor("#4F9DF1");
    /**
     * 路径二颜色
     */
    private static int path2Color = Color.parseColor("#7AC1FB");

    /**
     * 每次移动的像素
     */
    private int path1StepDx, path2StepDx = 0;
    /**
     * 圆半径
     */
    private int mRadius = 200;

    /**
     * 波浪宽度
     */
    private int horizontalSpace = mRadius / 5 * 4;

    /**
     * 波浪高度
     */
    private int verticalSpace = mRadius / 5;

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);

        path1 = new Path();
        path2 = new Path();
        /*关闭硬件加速*/
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        startPath1Anim();
        startPath2Anim();
    }

    public WaveView(Context context) {
        this(context, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //当设置宽度wrap_content是，设置宽度为match_parent
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        //当设置高度为wrap_content时设置默认高度50dp
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (heightSpecMode == MeasureSpec.AT_MOST) {
            heightSpecSize = 200;
        }
        if (widthSpecMode == MeasureSpec.AT_MOST) {
            heightSpecSize = 200;
        }
        mRadius = widthSpecSize > heightSpecSize ? heightSpecSize : widthSpecSize;
        super.onMeasure(mRadius, mRadius);
        setMeasuredDimension(mRadius, mRadius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);
        //绘制第一层浪
        getWavePath(canvas, horizontalSpace * 2, mRadius - verticalSpace / 3
                , horizontalSpace, verticalSpace, path2Color, path1StepDx);

        //绘制第二次浪
        getWavePath(canvas, 0, mRadius, horizontalSpace, verticalSpace, path1Color, path2StepDx);
    }


    /**
     * 绘制背景
     */
    private void drawBackground(Canvas canvas) {
        paint.setColor(backgroundColor);
        canvas.drawCircle(mRadius, mRadius, mRadius, paint);
    }

    /**
     * 绘制海浪曲线
     * <p>
     * rQuadTo(float dx1, float dy1, float dx2, float dy2)
     * dx1:控制点X坐标，表示相对上一个终点X坐标的位移坐标，可为负值，正值表示相加，负值表示相减；
     * dy1:控制点Y坐标，相对上一个终点Y坐标的位移坐标。同样可为负值，正值表示相加，负值表示相减；
     * dx2:终点X坐标，同样是一个相对坐标，相对上一个终点X坐标的位移值，可为负值，正值表示相加，负值表示相减；
     * dy2:终点Y坐标，同样是一个相对，相对上一个终点Y坐标的位移值。可为负值，正值表示相加，负值表示相减；
     *
     * @param canvas          画布
     * @param xOffset         起点x偏移量
     * @param yOffset         起点y偏移量
     * @param horizontalSpace 一个二阶贝塞尔函数宽度（海浪一个波宽）
     * @param verticalSpace   一个二阶贝塞尔函数高度（海浪一个波高）
     * @param pathColor       画笔颜色
     */
    private void getWavePath(Canvas canvas, int xOffset, int yOffset,
                             int horizontalSpace, int verticalSpace,
                             int pathColor, int stepDx) {
        PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
        paint.setXfermode(new PorterDuffXfermode(mode));
        paint.setColor(pathColor);

        path1.reset();
        path1.moveTo(0 - stepDx - xOffset, verticalSpace + yOffset);
        for (int i = 0; i < mRadius / horizontalSpace * 2 + 1; i++) {
            path1.rQuadTo(horizontalSpace, -verticalSpace, horizontalSpace * 2, 0);
            path1.rQuadTo(horizontalSpace, verticalSpace, horizontalSpace * 2, 0);
        }
        path1.lineTo(2 * mRadius, 2 * mRadius);
        path1.lineTo(0, 2 * mRadius);
        canvas.drawPath(path1, paint);
        //清除混合模式
        paint.setXfermode(null);
    }

    /**
     * 动画的目的是让波纹移动起来
     * 利用调用在path.moveTo的时候，将起始点向右移动即可实现移动，
     * 而且只要我们移动一个波长的长度，波纹就会重合，就可以实现无限循环了
     */
    public void startPath1Anim() {
        //动画移动的距离 0~mItemWaveLength
        ValueAnimator animator = ValueAnimator.ofInt(0, horizontalSpace * 4);
        //时间
        animator.setDuration(1500);
        //重复次数，这里是无限次
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        //动画刷新监听
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //每次移动的距离
                path1StepDx = (int) animation.getAnimatedValue();
                //刷新View
                postInvalidate();
            }
        });
        animator.start();
    }

    /**
     * 动画的目的是让波纹移动起来
     * 利用调用在path.moveTo的时候，将起始点向右移动即可实现移动，
     * 而且只要我们移动一个波长的长度，波纹就会重合，就可以实现无限循环了
     */
    public void startPath2Anim() {
        //动画移动的距离 0~mItemWaveLength
        ValueAnimator animator = ValueAnimator.ofInt(0, horizontalSpace * 4);
        //时间
        animator.setDuration(1500);
        //重复次数，这里是无限次
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        //动画刷新监听
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //每次移动的距离
                path2StepDx = (int) animation.getAnimatedValue();
                //刷新View
                postInvalidate();
            }
        });
        animator.start();
    }
}
