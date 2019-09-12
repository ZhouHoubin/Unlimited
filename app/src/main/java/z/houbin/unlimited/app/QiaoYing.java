package z.houbin.unlimited.app;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import z.houbin.unlimited.BaseApp;
import z.houbin.xposed.lib.log.Logs;

//巧影（com.nexstreaming.app.kinemasterfree）（4.11.13.14060.CZ）
public class QiaoYing extends BaseApp {
    public QiaoYing(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        super(loadPackageParam);
    }

    @Override
    protected void onHook() {
        super.onHook();
        try {
            Class abManager = XposedHelpers.findClass("com.nexstreaming.app.general.iab.IABManager",loadPackageParam.classLoader);
            XposedHelpers.findAndHookMethod(abManager, "K", XC_MethodReplacement.returnConstant(true));
            XposedHelpers.findAndHookMethod(abManager, "M", XC_MethodReplacement.returnConstant(true));
            XposedHelpers.findAndHookMethod(abManager, "N", XC_MethodReplacement.returnConstant(true));
        } catch (Exception e) {
            Logs.e(e);
        }
    }
}
