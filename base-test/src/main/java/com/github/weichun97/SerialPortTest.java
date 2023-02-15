package com.github.weichun97;

import cn.hutool.core.util.HexUtil;
import gnu.io.SerialPort;

/**
 * 串口测试程序
 * @author wusq
 * @date 2021/1/1
 */
public class SerialPortTest {

    public static void main(String[] args) throws Exception{

        // 打开串口
        SerialPort serialPort = SerialPortUtils.open("COM2", 9600, SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1, SerialPort.PARITY_EVEN);

        // 监听串口读取数据
        SerialPortUtils.addListener(serialPort, () -> {
            byte[] data = SerialPortUtils.read(serialPort);
            System.out.println(HexUtil.encodeHexStr(data));
        });

        // 往串口发送数据
        byte[] data = {1, 2, 3};
        SerialPortUtils.write(serialPort, data);

        /*// 关闭串口
        Thread.sleep(2000);
        SerialPortUtils.close(serialPort);*/

        // 测试可用端口
        //SerialPortUtils.listPortName().forEach(o -> System.out.println(o));
    }
}