package save.lxf.com.savevideopic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.gifencoder.AnimatedGifEncoder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String VIDEO_PATH = Environment.getExternalStorageDirectory().getPath() + "/hx_video/";
    public static final String PIC_PATH = Environment.getExternalStorageDirectory().getPath() + "/hx_pic/";
    public static final String GIF_PATH = Environment.getExternalStorageDirectory().getPath() + "/hx_gif/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serchvideo(VIDEO_PATH);
        Log.e("lxf", "end");

    }


    /**
     * diy视频路径
     *
     * @param strPath
     */
    private void serchvideo(String strPath) {
        String filename;//文件名
        String suf;//文件后缀
        File dir = new File(strPath);//文件夹dir
        File[] files = dir.listFiles();//文件夹下的所有文件或文件夹
        Log.e("lxf", "files.length == " + files.length);
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                Log.e("lxf", "i == " + i);
                Log.e("lxf", "files name == " + files[i].getName());
                Log.e("lxf", "files path == " + VIDEO_PATH + files[i].getName());
                getPicture(VIDEO_PATH + files[i].getName(), files[i].getName().replace(".mp4", "").replace(" ", ""));
                Bitmap videoThumb = getVideoThumb(VIDEO_PATH + files[i].getName());
                bitmap2File(videoThumb, files[i].getName());
            }
        }

    }


    /**
     * 获取视频文件截图
     *
     * @param path 视频文件的路径
     * @return Bitmap 返回获取的Bitmap
     */

    public static Bitmap getVideoThumb(String path) {
        MediaMetadataRetriever media = new MediaMetadataRetriever();
        media.setDataSource(path);
        return media.getFrameAtTime(3 * 1000 * 1000, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
    }


    /**
     * Bitmap保存成File
     *
     * @param bitmap input bitmap
     * @param name   output file's name
     * @return String output file's path
     */

    public static String bitmap2File(Bitmap bitmap, String name) {

        File f = new File(PIC_PATH + name.replace(".mp4", "").replace(" ", "") + ".jpg");
        try {
            if (!f.exists()) {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
            FileOutputStream fOut = null;
            fOut = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
        } catch (IOException e) {
            return null;
        }
        return f.getAbsolutePath();
    }

    public void getPicture(String m_path, String name) {
        //设置视频路径
        String path = m_path;
        Log.e("lxf", "Path:" + path);
        //将路径实例化为一个文件对象
        File file = new File(path);
        //判断对象是否存在，不存在的话给出Toast提示
        if (file.exists()) {
            //提供统一的接口用于从一个输入媒体中取得帧和元数据
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(path);
            String time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            Log.e("lxf", "Time:" + time);

            int seconds = Integer.valueOf(time) / 1000;

            Log.e("lxf", "name ==" + name + ";time ==" + seconds);
            seconds = 10;

            ArrayList<String> gifList = new ArrayList<>();
            for (int i = 1; i < seconds; i++) {
//                Bitmap bitmap = retriever.getFrameAtTime(i * 1000 * 1000, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);

//                 Bitmap bitmap = retriever.getFrameAtTime(i * 1000 * 1000, MediaMetadataRetriever.OPTION_PREVIOUS_SYNC);
//                Bitmap bitmap = retriever.getFrameAtTime(i * 1000 * 1000, MediaMetadataRetriever.OPTION_NEXT_SYNC);
                // Bitmap bitmap = retriever.getFrameAtTime(i * 1000 * 1000, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
                 Bitmap bitmap = retriever.getFrameAtTime(i * 100 * 1000, MediaMetadataRetriever.OPTION_CLOSEST);

                String pathPic = "";
                switch (i) {
                    case 1:
                        pathPic = PIC_PATH + File.separator + name + i + ".jpg";
                        gifList.add(pathPic);
                        break;
                    case 2:
                        pathPic = PIC_PATH + File.separator + name + i + ".jpg";
                        gifList.add(pathPic);
                        break;
                    case 3:
                        pathPic = PIC_PATH + File.separator + name + i + ".jpg";
                        gifList.add(pathPic);
                        break;
                    case 4:
                        pathPic = PIC_PATH + File.separator + name + i + ".jpg";
                        gifList.add(pathPic);
                        break;
                    case 5:
                        pathPic = PIC_PATH + File.separator + name + i + ".jpg";
                        gifList.add(pathPic);
                        break;
                    case 6:
                        pathPic = PIC_PATH + File.separator + name + i + ".jpg";
                        gifList.add(pathPic);
                        break;
                    case 7:
                        pathPic = PIC_PATH + File.separator + name + i + ".jpg";
                        gifList.add(pathPic);
                        break;
                    case 8:
                        pathPic = PIC_PATH + File.separator + name + i + ".jpg";
                        gifList.add(pathPic);
                        break;
                    case 9:
                        pathPic = PIC_PATH + File.separator + name + i + ".jpg";
                        gifList.add(pathPic);
                        break;
                    case 10:
                        pathPic = PIC_PATH + File.separator + name + i + ".jpg";
                        gifList.add(pathPic);
                        break;
                }

                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(pathPic);
//                    gifList.add(pathPic);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                Log.e("lxf", "gifList.size ==" + gifList.size());
                createGif(name, gifList, 200, 480, 800);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(MainActivity.this, "文件不存在", Toast.LENGTH_SHORT).show();
        }
    }


    public static String createGif(String filename, List<String> paths, int fps, int width, int height) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        AnimatedGifEncoder localAnimatedGifEncoder = new AnimatedGifEncoder();
        localAnimatedGifEncoder.start(baos);//start
        localAnimatedGifEncoder.setRepeat(0);//设置生成gif的开始播放时间。0为立即开始播放
        localAnimatedGifEncoder.setDelay(fps);
        if (paths.size() > 0) {
            for (int i = 0; i < paths.size(); i++) {
                Bitmap bitmap = BitmapFactory.decodeFile(paths.get(i));
                Bitmap resizeBm = resizeImage(bitmap, width, height);
                localAnimatedGifEncoder.addFrame(resizeBm);
            }
        }
        localAnimatedGifEncoder.finish();//finish

        File file = new File(GIF_PATH);
        if (!file.exists()) file.mkdir();
        String path = GIF_PATH + filename.replace(" ", "") + ".gif";
        FileOutputStream fos = new FileOutputStream(path);
        baos.writeTo(fos);
        baos.flush();
        fos.flush();
        baos.close();
        fos.close();

        return path;
    }


    //使用Bitmap加Matrix来缩放
    public static Bitmap resizeImage(Bitmap bitmap, int w, int h) {
        Bitmap BitmapOrg = bitmap;
        int width = BitmapOrg.getWidth();
        int height = BitmapOrg.getHeight();
        int newWidth = w;
        int newHeight = h;

        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // if you want to rotate the Bitmap
        // matrix.postRotate(45);
        Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, width,
                height, matrix, true);
        return resizedBitmap;
    }

}
