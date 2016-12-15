package com.tongji409.website.services;

import metrics.MetricsEvaluator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.tongji409.*;

import java.util.List;

/**
 * Project: SwQualityAssesment
 * Package: com.tongji409.website.services
 * Author:  Novemser
 * 2016/12/15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FileSystemService.class)
public class FileSystemServiceTest {

    @Autowired
    private FileSystemService fileSystemService;

    @Test
    public void saveArchiveToFile() throws Exception {
        String path = "Test1";
        String archive = "http://gitlab.lab-sse.cn/Novemser/Carpool/repository/archive.zip?ref=master";
        // 1. 39维度分析
        // 下载
        fileSystemService.saveArchiveToFile(path, archive);

        // 解压
        String pth = fileSystemService.unzipProject(path);

        // 分析
        // 如果缺少Jar包,请注释此行代码
        if (pth != null) {

            DimensionCalculator calculator = new DimensionCalculator();
            calculator.calculateFiles(fileSystemService.listServerFiles(pth));
            List<List<MetricsEvaluator>> projectMetricsList = calculator.getProjectMetrics();
            System.out.println("Total file analysed:" + projectMetricsList.size());

            for (List<MetricsEvaluator> moduleMetricList : projectMetricsList) {
                System.out.println("Module analysed:" + moduleMetricList.size());
                for (MetricsEvaluator item : moduleMetricList) {
                    System.out.println(item.moduleName);
                }
            }
        }
    }

}