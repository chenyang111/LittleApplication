package com.example.chenyang13.littleapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class MainActivity extends AppCompatActivity {

    Button share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        share = (Button) findViewById(R.id.share);

        String appId = "";
        final IWXAPI api = WXAPIFactory.createWXAPI(this,appId , true);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                WXMiniProgramObject miniProgramObj = new WXMiniProgramObject();
                miniProgramObj.webpageUrl = "http://www.qq.com"; // 兼容低版本的网页链接
                miniProgramObj.miniprogramType = WXMiniProgramObject.MINIPTOGRAM_TYPE_RELEASE;// 正式版:0，测试版:1，体验版:2
                miniProgramObj.userName = "gh_d43f693ca31f";     // 小程序原始id
                miniProgramObj.path = "/pages/media";            //小程序页面路径
                WXMediaMessage msg = new WXMediaMessage(miniProgramObj);
                msg.title = "小程序消息Title";                    // 小程序消息title
                msg.description = "小程序消息Desc";               // 小程序消息desc
//                msg.thumbData = getThumb();                      // 小程序消息封面图片，小于128k

                SendMessageToWX.Req req = new SendMessageToWX.Req();
//                req.transaction = buildTransaction("webpage");
                req.message = msg;
                req.scene = SendMessageToWX.Req.WXSceneSession;  // 目前支持会话
                api.sendReq(req);
            }
        });
    }
}
