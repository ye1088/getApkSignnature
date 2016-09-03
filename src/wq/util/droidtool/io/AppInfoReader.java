package wq.util.droidtool.io;

import java.util.ArrayList;
import java.util.List;

import wq.util.droidtool.model.AppInfo;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;

/**
 * read appinfo from system
 * @author appchina
 *
 */
public class AppInfoReader {
	
	public static List<AppInfo> readAppInfo(Context context){
		List<AppInfo> infolist = new ArrayList<AppInfo>();
		List<PackageInfo> applist = context.getPackageManager().getInstalledPackages(0);
		for(PackageInfo pinfo: applist){
			ApplicationInfo ainfo = pinfo.applicationInfo;
			AppInfo appinfo = new AppInfo();
			appinfo.appName =  ainfo.loadLabel(context.getPackageManager()).toString();
			appinfo.packageName = pinfo.packageName;
			appinfo.versionName = pinfo.versionName; 
			appinfo.versionCode = pinfo.versionCode; 
			appinfo.appIcon = pinfo.applicationInfo.loadIcon(context.getPackageManager());
			// 系统应用
//			if((ainfo.flags & ainfo.FLAG_SYSTEM) <= 0){
				infolist.add(appinfo);
//			}
		}
		return infolist;
	}
	
	public static String readSignature(Context context, String pkgName) throws NameNotFoundException{
		Signature sig = context.getApplicationContext().getPackageManager().getPackageInfo(pkgName, 64).signatures[0];
		return sig.toCharsString();
	}

}
