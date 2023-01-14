package a3wt.awt;

import a3wt.graphics.A3Graphics;
import a3wt.graphics.A3Image;

import java.awt.image.BufferedImage;

import static a3wt.util.A3Preconditions.checkArgNotNull;
import static a3wt.util.A3Preconditions.checkArgRangeMin;

public class AWTA3Image implements A3Image {

    protected volatile BufferedImage bufferedImage;
    protected volatile AWTA3Graphics graphics;
    protected volatile boolean disposed = false;

    protected volatile long duration;
    protected volatile int hotSpotX;
    protected volatile int hotSpotY;

    public AWTA3Image(final BufferedImage bufferedImage, final long duration, final int hotSpotX, final int hotSpotY) {
        checkArgNotNull(bufferedImage, "bufferedImage");
        checkArgRangeMin(duration, 0, true, "duration");
        this.bufferedImage = bufferedImage;
        this.duration = duration;
        this.hotSpotX = hotSpotX;
        this.hotSpotY = hotSpotY;
    }

    public AWTA3Image(final BufferedImage bufferedImage) {
        this(bufferedImage, 0, 0, 0);
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    @Override
    public A3Graphics createGraphics() {
        if (graphics == null) graphics = new AWTA3Graphics(bufferedImage);
        return graphics;
    }

    @Override
    public int getType() {
        checkDisposed("Can't call getType() on a disposed A3Image");
        return A3AWTUtils.bufferedImageType2ImageType(bufferedImage.getType());
    }

    @Override
    public int getWidth() {
        checkDisposed("Can't call getWidth() on a disposed A3Image");
        return bufferedImage.getWidth();
    }

    @Override
    public int getHeight() {
        checkDisposed("Can't call getHeight() on a disposed A3Image");
        return bufferedImage.getHeight();
    }

    @Override
    public int getPixel(final int x, final int y) {
        checkDisposed("Can't call getPixel() on a disposed A3Image");
        return bufferedImage.getRGB(x, y);
    }

    @Override
    public A3Image setPixel(final int x, final int y, final int color) {
        checkDisposed("Can't call setPixel() on a disposed A3Image");
        bufferedImage.setRGB(x, y, color);
        return this;
    }

    @Override
    public void getPixels(final int[] pixels, final int offset, final int stride, final int x, final int y, final int width, final int height) {
        checkArgNotNull(pixels, "pixels");
        checkDisposed("Can't call getPixels() on a disposed A3Image");
        bufferedImage.getRGB(x, y, width, height, pixels, offset, stride);
    }

    @Override
    public A3Image setPixels(final int[] pixels, final int offset, final int stride, final int x, final int y, final int width, final int height) {
        checkArgNotNull(pixels, "pixels");
        checkDisposed("Can't call setPixels() on a disposed A3Image");
        bufferedImage.setRGB(x, y, width, height, pixels, offset, stride);
        return this;
    }

    @Override
    public boolean isDisposed() {
        return disposed;
    }

    @Override
    public void dispose() {
        if (isDisposed()) return;
        disposed = true;
        if (graphics != null) {
            graphics.dispose();
            graphics = null;
        }
        bufferedImage.flush();
        bufferedImage = null;
        duration = -1;
    }

    @Override
    public A3Image copy() {
        checkDisposed("Can't call copy() on a disposed A3Image");
        final AWTA3Image copy = new AWTA3Image(A3AWTUtils.copyBufferedImage(bufferedImage));
        copy.duration = duration;
        copy.hotSpotX = hotSpotX;
        copy.hotSpotY = hotSpotY;
        return copy;
    }

    @Override
    public A3Image copy(final int type) {
        checkDisposed("Can't call copy() on a disposed A3Image");
        return new AWTA3Image(A3AWTUtils.getImage(bufferedImage, A3AWTUtils.imageType2BufferedImageType(type)));
    }

    public void setBufferedImage(final BufferedImage bufferedImage) {
        checkArgNotNull(bufferedImage, "bufferedImage");
        checkDisposed("Can't call setBufferedImage() on a disposed AWTA3Image");
        this.bufferedImage = bufferedImage;
        setGraphics(new AWTA3Graphics(this.bufferedImage));
    }

    public void setGraphics(final AWTA3Graphics graphics) {
        checkArgNotNull(graphics, "graphics");
        checkDisposed("Can't call setGraphics() on a disposed AWTA3Image");
        this.graphics.dispose();
        this.graphics = graphics;
    }

    @Override
    public void to(final A3Image dst) {
        checkArgNotNull(dst, "dst");
        checkDisposed("Can't call to() on a disposed A3Image");
        final AWTA3Image dst0 = (AWTA3Image) dst;
        dst0.bufferedImage = A3AWTUtils.copyBufferedImage(bufferedImage);
        if (graphics != null) dst0.createGraphics().setData(graphics.data);
        dst0.duration = duration;
        dst0.hotSpotX = hotSpotX;
        dst0.hotSpotY = hotSpotY;
    }

    @Override
    public void from(final A3Image src) {
        checkArgNotNull(src, "src");
        checkDisposed("Can't call from() on a disposed A3Image");
        src.to(this);
    }

}
