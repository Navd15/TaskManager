package brewcrew.com.taskmanager.UIComponents;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import brewcrew.com.taskmanager.R;

/**
 * Created by navdeep on 24/2/18.
 */

public class UserGuideView extends View {

    private static final String TAG = "UserGuideView";

    private Paint textPaint, circlePaint;

    private String mText;

    private int mTextSize;

    private Path path;

    private int viewHeight;

    private int viewWidth;

    private Region region;

    public UserGuideView(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.UserGuideView, 0, 0);
        try {
            mText = a.getString(R.styleable.UserGuideView_text);
            mTextSize = a.getDimensionPixelSize(R.styleable.UserGuideView_textSize, 12);
        } finally {
            a.recycle();
        }

        init();

    }

    /*

    Overided methods


    */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        super.onSizeChanged(w, h, oldw, oldh);
        viewHeight = h;
        viewWidth = w;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        //canvas.clipRect(0,0,90,90);

        clipCircle(canvas);
        canvas.drawColor(getResources().getColor(R.color.clipPathColor));
        canvas.drawText("Toogle it to get Notified", viewWidth / 2, viewHeight / 2, textPaint);

    }

    /*

    helper methods

    */
    private int calculateHeight(int height) {

        return 0;
    }

    private int calculateWidth(int width) {

        return 0;
    }

    private void init() {
        //rect paint
        textPaint = new Paint(Paint.LINEAR_TEXT_FLAG);
        textPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(80);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setFakeBoldText(true);

//circle paint

        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(Color.BLACK);

        //path
        path = new Path();

        path.addCircle(120, 210, 100, Path.Direction.CW);
        path.setFillType(Path.FillType.EVEN_ODD);

    }

    //clip circle at notificatation area

    private void clipCircle(Canvas canvas) {

        canvas.clipPath(path, Region.Op.DIFFERENCE);

    }
}
