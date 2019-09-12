package z.houbin.unlimited;

import java.util.ArrayList;
import java.util.List;

import z.houbin.unlimited.app.QiaoYing;
import z.houbin.unlimited.app.Usageanalyzer;
import z.houbin.xposed.lib.hot.BaseHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage;


public class MainHook extends BaseHook {
    private static final String LOCALE_PACKAGE = "z.houbin.unlimited";
    private static List<String> packageList = new ArrayList<>();


    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        try {
            packageList.clear();
            packageList.add("z.houbin.unlimited");
            packageList.add("com.nexstreaming.app.kinemasterfree");
            packageList.add("info.kfsoft.usageanalyzer");

            if (!packageList.contains(loadPackageParam.packageName)) {
                return;
            }

            startHotXPosed(MainHook.class, loadPackageParam, LOCALE_PACKAGE);
        } catch (Exception e) {
            dispatch(loadPackageParam);
        }
    }

    @Override
    public void dispatch(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        super.dispatch(loadPackageParam);

        switch (loadPackageParam.packageName) {
            case "com.nexstreaming.app.kinemasterfree":
                new QiaoYing(loadPackageParam);
                break;
            case "info.kfsoft.usageanalyzer":
                new Usageanalyzer(loadPackageParam);
                break;
        }
    }
}
