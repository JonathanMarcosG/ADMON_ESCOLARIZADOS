/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mx.edu.ittoluca.logutils.Logger;

/**
 *
 * @author giovanni
 */
//@WebServlet(name = "CaptchaServlet", urlPatterns = {"/login/captcha"})
public class CaptchaServlet extends HttpServlet {
    
    @Inject
    HttpSession httpSession;

    /**
     * Generates a randomized image with chars. Use it to create captcha
     * images at login screens.
     * 
     * @param req
     * @param res 
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) {

        try {
            final int width = 150;
            final int height = 50;
            
            StringBuilder cadena = new StringBuilder("");
            for (int i=0; i < 6; i++) {
                int num = (int) (Math.random() * (35 - 1 + 1) + 1);
                char caracteres[] = {
                    's', 'a', 'b', 'c', 'd', 'e', 'f',
                    'g', 'h', 'i', 'j', 'k', 'l', 'm',
                    'n', 'o', 'p', 'q', 'r', 's', 't',
                    'u', 'v', 'w', 'x', 'y', 'z', '1',
                    '2', '3', '4', '5', '6', '7', '8', '9'
                };
                
                cadena.append(caracteres[num]);
            }
            
            // Put captcha code in session
            httpSession=req.getSession();
            httpSession.setAttribute("captcha", cadena.toString());

            
            
            BufferedImage bufferedImage = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            
            Font font = new Font("Georgia", Font.BOLD, 18);
            
            RenderingHints rh = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            rh.put(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            
            //GradientPaint gp = new GradientPaint(0, 0,
            //        Color.white, 0, height / 2, Color.blue, true);
            
            // Image background gradient configuration
            Color color1 = new Color(230, 126, 34);
            Color color2 = new Color(230, 126, 34);
            GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
            
            
            Graphics2D g2d = bufferedImage.createGraphics();
            g2d.setFont(font);
            g2d.setRenderingHints(rh);
            g2d.setPaint(gp);
            
            g2d.fillRect(0, 0, width, height);
            
            g2d.setColor(new Color(255, 255, 255));
            
            Random r = new Random();
            
            int x = 0;
            int y;
            
            char[] data = cadena.toString().toCharArray();
            int tam = data.length;
            
            for (int i = 0; i < tam; i++) {
                x += 10 + (Math.abs(r.nextInt()) % 15);
                y = 20 + Math.abs(r.nextInt()) % 20;
                g2d.drawChars(data, i, 1, x, y);
            }
            
            g2d.dispose();
            
            res.setContentType("image/png");
            OutputStream os = res.getOutputStream();
            ImageIO.write(bufferedImage, "png", os);
            
            os.close();
        } 
        catch (IOException ex) {
            Logger logger = new Logger();
            String errMsg = "No se pudo generar imagen de captcha. " + ex.getMessage();
            logger.registrarError(Logger.ADVERTENCIA, errMsg, "ITTLogin", "Captcha", "- - -");
        }
    }

}
