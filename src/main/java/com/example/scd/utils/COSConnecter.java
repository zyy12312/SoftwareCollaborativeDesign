package com.example.scd.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import org.joda.time.DateTime;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;

public class COSConnecter {

    private String secretId = "AKIDwlikjZDfuwjS4SbTIyQdcUM2VqwyudGh";
    private String secretKey = "LSIt9E5fz3tTnu3xb0EExRijTxc3D7kS";

    private String region = " https://cos-for-scd-1312783961.cos-website.ap-shanghai.myqcloud.com";
    private String bucketName ="test-1314";

    private COSClient cosClient;
    public void initCOSClient(){
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        Region region = new Region(this.region);
        ClientConfig clientConfig = new ClientConfig(region);
        // 生成 cos 客户端。
        cosClient = new COSClient(cred, clientConfig);
    }
    /**
     * 上传文件
     */
    public void upLoad(){
        try {
            String filePath = "E:\\Downloads\\1111.png";
            // 指定要上传的文件
            File localFile = new File(filePath);
            // 指定要上传到 COS 上对象键
            String key = getFileKey(filePath);
            System.out.println("key------------" + key);
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            // 设置权限(公开读)
            cosClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);

            System.out.println("上传ID:"+putObjectResult.getRequestId()+" "+"上传时间"+putObjectResult.getDateStr());

        } catch (CosServiceException serverException) {
            serverException.printStackTrace();
        } catch (CosClientException clientException) {
            clientException.printStackTrace();
        }
    }

    private String getFileKey(String originalfileName) {
        String filePath = "test/";
        //1.获取后缀名 2.去除文件后缀 替换所有特殊字符
        String fileType = originalfileName.substring(originalfileName.lastIndexOf("."));
//        String fileStr = StrUtil.removeSuffix(originalfileName, fileType).replaceAll("[^0-9a-zA-Z\\u4e00-\\u9fa5]", "_");
//        filePath +=  new DateTime().toString("yyyyMMddHHmmss") + "_" + fileStr + fileType;
        return filePath;
    }

    public COSClient cosClient(){
        initCOSClient();
        return cosClient;
    }

    public void download() throws IOException {
        // 生成 cos 客户端。
        if (cosClient==null){
            initCOSClient();
        }
        // 设置要下载到的本地路径
        File downFile = new File("D:/java/project/2222.jpg");
        String key = "test/20230112173441_E__Downloads_1111.png";

        GetObjectRequest getObjectReq = new GetObjectRequest(bucketName, key);
        ObjectMetadata downObjectMeta = cosClient.getObject(getObjectReq, downFile);

    }

    /**
     * 删除文件
     */
    public void delete() {
        // 调用 COS 接口之前必须保证本进程存在一个 COSClient 实例，如果没有则创建
        if (cosClient==null){
            initCOSClient();
        }
        String key = "test/20230112173441_E__Downloads_1111.png";
        try{
            cosClient.deleteObject(bucketName,key);
            System.out.println("del successfully");
        }catch (CosClientException e){
            System.out.println(e.getMessage());
        }
    }

}


