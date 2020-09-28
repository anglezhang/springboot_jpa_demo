package cn.com.doone.tx.cloud.service.config.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.com.doone.tx.cloud.service.config.info.UploadInfo;
import cn.com.doone.tx.cloud.tool.common.invoke.ServerResp;
import cn.com.doone.tx.cloud.tool.common.random.RandomUtil;

/**
 * (ftp文件上传控制类)
 */
@Component
public class FtpFileBaseController {

    /** FTP上传 */
//    FtpUtil ftpUtil = new FtpUtil();

    @Value("${ftpUser}")
    String user;
    @Value("${ftpPassword}")
    String password;
    @Value("${ftpServer}")
    String server;
    @Value("${ftpPort}")
    String port;
    @Value("${images.server.url}")
    String webServer;

    /**
     * ftp文件上传
     * @param file
     * @param fileName
     * @param path
     * @return
     */
    public ServerResp<UploadInfo> uploadFile(File file, String fileName, String path){
        ServerResp<UploadInfo> resp = new ServerResp<UploadInfo>();
        if(StringUtils.isBlank(user)){
            return resp.error("FTP服务器[用户名]配置信息缺失");
        }
        if(StringUtils.isBlank(password)){
            return resp.error("FTP服务器[密码]配置信息缺失");
        }
        if(StringUtils.isBlank(server)){
            return resp.error("FTP服务器[服务器地址]配置信息缺失");
        }
        if(StringUtils.isBlank(port)){
            return resp.error("FTP服务器[端口]配置信息缺失");
        }
        if(file==null){
            return resp.error("上传的文件不能为空");
        }
        if(StringUtils.isBlank(fileName)){
            fileName = getFileNameWithFile(file);
        }
        if(StringUtils.isBlank(path)){
        	path = getFilePathWithFile(file);//生成文件目录
        }
        String ftpUrl = "ftp://" + user + ":" +
                password + "@" + server + ":" + port + "/" + path;
        try {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>正在上传<<<<<<<<<<<<<<<<<<");
            boolean success = false;//ftpUtil.upload(ftpUrl,file,fileName);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>上传结束<<<<<<<<<<<<<<<<<<");
            if(success){
                UploadInfo uploadInfo = new UploadInfo();
                String ftpAddress = "ftp://" + server + ":" + port + "/" + path + fileName;
                String webAddress = webServer + path + fileName;
                String[] split = fileName.split("\\.");
                if (split!=null&&split.length==2) {
                	String extName = split[1];
                    uploadInfo.setFileSuffix(extName);//文件扩展名
				}
                uploadInfo.setSaveFileName(fileName);//文件名
                uploadInfo.setFtpUrl(ftpAddress);//Ftp路径
                uploadInfo.setWebUrl(webAddress);//Web路径
                uploadInfo.setFilePath("/"+path);//文件目录
                resp.success(uploadInfo);
            }
        } catch (Exception e) {
            return resp.error("上传失败");
        }
        return resp;
    }

    public String getFileNameWithFile(File file){
        String fileName = file.getName();
        String[] split = fileName.split("\\.");
        String extName = split[1];
        return System.currentTimeMillis()+ RandomUtil.randomAll(3)+"."+extName;
    }

    public String getFilePathWithFile(File file){
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
    	String fileName = file.getName();
        String[] split = fileName.split("\\.");
        String extName = split[1];
        String imgExtNames = "jpg/jpeg/png/img";
        if (imgExtNames.contains(extName.toLowerCase())) {
			return "images/"+dateFormat.format(new Date())+"/";
		}else {
			return "other/"+dateFormat.format(new Date())+"/";
		}

    }

    /**
     * ftp文件下载
     * @param fileName
     * @param filePath
     * @param fullPath
     * @return
     */
    public File downLoadFile(String fileName,String filePath,String fullPath){
        String ftpUrl = "ftp://" + user + ":" +
                password + "@" + server + ":" + port + "/" + filePath+fileName;
        File ftpfile = null;
        try {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>正在下载<<<<<<<<<<<<<<<<<<");
            ftpfile = null;//ftpUtil.download(ftpUrl, fullPath, 1);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>下载结束<<<<<<<<<<<<<<<<<<");
        } catch (Exception e) {
            return ftpfile;
        }
        return ftpfile;
    }



}
