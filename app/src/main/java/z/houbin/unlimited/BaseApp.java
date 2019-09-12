package z.houbin.unlimited;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import z.houbin.xposed.lib.Util;

public abstract class BaseApp {
    protected XC_LoadPackage.LoadPackageParam loadPackageParam;
    protected Activity focusActivity;

    public BaseApp(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        this.loadPackageParam = loadPackageParam;
        onHook();
    }

    protected void onHook(){
        XposedHelpers.findAndHookMethod(Application.class, "attach", Context.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                Application context = (Application) param.thisObject;
                context.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
                    @Override
                    public void onActivityCreated(Activity activity, Bundle bundle) {

                    }

                    @Override
                    public void onActivityStarted(Activity activity) {

                    }

                    @Override
                    public void onActivityResumed(Activity activity) {
                        if (focusActivity == null) {
                            focusActivity = activity;
                            Util.showVersion(activity);
                        } else {
                            focusActivity = activity;
                        }
                    }

                    @Override
                    public void onActivityPaused(Activity activity) {

                    }

                    @Override
                    public void onActivityStopped(Activity activity) {

                    }

                    @Override
                    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

                    }

                    @Override
                    public void onActivityDestroyed(Activity activity) {

                    }
                });
            }
        });
    };
}
