package z.houbin.unlimited.app;

import android.content.Intent;
import android.widget.Toast;

import org.json.JSONObject;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import z.houbin.unlimited.BaseApp;
import z.houbin.xposed.lib.log.Logs;

//用量分析(info.kfsoft.usageanalyzer)(1.0.122)
public class Usageanalyzer extends BaseApp {
    private final String tag = "info.kfsoft.usageanalyzer";

    public Usageanalyzer(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        super(loadPackageParam);
    }

    @Override
    protected void onHook() {
        super.onHook();
        try {
            XposedHelpers.findAndHookMethod("info.kfsoft.usageanalyzer.UpgradeActivity$a", loadPackageParam.classLoader, "a", String.class, new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                    String productId = String.valueOf(methodHookParam.args[0]);
                    Toast.makeText(focusActivity, "购买成功", Toast.LENGTH_SHORT).show();

                    Class au = XposedHelpers.findClass("info.kfsoft.usageanalyzer.au", loadPackageParam.classLoader);
                    if (productId.equals("remove_ad")) {
                        //au.b(this.h).a(true);
                        Object b = XposedHelpers.callStaticMethod(au, "b", focusActivity);
                        XposedHelpers.callMethod(b, "a", true);
                    } else if (productId.equals("all_feature")) {
                        //au.b(this.h).d(true);
                        Object b = XposedHelpers.callStaticMethod(au, "b", focusActivity);
                        XposedHelpers.callMethod(b, "d", true);
                    }

                    Object j = XposedHelpers.getObjectField(focusActivity,"j");
                    XposedHelpers.callMethod(j,"notifyDataSetChanged");
                    return null;
                }
            });
        } catch (Exception e) {
            Logs.e(tag, e);
        }
    }
}
