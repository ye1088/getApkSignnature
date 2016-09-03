package wq.util.droidtool.adapter;

import java.util.List;

import wq.util.droidtool.R;
import wq.util.droidtool.io.AppInfoReader;
import wq.util.droidtool.model.AppInfo;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.AdapterView.OnItemClickListener;

public class AppAdapter extends BaseAdapter {
	
	List<AppInfo> items = null;
	
	Context mContext;
	LayoutInflater mInflater;
	public AppAdapter(Context context){
		mContext = context;
		mInflater = LayoutInflater.from(context);
		
		items = AppInfoReader.readAppInfo(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items == null ?0: items.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		ViewHolder holder = null;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.app_item, null);
			
			holder.ivIcon = (ImageView)convertView.findViewById(R.id.iv_icon);
			holder.tvAppName = (TextView) convertView.findViewById(R.id.tv_appname);
			holder.tvPkgName = (TextView) convertView.findViewById(R.id.tv_pkgname);
			holder.tvVcode = (TextView) convertView.findViewById(R.id.tv_v_code);
			holder.tvVname = (TextView) convertView.findViewById(R.id.tv_v_name);
			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		AppInfo item = (AppInfo)getItem(position);
		holder.ivIcon.setBackgroundDrawable(item.appIcon);
		holder.tvAppName.setText(item.appName);
		holder.tvPkgName.setText(item.packageName);
		holder.tvVcode.setText(item.versionCode+"");
		holder.tvVname.setText(item.versionName);
		
		return convertView;
	}
	
	class ViewHolder{
		ImageView ivIcon;
		TextView tvAppName, tvPkgName, tvVcode, tvVname;
	}
	
	public class AppClickListener implements OnItemClickListener{

		@SuppressLint("NewApi") @Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			AppInfo item = (AppInfo) getItem(position);
			try {
				
				ClipboardManager cmb = (ClipboardManager)mContext.getSystemService(Context.CLIPBOARD_SERVICE);  
				cmb.setPrimaryClip(ClipData.newPlainText(null, AppInfoReader.readSignature(mContext, item.packageName)));  
			} catch (NameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Toast.makeText(mContext, item.packageName +" 签名 已复制到剪切板", Toast.LENGTH_SHORT).show();
		}
		
	}

}
