package com.zipe.utils.image;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 圖片處理工具類：<br>
 * 功能：縮放圖像、切割圖像、圖像類型轉換、彩色轉黑白、文字水印、圖片水印等
 * 
 * @author Administrator
 */
public class ImageUtils {

	/**
	 * 幾種常見的圖片格式
	 */
	public static String IMAGE_TYPE_GIF = "gif";// 圖形交換格式
	public static String IMAGE_TYPE_JPG = "jpg";// 聯合照片專家組
	public static String IMAGE_TYPE_JPEG = "jpeg";// 聯合照片專家組
	public static String IMAGE_TYPE_BMP = "bmp";// 英文Bitmap（位圖）的簡寫，它是Windows操作系統中的標准圖像文件格式
	public static String IMAGE_TYPE_PNG = "png";// 可移植網络圖形
	public static String IMAGE_TYPE_PSD = "psd";// Photoshop的專用格式Photoshop
	public static String IMAGE_TYPE_UNKNOWN = "unknown";// 未知圖片格式
	/**
	 * 程序入口：用於測試
	 * 
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// // 1-縮放圖像：
		// // 方法一：按比例縮放
		// ImageUtils.scale("e:/abc.jpg", "e:/abc_scale.jpg", 2, true);// 測試OK
		// 方法二：按高度和寬度縮放
		ImageUtils.scale2("/home/zipe/tmp/teeeee.jpg", "/home/zipe/tmp/3.jpg", 172, 112, true);// 測試OK

		// // 2-切割圖像：
		// // 方法一：按指定起點坐標和寬高切割
		// ImageUtils.cut("e:/abc.jpg", "e:/abc_cut.jpg", 0, 0, 400, 400);//
		// 測試OK
		// // 方法二：指定切片的行數和列數
		// ImageUtils.cut2("e:/abc.jpg", "e:/", 2, 2);// 測試OK
		// // 方法三：指定切片的寬度和高度
		// ImageUtils.cut3("e:/abc.jpg", "e:/", 300, 300);// 測試OK
		//
		// // 3-圖像類型轉換：
		// ImageUtils.convert("e:/abc.jpg", "GIF", "e:/abc_convert.gif");// 測試OK

		// 4-彩色轉黑白：
		// ImageUtils.gray("/home/zipe/1357030735-3177938486.jpg",
		// "/home/zipe/test.jpg");// 測試OK

		// 5-给圖片添加文字水印：
		// 方法一：
		// ImageUtils.pressText("我是水印文字",
		// "/home/zipe/1357030735-3177938486.jpg", "/home/zipe/test2.jpg",
		// "宋體", Font.BOLD, Color.white, 80, 0, 0, 0.5f);// 測試OK
		// // 方法二：
		// ImageUtils.pressText2("我也是水印文字",
		// "/home/zipe/1357030735-3177938486.jpg", "/home/zipe/test3.jpg",
		// "黑體", 36, Color.white, 80, 0, 0, 0.5f);// 測試OK

		// 6-给圖片添加圖片水印：
		// ImageUtils.pressImage("/home/zipe/test/test_cut.jpg",
		// "/home/zipe/test/example.jpg",
		// "/home/zipe/test/example.jpg", 0, 112-17, 1.0f);// 測試OK
		
		System.out.println(getPicType(new File("/home/zipe/tmp/test.jpg")));
	}

	/**
	 * 縮放圖像（按比例縮放）
	 * 
	 * @param srcImageFile
	 *            源圖像文件地址
	 * @param result
	 *            縮放後的圖像地址
	 * @param scale
	 *            縮放比例
	 * @param flag
	 *            縮放選擇:true 放大; false 縮小;
	 */
	public final static void scale(String srcImageFile, String result, int scale, boolean flag) {
		try {
			BufferedImage src = ImageIO.read(new File(srcImageFile)); // 讀入文件
			int width = src.getWidth(); // 得到源圖寬
			int height = src.getHeight(); // 得到源圖長
			if (flag) {// 放大
				width = width * scale;
				height = height * scale;
			} else {// 縮小
				width = width / scale;
				height = height / scale;
			}
			Image image = src.getScaledInstance(width, height, Image.SCALE_DEFAULT);
			BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 繪制縮小後的圖
			g.dispose();
			ImageIO.write(tag, "JPEG", new File(result));// 輸出到文件流
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 縮放圖像（按高度和寬度縮放）
	 * 
	 * @param srcImageFile
	 *            源圖像文件地址
	 * @param result
	 *            縮放後的圖像地址
	 * @param height
	 *            縮放後的高度
	 * @param width
	 *            縮放後的寬度
	 * @param bb
	 *            比例不對時是否需要補白：true为補白; false为不補白;
	 */
	public final static void scale2(String srcImageFile, String result, int height, int width, boolean bb) {
		try {
			double ratio = 0.0; // 縮放比例
			File f = new File(srcImageFile);
			BufferedImage bi = ImageIO.read(f);
			Image itemp = bi.getScaledInstance(width, height, bi.SCALE_SMOOTH);
			// 計算比例
			if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
				if (bi.getHeight() > bi.getWidth()) {
					ratio = (new Integer(height)).doubleValue() / bi.getHeight();
				} else {
					ratio = (new Integer(width)).doubleValue() / bi.getWidth();
				}
				AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
				itemp = op.filter(bi, null);
			}
			if (bb) {// 補白
				BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				Graphics2D g = image.createGraphics();
				g.setColor(Color.white);
				g.fillRect(0, 0, width, height);
				if (width == itemp.getWidth(null))
					g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2, itemp.getWidth(null),
							itemp.getHeight(null), Color.white, null);
				else
					g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0, itemp.getWidth(null),
							itemp.getHeight(null), Color.white, null);
				g.dispose();
				itemp = image;
			}
			ImageIO.write((BufferedImage) itemp, "JPEG", new File(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 圖像切割(按指定起點坐標和寬高切割)
	 * 
	 * @param srcImageFile
	 *            源圖像地址
	 * @param result
	 *            切片後的圖像地址
	 * @param x
	 *            目標切片起點坐標X
	 * @param y
	 *            目標切片起點坐標Y
	 * @param width
	 *            目標切片寬度
	 * @param height
	 *            目標切片高度
	 */
	public final static void cut(String srcImageFile, String result, int x, int y, int width, int height) {
		try {
			// 讀取源圖像
			BufferedImage bi = ImageIO.read(new File(srcImageFile));
			int srcWidth = bi.getHeight(); // 源圖寬度
			int srcHeight = bi.getWidth(); // 源圖高度
			if (srcWidth > 0 && srcHeight > 0) {
				Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
				// 四個参數分別为圖像起點坐標和寬高
				// 即: CropImageFilter(int x,int y,int width,int height)
				ImageFilter cropFilter = new CropImageFilter(x, y, width, height);
				Image img = Toolkit.getDefaultToolkit()
						.createImage(new FilteredImageSource(image.getSource(), cropFilter));
				BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				Graphics g = tag.getGraphics();
				g.drawImage(img, 0, 0, width, height, null); // 繪制切割後的圖
				g.dispose();
				// 輸出为文件
				ImageIO.write(tag, "JPEG", new File(result));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 圖像切割（指定切片的行數和列數）
	 * 
	 * @param srcImageFile
	 *            源圖像地址
	 * @param descDir
	 *            切片目標文件夾
	 * @param rows
	 *            目標切片行數。默認2，必須是範圍 [1, 20] 之內
	 * @param cols
	 *            目標切片列數。默認2，必須是範圍 [1, 20] 之內
	 */
	public final static void cut2(String srcImageFile, String descDir, int rows, int cols) {
		try {
			if (rows <= 0 || rows > 20)
				rows = 2; // 切片行數
			if (cols <= 0 || cols > 20)
				cols = 2; // 切片列數
			// 讀取源圖像
			BufferedImage bi = ImageIO.read(new File(srcImageFile));
			int srcWidth = bi.getHeight(); // 源圖寬度
			int srcHeight = bi.getWidth(); // 源圖高度
			if (srcWidth > 0 && srcHeight > 0) {
				Image img;
				ImageFilter cropFilter;
				Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
				int destWidth = srcWidth; // 每張切片的寬度
				int destHeight = srcHeight; // 每張切片的高度
				// 計算切片的寬度和高度
				if (srcWidth % cols == 0) {
					destWidth = srcWidth / cols;
				} else {
					destWidth = (int) Math.floor(srcWidth / cols) + 1;
				}
				if (srcHeight % rows == 0) {
					destHeight = srcHeight / rows;
				} else {
					destHeight = (int) Math.floor(srcWidth / rows) + 1;
				}
				// 循環建立切片
				// 改進的想法:是否可用多線程加快切割速度
				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < cols; j++) {
						// 四個参數分別为圖像起點坐標和寬高
						// 即: CropImageFilter(int x,int y,int width,int height)
						cropFilter = new CropImageFilter(j * destWidth, i * destHeight, destWidth, destHeight);
						img = Toolkit.getDefaultToolkit()
								.createImage(new FilteredImageSource(image.getSource(), cropFilter));
						BufferedImage tag = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
						Graphics g = tag.getGraphics();
						g.drawImage(img, 0, 0, null); // 繪制縮小後的圖
						g.dispose();
						// 輸出为文件
						ImageIO.write(tag, "JPEG", new File(descDir + "_r" + i + "_c" + j + ".jpg"));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 圖像切割（指定切片的寬度和高度）
	 * 
	 * @param srcImageFile
	 *            源圖像地址
	 * @param descDir
	 *            切片目標文件夾
	 * @param destWidth
	 *            目標切片寬度。默認200
	 * @param destHeight
	 *            目標切片高度。默認150
	 */
	public final static void cut3(String srcImageFile, String descDir, int destWidth, int destHeight) {
		try {
			if (destWidth <= 0)
				destWidth = 200; // 切片寬度
			if (destHeight <= 0)
				destHeight = 150; // 切片高度
			// 讀取源圖像
			BufferedImage bi = ImageIO.read(new File(srcImageFile));
			int srcWidth = bi.getHeight(); // 源圖寬度
			int srcHeight = bi.getWidth(); // 源圖高度
			if (srcWidth > destWidth && srcHeight > destHeight) {
				Image img;
				ImageFilter cropFilter;
				Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
				int cols = 0; // 切片橫向數量
				int rows = 0; // 切片縱向數量
				// 計算切片的橫向和縱向數量
				if (srcWidth % destWidth == 0) {
					cols = srcWidth / destWidth;
				} else {
					cols = (int) Math.floor(srcWidth / destWidth) + 1;
				}
				if (srcHeight % destHeight == 0) {
					rows = srcHeight / destHeight;
				} else {
					rows = (int) Math.floor(srcHeight / destHeight) + 1;
				}
				// 循環建立切片
				// 改進的想法:是否可用多線程加快切割速度
				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < cols; j++) {
						// 四個参數分別为圖像起點坐標和寬高
						// 即: CropImageFilter(int x,int y,int width,int height)
						cropFilter = new CropImageFilter(j * destWidth, i * destHeight, destWidth, destHeight);
						img = Toolkit.getDefaultToolkit()
								.createImage(new FilteredImageSource(image.getSource(), cropFilter));
						BufferedImage tag = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
						Graphics g = tag.getGraphics();
						g.drawImage(img, 0, 0, null); // 繪制縮小後的圖
						g.dispose();
						// 輸出为文件
						ImageIO.write(tag, "JPEG", new File(descDir + "_r" + i + "_c" + j + ".jpg"));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 圖像類型轉換：GIF->JPG、GIF->PNG、PNG->JPG、PNG->GIF(X)、BMP->PNG
	 * 
	 * @param srcImageFile
	 *            源圖像地址
	 * @param formatName
	 *            包含格式非正式名稱的 String：如JPG、JPEG、GIF等
	 * @param destImageFile
	 *            目標圖像地址
	 */
	public final static void convert(String srcImageFile, String formatName, String destImageFile) {
		try {
			File f = new File(srcImageFile);
			f.canRead();
			f.canWrite();
			BufferedImage src = ImageIO.read(f);
			ImageIO.write(src, formatName, new File(destImageFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 彩色轉为黑白
	 * 
	 * @param srcImageFile
	 *            源圖像地址
	 * @param destImageFile
	 *            目標圖像地址
	 */
	public final static void gray(String srcImageFile, String destImageFile) {
		try {
			BufferedImage src = ImageIO.read(new File(srcImageFile));
			ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
			ColorConvertOp op = new ColorConvertOp(cs, null);
			src = op.filter(src, null);
			ImageIO.write(src, "JPEG", new File(destImageFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 给圖片添加文字水印
	 * 
	 * @param pressText
	 *            水印文字
	 * @param srcImageFile
	 *            源圖像地址
	 * @param destImageFile
	 *            目標圖像地址
	 * @param fontName
	 *            水印的字體名稱
	 * @param fontStyle
	 *            水印的字體样式
	 * @param color
	 *            水印的字體顏色
	 * @param fontSize
	 *            水印的字體大小
	 * @param x
	 *            修正值
	 * @param y
	 *            修正值
	 * @param alpha
	 *            透明度：alpha 必須是範圍 [0.0, 1.0] 之內（包含邊界值）的一個浮點數字
	 */
	public final static void pressText(String pressText, String srcImageFile, String destImageFile, String fontName,
			int fontStyle, Color color, int fontSize, int x, int y, float alpha) {
		try {
			File img = new File(srcImageFile);
			Image src = ImageIO.read(img);
			int width = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, width, height, null);
			g.setColor(color);
			g.setFont(new Font(fontName, fontStyle, fontSize));
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			// 在指定坐標繪制水印文字
			g.drawString(pressText, (width - (getLength(pressText) * fontSize)) / 2 + x, (height - fontSize) / 2 + y);
			g.dispose();
			ImageIO.write((BufferedImage) image, "JPEG", new File(destImageFile));// 輸出到文件流
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 给圖片添加文字水印
	 * 
	 * @param pressText
	 *            水印文字
	 * @param srcImageFile
	 *            源圖像地址
	 * @param destImageFile
	 *            目標圖像地址
	 * @param fontName
	 *            字體名稱
	 * @param fontStyle
	 *            字體样式
	 * @param color
	 *            字體顏色
	 * @param fontSize
	 *            字體大小
	 * @param x
	 *            修正值
	 * @param y
	 *            修正值
	 * @param alpha
	 *            透明度：alpha 必須是範圍 [0.0, 1.0] 之內（包含邊界值）的一個浮點數字
	 */
	public final static void pressText2(String pressText, String srcImageFile, String destImageFile, String fontName,
			int fontStyle, Color color, int fontSize, int x, int y, float alpha) {
		try {
			File img = new File(srcImageFile);
			Image src = ImageIO.read(img);
			int width = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, width, height, null);
			g.setColor(color);
			g.setFont(new Font(fontName, fontStyle, fontSize));
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			// 在指定坐標繪制水印文字
			g.drawString(pressText, (width - (getLength(pressText) * fontSize)) / 2 + x, (height - fontSize) / 2 + y);
			g.dispose();
			ImageIO.write((BufferedImage) image, "JPEG", new File(destImageFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 给圖片添加圖片水印
	 * 
	 * @param pressImg
	 *            水印圖片
	 * @param srcImageFile
	 *            源圖像地址
	 * @param destImageFile
	 *            目標圖像地址
	 * @param x
	 *            修正值。 默認在中間
	 * @param y
	 *            修正值。 默認在中間
	 * @param alpha
	 *            透明度：alpha 必須是範圍 [0.0, 1.0] 之內（包含邊界值）的一個浮點數字
	 */
	public final static void pressImage(String pressImg, String srcImageFile, String destImageFile, int x, int y,
			float alpha) {
		try {
			File img = new File(srcImageFile);
			Image src = ImageIO.read(img);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, null);
			// 水印文件
			Image src_biao = ImageIO.read(new File(pressImg));
			int wideth_biao = src_biao.getWidth(null);
			int height_biao = src_biao.getHeight(null);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			if (x != 0 || y != 0) {
				g.drawImage(src_biao, x, y, wideth_biao, height_biao, null);
			} else {
				g.drawImage(src_biao, (wideth - wideth_biao) / 2, (height - height_biao) / 2, wideth_biao, height_biao,
						null);
			}

			// 水印文件結束
			g.dispose();
			ImageIO.write((BufferedImage) image, "JPEG", new File(destImageFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 計算text的長度（一個中文算兩個字符）
	 * 
	 * @param text
	 * @return
	 */
	public final static int getLength(String text) {
		int length = 0;
		for (int i = 0; i < text.length(); i++) {
			if (new String(text.charAt(i) + "").getBytes().length > 1) {
				length += 2;
			} else {
				length += 1;
			}
		}
		return length / 2;
	}

	/**
	 * 
	 * byte數組轉換成16進製字符串
	 * 
	 * @param src
	 * 
	 * @return
	 * 
	 */
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * 
	 * 根據文件流判斷圖片類型
	 * 
	 * @param file
	 * 
	 * @return jpg/png/gif/bmp
	 * @throws FileNotFoundException 
	 * 
	 */
	public static String getPicType(File file) throws FileNotFoundException {

		FileInputStream fis = new FileInputStream(file);
		// 讀取文件的前幾個字節來判斷圖片格式
		byte[] b = new byte[4];
		try {
			fis.read(b, 0, b.length);
			String type = bytesToHexString(b).toUpperCase();
			if (type.contains("FFD8FF")) {
				return IMAGE_TYPE_JPG;
			} else if (type.contains("89504E47")) {
				return IMAGE_TYPE_PNG;
			} else if (type.contains("47494638")) {
				return IMAGE_TYPE_GIF;
			} else if (type.contains("424D")) {
				return IMAGE_TYPE_BMP;
			} else {
				return IMAGE_TYPE_UNKNOWN;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}