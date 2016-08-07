package com.example.administrator.yicheng.main.Read.webcontent.comment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.adapter.CommentAdapter;
import com.example.administrator.yicheng.base.BaseActivity;
import com.example.administrator.yicheng.bean.Comment;
import com.example.administrator.yicheng.config.Flags;
import com.example.administrator.yicheng.main.minef.login.LogInActivity;
import com.example.administrator.yicheng.utils.LiteOrmUtils;
import com.example.administrator.yicheng.utils.SharedPreferenceUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Jensen on 2016/8/7.
 */
public class CommentActivity extends BaseActivity {
    @BindView(R.id.iv_comment)
    ImageView ivComment;
    @BindView(R.id.comment_lv)
    ListView commentLv;
    @BindView(R.id.ed_comment)
    EditText edComment;
    @BindView(R.id.commit_bt)
    Button commitBt;
    private String url;
    private List<Comment> list;
    private CommentAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_comment;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        commentLv.setEmptyView(ivComment);
        list = LiteOrmUtils.getQueryByWhere(Comment.class, "url", new String[]{this.url});
        adapter = new CommentAdapter(list);
        commentLv.setAdapter(adapter);
    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.ed_comment, R.id.commit_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ed_comment:
                Boolean f = (Boolean) SharedPreferenceUtils.get(this, Flags.IsLogInFlag, false);
                if (!f) {
                    startActivity(new Intent(this, LogInActivity.class));
                }
                break;
            case R.id.commit_bt:
                String s = edComment.getText().toString();
                if (!TextUtils.isEmpty(s)) {
                    String name = (String) SharedPreferenceUtils.get(this, Flags.IsLogIngNameFlag, "");
                    if (TextUtils.isEmpty(name)) {
                        name = "匿名";
                    }
                    Comment comment = new Comment(url, s, System.currentTimeMillis(), name);
                    LiteOrmUtils.insert(comment);
                    list.add(comment);
                    adapter.notifyDataSetChanged();
                    edComment.setText("");
                }
                break;
        }
    }

    @OnClick(R.id.comment_Icon)
    public void onClick() {
        finish();
    }
}
