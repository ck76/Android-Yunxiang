package com.yunxiang.android.product.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunxiang.android.R;


public class SimpleItemView extends FrameLayout {
    private static final int EDIT = 0;
    private static final int BUTTON = 1;
    private static final int NUMBER_DECIMAL = 2;
    private static final int NUMBER = 3;
    /**
     * 小数的位数
     **/
    private static final int DECIMAL_DIGITS = 2;
    /**
     * edittext输入模式(数字)
     **/
    private static final int TYPE_NUMBER_FLAG_DECIMAL = 8194;
    private String mTitle = "";
    private String mHint = "";
    private boolean hasIcon;
    private int mType;
    private TextView mTitleTv;
    private ImageView mIconImg;
    private TextView mContentTxt;
    private EditText mContentEdit;
    private OnIconClickListener mOnIconClickListener;

    public SimpleItemView(@NonNull Context context) {
        this(context, null);
    }

    public SimpleItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.product_publish_simple_item, this);

        mTitleTv = findViewById(R.id.tv_product_publish_item_title);
        mIconImg = findViewById(R.id.img_product_publish_item_down);
        mContentEdit = findViewById(R.id.edit_product_publish_item_content);
        mContentTxt = findViewById(R.id.btn_product_publish_item);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SimpleItemView);
        mTitle = array.getString(R.styleable.SimpleItemView_simple_title);
        mHint = array.getString(R.styleable.SimpleItemView_simple_hint);
        hasIcon = array.getBoolean(R.styleable.SimpleItemView_hasIcon, false);
        mType = array.getInt(R.styleable.SimpleItemView_simple_type, 0);
        array.recycle();
        initIcon();
        initTextView();
        initContent();
    }

    private void initContent() {
        mTitleTv.setText(mTitle);
        switch (mType) {
            case EDIT:
                mContentEdit.setVisibility(VISIBLE);
                mContentTxt.setVisibility(GONE);
                mContentEdit.setHint(mHint);
                break;
            case BUTTON:
                mContentEdit.setVisibility(GONE);
                mContentTxt.setVisibility(VISIBLE);
                mContentTxt.setText(mHint);
                break;
            case NUMBER_DECIMAL:
                mContentEdit.setVisibility(VISIBLE);
                mContentTxt.setVisibility(GONE);
                mContentEdit.setHint(mHint);
                mContentEdit.setInputType(TYPE_NUMBER_FLAG_DECIMAL);
                setDecimalPoint(mContentEdit);
                break;
            case NUMBER:
                mContentEdit.setVisibility(VISIBLE);
                mContentTxt.setVisibility(GONE);
                mContentEdit.setHint(mHint);
                mContentEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            default:
                break;
        }
    }

    private void initIcon() {
        if (hasIcon) {
            mIconImg.setVisibility(VISIBLE);
        } else {
            mIconImg.setVisibility(GONE);
        }
        mIconImg.setOnClickListener(v -> {
            if (mOnIconClickListener != null) {
                mOnIconClickListener.onClick();
            }
        });
    }

    private void initTextView(){
        mContentTxt.setOnClickListener(v -> {
            if (mOnIconClickListener != null) {
                mOnIconClickListener.onClick();
            }
        });
    }

    public String getContent() {
        String ret = "";
        ret = mContentEdit.getText().toString();
        return ret;
    }

    public void setContent(String content) {
        if (TextUtils.isEmpty(content)) {
            return;
        }
        switch (mType) {
            case EDIT:
                mContentEdit.setText(content);
                break;
            case BUTTON:
                mContentTxt.setText(content);
                break;
            default:
                break;
        }
    }

    /**
     * 设置显示小数点后两位
     */
    public void setDecimalPoint(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > DECIMAL_DIGITS) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + DECIMAL_DIGITS + 1);
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }
                }
                if (s.toString().trim().equals(".")) {
                    s = "0" + s;
                    editText.setText(s);
                    editText.setSelection(2);
                }
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        editText.setText(s.subSequence(0, 1));
                        editText.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    /**
     * 设置Icon点击事件监听
     */
    public void setOnIconClickListener(OnIconClickListener onIconClickListener) {
        mOnIconClickListener = onIconClickListener;
    }

    public interface OnIconClickListener {
        void onClick();
    }
}
