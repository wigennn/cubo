package com.redbyte.platform.filemanager;

/**
 * <p>
 *
 * </p>
 *
 * @author wangwq
 */
public interface FileManager {

    boolean uploadFile(String bucketName, String fileName, String filePath) throws Exception;

    String download(String bucketName, String fileName) throws Exception;

    String downloadAnonymous(String bucketName, String fileName) throws Exception;

    String downloadAnonymousWithExpireTime(String bucketName, String fileName, Integer expires) throws Exception;
}
