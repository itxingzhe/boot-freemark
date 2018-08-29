package cn.wyb.presonal.nioandio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * NioAndIoTest: (请描述这个类).
 *
 * @author wangyibin
 * @date 2018/8/29 16:03
 * @see
 */
public class NioAndIoTest {

	public static void main(String[] args) {

		long start = System.currentTimeMillis();
		//test1();

		try {
			//test2();
			test3();
		} catch (Exception e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		System.out.println("运行耗时" + (end - start) + "毫秒");
	}

	public static void test1() {
		//底层为10 的byte[]数组
		ByteBuffer byteBuffer = ByteBuffer.allocate(10);
		System.out.println(byteBuffer.capacity());
		System.out.println(byteBuffer.limit());
		System.out.println(byteBuffer.position());
		System.out.println(byteBuffer.mark());
		byteBuffer.put("ddd".getBytes());
		System.out.println("***************");
		System.out.println(byteBuffer.capacity());
		System.out.println(byteBuffer.limit());
		//每一次put都会将值移动一次
		System.out.println(byteBuffer.position());

		System.out.println("**********flip（）********");
		//将存入数据模式变成取出数据模式 已经存入的数据posstion又变成0，从头继续读
		byteBuffer.flip();

		System.out.println(byteBuffer.capacity());
		System.out.println(byteBuffer.limit());
		System.out.println("----" + byteBuffer.position());

		System.out.println("**********get（）********");
		//每get一次posstion+1
		System.out.println((char) byteBuffer.get());
		System.out.println((char) byteBuffer.get());
		System.out.println(byteBuffer.capacity());
		System.out.println(byteBuffer.limit());
		System.out.println(byteBuffer.position());

		//重置position
		byteBuffer.rewind();
		//清空  回到用户最初的状态  10，10，0
		byteBuffer.clear();
		//byteBuffer.put("c".getBytes());
		byteBuffer.flip();
		System.out.println((char) byteBuffer.get());

	}

	public static void test2() throws IOException {

		//1.提供相应的输入输出流
		FileInputStream fis = new FileInputStream("F:\\Downloads\\kugou.exe");
		FileOutputStream fos = new FileOutputStream("F:\\download\\batdemo\\kuGou.exe");

		//创建相应的Channel  通过我们的流去创建对应的通道，然后通过通道继续读写数据
		FileChannel inchannel = fis.getChannel();
		FileChannel outchannel = fos.getChannel();

		//3.提供缓冲区
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		while ((inchannel.read(byteBuffer)) != -1) {
			//切换为读数据的模式
			byteBuffer.flip();
			//如果这里不切换的话，缓存区会满
			outchannel.write(byteBuffer);
			//清空，读完当前的缓存区然后再继续
			byteBuffer.clear();
		}
		fis.close();
		fos.close();
		inchannel.close();
		outchannel.close();


	}

	public static void test3() throws Exception {

		//1.创建Channel
		FileChannel inChannel = FileChannel.open(Paths.get("F:\\Downloads\\kugou.exe"), StandardOpenOption.READ);
		FileChannel outChannel = FileChannel.open(Paths.get("F:\\download\\batdemo\\music.exe"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

		//2.创建得到直接缓冲区
		MappedByteBuffer inMappedBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
		MappedByteBuffer outMappedBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

		//3.数据的读写
		byte[] dst = new byte[inMappedBuffer.limit()];
		//将数据写入到dst中
		inMappedBuffer.get(dst);
		//从dst中将数据取出
		outMappedBuffer.put(dst);
		//outChannel.write(outMappedBuffer);
		outMappedBuffer.flip();
		//4.关闭资源
		inChannel.close();
		outChannel.close();
	}

}
