//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package com.socket9.pointube.customviews;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;

import com.github.glomadrian.codeinputlib.R.color;
import com.github.glomadrian.codeinputlib.R.dimen;
import com.github.glomadrian.codeinputlib.R.integer;
import com.github.glomadrian.codeinputlib.R.styleable;
import com.github.glomadrian.codeinputlib.data.FixedStack;
import com.github.glomadrian.codeinputlib.model.Underline;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeInput extends View {
    private static final int DEFAULT_CODES = 6;
    private static final Pattern KEYCODE_PATTERN = Pattern.compile("KEYCODE_(\\w)");
    private static final List<Integer> phoneList = Arrays.asList(7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 67);
    private FixedStack<Character> characters;
    private Underline[] underlines;
    private Paint underlinePaint;
    private Paint underlineSelectedPaint;
    private Paint textPaint;
    private Paint hintPaint;
    private ValueAnimator reductionAnimator;
    private ValueAnimator hintYAnimator;
    private ValueAnimator hintSizeAnimator;
    private float underlineReduction;
    private float underlineStrokeWidth;
    private float underlineWidth;
    private float reduction;
    private float textSize;
    private float textMarginBottom;
    private float hintX;
    private float hintNormalSize;
    private float hintSmallSize;
    private float hintMarginBottom;
    private float hintActualMarginBottom;
    private float viewHeight;
    private long animationDuration;
    private int height;
    private int underlineAmount;
    private int underlineColor;
    private int underlineSelectedColor;
    private int hintColor;
    private int textColor;
    private boolean underlined = true;
    private String hintText;

    public CodeInput(Context context) {
        super(context);
        this.init((AttributeSet) null);
    }

    public CodeInput(Context context, AttributeSet attributeset) {
        super(context, attributeset);
        this.init(attributeset);
    }

    public CodeInput(Context context, AttributeSet attributeset, int defStyledAttrs) {
        super(context, attributeset, defStyledAttrs);
        this.init(attributeset);
    }

    private void init(AttributeSet attributeset) {
        this.initDefaultAttributes();
        this.initCustomAttributes(attributeset);
        this.initDataStructures();
        this.initPaint();
        this.initAnimator();
        this.initViewOptions();
        this.startAnimation();
    }

    private void initDefaultAttributes() {
        this.underlineStrokeWidth = this.getContext().getResources().getDimension(dimen.underline_stroke_width);
        this.underlineWidth = this.getContext().getResources().getDimension(dimen.underline_width);
        this.underlineReduction = this.getContext().getResources().getDimension(dimen.section_reduction);
        this.textSize = this.getContext().getResources().getDimension(dimen.text_size);
        this.textMarginBottom = this.getContext().getResources().getDimension(dimen.text_margin_bottom);
        this.underlineColor = this.getContext().getResources().getColor(color.underline_default_color);
        this.underlineSelectedColor = this.getContext().getResources().getColor(color.underline_selected_color);
        this.hintColor = this.getContext().getResources().getColor(color.hintColor);
        this.textColor = this.getContext().getResources().getColor(color.textColor);
        this.hintMarginBottom = this.getContext().getResources().getDimension(dimen.hint_margin_bottom);
        this.hintNormalSize = this.getContext().getResources().getDimension(dimen.hint_size);
        this.hintSmallSize = this.getContext().getResources().getDimension(dimen.hint_small_size);
        this.animationDuration = (long) this.getContext().getResources().getInteger(integer.animation_duration);
        this.viewHeight = this.getContext().getResources().getDimension(dimen.view_height);
        this.hintX = 0.0F;
        this.hintActualMarginBottom = 0.0F;
        this.underlineAmount = 6;
        this.reduction = 0.0F;
    }

    private void initCustomAttributes(AttributeSet attributeset) {
        TypedArray attributes = this.getContext().obtainStyledAttributes(attributeset, styleable.core_area);
        this.underlineColor = attributes.getColor(styleable.core_area_underline_color, this.underlineColor);
        this.underlineSelectedColor = attributes.getColor(styleable.core_area_underline_selected_color, this.underlineSelectedColor);
        this.hintColor = attributes.getColor(styleable.core_area_underline_color, this.hintColor);
        this.hintText = attributes.getString(styleable.core_area_hint_text);
        this.underlineAmount = attributes.getInt(styleable.core_area_codes, this.underlineAmount);
        this.textColor = attributes.getInt(styleable.core_area_text_color, this.textColor);
    }

    private void initDataStructures() {
        this.underlines = new Underline[this.underlineAmount];
        this.characters = new FixedStack();
        this.characters.setMaxSize(this.underlineAmount);
    }

    private void initPaint() {
        this.underlinePaint = new Paint();
        this.underlinePaint.setColor(this.underlineColor);
        this.underlinePaint.setStrokeWidth(this.underlineStrokeWidth);
        this.underlinePaint.setStyle(Style.STROKE);
        this.underlineSelectedPaint = new Paint();
        this.underlineSelectedPaint.setColor(this.underlineSelectedColor);
        this.underlineSelectedPaint.setStrokeWidth(this.underlineStrokeWidth);
        this.underlineSelectedPaint.setStyle(Style.STROKE);
        this.textPaint = new Paint();
        this.textPaint.setTextSize(this.textSize);
        this.textPaint.setColor(this.textColor);
        this.textPaint.setAntiAlias(true);
        this.textPaint.setTextAlign(Align.CENTER);
        this.hintPaint = new Paint();
        this.hintPaint = new Paint();
        this.hintPaint.setTextSize(this.hintNormalSize);
        this.hintPaint.setAntiAlias(true);
        this.hintPaint.setColor(this.underlineColor);
    }

    private void initAnimator() {
        this.reductionAnimator = ValueAnimator.ofFloat(new float[]{0.0F, this.underlineReduction});
        this.reductionAnimator.setDuration(this.animationDuration);
        this.reductionAnimator.addUpdateListener(new CodeInput.ReductionAnimatorListener());
        this.reductionAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        this.hintSizeAnimator = ValueAnimator.ofFloat(new float[]{this.hintNormalSize, this.hintSmallSize});
        this.hintSizeAnimator.setDuration(this.animationDuration);
        this.hintSizeAnimator.addUpdateListener(new CodeInput.HintSizeAnimatorListener());
        this.hintSizeAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        this.hintYAnimator = ValueAnimator.ofFloat(new float[]{0.0F, this.hintMarginBottom});
        this.hintYAnimator.setDuration(this.animationDuration);
        this.hintYAnimator.addUpdateListener(new CodeInput.HintYAnimatorListener());
        this.hintYAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
    }

    private void initViewOptions() {
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
    }

    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        if (!gainFocus && this.characters.size() == 0) {
            this.reverseAnimation();
        }

    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, (int) this.viewHeight, oldw, oldh);
        this.height = h;
        this.initUnderline();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.setMeasuredDimension(this.getMeasuredWidth(), (int) this.viewHeight);
    }

    private void initUnderline() {
        for (int i = 0; i < this.underlineAmount; ++i) {
            this.underlines[i] = this.createPath(i, this.underlineWidth);
        }

    }

    private Underline createPath(int position, float sectionWidth) {
        float fromX = sectionWidth * (float) position;
        return new Underline(fromX, (float) this.height, fromX + sectionWidth, (float) this.height);
    }

    private void showKeyboard() {
        InputMethodManager inputmethodmanager = (InputMethodManager) this.getContext().getSystemService("input_method");
        inputmethodmanager.showSoftInput(this, 0);
        inputmethodmanager.viewClicked(this);
    }

