package example.com.testdragsortlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by admin on 2017/7/6.
 */
public class AMDragRateAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<DragResponse> list;//适配器的数据源

    public AMDragRateAdapter(Context context, List<DragResponse> list) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;
    }

    public class ViewHolder {
        public ImageView drag_handle;
        public ImageView click_remove;
        public TextView tv_drag_content;
        public TextView btn_click_remove;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    public void remove(int arg0) {//删除指定位置的item
        list.remove(arg0);
        this.notifyDataSetChanged();//不要忘记更改适配器对象的数据源
    }

    public void insert(DragResponse item, int arg0) {
        //在指定位置插入item
        list.add(arg0, item);
        this.notifyDataSetChanged();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final ViewHolder holder;
        if (convertView == null) {
            view = inflater.inflate(R.layout.adapter_amdragrate, parent,
                    false);
            holder = new ViewHolder();
            holder.tv_drag_content = (TextView) view.findViewById(R.id.tv_drag_content);
            holder.drag_handle = (ImageView) view.findViewById(R.id.drag_handle);
            holder.click_remove = (ImageView) view.findViewById(R.id.click_remove);
//            holder.btn_click_remove = (TextView) view.findViewById(R.id.click_remove);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tv_drag_content.setText(list.get(position).getTv1());
//        Log.i("11111", "----->" + position + "|||" + list.get(position).getTv1());
        return view;
    }
}
