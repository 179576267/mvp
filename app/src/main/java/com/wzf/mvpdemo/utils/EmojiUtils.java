package com.wzf.mvpdemo.utils;

import android.content.Context;
import android.net.Uri;
import android.text.Spannable;
import android.text.style.ImageSpan;

import com.wzf.mvpdemo.R;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-06-08 16:18
 */

public class EmojiUtils {
    public static final String ee_1 = "[):]";
    public static final String ee_2 = "[:D]";
    public static final String ee_3 = "[;)]";
    public static final String ee_4 = "[:-o]";
    public static final String ee_5 = "[:p]";
    public static final String ee_6 = "[(H)]";
    public static final String ee_7 = "[:@]";
    public static final String ee_8 = "[:s]";
    public static final String ee_9 = "[:$]";
    public static final String ee_10 = "[:(]";
    public static final String ee_11 = "[:'(]";
    public static final String ee_12 = "[:|]";
    public static final String ee_13 = "[(a)]";
    public static final String ee_14 = "[8o|]";
    public static final String ee_15 = "[8-|]";
    public static final String ee_16 = "[+o(]";
    public static final String ee_17 = "[<o)]";
    public static final String ee_18 = "[|-)]";
    public static final String ee_19 = "[*-)]";
    public static final String ee_20 = "[:-#]";
    public static final String ee_21 = "[:-*]";
    public static final String ee_22 = "[^o)]";
    public static final String ee_23 = "[8-)]";
    public static final String ee_24 = "[(|)]";
    public static final String ee_25 = "[(u)]";
    public static final String ee_26 = "[(S)]";
    public static final String ee_27 = "[(*)]";
    public static final String ee_28 = "[(#)]";
    public static final String ee_29 = "[(R)]";
    public static final String ee_30 = "[({)]";
    public static final String ee_31 = "[(})]";
    public static final String ee_32 = "[(k)]";
    public static final String ee_33 = "[(F)]";
    public static final String ee_34 = "[(W)]";
    public static final String ee_35 = "[(D)]";

    private static String[] emojis = new String[]{
            EmojiUtils.ee_1,
            EmojiUtils.ee_2,
            EmojiUtils.ee_3,
            EmojiUtils.ee_4,
            EmojiUtils.ee_5,
            EmojiUtils.ee_6,
            EmojiUtils.ee_7,
            EmojiUtils.ee_8,
            EmojiUtils.ee_9,
            EmojiUtils.ee_10,
            EmojiUtils.ee_11,
            EmojiUtils.ee_12,
            EmojiUtils.ee_13,
            EmojiUtils.ee_14,
            EmojiUtils.ee_15,
            EmojiUtils.ee_16,
            EmojiUtils.ee_17,
            EmojiUtils.ee_18,
            EmojiUtils.ee_19,
            EmojiUtils.ee_20,
            EmojiUtils.ee_21,
            EmojiUtils.ee_22,
            EmojiUtils.ee_23,
            EmojiUtils.ee_24,
            EmojiUtils.ee_25,
            EmojiUtils.ee_26,
            EmojiUtils.ee_27,
            EmojiUtils.ee_28,
            EmojiUtils.ee_29,
            EmojiUtils.ee_30,
            EmojiUtils.ee_31,
            EmojiUtils.ee_32,
            EmojiUtils.ee_33,
            EmojiUtils.ee_34,
            EmojiUtils.ee_35,

    };

    private static int[] icons = new int[]{
            R.drawable.ee_1,
            R.drawable.ee_2,
            R.drawable.ee_3,
            R.drawable.ee_4,
            R.drawable.ee_5,
            R.drawable.ee_6,
            R.drawable.ee_7,
            R.drawable.ee_8,
            R.drawable.ee_9,
            R.drawable.ee_10,
            R.drawable.ee_11,
            R.drawable.ee_12,
            R.drawable.ee_13,
            R.drawable.ee_14,
            R.drawable.ee_15,
            R.drawable.ee_16,
            R.drawable.ee_17,
            R.drawable.ee_18,
            R.drawable.ee_19,
            R.drawable.ee_20,
            R.drawable.ee_21,
            R.drawable.ee_22,
            R.drawable.ee_23,
            R.drawable.ee_24,
            R.drawable.ee_25,
            R.drawable.ee_26,
            R.drawable.ee_27,
            R.drawable.ee_28,
            R.drawable.ee_29,
            R.drawable.ee_30,
            R.drawable.ee_31,
            R.drawable.ee_32,
            R.drawable.ee_33,
            R.drawable.ee_34,
            R.drawable.ee_35,
    };
    private static final Spannable.Factory spannableFactory = Spannable.Factory
            .getInstance();
    private static final Map<Pattern, Object> emoticons = new HashMap<Pattern, Object>();
    static {
        for(int i = 0; i < icons.length; i++){
            addPattern(emojis[i], icons[i]);
        }
    }
    public static void addPattern(String emojiText, Object icon){
        emoticons.put(Pattern.compile(Pattern.quote(emojiText)), icon);
    }

    public static Spannable getSmiledText(Context context, CharSequence text) {
        Spannable spannable = spannableFactory.newSpannable(text);
        addSmiles(context, spannable);
        return spannable;
    }


    /**
     * replace existing spannable with smiles
     * @param context
     * @param spannable
     * @return
     */
    public static boolean addSmiles(Context context, Spannable spannable) {
        boolean hasChanges = false;
        for (Map.Entry<Pattern, Object> entry : emoticons.entrySet()) {
            Matcher matcher = entry.getKey().matcher(spannable);
            while (matcher.find()) {
                boolean set = true;
                for (ImageSpan span : spannable.getSpans(matcher.start(),
                        matcher.end(), ImageSpan.class))
                    if (spannable.getSpanStart(span) >= matcher.start()
                            && spannable.getSpanEnd(span) <= matcher.end())
                        spannable.removeSpan(span);
                    else {
                        set = false;
                        break;
                    }
                if (set) {
                    hasChanges = true;
                    Object value = entry.getValue();
                    if(value instanceof String && !((String) value).startsWith("http")){
                        File file = new File((String) value);
                        if(!file.exists() || file.isDirectory()){
                            return false;
                        }
                        spannable.setSpan(new ImageSpan(context, Uri.fromFile(file)),
                                matcher.start(), matcher.end(),
                                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }else{
                        spannable.setSpan(new ImageSpan(context, (Integer)value),
                                matcher.start(), matcher.end(),
                                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }
            }
        }

        return hasChanges;
    }
}
