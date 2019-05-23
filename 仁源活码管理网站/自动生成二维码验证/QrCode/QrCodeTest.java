package com.qhit.QrCode;


import com.qhit.utils.QRCodeUtil;

/**
 * Created by yy on 2019/5/8.
 */
public class QrCodeTest {

    public static void main(String[] args) throws Exception {
        // 存放在二维码中的内容
        int[] arr = new int[6];
        for (int i = 0; i <arr.length ; i++) {
            int temp =  (int)(Math.random()*10)+1;//随机产生一个 1~10 的整数
            arr[i] = temp;//将产生的数添加到数组
        }
     String s="验证码是：";
        for (int i:arr) {
            //System.out.print(i);
            s+=i;
        }
        // 嵌入二维码的图片路径
        String imgPath = "f:/二维码/QRCode/dog.jpg";
        // 生成的二维码的路径及名称
        String destPath = "f:/二维码/code/jam.jpg";
        //生成二维码
        QRCodeUtil.encode(s, imgPath, destPath, true);
        // 解析二维码dddd
        String str = QRCodeUtil.decode(destPath);
        // 打印出解析出的内容
        System.out.println(str);

    }

}