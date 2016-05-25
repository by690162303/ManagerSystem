package com.bcx.managersystem.fragment;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.bcx.managersystem.Interface.Dosoming;
import com.bcx.managersystem.R;
import com.bcx.managersystem.activity.LoginActivity;
import com.bcx.managersystem.activity.SaveBMUtil;
import com.bcx.managersystem.adapter.FragMySelfAdapter;
import com.bcx.managersystem.base.BaseContent;
import com.bcx.managersystem.base.BaseFragment;
import com.bcx.managersystem.util.PreferenceUtil;
import com.bcx.managersystem.util.RoundedImageView.CircleImageView;

import java.io.File;
import java.io.FileNotFoundException;




public class Frag_MySelf extends BaseFragment implements View.OnClickListener,AdapterView.OnItemClickListener {
    private CircleImageView headview;
    private ListView mlistview;
    private Button bt_exit;
    private FragMySelfAdapter fragMySelfAdapter;
    private String[] str = BaseContent.ARRANGETITLE2;
    private CharSequence[] cs = {"相机", "相册"};
    private Myself_frag_setting  frag_setting = new Myself_frag_setting();
    //相机
    private static final int REQUEST_CODE_TAKE_PHOTO = 10;
    //相册
    private static final int REQUEST_CODE_CLIP_PHOTO = 20;
    public static final int RESULT_CANCELED = 0;
    public static final int RESULT_OK = -1;
    private static final int REQUEST_CODE_GALLARY_NO_CROP = 110;
    //截取
    private Bitmap bmp;
    private int[] img_id = {R.drawable.myself, R.drawable.shezhi};
    private File mOutputFile;
    private Dosoming dd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag__myself, container, false);
        headview = (CircleImageView) view.findViewById(R.id.frag_me_head_img);
        mlistview = (ListView) view.findViewById(R.id.frag_me_listview);
        bt_exit = (Button) view.findViewById(R.id.frag_me_exit_bt);
        init();
//        String sdPath = Environment.getExternalStorageDirectory()
//                .getAbsolutePath() + "/managerHeadView/";
//        L(sdPath);
        return view;
    }

    public void setDd(Dosoming dd) {
        this.dd = dd;
    }

    public void init() {
        mContext = getContext();
        frag_setting.setDd(dd);
        headview.setImageResource(R.drawable.morentouxiang);
        fragMySelfAdapter = new FragMySelfAdapter(getContext(), str, img_id);
        mlistview.setAdapter(fragMySelfAdapter);
        mlistview.setOnItemClickListener(this);
        headview.setOnClickListener(this);
        bt_exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        switch (v.getId()) {
            case R.id.frag_me_head_img:
                builder.setItems(cs, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                takePhoto();
                                break;
                            case 1:
                                chooseFromMediaStore();
                                break;
                        }
                    }
                });
                builder.show();
                break;
            case R.id.frag_me_exit_bt:
                builder.setTitle("退出提示").setNegativeButton("退出应用", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                }).setNeutralButton("重新登陆", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intents = new Intent(mContext, LoginActivity.class);
                        startActivity(intents);
                        PreferenceUtil.saveBoolean(mContext,"isLogin",false);
                        getActivity().finish();
                    }
                }).setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mFragmentManager = this.getFragmentManager();
        transaction = mFragmentManager.beginTransaction();
        switch (position){
            case 0:
                //个人信息
                transaction.add(R.id.content,new Myself_frag_msg(),"myself_msg");
                transaction.addToBackStack("myself_msg");
                break;
            case 1:
                //系统设置
                transaction.add(R.id.content,frag_setting,"myself_setting");
                transaction.addToBackStack("myself_setting");
                break;
        }
        transaction.commit();
    }
    //拍照方法
    private void takePhoto() {
        if (!hasCarema()) {
            return;
        }
        //生成一个文件，存储我们将来拍照的照片
        String sdPath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/managerHeadView/";

        mOutputFile = new File(sdPath, System.currentTimeMillis() + ".tmp");
        Uri uri = Uri.fromFile(mOutputFile);
        //跳转到我们系统的相机界面
        //自己写相机界面   Camera +SurfaceView +SurfaceHolder
        Intent newIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //最终把我们拍摄的相片，输出到uri指向
        newIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(newIntent, REQUEST_CODE_TAKE_PHOTO);
    }

    //拍照返回
    private void onTakePhotoFinished(int resultCode, Intent data) {
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(mContext, "take photo canceled", Toast.LENGTH_SHORT)
                    .show();
            return;
        } else if (resultCode != RESULT_OK) {
            Toast.makeText(mContext, "take photo failed", Toast.LENGTH_SHORT)
                    .show();
        } else {
            clipPhoto(Uri.fromFile(mOutputFile));
        }
    }

    //判断是否有支持相机
    private boolean hasCarema() {
        PackageManager pm = mContext.getPackageManager();
        if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)
                && !pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)) {
            Toast.makeText(mContext, "no camera found", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //从相册选取
    private void chooseFromMediaStore() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //只要图片的URI
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        //表示要关联图库类的应用打开
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_GALLARY_NO_CROP);
    }

    //裁剪返回
    private void onClipPhotoFinished(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(mContext, "clip photo canceled", Toast.LENGTH_SHORT)
                    .show();
            return;
        } else if (resultCode != RESULT_OK) {
            Toast.makeText(mContext, "take photo failed", Toast.LENGTH_SHORT)
                    .show();
        } else {
            //此处可替换成ImageLoader处理
            Bitmap bm = BitmapFactory.decodeFile(mOutputFile.getAbsolutePath()
                    + "tmp");
            headview.setImageBitmap(bm);
        }
    }

    //裁剪图片方法
    private void clipPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 440);
        intent.putExtra("outputY", 440);
        //把最后裁剪的图片保存在文件里
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(new File(mOutputFile.getAbsoluteFile() + "tmp")));
        startActivityForResult(intent, REQUEST_CODE_CLIP_PHOTO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //处理裁剪结果
        //针对4.4前后的路径不一致问题：http://blog.csdn.net/tempersitu/article/details/20557383
        //以下处理选择图片结果
        //---
        //获取到选择的图片，并且把从相册中选好的图片保存在SD卡
        //选择
        switch (requestCode) {
            case REQUEST_CODE_TAKE_PHOTO:
                onTakePhotoFinished(resultCode, data);
                break;
            case REQUEST_CODE_CLIP_PHOTO:
                onClipPhotoFinished(requestCode, resultCode, data);
                break;
            default:
                Uri uri = data.getData();
                ContentResolver cr = mContext.getContentResolver();
                try {
                    bmp = BitmapFactory.decodeStream(cr.openInputStream(uri));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                mOutputFile = SaveBMUtil.saveMyBitmap(bmp, "tx" + System.currentTimeMillis());
                if (requestCode == REQUEST_CODE_GALLARY_NO_CROP) {
                    //保存图片到本地，随后准备上传
                    clipPhoto(Uri.fromFile(mOutputFile));
                }
                break;
        }
    }

}
