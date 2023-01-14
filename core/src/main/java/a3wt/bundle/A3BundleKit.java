package a3wt.bundle;

import a3wt.util.A3Callable;

import java.util.Map;

public interface A3BundleKit {

    default A3MapBundle createMapBundle() {
        return new DefaultA3MapBundle();
    }

    default A3MapBundle createMapBundle(final boolean concurrent) {
        return concurrent ? new DefaultConcurrentA3MapBundle() : new DefaultA3MapBundle();
    }

    default A3MapBundle createConcurrentMapBundle() {
        return new DefaultConcurrentA3MapBundle();
    }

    default A3SecMapBundle createSecMapBundle() {
        return new DefaultA3SecMapBundle();
    }

    default A3SecMapBundle createSecMapBundle(final boolean concurrent) {
        return concurrent ? new DefaultConcurrentA3SecMapBundle() : new DefaultA3SecMapBundle();
    }

    default A3SecMapBundle createConcurrentSecMapBundle() {
        return new DefaultConcurrentA3SecMapBundle();
    }

    default A3ExtMapBundle createExtMapBundle() {
        return new DefaultA3ExtMapBundle(this);
    }

    default A3ExtMapBundle createExtMapBundle(final boolean concurrent) {
        return concurrent ? new DefaultConcurrentA3ExtMapBundle(this) : new DefaultA3ExtMapBundle(this);
    }

    default A3ExtMapBundle createConcurrentExtMapBundle() {
        return new DefaultConcurrentA3ExtMapBundle(this);
    }

    Map<Class<? extends A3ExtMapBundle.Delegate>, A3Callable<? extends A3ExtMapBundle.Delegate>> getExtMapBundleDelegateMappings();
    A3Callable<? extends A3ExtMapBundle.Delegate> putExtMapBundleDelegateMapping(final Class<? extends A3ExtMapBundle.Delegate> typeClass, final A3Callable<? extends A3ExtMapBundle.Delegate> mapping);
    A3Callable<? extends A3ExtMapBundle.Delegate> removeExtMapBundleDelegateMapping(final Class<? extends A3ExtMapBundle.Delegate> typeClass);
    A3Callable<? extends A3ExtMapBundle.Delegate> getExtMapBundleDelegateMapping(final Class<? extends A3ExtMapBundle.Delegate> typeClass);
    void clearExtMapBundleMappings();

    A3ExtMapBundle.Delegate createExtMapBundleDelegate(final Class<? extends A3ExtMapBundle.Delegate> typeClass);

}
