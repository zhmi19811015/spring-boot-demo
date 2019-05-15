package com.ming.hadoopdemo.wccount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @Description: 类作用描述
 * @Author: zhangming
 * @CreateDate: 2019/4/25 9:54 AM
 * @Version: 1.0
 */
public class WCMain {
    private static String iPath = "hdfs://localhost:9000/wordcount/input/test.txt";
    private static String oPath = "hdfs://localhost:9000/wordcount/output/";

    /**
     * 1. 业务逻辑相关信息通过job对象定义与实现 2. 将绑定好的job提交给集群去运行
     */
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job wcjob = Job.getInstance(conf);

        wcjob.setJarByClass(WCMain.class);
        wcjob.setMapperClass(WCMapper.class);
        wcjob.setReducerClass(WCReducer.class);

        // 设置业务逻辑Mapper类的输出key和value的数据类型
        wcjob.setMapOutputKeyClass(Text.class);
        wcjob.setMapOutputValueClass(IntWritable.class);

        // 设置业务逻辑Reducer类的输出key和value的数据类型
        wcjob.setOutputKeyClass(Text.class);
        wcjob.setOutputValueClass(IntWritable.class);

        // 指定要处理的数据所在的位置
        FileSystem fs = FileSystem.get(conf);
        Path IPath = new Path(iPath);
        if (fs.exists(IPath)) {
            FileInputFormat.addInputPath(wcjob, IPath);
        }

        // 指定处理完成之后的结果所保存的位置
        Path OPath = new Path(oPath);
        fs.delete(OPath, true);
        FileOutputFormat.setOutputPath(wcjob, OPath);

        // 向yarn集群提交这个job
        boolean res = wcjob.waitForCompletion(true);
        System.exit(res ? 0 : 1);
    }
}
