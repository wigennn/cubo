package com.redbyte.platform.filemanager;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;

/**
 * <p>
 *
 * </p>
 *
 * @author wangwq
 */
public class MinoFileManager implements FileManager {

    private static MinioClient minioClient;

    static {
        try {
            minioClient = new MinioClient("http://139.196.205.76", "minioadmin", "minioadmin");
        } catch (InvalidEndpointException e) {
            e.printStackTrace();
        } catch (InvalidPortException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean uploadFile(String bucketName, String fileName, String filePath) {
        try {
            if (isExistBucket(bucketName)) {
                minioClient.putObject(bucketName, fileName, filePath);
            } else return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public String download(String bucketName, String fileName) throws Exception {
        return minioClient.getObjectUrl(bucketName, fileName);
    }

    @Override
    public String downloadAnonymous(String bucketName, String fileName) throws Exception {
        return minioClient.presignedGetObject(bucketName, fileName);
    }

    @Override
    public String downloadAnonymousWithExpireTime(String bucketName, String fileName, Integer expires) throws Exception {
        return minioClient.presignedGetObject(bucketName, fileName, expires, null);
    }

    private boolean isExistBucket(String bucket) throws Exception {
        return minioClient.bucketExists(bucket);
    }

    public static void main(String[] args) throws Exception {
        FileManager fileManager = new MinoFileManager();
//        fileManager.uploadFile("wigen", "my.png", "E:\\1.png");
        System.out.println(fileManager.downloadAnonymous("wigen", "my.png"));
    }

}