//    @Override
//    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
//        InputConnection connection = super.onCreateInputConnection(outAttrs);
//        outAttrs.inputType |= InputType.TYPE_CLASS_NUMBER;
//        return connection;
//    }


    private void startAnimation() {
        this.reductionAnimator.start();
        this.hintSizeAnimator.start();
        this.hintYAnimator.start();
        this.underlined = false;
    }

    private void reverseAnimation() {
        this.reductionAnimator.reverse();
        this.hintSizeAnimator.reverse();
        this.hintYAnimator.reverse();
        this.underlined = true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent keyevent) {
        Log.d("KeyDown", keyCode+"");
        if ((keyCode == 67) && this.characters.size() != 0) {
            this.characters.pop();
        }

        return super.onKeyDown(keyCode, keyevent);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent keyevent) {
        Log.d("KeyUp", keyCode + "");
        String text = KeyEvent.keyCodeToString(keyCode);
        Matcher matcher = KEYCODE_PATTERN.matcher(text);
        if (matcher.matches() && phoneList.contains(keyCode)) {
            char character = matcher.group(1).charAt(0);
            this.characters.push(Character.valueOf(character));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionevent) {
        if (motionevent.getAction() == 0) {
            this.requestFocus();
            if (this.underlined) {
                this.startAnimation();
            }

            this.showKeyboard();
        }

        return super.onTouchEvent(motionevent);
    }

    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < this.underlines.length; ++i) {
            Underline sectionpath = this.underlines[i];
            float fromX = sectionpath.getFromX() + this.reduction;
            float fromY = sectionpath.getFromY();
            float toX = sectionpath.getToX() - this.reduction;
            float toY = sectionpath.getToY();
            this.drawSection(i, fromX, fromY, toX, toY, canvas);
            if (this.characters.toArray().length > i && this.characters.size() != 0) {
                this.drawCharacter(fromX, toX, (Character) this.characters.get(i), canvas);
            }
        }

        if (this.hintText != null) {
            this.drawHint(canvas);
        }

        this.invalidate();
    }

    private void drawSection(int position, float fromX, float fromY, float toX, float toY, Canvas canvas) {
        Paint paint = this.underlinePaint;
        if (position == this.characters.size() && !this.underlined) {
            paint = this.underlineSelectedPaint;
        }

        canvas.drawLine(fromX, fromY, toX, toY, paint);
    }

    private void drawCharacter(float fromX, float toX, Character character, Canvas canvas) {
        float actualWidth = toX - fromX;
        float centerWidth = actualWidth / 2.0F;
        float centerX = fromX + centerWidth;
        canvas.drawText(character.toString(), centerX, (float) this.height - this.textMarginBottom, this.textPaint);
    }

    private void drawHint(Canvas canvas) {
        canvas.drawText(this.hintText, this.hintX, (float) this.height - this.textMarginBottom - this.hintActualMarginBottom, this.hintPaint);
    }

    public Character[] getCode() {
        return (Character[]) this.characters.toArray(new Character[this.underlineAmount]);
    }

    private class HintSizeAnimatorListener implements AnimatorUpdateListener {
        private HintSizeAnimatorListener() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            float size = ((Float) animation.getAnimatedValue()).floatValue();
            CodeInput.this.hintPaint.setTextSize(size);
        }
    }

    private class HintYAnimatorListener implements AnimatorUpdateListener {
        private HintYAnimatorListener() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            CodeInput.this.hintActualMarginBottom = ((Float) animation.getAnimatedValue()).floatValue();
        }
    }

    private class ReductionAnimatorListener implements AnimatorUpdateListener {
        private ReductionAnimatorListener() {
        }

        public void onAnimationUpdate(ValueAnimator valueanimator) {
            float value = ((Float) valueanimator.getAnimatedValue()).floatValue();
            CodeInput.this.reduction = value;
        }
    }
}
