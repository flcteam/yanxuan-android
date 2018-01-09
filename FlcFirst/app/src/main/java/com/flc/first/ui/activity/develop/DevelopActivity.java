package com.flc.first.ui.activity.develop;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.flc.first.R;
import com.flc.framework.callback.OnItemClickListener;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by channagihong on 09/01/2018.
 */

public class DevelopActivity extends Activity implements OnItemClickListener<String> {

    @BindView(R.id.activity_develop_recycler)
    RecyclerView recyclerView;

    private final String DROP_DOWN_CATEGORY = "DROP_DOWN_CATEGORY";
    private List<String> data = Arrays.asList(DROP_DOWN_CATEGORY);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_develop);
        ButterKnife.bind(this);
        initRecycler();
    }

    private void initRecycler() {
        DevelopAdapter adapter = new DevelopAdapter();
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position, String s) {
        switch (s) {
            case DROP_DOWN_CATEGORY:
                break;
            default:
                break;
        }
    }
}
