package com.ansdoship.a3wt.android;

import android.os.Build;
import com.ansdoship.a3wt.A3WT;
import com.ansdoship.a3wt.app.A3Platform;
import com.ansdoship.a3wt.graphics.A3GraphicsKit;
import com.ansdoship.a3wt.util.A3I18NBundle;
import com.ansdoship.a3wt.util.A3Logger;
import com.ansdoship.a3wt.util.CommonA3I18NBundle;

public class AndroidA3Platform implements A3Platform {

    protected static volatile AndroidA3Logger logger = new AndroidA3Logger();
    protected static volatile AndroidA3GraphicsKit graphicsKit = new AndroidA3GraphicsKit();
    protected static volatile CommonA3I18NBundle i18NBundle = new CommonA3I18NBundle();

    public static final String BACKEND_NAME = "Android GUI";

    static {
        if (A3WT.getPlatform() == null) A3WT.setPlatform(new AndroidA3Platform());
    }

    @Override
    public A3Logger getLogger() {
        return logger;
    }

    @Override
    public A3GraphicsKit getGraphicsKit() {
        return graphicsKit;
    }

    @Override
    public String getBackendName() {
        return BACKEND_NAME;
    }

    @Override
    public String getPlatformName() {
        return Build.PRODUCT;
    }

    @Override
    public String getPlatformVersion() {
        return Build.VERSION.RELEASE;
    }

    @Override
    public String getPlatformArch() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return Build.SUPPORTED_ABIS[0];
        }
        else return Build.CPU_ABI;
    }

    @Override
    public A3I18NBundle getI18NBundle() {
        return i18NBundle;
    }

}
