package example.com.testdragsortlistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mobeta.android.dslv.DragSortListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DragSortListView dragSortListView;
    private AMDragRateAdapter adapter;
    private List<DragResponse> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dragSortListView = (DragSortListView) findViewById(R.id.dslvList);
        list = getDateSource();

        //设置监听
        dragSortListView.setRemoveListener(onRemove);
        dragSortListView.setDropListener(onDrop);

        adapter = new AMDragRateAdapter(MainActivity.this, list);
        dragSortListView.setAdapter(adapter);
        dragSortListView.setDragEnabled(true);//设置是否可以拖动
    }


    //删除监听器，点击左边差号就触发。删除item操作。
    private DragSortListView.RemoveListener onRemove = new DragSortListView.RemoveListener() {
        @Override
        public void remove(int which) {
            adapter.remove(which);
        }
    };
    //监听器在手机拖动停下的时候触发
    private DragSortListView.DropListener onDrop =
            new DragSortListView.DropListener() {
                @Override
                public void drop(int from, int to) {//from to 分别表示 被拖动控件原位置 和目标位置
                    if (from != to) {
                        DragResponse item = (DragResponse) adapter.getItem(from);//得到listview的适配器
                        adapter.remove(from);//在适配器中”原位置“的数据。
                        adapter.insert(item, to);//在目标位置中插入被拖动的控件。
                    }
                }
            };


    /**
     * 测试数据
     */
    public List<DragResponse> getDateSource() {
        List<DragResponse> list = new ArrayList<DragResponse>();
        String testString = "我还是海贼王路飞";
        for (int i = 0; i < 20; i++) {
            DragResponse dragResponse = new DragResponse();
            dragResponse.setTv1(testString + i);
            list.add(dragResponse);
        }
        return list;
    }
}
