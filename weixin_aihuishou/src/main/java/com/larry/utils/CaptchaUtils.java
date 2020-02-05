package com.larry.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
@Component
public class CaptchaUtils {
	private static String[] fuhao = { "+", "-", "*"};
	private int num = 0;

	public String random() {
		List list = new ArrayList();
		Random ran = new Random();
		int n1 = ran.nextInt(10);
		int n2 = ran.nextInt(10);
		String f = fuhao[ran.nextInt(fuhao.length)];
		StringBuilder sb = new StringBuilder(4);
		if ("+".equals(f)) {
			num = n1 + n2;
		} else if ("-".equals(f)) {
			//num = n1 - n2;
			int temp=0;
			if(n1>n2) {
				
			}else {
				temp=n1;
				n1=n2;
				n2=temp;
			}
			num = n1 - n2;
		} else if ("*".equals(f)) {
			num = n1 * n2;
		} else if ("/".equals(f)) {
			if (n2 == 0) {
				n2 = ran.nextInt(10) + 1;
				num = n1 / n2;
			} else {
				num = n1 / n2;
			}
		}
		/*
		 * switch (f) { case "+": num = n1 + n2; break; case "-": num = n1 - n2; break;
		 * case "*": num = n1 * n2; break; case "/": if (n2 == 0) { n2 = ran.nextInt(10)
		 * + 1; num = n1 / n2; } else { num = n1 / n2; } break; }
		 */
		sb.append(n1);
		sb.append(f);
		sb.append(n2);
		sb.append("=");
		
		return sb.toString();
	}

	public void outputImage(String str, OutputStream os) {
		Random ran = new Random();
		BufferedImage img = new BufferedImage(100, 40, BufferedImage.TYPE_INT_RGB);
		Graphics g = img.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, 100, 40);
		g.setColor(Color.black);
		g.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		g.drawString(str, 10, 40); // 随机三条线
		g.drawLine(ran.nextInt(100), ran.nextInt(40), ran.nextInt(100), ran.nextInt(40));
		g.drawLine(ran.nextInt(100), ran.nextInt(40), ran.nextInt(100), ran.nextInt(40));
		g.drawLine(ran.nextInt(100), ran.nextInt(40), ran.nextInt(100), ran.nextInt(40));
		try {
			ImageIO.write(img, "png", os);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int getNum() {
		return this.num;
	}
}
