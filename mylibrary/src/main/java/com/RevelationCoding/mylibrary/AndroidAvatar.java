package com.RevelationCoding.mylibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.os.HandlerCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AndroidAvatar extends RelativeLayout {
    String text;
    Drawable imageSrc;
    int backgroundHeight = -1;
    int backgroundWidth = -1;
    int textSize = -1;
    Boolean randomColor = false;
    int backColor;
    int textColor;
    //views
    TextView textView;
    ProgressBar progressBar;
    ImageButton imageView;
    private final Context mContext;
    private AttributeSet attrs;
    private int styleAttr;
    private View view;
    private OnClickListener listener;
    ImageButton LLRipple;

    public AndroidAvatar(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public AndroidAvatar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.attrs = attrs;
        initView();

    }

    public AndroidAvatar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        this.attrs = attrs;
        this.styleAttr = defStyleAttr;
        initView();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            if (listener != null) listener.onClick(this);
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_UP && (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_CENTER || event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
            if (listener != null) listener.onClick(this);
        }
        return super.dispatchKeyEvent(event);
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    //    public AndroidAvatar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }
    private void initView() {
        this.view = this;
        //Inflating the XML view
        inflate(mContext, R.layout.avatar_layout, this);

        TypedArray arr = mContext.obtainStyledAttributes(attrs, R.styleable.AndroidAvatar,
                styleAttr, 0);

        text = arr.getString(R.styleable.AndroidAvatar_text);
        imageSrc = arr.getDrawable(R.styleable.AndroidAvatar_imageSrc);
        backgroundHeight = arr.getInteger(R.styleable.AndroidAvatar_backgroundHeight, 180);
        backgroundWidth = arr.getInteger(R.styleable.AndroidAvatar_backgroundWidth, 180);
        textSize = arr.getInteger(R.styleable.AndroidAvatar_textSize, 34);
        randomColor = arr.getBoolean(R.styleable.AndroidAvatar_randomColor, false);
        backColor = arr.getColor(R.styleable.AndroidAvatar_backgroundColor, Color.rgb(0, 0, 0));
        textColor = arr.getColor(R.styleable.AndroidAvatar_textColor, Color.rgb(255, 255, 255));
        //components
        imageView = (ImageButton) findViewById(R.id.avatarBack);
        textView = (TextView) findViewById(R.id.textDisplay);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        LLRipple = (ImageButton) findViewById(R.id.LLRipple);

        ViewGroup.LayoutParams LLParams = LLRipple.getLayoutParams();

        if (text != null) {
            char nS = text.charAt(0);
            setChar(nS);
        }
        if (imageSrc != null) {
            setImageSrc(imageSrc, R.drawable.ic_error, ImageView.ScaleType.CENTER_CROP);
        }

        //set ripple length here
        if (backgroundHeight > 0) {
            LLParams.width = backgroundWidth-20;
            LLParams.height = backgroundHeight-20;
            setBackgroundHeight(backgroundHeight);
        }
        if (backgroundWidth > 0) {
            LLParams.width = backgroundWidth-20;
            LLParams.height = backgroundHeight-20;
            setBackgroundWidth(backgroundWidth);
        }
        if (textSize > 0) {
            setTextSize(textSize);
        }

        if (randomColor != false) {
            setRandomColor();
        } else {
            setBackColor(backColor);
            setTextColor(textColor);
        }
        arr.recycle();
    }

    /**
     * @param  color an integer color
     */
    public void setBackColor(int color) {
        imageView.setImageBitmap(getRoundedShape(color));
    }

    /**
     * @param  color an integer color
     */
    public void setTextColor(int color) {
        textView.setTextColor(color);
    }

    /**
     * @param  c a single character
     */
    public void setChar(char c) {
        textView.setText(String.valueOf(c));
    }

    /**
     * @param  size size of textview measured in DP accepts int
     */
    public void setTextSize(int size) {
        textView.setTextSize(size);
    }


    public void setRandomColor() {
        Random r = new Random();
        int[] colorList = getResources().getIntArray(R.array.materialColor);
//        int R = r.nextInt(70);
//        int G = r.nextInt(70);
//        int B = r.nextInt(70);
//        int color = Color.argb(100, R, G, B);

        imageView.setImageBitmap(getRoundedShape(colorList[r.nextInt(colorList.length)]));
        textView.setTextColor(Color.rgb(255, 255, 255));
    }


    /**
     * @param  height height of avatar
     */
    public void setBackgroundHeight(int height) {
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        params.height = height;
        imageView.setLayoutParams(params);
    }

    /**
     * @param  width height of avatar
     */
    public void setBackgroundWidth(int width) {
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        params.width = width;
        imageView.setLayoutParams(params);
    }

    /**
     * @param  string an string, accepts username and sets first word to textview
     */
    public void setText(String string) {
        char nS = string.charAt(0);
        textView.setText(nS);
    }


    /**
     * @param  imageFile a Drawable/Local JPEG,PNG..
     * @param  imageError Drawable Resource XML invoked on error
     * @param  scaleType used for scaling the bounds of an image to the bounds of the image view
     */
    public void setImageSrc(Drawable imageFile, int imageError, ImageView.ScaleType scaleType) {
        imageView.setScaleType(scaleType);
        Glide.with(mContext)
                .load(imageFile)
                .circleCrop()
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(imageError)
                .into(imageView);
    }
    
    public void setImageBitmap(Bitmap imageFile, int imageError, ImageView.ScaleType scaleType) {
        imageView.setScaleType(scaleType);
        Glide.with(mContext)
                .load(imageFile)
                .circleCrop()
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(imageError)
                .into(imageView);
    }

    /**
     * @param  imageUrl an absolute URL giving the base location of the image
     * @param  imageError Drawable Resource XML invoked on error
     * @param  scaleType used for scaling the bounds of an image to the bounds of the image view
     */
    public void setImageUrl(String imageUrl, int imageError, ImageView.ScaleType scaleType) {
        imageView.setScaleType(scaleType);
        Glide.with(mContext).load(imageUrl).circleCrop().listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }
        }).transition(DrawableTransitionOptions.withCrossFade()).dontAnimate().error(imageError)
                .into(imageView);
    }

    private Bitmap getRoundedShape(int color) {
        Bitmap bmp = Bitmap.createBitmap(backgroundWidth, backgroundHeight, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);
        c.drawColor(color);
        Bitmap scaleBitmapImage = bmp;
        int targetWidth = backgroundWidth;
        int targetHeight = backgroundHeight;
        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
                targetHeight, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(targetBitmap);
        Path path = new Path();
        path.addCircle(((float) targetWidth - 1) / 2,
                ((float) targetHeight - 1) / 2,
                (Math.min(((float) targetWidth),
                        ((float) targetHeight)) / 2),
                Path.Direction.CCW);

        canvas.clipPath(path);
        Bitmap sourceBitmap = scaleBitmapImage;
        canvas.drawBitmap(sourceBitmap,
                new Rect(0, 0, sourceBitmap.getWidth(),
                        sourceBitmap.getHeight()),
                new Rect(0, 0, targetWidth,
                        targetHeight), null);
        return targetBitmap;

    }
}
