package com.scxd.timedtask;

import com.scxd.service.UpdateBaseTableService;
import com.scxd.service.common.LibSysParam;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.Date;

/**
 * 定时器:
 * 每隔5分钟判断一次时间,到配置的时间去跟新执法站,警员,卡口表
 */
@Component
public class TimerTaskFromWeb {



    @Resource
    UpdateBaseTableService updateBaseTableService;
    //每５分钟执行一次
   /* @Scheduled(cron = "0 0/30 * * * *")*/
    public void checkTime(){
        System.out.println(new Date().getTime());
        try {
            updateBaseTableService.updateBaseTable();
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
