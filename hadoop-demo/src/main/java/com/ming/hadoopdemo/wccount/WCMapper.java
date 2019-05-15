package com.ming.hadoopdemo.wccount;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
/**
 * @Description: 类作用描述
 * @Author: zhangming
 * @CreateDate: 2019/4/25 9:52 AM
 * @Version: 1.0
 */
public class WCMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    // map方法的生命周期： 框架每传一行数据就被调用一次
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();  // 行数据转换为string
        String[] words = line.split(" ");  // 行数据分隔单词

        for (String word : words) {  // 遍历数组，输出<单词，1>
            context.write(new Text(word), new IntWritable(1));
        }
    }
}
