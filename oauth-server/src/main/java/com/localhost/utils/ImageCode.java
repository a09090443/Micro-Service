package com.localhost.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ImageCode {

	private static char mapTable[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9' };

	public static Map<String, Object> getImageCode(int width, int height, OutputStream os) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (width <= 0)
			width = 60;
		if (height <= 0)
			height = 20;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 獲取圖形上下文
		Graphics g = image.getGraphics();
		// 生成隨機類
		Random random = new Random();
		// 設定背景色
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		// 設定字體
		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		// 隨機產生168條干擾線，使圖像中的認證碼不易被其它程序探測到
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 168; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		// 取隨機產生的碼
		String strEnsure = "";
		// 4代表4位驗證碼,如果要生成更多位的認證碼,則加大數值
		for (int i = 0; i < 4; ++i) {
			strEnsure += mapTable[(int) (mapTable.length * Math.random())];
			// 將認證碼顯示到圖像中
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			// 直接生成
			String str = strEnsure.substring(i, i + 1);
			g.drawString(str, 13 * i + 6, 16);
		}

		// 釋放圖形上下文
		g.dispose();
		returnMap.put("image", image);
		returnMap.put("strEnsure", strEnsure);
		return returnMap;
	}

	// 給定範圍獲得隨機顏色
	static Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}