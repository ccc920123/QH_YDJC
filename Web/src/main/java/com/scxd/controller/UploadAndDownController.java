package com.scxd.controller;

import com.scxd.aspect.IgnoreSecurity;
import com.scxd.beans.biz.BizPhotoInfo;
import com.scxd.beans.common.User;
import com.scxd.beans.pojo.SysIfLogWithBLOBs;
import com.scxd.beans.pojo.SysPdaVersion;
import com.scxd.common.Response;
import com.scxd.dao.mapper.PhotoDao;
import com.scxd.dao.mapper.SysPdaVersionMapper;
import com.scxd.service.CommonService;
import com.scxd.service.PhotoService;
import com.scxd.service.common.LibSysParam;
import com.scxd.service.common.impl.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;
import java.util.UUID;

/**
 * @Auther:陈攀
 * @Description:文件上传接口控制器
 * @Date:Created in 15:01 2018/5/22
 * @Modified By:
 */
@RestController
public class UploadAndDownController {
    @Resource
    private SysPdaVersionMapper sysPdaVersionMapper;
    @Resource
    private CommonService commonService;
    @Autowired
    private PhotoService ps;
    @Resource
    private PhotoDao photoDao;
    @Resource
    private LibSysParam libSysParam;
    @Resource
    private LoggerService loggerService;
    @IgnoreSecurity
    @RequestMapping(value = "pdas/management/pdaVersion/uploadapk", method = RequestMethod.POST)
    public Response uploadBigFile(@RequestParam("file") MultipartFile file, String version,String dictionary, String bmbh, String desction, HttpSession session,HttpServletRequest request) {

        String photopath = libSysParam.getPHOTO_PATH();
        String systempath=System.getProperty("catalina.home");
         String realPath = systempath+"/upload/apks/";
        String returnUrl = request.getScheme() + "://" +photopath + request.getContextPath() +"/downloadAPK?id=";//存储路径
        Response response = null;
        String id=UUID.randomUUID().toString();
       // String realPath = "D:\\ServiceUpload";
        boolean isSave = true;
        String message = "";
        String fileName = null;
        String path = null;
        File fileD = new File((realPath));
        if (fileD.exists()) {
            if (!fileD.isDirectory()) {
                fileD.delete();
                fileD.mkdirs();
            }
        } else {
            fileD.mkdirs();
        }
        if (file != null) {// 判断上传的文件是否为空
            fileName = id+".apk";
            path = realPath + System.getProperty("file.separator") + fileName;
            // 转存文件到指定的路径
            File mFile = new File(path);
            if (mFile.exists()) {
                mFile.delete();
            }
            try {
                file.transferTo(mFile);
            } catch (IOException e) {
                isSave = false;
                message = "上传失败" + e.getMessage();
            }

        } else {
            isSave = false;
            message = "文件上传失败：上传文件为空";
        }
        if (isSave) {
            String name = null;
            try {
                User user = commonService.getUserInfoFromSession(session);
                name = user.getRealname();
            } catch (Exception e) {
                e.printStackTrace();
            }

            SysPdaVersion sysPdaVersion = new SysPdaVersion();
            sysPdaVersion.setId(id);
            sysPdaVersion.setUrl(returnUrl+id);
            sysPdaVersion.setBmbh(bmbh);
            sysPdaVersion.setDescription(desction);
            sysPdaVersion.setCzryzh(name);
            sysPdaVersion.setVersion(version);
            sysPdaVersion.setDictionary(dictionary);
            sysPdaVersion.setCzsj(new Date(System.currentTimeMillis()));
            int i = sysPdaVersionMapper.insert(sysPdaVersion);
            if (i > 0) {
                response = new Response().success("上传成功");
            }
        } else {
            response = new Response().failure(message);
        }
        return response;

    }

    @IgnoreSecurity
    @RequestMapping(value = "/downloadAPK", method = RequestMethod.GET)
    public void downloadAPK(String id, HttpServletResponse res,HttpServletRequest request) {
        String systempath=System.getProperty("catalina.home");
        String realPath = systempath+"/upload/apks/";
        String fileName = "scxd.apk";
        String filePath = "";

        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        SysPdaVersion sysPdaVersion = sysPdaVersionMapper.selectByPrimaryKey(id);
        if (sysPdaVersion != null) {
            filePath = realPath + System.getProperty("file.separator") + id+".apk";
        }
        File tempFile=null;
        //获取文件名
        if (filePath != null) {
            tempFile = new File(filePath.trim());

            fileName = tempFile.getName();
        }else {
        }
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        res.setContentLengthLong(tempFile.length());
        OutputStream os = null;
//        byte [] data=getBytes(filePath);
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(tempFile));
//            bis = new BufferedInputStream(new ByteArrayInputStream(data));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private byte[] getBytes(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    //写入图片@RequestParam("file")
    @IgnoreSecurity
    @RequestMapping(value = "/uploadPhoto")
    public Response WritePhoto(@RequestParam("file") MultipartFile img, String id, String zpzl, String psr, HttpServletRequest request ){

        String contextPath = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" +
                request.getServerPort() + contextPath + "/getPhotos?"+"id=";
        if (img != null){
            try{
                byte[] a = img.getBytes();
                BizPhotoInfo photo = new BizPhotoInfo();
                String uuid= UUID.randomUUID().toString();
                photo.setId(uuid);
                photo.setGlid(id);
                photo.setZpzl(zpzl);
                photo.setPsry(psr);
                photo.setZp(a);
                photo.setPssj(new Date());
                photo.setSx1(basePath+uuid);
                String wym = "00000000";
                String jkid = "img";
                String indata = "imageName="+img.getName()+"glid="+id+"zpzl="+zpzl+"psr="+psr;
                String sid = UUID.randomUUID().toString();
                SysIfLogWithBLOBs sysIfLogWithBLOBs = new SysIfLogWithBLOBs();
                sysIfLogWithBLOBs.setCjsj(new Date());
                sysIfLogWithBLOBs.setId(sid);
                sysIfLogWithBLOBs.setJkid(jkid);
                sysIfLogWithBLOBs.setWym(wym);
                sysIfLogWithBLOBs.setIndata(indata);
                loggerService.writePdaInterfaceLog(sysIfLogWithBLOBs);
                int num = ps.inputPhoto(photo);
                return (num != 0)?new Response().success():new Response().failure();
            }catch(IOException ioe){
                return new Response().failure("上传失败");
            }
        }else{
            return new Response().failure("上传图片为空");
        }

    }

    @IgnoreSecurity
    @RequestMapping(value = "/getPhotos", method = RequestMethod.GET)
    public void getPhotos(String id, HttpServletResponse res) {
        String fileName = "unname.jpg";

        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        BizPhotoInfo bizPhotoInfo = photoDao.selectByPrimaryKey(id);
        byte [] data=null;
        if (bizPhotoInfo != null) {
            data = bizPhotoInfo.getZp();
        }
        //获取文件名
//        res.setHeader("content-type", "application/octet-stream");
//        res.setContentType("application/octet-stream");
//        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        res.setDateHeader("Expires", 0L);
        res.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        res.addHeader("Cache-Control", "post-check=0, pre-check=0");
        res.setHeader("Pragma", "no-cache");
        res.setContentType("image/jpeg");
        OutputStream os = null;

        try {
           os = res.getOutputStream();
//            bis = new BufferedInputStream(new FileInputStream(new File("D:\\kkk.mp4"
//            )));
           bis = new BufferedInputStream(new ByteArrayInputStream(data));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
