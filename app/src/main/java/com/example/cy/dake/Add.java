package com.example.cy.dake;

import android.annotation.TargetApi;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

public class Add extends AppCompatActivity {


    Button goods_add , selectPhoto;
    ImageView picture;
    public static final int CHOOSE_PHOTO = 3;
    public Goods goods;
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK){
                    //判断手机系统版本号
                    if (Build.VERSION.SDK_INT>=19){
                        //4.4以上使用这个方法处理图片
                        handleIMageOnKitKat(data);
                    }else{
                        handleIMageBeforKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    String imagePath = null;
    @TargetApi(19)
    private void handleIMageOnKitKat(Intent data) {
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this , uri)){
            //如果是document类型的URI，则使用document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = docId.split(":")[1];//解析出数字格式的id
                String selection = MediaStore.Images.Media._ID +"=" +id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI , selection);
            }else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(docId));
                imagePath = getImagePath(contentUri ,null);
            }
        }else if ("content".equalsIgnoreCase(uri.getScheme())){
            //如果不是document类型的URI，则使用普通方式处理
            imagePath = getImagePath(uri , null);
        }
        displayImage(imagePath);
    }

    private void displayImage(String imagePath) {
        if (imagePath!=null){
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            picture.setImageBitmap(bitmap);
        }else {
            Toast.makeText(Add.this, "未得到图片", Toast.LENGTH_SHORT).show();
        }
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri , null , selection , null , null);
        if (cursor!=null){
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void handleIMageBeforKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri , null);
        displayImage(imagePath);
    }



    private void initListener() {
        selectPhoto.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                intent.setType("image/*");
                startActivityForResult(intent, CHOOSE_PHOTO);
            }
        });
        goods_add.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View v) {
                final String name, icon_path;
//                final double ;
                name = ((MyApplication)getApplication()).getCheckname();


                //获取文件路径
                //icon_path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/1.jpg";
                icon_path = imagePath;

                final BmobFile bmobfile = new BmobFile(new File(icon_path));
                bmobfile.upload(Add.this, new UploadFileListener() {
                    @Override
                    public void onProgress(Integer integer) {

                    }
                    @Override
                    public void onSuccess() {
                        BmobQuery<Person> person = new BmobQuery<Person>();
                        person.addWhereEqualTo( "name",name );
                        person.findObjects( Add.this, new FindListener <Person>() {
                            @Override
                            public void onSuccess( List<Person> list ) {
                                Person person1= list.get( 0 );
                                person1.setIcon(bmobfile);
                                person1.update( Add.this, new UpdateListener() {
                                    @Override
                                    public void onSuccess() {
                                        Toast.makeText(Add.this, "成功!", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onFailure( int i, String s ) {
                                        Toast.makeText(Add.this, "失败!", Toast.LENGTH_SHORT).show();
                                    }
                                } );
                            }

                            @Override
                            public void onError( int i, String s ) {

                            }
                        } );
                        BmobQuery<lunt_neirong>bmobQuery=new BmobQuery <lunt_neirong>(  );
                        bmobQuery.addWhereEqualTo( "name",name );
                        bmobQuery.findObjects( Add.this, new FindListener <lunt_neirong>() {
                            @Override
                            public void onSuccess( List <lunt_neirong> list ) {
                                for(int i=0;i<list.size();i++){
                                    lunt_neirong lunt_neirong=list.get( i );
                                    lunt_neirong.setIcon( bmobfile );
                                    lunt_neirong.update( Add.this, new UpdateListener() {
                                        @Override
                                        public void onSuccess() {

                                        }

                                        @Override
                                        public void onFailure( int i, String s ) {
                                            Toast.makeText(Add.this, "失败!", Toast.LENGTH_SHORT).show();
                                        }
                                    } );
                                }


                            }

                            @Override
                            public void onError( int i, String s ) {

                            }
                        } );

                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(Add.this, "文件上传失败", Toast.LENGTH_SHORT).show();

                    }
                });
                /*
                bmobfile.upload(new UploadFileListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            System.out.println("头像上传成功");
                            Goods goods = new Goods();
                            goods.setName(name);
                            goods.setDesc(desc);
                            goods.setPrice(price);
                            goods.setIcon(bmobfile);
                            goods.save(new SaveListener<String>() {
                                @Override
                                public void done(String s, BmobException e) {
                                    if (e == null) {
                                        Toast.makeText(Add.this, "添加数据成功，：", Toast.LENGTH_SHORT).show();
                                        System.out.println("添加数据成功");
                                    } else {
                                        Toast.makeText(Add.this, "添加数据失败", Toast.LENGTH_SHORT).show();
                                        System.out.println("添加数据失败");
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(Add.this, "文件上传失败", Toast.LENGTH_SHORT).show();
                            System.out.println("文件上传失败");
                        }
                    }
                });*/

                finish();
            }

        });
    }
    private void findView() {
        goods_add = (Button) findViewById(R.id.goods_add);
        picture = (ImageView) findViewById(R.id.picture);
        selectPhoto = (Button) findViewById(R.id.select_photo);
    }
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add );
        findView();
        initListener();
    }
}
