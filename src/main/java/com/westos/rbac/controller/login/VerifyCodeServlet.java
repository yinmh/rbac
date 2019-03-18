package com.westos.rbac.controller.login;

import com.westos.rbac.util.RandomCodeUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Author :ymh
 */
@WebServlet("/verifyCode")
public class VerifyCodeServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedImage img = new BufferedImage(100, 60, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        Color color = new Color(28, 204, 132);
        g.setColor(color);
        g.fillRect(0,0,100,60);

        g.setColor(Color.CYAN);
        g.setFont(new Font("微软雅黑", Font.ITALIC, 28));
        String vcode = RandomCodeUtil.randomCode();
        req.getSession().setAttribute("vcode",vcode);
        g.drawString(vcode, 7, 39);
        g.setColor(Color.cyan);
        g.drawLine(0,0,70,60);
        g.setColor(Color.black);
        g.drawLine(10,20,60,100);

        ImageIO.write(img, "png", resp.getOutputStream());
    }
}
