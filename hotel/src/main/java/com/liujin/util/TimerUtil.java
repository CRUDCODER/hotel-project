package com.liujin.util;

import com.liujin.entity.Live;
import com.liujin.service.ILiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author liujin
 * @date created in 2020/2/4 10:27
 */
@Component
public class TimerUtil {
    @Autowired
    private ILiveService liveService;
    @Scheduled(cron = "0/10 * * * * ?")
    public void test(){
        String date=new SimpleDateFormat("yyyy-MM-dd 12:00:00").format(new Date());
        List<Live> lives = liveService.selectAllExpire(date);
        if (!lives.isEmpty()){
            for (Live life : lives) {
                life.setLiveFlag(2);
            }
            //批量修改
            liveService.updateBatchById(lives);
        }
    }
}
